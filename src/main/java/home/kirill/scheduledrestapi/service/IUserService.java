package home.kirill.scheduledrestapi.service;

import home.kirill.scheduledrestapi.entity.User;

import java.util.Optional;

public interface IUserService {
    Optional<User> findByLogin(String login);
}
