package home.kirill.scheduledrestapi.service.impl;

import home.kirill.scheduledrestapi.dao.IPatientNoteRepository;
import home.kirill.scheduledrestapi.entity.Patient;
import home.kirill.scheduledrestapi.entity.PatientNote;
import home.kirill.scheduledrestapi.entity.User;
import home.kirill.scheduledrestapi.entity.dto.ClientNote;
import home.kirill.scheduledrestapi.service.IPatientNoteService;
import home.kirill.scheduledrestapi.service.IPatientService;
import home.kirill.scheduledrestapi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PatientNoteServiceImpl implements IPatientNoteService {

    private final IPatientNoteRepository repository;

    private final IUserService userService;

    private final IPatientService patientService;

    @Autowired
    public PatientNoteServiceImpl(IPatientNoteRepository repository, IUserService userService, IPatientService patientService) {
        this.repository = repository;
        this.userService = userService;
        this.patientService = patientService;
    }

    @Override
    public List<PatientNote> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<PatientNote> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public PatientNote save(PatientNote note) {
        return repository.save(note);
    }

    @Override
    public PatientNote convertFromClientNote(ClientNote clientNote) {

        if (clientNote.getDatetime() == null || clientNote.getModified_datetime() == null) {
            return null;
        }

        PatientNote patientNote = new PatientNote();
        Patient patient = null;

        Optional<Patient> optPatient = patientService.findByOldClientGUID(clientNote.getClient_guid());
        if (optPatient.isPresent()) {
            patient = optPatient.get();
            User user = null;
            Optional<User> optUser = userService.findByLogin(clientNote.getLogged_user());

            if (optUser.isPresent()) {
                user = optUser.get();
            }
            patientNote.setCreatedUser(user);
            patientNote.setLastModifiedUser(user);
            patientNote.setPatient(patient);
            patientNote.setCreateDate(clientNote.getDatetime());
            patientNote.setLastModifiedDate(clientNote.getModified_datetime());
            patientNote.setNote(clientNote.getComments());

            return patientNote;

        } else {
            return null;
        }
    }
}
