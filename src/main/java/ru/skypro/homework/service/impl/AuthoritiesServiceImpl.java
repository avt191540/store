package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.model.Authorities;
import ru.skypro.homework.repo.AuthoritiesRepository;
import ru.skypro.homework.service.AuthoritiesService;

@Service
public class AuthoritiesServiceImpl implements AuthoritiesService {

    private final AuthoritiesRepository authoritiesRepository;

    public AuthoritiesServiceImpl(AuthoritiesRepository authoritiesRepository) {
        this.authoritiesRepository = authoritiesRepository;
    }

    public Authorities getAuthority(String username) throws NotFoundException {
        return authoritiesRepository.findAuthoritiesByUsername(username).orElseThrow(NotFoundException::new);
    }

    public void saveAuthority(Authorities authority){
        authoritiesRepository.save(authority);
    }
}
