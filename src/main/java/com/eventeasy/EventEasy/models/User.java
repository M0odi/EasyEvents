package com.eventeasy.EventEasy.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.IdentityHashMap;
import java.util.Set;

@Entity
@Table (name = "users")
@Data
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "user_id")
    private Integer id;
    @Column (name = "user_name")
    private String name;
    @Column (name = "password")
    private String password;
    @Column (name = "login")
    private String login;
    //TODO
/*    @Column (name = "family")
    private Set<User> family;*/

}
