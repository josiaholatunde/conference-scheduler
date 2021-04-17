package com.olatunde.conferencescheduler.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SpeakerDto {

    private String code;

    private String firstName;

    private String lastName;

    private String company;

    private String speakerBio;

    private String createdAt;

    private String updatedAt;

}
