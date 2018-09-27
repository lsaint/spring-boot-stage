package com.ethan.stage.common;

import com.ethan.stage.common.enums.ErrEnum;
import java.util.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

// 应用中不可直接使用日志系统(Log4j、Logback)中的 API，而应依赖使用日志框架
// SLF4J 中的 API，使用facade模式的日志框架，有利于维护和各个类的日志处理方式统一
@Slf4j
@SuppressWarnings("rawtypes")
@RestControllerAdvice // 该注解实现全局异常处理 401/403除外
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

    @ExceptionHandler(value = APIException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GenericResponse apiExceptionHandler(APIException ex) {
        log.debug(ex.toString());
        return new GenericResponse<>(ex.getCode(), ex.getMsg(), Const.EMPTY_MAP);
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public GenericResponse methodExceptionHandler(HttpRequestMethodNotSupportedException ex) {
        log.debug(ex.toString());
        return new GenericResponse<>(ErrEnum.METHOD_NOT_ALLOWED, Const.EMPTY_MAP);
    }
}
