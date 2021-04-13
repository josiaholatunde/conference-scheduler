package com.olatunde.conferencescheduler.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "sessions")
@NoArgsConstructor
@Data
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    private String description;

    private Long length;

    @ManyToMany(mappedBy = "sessions")
    private List<Speaker> speaker;

}
