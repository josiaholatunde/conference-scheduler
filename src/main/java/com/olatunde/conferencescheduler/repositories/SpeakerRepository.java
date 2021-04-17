package com.olatunde.conferencescheduler.repositories;

import com.olatunde.conferencescheduler.models.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpeakerRepository extends JpaRepository<Speaker, Long> {

    Optional<Speaker> findByCode(String code);
}
