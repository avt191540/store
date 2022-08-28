package ru.skypro.homework.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.Authorities;

import java.util.Optional;

@Repository
public interface AuthoritiesRepository extends JpaRepository<Authorities, Long> {
    Optional<Authorities> findAuthoritiesByUsername(String username);
}
