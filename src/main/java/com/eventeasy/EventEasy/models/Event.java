package com.eventeasy.EventEasy.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
@Builder
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
            cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}
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
    @Column
    private Integer amount ;

    public Event() {

    }

    public Integer getAmount() {
        return members.size() + organizers.size();
    }

    @ManyToMany(
            cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}
    )
    @JoinTable(
            name = "members_events",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "event_id", referencedColumnName = "id"
            )
    )
    private Set<User> members;


}
