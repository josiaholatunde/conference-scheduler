package com.olatunde.conferencescheduler.advice;

import com.olatunde.conferencescheduler.exceptions.ResourceNotFoundException;
import com.olatunde.conferencescheduler.response.ConferenceSchedulerResponse;
import com.olatunde.conferencescheduler.utils.ResponseServiceUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalControllerAdvice {

    private final ResponseServiceUtil responseServiceUtil;

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    public ConferenceSchedulerResponse buildNotFoundResponse(ResourceNotFoundException ex, HttpServletRequest request) {
        log.error("Resource not found exception {}", ex.getMessage());
        return  responseServiceUtil.buildErrorResponse(ex.getMessage());
    }
}
