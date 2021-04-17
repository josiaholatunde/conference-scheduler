package com.olatunde.conferencescheduler.controllers;

import com.olatunde.conferencescheduler.annotations.WrapResponse;
import com.olatunde.conferencescheduler.dtos.SessionDto;
import com.olatunde.conferencescheduler.models.Session;
import com.olatunde.conferencescheduler.services.SessionService;
import com.olatunde.conferencescheduler.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public SessionDto createSession(@RequestBody @Validated SessionDto sessionDto) throws Exception {
        return sessionService.createSession(sessionDto);
    }

    @DeleteMapping("{code}")
    public void deleteSession(@PathVariable String code) {
        sessionService.deleteByCode(code);
    }


    @PutMapping("{code}")
    public SessionDto editSession(@PathVariable String code, @RequestBody @Validated SessionDto sessionDto) throws Exception {
        return sessionService.updateSession(code, sessionDto);
    }
}
