package com.olatunde.conferencescheduler.services;

import com.olatunde.conferencescheduler.dtos.SessionDto;
import com.olatunde.conferencescheduler.dtos.SpeakerDto;

import java.util.List;

public interface SpeakerService {
    List<SpeakerDto> retrieveSpeakers();

    SpeakerDto findByCode(String code);
}
