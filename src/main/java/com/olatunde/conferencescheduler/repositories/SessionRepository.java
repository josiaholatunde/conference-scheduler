package com.olatunde.conferencescheduler.repositories;

import com.olatunde.conferencescheduler.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {

    Optional<Session> findByCode(String code);

    Optional<Session> findByName(String sessionName);

}
