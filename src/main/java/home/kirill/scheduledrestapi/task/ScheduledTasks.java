package home.kirill.scheduledrestapi.task;

import home.kirill.scheduledrestapi.entity.Patient;
import home.kirill.scheduledrestapi.entity.PatientNote;
import home.kirill.scheduledrestapi.entity.dto.Client;
import home.kirill.scheduledrestapi.entity.dto.ClientNote;
import home.kirill.scheduledrestapi.prop.PropertiesReader;
import home.kirill.scheduledrestapi.service.IPatientNoteService;
import home.kirill.scheduledrestapi.service.IPatientService;
import home.kirill.scheduledrestapi.service.ITaskService;
import home.kirill.scheduledrestapi.service.IUserService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


@Component
public class ScheduledTasks {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private static final Logger logger = Logger.getLogger(ScheduledTasks.class.getName());

    private final IPatientService patientService;

    private final IPatientNoteService patientNoteService;

    private final IUserService userService;

    private final ITaskService taskService;

    @Autowired
    public ScheduledTasks(IPatientService patientService, IPatientNoteService patientNoteService, IUserService userService, ITaskService taskService) {
        this.patientService = patientService;
        this.patientNoteService = patientNoteService;
        this.userService = userService;
        this.taskService = taskService;
    }

    @Scheduled(fixedRate = 5000)
    public void importTask() {

        logger.debug("Import task start " + dateFormat.format(new Date()));

        try {

            List<Patient> patients = patientService.findAllByRegionId(
                    PropertiesReader.getLong("region_id", 0L));

            patients.forEach(patient -> {
                String oldGUID = patient.getOldClientGUID();
                Client client = taskService.getClient(patient.getOldClientGUID());
                if (client != null) {
                    //получаем список outsideNotes из стороннего api
                    List<ClientNote> outsideNotes = taskService.getClientNotes(client.getAgency(), client.getClient_guid());
                    //получаем список логинов пользователей создавших notes для текущего пациента
                    Set<String> notesLogins = patient.getNotes().stream()
                            .map(note -> note.getCreatedUser().getLogin())
                            .collect(Collectors.toSet());

                    outsideNotes.forEach(note -> {
                        //если логины logged_user в сторонней системе и в нашей не совпадают,
                        //то сохраняем note из сторонней системы в нашей
                        if (!notesLogins.contains(note.getLogged_user())) {
                            PatientNote patientNote = patientNoteService.convertFromClientNote(note);
                            if (patientNote != null) {
                                patientNoteService.save(patientNote);
                            }
                        } else {
                            //ищем у пациента note по логину создавшего его пользователя
                            Optional<PatientNote> optPatientNote = patient.getNotes().stream()
                                    .filter(x -> StringUtils.equalsIgnoreCase(x.getCreatedUser().getLogin(), note.getLogged_user()))
                                    .findFirst();
                            //если нашли и дата последнего обновления в сторонней системе больше даты в нашей системе,
                            //то обновляем текст заметки и дату изменения в нашей системе
                            if (optPatientNote.isPresent()) {
                                PatientNote patientNote = optPatientNote.get();
                                if (note.getModified_datetime().getTime() > patientNote.getLastModifiedDate().getTime()) {
                                    patientNote.setNote(note.getComments());
                                    patientNote.setLastModifiedDate(note.getModified_datetime());
                                    patientNoteService.save(patientNote);
                                }
                            }
                        }
                    });

                }
            });
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        logger.debug("Import task end " + dateFormat.format(new Date()));
    }

}
