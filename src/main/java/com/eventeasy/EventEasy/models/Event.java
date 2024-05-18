package com.eventeasy.EventEasy.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

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

    // TODO - ogranizer_ids, members_ids

}
