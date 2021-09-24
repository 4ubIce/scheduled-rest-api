package home.kirill.scheduledrestapi.service;

import home.kirill.scheduledrestapi.entity.PatientNote;
import home.kirill.scheduledrestapi.entity.dto.ClientNote;

import java.util.List;
import java.util.Optional;

public interface IPatientNoteService {
    List<PatientNote> findAll();

    Optional<PatientNote> findById(Long id);

    PatientNote save(PatientNote note);

    PatientNote convertFromClientNote(ClientNote clientNote);
}
