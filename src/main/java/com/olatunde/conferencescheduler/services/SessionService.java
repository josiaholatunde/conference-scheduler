package com.olatunde.conferencescheduler.services;

import com.olatunde.conferencescheduler.dtos.SessionDto;

import java.util.List;

public interface SessionService {
    List<SessionDto> retrieveSessions();

    SessionDto findByCode(String code);

    SessionDto createSession(SessionDto sessionDto) throws Exception;

    void deleteByCode(String code);

    SessionDto updateSession(String code, SessionDto sessionDto);
}
