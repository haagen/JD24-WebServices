package se.apiva.demospringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.apiva.demospringboot.model.AppUser;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);
}
