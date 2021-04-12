package com.olatunde.conferencescheduler.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "speakers")
@NoArgsConstructor
@Data
public class Speaker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    private String title;

    private String company;

    @Column(name = "speaker_bio", nullable = false)
    private String speakerBio;

    @OneToMany(mappedBy = "speaker")
    private List<Photo> photos;

    @ManyToMany
    @JoinTable(name = "session_speakers",
            joinColumns = @JoinColumn(name = "speaker_id"),
    inverseJoinColumns = @JoinColumn(name = "session_id"))
    private List<Session> session;

}
