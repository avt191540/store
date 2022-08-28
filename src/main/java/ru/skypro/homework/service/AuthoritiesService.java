package ru.skypro.homework.service;

import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.model.Authorities;

public interface AuthoritiesService {

    Authorities getAuthority(String username) throws NotFoundException;

    void saveAuthority(Authorities authority);
}
