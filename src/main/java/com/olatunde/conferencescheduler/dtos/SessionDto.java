package com.olatunde.conferencescheduler.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SessionDto {

    private String code;

    private String name;

    private String description;

    private Long length;
}
