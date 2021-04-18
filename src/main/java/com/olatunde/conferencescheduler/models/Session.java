package com.olatunde.conferencescheduler.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "sessions")
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    private String description;

    private Long length;

    @ManyToMany
    @JoinTable(name = "session_speakers",
            joinColumns = @JoinColumn(name = "speaker_id"),
            inverseJoinColumns = @JoinColumn(name = "session_id"))
    private List<Speaker> speakers;

    @CreatedDate
    @Column(name = "created_at")
    private Timestamp createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Timestamp updatedAt;

}
