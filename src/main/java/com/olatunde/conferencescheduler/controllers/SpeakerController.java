package com.olatunde.conferencescheduler.controllers;

import com.olatunde.conferencescheduler.annotations.WrapResponse;
import com.olatunde.conferencescheduler.dtos.SessionDto;
import com.olatunde.conferencescheduler.dtos.SpeakerDto;
import com.olatunde.conferencescheduler.services.SessionService;
import com.olatunde.conferencescheduler.services.SpeakerService;
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
@RequestMapping(value = Constants.API_VERSION_1+"/speakers",
produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class SpeakerController {

    private final SpeakerService speakerService;

    @GetMapping
    public List<SpeakerDto> getSpeakers() {
        return speakerService.retrieveSpeakers();
    }

    @GetMapping("{code}")
    public SpeakerDto getSpeakerByCode(@PathVariable(name = "code") String code) {
        return speakerService.findByCode(code);
    }
}
