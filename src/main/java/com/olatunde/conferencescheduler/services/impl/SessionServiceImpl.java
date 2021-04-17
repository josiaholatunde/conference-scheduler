package com.olatunde.conferencescheduler.services.impl;

import com.olatunde.conferencescheduler.dtos.SessionDto;
import com.olatunde.conferencescheduler.exceptions.ResourceNotFoundException;
import com.olatunde.conferencescheduler.models.Session;
import com.olatunde.conferencescheduler.repositories.SessionRepository;
import com.olatunde.conferencescheduler.services.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {
    private final SessionRepository sessionRepository;

    @Override
    public List<SessionDto> retrieveSessions() {
        return StreamSupport.stream(sessionRepository.findAll().spliterator(), false).map(this::convertEntityToData)
                .collect(Collectors.toList());
    }

    @Override
    public SessionDto findByCode(String code) {
        Session session = sessionRepository.findByCode(code).orElseThrow(() ->
                new ResourceNotFoundException("Session with code {} was not found"));
        return convertEntityToData(session);
    }

    private SessionDto convertEntityToData(Session session) {
        SessionDto sessionDto = SessionDto.builder().build();
        BeanUtils.copyProperties(session, sessionDto);
        return sessionDto;
    }
}
