package com.olatunde.conferencescheduler.integrations;

import com.olatunde.conferencescheduler.dtos.SessionDto;
import com.olatunde.conferencescheduler.exceptions.ResourceNotFoundException;
import com.olatunde.conferencescheduler.services.SessionService;
import com.olatunde.conferencescheduler.utils.Constants;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SessionControllerTest extends BaseIntegrationTest{

    @SpyBean
    private SessionService sessionService;

    @Test
    public void testShouldGetSessionsShouldReturnSuccess() throws Exception {
        doReturn(Collections.singletonList(SessionDto.builder()
                        .code("random_code")
                        .name("First Session")
                        .description("For the smart in mind")
                        .build()))
                .when(sessionService).retrieveSessions();
        mockMvc.perform(get(Constants.API_VERSION_1+"sessions"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.requestSuccessful").value(true))
                .andExpect(jsonPath("$.data").isNotEmpty());

        verify(sessionService, times(1)).retrieveSessions();
    }

    @Test
    public void testShouldGetSessionByCodeShouldReturnSuccess() throws Exception {
        SessionDto sessionDto = SessionDto.builder()
                .code("random_code")
                .name("First Session")
                .description("For the smart in mind")
                .build();
        doReturn(sessionDto)
                .when(sessionService).findByCode(sessionDto.getCode());
        mockMvc.perform(get(String.format("%s/sessions/%s",Constants.API_VERSION_1,sessionDto.getCode())))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.requestSuccessful").value(true))
                .andExpect(jsonPath("$.data").isNotEmpty());

        verify(sessionService, times(1)).findByCode(sessionDto.getCode());
    }

    @Test
    public void testShouldGetSessionByCodeShouldReturnNotFoundForNonExistentSessionCode() throws Exception {
        SessionDto sessionDto = SessionDto.builder()
                .code("random_code")
                .name("First Session")
                .description("For the smart in mind")
                .build();
        doThrow(ResourceNotFoundException.class)
                .when(sessionService).findByCode(sessionDto.getCode());
        mockMvc.perform(get(String.format("%s/sessions/%s",Constants.API_VERSION_1,sessionDto.getCode())))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.requestSuccessful").value(false))
                .andExpect(jsonPath("$.data").isEmpty());

        verify(sessionService, times(1)).findByCode(sessionDto.getCode());
    }
}
