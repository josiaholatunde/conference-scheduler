package com.olatunde.conferencescheduler.response;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Builder
@Data
@Slf4j
public class ConferenceSchedulerResponse {
    private boolean requestSuccessful;
    private Object data;
    private String message;
}
