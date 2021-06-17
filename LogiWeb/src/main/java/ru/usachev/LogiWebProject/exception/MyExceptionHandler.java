package ru.usachev.LogiWebProject.exception;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * ExceptionHandler class works when getting any exception in LogiWebProject app
 */
@ControllerAdvice
public class MyExceptionHandler {

    private static final Logger logger = Logger.getLogger(MyExceptionHandler.class);

    @ExceptionHandler({Exception.class})
    public String handleRuntimeException(Exception ex) {
        logger.error("Get exception: " + ex);
        return "pages/500";
    }
}