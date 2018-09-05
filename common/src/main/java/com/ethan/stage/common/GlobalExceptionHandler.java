package com.ethan.stage.common;

import com.ethan.stage.common.enums.ErrEnum;
import java.util.*;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@Log4j2
@SuppressWarnings("rawtypes")
// 该注解实现全局异常处理
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GenericResponse exceptionHandler(Exception ex) {
        log.error("Traceback: " + ExceptionUtils.getStackTrace(ex));
        return new GenericResponse<>(ErrEnum.INTERNAL_SERVER_ERROR, Const.EMPTY_MAP);
    }

    @ExceptionHandler(value = {NoHandlerFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public GenericResponse http404Handler(Exception ex) {
        log.warn(ExceptionUtils.getMessage(ex));
        return new GenericResponse<>(ErrEnum.NOT_FOUND, Const.EMPTY_MAP);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GenericResponse validHandler(MethodArgumentNotValidException ex) {
        var fe = ex.getBindingResult().getFieldErrors();
        var result = new HashMap<String, HashMap<String, String>>(1);
        var errors = new HashMap<String, String>();
        for (var e : fe) {
            log.debug(e.getField() + " -- " + e.getDefaultMessage());
            errors.put(e.getField(), e.getDefaultMessage());
        }
        result.put("errors", errors);
        return new GenericResponse<>(ErrEnum.BAD_REQUEST, result);
    }
}
