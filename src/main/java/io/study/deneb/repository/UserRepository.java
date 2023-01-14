package io.study.deneb.repository;

import io.study.deneb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUsername(String username);
}
