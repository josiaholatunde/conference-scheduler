package com.olatunde.conferencescheduler.repositories;

import com.olatunde.conferencescheduler.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
}
