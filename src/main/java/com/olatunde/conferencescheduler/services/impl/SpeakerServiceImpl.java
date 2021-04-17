package com.olatunde.conferencescheduler.services.impl;

import com.olatunde.conferencescheduler.dtos.SpeakerDto;
import com.olatunde.conferencescheduler.exceptions.ResourceNotFoundException;
import com.olatunde.conferencescheduler.models.Speaker;
import com.olatunde.conferencescheduler.repositories.SpeakerRepository;
import com.olatunde.conferencescheduler.services.SpeakerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Slf4j
public class SpeakerServiceImpl implements SpeakerService {

    private final SpeakerRepository speakerRepository;

    @Override
    public List<SpeakerDto> retrieveSpeakers() {
        return StreamSupport.stream(speakerRepository.findAll().spliterator(), false)
                .map(this::convertEntityToData)
                .collect(Collectors.toList());
    }

    @Override
    public SpeakerDto findByCode(String code) {
        Speaker speaker= speakerRepository.findByCode(code).orElseThrow(
                () -> new ResourceNotFoundException("Speaker with code was not found"));
        return convertEntityToData(speaker);
    }

    private SpeakerDto convertEntityToData(Speaker speaker) {
        SpeakerDto speakerDto = SpeakerDto.builder().build();
        BeanUtils.copyProperties(speaker, speakerDto);
        return speakerDto;
    }

}
