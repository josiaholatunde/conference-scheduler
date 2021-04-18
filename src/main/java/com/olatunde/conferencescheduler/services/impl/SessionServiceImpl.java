package com.olatunde.conferencescheduler.services.impl;

import com.olatunde.conferencescheduler.dtos.SessionDto;
import com.olatunde.conferencescheduler.exceptions.BadRequestException;
import com.olatunde.conferencescheduler.exceptions.ResourceNotFoundException;
import com.olatunde.conferencescheduler.models.Session;
import com.olatunde.conferencescheduler.repositories.SessionRepository;
import com.olatunde.conferencescheduler.services.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {
    private final SessionRepository sessionRepository;
    private final int MAX_NO_GENERATION_ATTEMPTS = 9999999;

    @Override
    public List<SessionDto> retrieveSessions() {
        return StreamSupport.stream(sessionRepository.findAll().spliterator(), false).map(this::convertEntityToData)
                .collect(Collectors.toList());
    }

    @Override
    public SessionDto findByCode(String code) {
        Session session = findSessionByCode(code);
        return convertEntityToData(session);
    }

    private Session findSessionByCode(String code) {
        return sessionRepository.findByCode(code).orElseThrow(() ->
                new ResourceNotFoundException(String.format("Session with code %s was not found", code)));
    }

    public boolean existsByCode(String code) {
       return sessionRepository.findByCode(code).isPresent();
    }

    @Override
    public SessionDto createSession(SessionDto sessionDto) throws Exception {
        validateSessionDto(sessionDto);
        Session session = convertDataToEntity(sessionDto);
        String code = generateCode();
        session.setCode(code);
        return convertEntityToData(sessionRepository.saveAndFlush(session));
    }

    @Override
    public void deleteByCode(String code) {
        Session session = findSessionByCode(code);
        sessionRepository.delete(session);
    }

    @Override
    public SessionDto updateSession(String code, SessionDto sessionDto) {
       Session session = validateAndRetrieveSessionForUpdate(code, sessionDto);
       BeanUtils.copyProperties(sessionDto, session, "code");
       return convertEntityToData(sessionRepository.saveAndFlush(session));
    }

    private void validateSessionDto(SessionDto sessionDto) {
        if (!isSessionNameUnique(sessionDto)) {
           throw new BadRequestException("Session name has been taken");
        }
    }

    private Session validateAndRetrieveSessionForUpdate(String code, SessionDto sessionDto) {
        Session session = findSessionByCode(code);
        Optional<Session> sessionFromDbOptional = sessionRepository.findByName(sessionDto.getName());
        if (sessionFromDbOptional.isPresent() && sessionFromDbOptional.get().getId() != session.getId()) {
            throw new BadRequestException("Session name has been taken");
        }
        return session;
    }

    private boolean isSessionNameUnique(SessionDto sessionDto) {
        return !sessionRepository.findByName(sessionDto.getName()).isPresent();
    }

    private String generateCode() throws Exception {
        int noOfGeneratedAttempts = 0;
        while (noOfGeneratedAttempts <= MAX_NO_GENERATION_ATTEMPTS) {
            String generatedCode = UUID.randomUUID().toString();
            if (!existsByCode(generatedCode)) {
                return generatedCode;
            }
            noOfGeneratedAttempts++;
        }
        throw new Exception("Reached maximum attempts for generating code");
    }

    private SessionDto convertEntityToData(Session session) {
        SessionDto sessionDto = SessionDto.builder().build();
        BeanUtils.copyProperties(session, sessionDto);
        return sessionDto;
    }

    private Session convertDataToEntity(SessionDto sessionDto) {
        Session session = Session.builder().build();
        BeanUtils.copyProperties(sessionDto, session);
        return session;
    }
}
