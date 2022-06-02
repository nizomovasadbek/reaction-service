package com.test.reaction.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.function.Supplier;

@Slf4j
@Component
@RequiredArgsConstructor
public class FeignErrorWrapper {
    public static final String DEFAULT_FEIGN_CLIENT_ERROR = "Error during [{0}]";

    private static final String START_REQUEST_TEMPLATE = "Starting [{}]";
    private static final String END_REQUEST_TEMPLATE = "Finished [{}] with response {}";

    public <R> R wrapException(
            Supplier<R> httpCall,
            String actionMessage,
            String errorCode) {
        return wrapException(httpCall, actionMessage, HttpStatus.BAD_REQUEST, errorCode, DEFAULT_FEIGN_CLIENT_ERROR, actionMessage);
    }

    public <R> R wrapException(
            Supplier<R> httpCall,
            String actionMessage,
            HttpStatus errorStatus,
            String errorCode,
            String errorMessageCode,
            Object... errorMsgParams) {
        try {
            log.debug(START_REQUEST_TEMPLATE, actionMessage);
            R response = httpCall.get();
            log.debug(END_REQUEST_TEMPLATE, actionMessage, response);
            return response;
        } catch (Exception e) {
            String errorMsg = MessageFormat.format(errorMessageCode, errorMsgParams);
            log.error(errorMsg);
            log.error(e.getMessage());
            throw new ApiException(e.getMessage());
        }
    }
}
