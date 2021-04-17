package com.olatunde.conferencescheduler.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity(name = "speakers")
@NoArgsConstructor
@Data
public class Speaker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

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
    private List<Session> sessions;

    @CreatedDate
    @Column(name = "created_at")
    private Timestamp createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Timestamp updatedAt;

}
