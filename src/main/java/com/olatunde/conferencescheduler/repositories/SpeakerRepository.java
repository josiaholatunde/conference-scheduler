package com.olatunde.conferencescheduler.repositories;

import com.olatunde.conferencescheduler.models.Session;
import com.olatunde.conferencescheduler.models.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeakerRepository extends JpaRepository<Speaker, Long> {
}
