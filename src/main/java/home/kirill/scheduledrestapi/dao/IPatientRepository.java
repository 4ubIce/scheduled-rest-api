package home.kirill.scheduledrestapi.dao;

import home.kirill.scheduledrestapi.entity.Patient;
import home.kirill.scheduledrestapi.entity.PatientNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPatientRepository extends JpaRepository<Patient, Long> {

    List<Patient> findAllByRegionId(Long id);

    Optional<Patient> findByOldClientGUID(String oldClientGUID);

}
