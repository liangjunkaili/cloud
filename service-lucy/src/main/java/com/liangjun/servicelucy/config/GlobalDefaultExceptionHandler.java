package com.liangjun.servicelucy.config;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.NestedServletException;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String defaultExceptionHandler(HttpServletRequest request, Exception e) {
        if (e instanceof SQLIntegrityConstraintViolationException){
            return "SQLIntegrityConstraintViolationException";
        }
        if (e instanceof DuplicateKeyException){
            return "DuplicateKeyException";
        }
        if (e instanceof NestedServletException){
            return "NestedServletException";
        }
	    return "Exception";
    }
}
