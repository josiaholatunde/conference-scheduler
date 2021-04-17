package com.olatunde.conferencescheduler.controllers;

import com.olatunde.conferencescheduler.annotations.WrapResponse;
import com.olatunde.conferencescheduler.dtos.SessionDto;
import com.olatunde.conferencescheduler.services.SessionService;
import com.olatunde.conferencescheduler.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@WrapResponse
@RestController
@RequestMapping(value = Constants.API_VERSION_1+"/sessions",
produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    @GetMapping
    public List<SessionDto> getSessions() {
        return sessionService.retrieveSessions();
    }

    @GetMapping("{code}")
    public SessionDto getSessionByCode(@PathVariable(name = "code") String code) {
        return sessionService.findByCode(code);
    }
}
