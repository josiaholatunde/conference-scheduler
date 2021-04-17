package com.olatunde.conferencescheduler.integrations;

import com.olatunde.conferencescheduler.dtos.SessionDto;
import com.olatunde.conferencescheduler.response.ConferenceSchedulerResponse;
import com.olatunde.conferencescheduler.services.SessionService;
import com.olatunde.conferencescheduler.utils.Constants;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.Collections;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
                .andExpect(jsonPath("$.requestSuccessful").value(true));

        verify(sessionService, times(1)).retrieveSessions();
    }
}
