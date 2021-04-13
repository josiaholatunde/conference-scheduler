package com.olatunde.conferencescheduler.advice;

import com.olatunde.conferencescheduler.annotations.IgnoreWrapResponse;
import com.olatunde.conferencescheduler.annotations.WrapResponse;
import com.olatunde.conferencescheduler.response.ConferenceSchedulerResponse;
import com.olatunde.conferencescheduler.utils.Constants;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice(annotations = WrapResponse.class)
public class WrapResponseAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return methodParameter.getMethod() == null || (methodParameter.getMethod().getDeclaredAnnotation(IgnoreWrapResponse.class) == null);
    }

    @Nullable
    @Override
    public Object beforeBodyWrite(@Nullable Object data, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        return ConferenceSchedulerResponse.builder()
                .data(data)
                .requestSuccessful(true)
                .message(Constants.RESPONSE_SUCCESS_MESSAGE)
                .build();
    }
}
