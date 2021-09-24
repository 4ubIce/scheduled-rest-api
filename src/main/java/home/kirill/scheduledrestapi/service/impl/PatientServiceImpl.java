package home.kirill.scheduledrestapi.service.impl;

import home.kirill.scheduledrestapi.dao.IPatientRepository;
import home.kirill.scheduledrestapi.entity.Patient;
import home.kirill.scheduledrestapi.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PatientServiceImpl implements IPatientService {

    private final IPatientRepository repository;

    @Autowired
    public PatientServiceImpl(IPatientRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Patient> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Patient> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Patient> findAllByRegionId(Long regionId) {
        return repository.findAllByRegionId(regionId);
    }

    @Override
    public Optional<Patient> findByOldClientGUID(String oldClientGUID) {
        return repository.findByOldClientGUID(oldClientGUID);
    }
}
