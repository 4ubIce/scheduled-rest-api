package home.kirill.scheduledrestapi.service;

import home.kirill.scheduledrestapi.entity.Patient;

import java.util.List;
import java.util.Optional;

public interface IPatientService {
    List<Patient> findAll();

    Optional<Patient> findById(Long id);

    List<Patient> findAllByRegionId(Long regionId);

    Optional<Patient> findByOldClientGUID(String oldClientGUID);
}
