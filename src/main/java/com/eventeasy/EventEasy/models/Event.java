package com.eventeasy.EventEasy.models;

import jakarta.annotation.Priority;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@EntityListeners(AuditingEntityListener.class)
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column

    private Integer id;

    @Column
    private String name;

    @Column
    private String description;

    @CreatedDate
    @Temporal(TemporalType.DATE)
    @Column
    private LocalDate dateOfCreate;

    @Column
    private LocalDate dateOfEvent;

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL

    )
    @JoinTable(
        name = "organizers_events",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "event_id", referencedColumnName = "id"
            )
    )
    private Set<User> organizers;


}
