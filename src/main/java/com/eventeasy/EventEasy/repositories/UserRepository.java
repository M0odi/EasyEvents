package com.eventeasy.EventEasy.repositories;

import java.util.Optional;

import com.eventeasy.EventEasy.models.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByEmail(String email);

}
