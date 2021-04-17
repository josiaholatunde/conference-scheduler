package com.olatunde.conferencescheduler.utils;

import com.olatunde.conferencescheduler.response.ConferenceSchedulerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ResponseServiceUtil {

    public ConferenceSchedulerResponse buildErrorResponse(String message) {
        return ConferenceSchedulerResponse
                .builder()
                .requestSuccessful(false)
                .message(message)
                .build();
    }
}
