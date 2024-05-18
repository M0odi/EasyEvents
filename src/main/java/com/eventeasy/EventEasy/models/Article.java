package com.eventeasy.EventEasy.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Entity
@Table
@Data
public class Article {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "article_id")
    private Integer id;
    @Column (name = "article_name")
    private String name;
    // TODO
//    @Column
//    private String content;
    @CreatedDate
    @Temporal(TemporalType.DATE)
    @Column
    private LocalDate dateOfCreate;
    @Column
    private String author;

    public Article() {
    }
}
