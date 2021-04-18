package com.olatunde.conferencescheduler.integrations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.olatunde.conferencescheduler.dtos.SessionDto;
import com.olatunde.conferencescheduler.exceptions.ResourceNotFoundException;
import com.olatunde.conferencescheduler.services.SessionService;
import com.olatunde.conferencescheduler.utils.Constants;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SessionControllerTest extends BaseIntegrationTest{

    @SpyBean
    private SessionService sessionService;

    @Test
    public void testShouldGetSessionsShouldReturnSuccess() throws Exception {
        doReturn(Collections.singletonList(buildSessionDto()))
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
        SessionDto sessionDto = buildSessionDto();
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
        SessionDto sessionDto = buildSessionDto();
        doThrow(ResourceNotFoundException.class)
                .when(sessionService).findByCode(sessionDto.getCode());
        mockMvc.perform(get(String.format("%s/sessions/%s",Constants.API_VERSION_1,sessionDto.getCode())))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.requestSuccessful").value(false))
                .andExpect(jsonPath("$.data").isEmpty());

        verify(sessionService, times(1)).findByCode(sessionDto.getCode());
    }

    @Test
    public void testShouldCreateSessionShouldReturnSuccess() throws Exception {
        SessionDto sessionDto = buildSessionDto();
        doReturn(sessionDto)
                .when(sessionService).createSession(sessionDto);
        mockMvc.perform(post(String.format("%s/sessions",Constants.API_VERSION_1))
                            .content(new ObjectMapper().writeValueAsString(sessionDto))
                            .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.requestSuccessful").value(true))
                .andExpect(jsonPath("$.data").isNotEmpty());

        verify(sessionService, times(1)).createSession(sessionDto);
    }

    @Test
    public void testShouldEditSessionShouldReturnSuccess() throws Exception {
        SessionDto sessionDto = buildSessionDto();
        doReturn(sessionDto)
                .when(sessionService).updateSession(sessionDto.getCode(), sessionDto);
        mockMvc.perform(put(String.format("%s/sessions/%s",Constants.API_VERSION_1, sessionDto.getCode()))
                .content(new ObjectMapper().writeValueAsString(sessionDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.requestSuccessful").value(true))
                .andExpect(jsonPath("$.data").isNotEmpty());

        verify(sessionService, times(1)).updateSession(sessionDto.getCode(), sessionDto);
    }

    @Test
    public void testShouldDeleteSessionShouldReturnSuccess() throws Exception {
        SessionDto sessionDto = buildSessionDto();
        doNothing()
                .when(sessionService).deleteByCode(sessionDto.getCode());
        mockMvc.perform(delete(String.format("%s/sessions/%s",Constants.API_VERSION_1, sessionDto.getCode()))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.requestSuccessful").value(true))
                .andExpect(jsonPath("$.data").isEmpty());

        verify(sessionService, times(1)).deleteByCode(sessionDto.getCode());
    }

    private SessionDto buildSessionDto() {
        return SessionDto.builder()
                .code("random_code")
                .name("First Session")
                .description("For the smart in mind")
                .build();
    }
}
