package com.olatunde.conferencescheduler.utils;

import com.olatunde.conferencescheduler.response.ConferenceSchedulerResponse;

public class ResponseServiceUtil {

    public ConferenceSchedulerResponse buildErrorResponse(String message) {
        return ConferenceSchedulerResponse
                .builder()
                .requestSuccessful(false)
                .message(message)
                .build();
    }
}
