package com.eventeasy.EventEasy.repositories;

import java.util.Optional;

import com.eventeasy.EventEasy.models.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByEmail(String email);
    boolean existsUserByEmail(String email);
}
