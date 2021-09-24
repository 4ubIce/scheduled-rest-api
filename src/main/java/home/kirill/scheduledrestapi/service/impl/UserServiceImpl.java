package home.kirill.scheduledrestapi.service.impl;

import home.kirill.scheduledrestapi.dao.IUserRepository;
import home.kirill.scheduledrestapi.entity.User;
import home.kirill.scheduledrestapi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    private final IUserRepository repository;

    @Autowired
    public UserServiceImpl(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return repository.findByLogin(login);
    }
}
