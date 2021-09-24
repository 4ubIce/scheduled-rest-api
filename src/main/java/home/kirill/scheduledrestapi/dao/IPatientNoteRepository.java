package home.kirill.scheduledrestapi.dao;

import home.kirill.scheduledrestapi.entity.PatientNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPatientNoteRepository extends JpaRepository<PatientNote, Long> {
}
