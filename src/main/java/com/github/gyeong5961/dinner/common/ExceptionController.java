package com.github.gyeong5961.dinner.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.annotation.PostConstruct;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    private final HttpHeaders headers = new HttpHeaders();

    @PostConstruct
    public void init() {
        this.headers.add(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML_VALUE + ";charset=utf-8");
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ConstraintViolationException.class})
    public Object handleDataIntegrityViolationException(final ConstraintViolationException ex) {
        log.error("error", ex);
        return "아이디 중복확인을 부탁드립니다.";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalStateException.class, IllegalArgumentException.class})
    public Object handleIllegalStateException(final Exception ex) {
        log.error("error", ex);
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({JsonProcessingException.class})
    public Object handleJsonProcessingException(final JsonProcessingException ex) {
        log.error("error", ex);
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({UsernameNotFoundException.class})
    public Object handleUsernameNotFoundException(final UsernameNotFoundException ex) {
        log.error("error", ex);
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({AuthenticationException.class})
    public Object handleAuthenticationException(final AuthenticationException ex) {
        log.error("error", ex);
        return "인증 실패입니다.";
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({AccessDeniedException.class})
    public Object handleAccessDeniedException(final AccessDeniedException ex) {
        log.error("error", ex);
        return ("권한이 없습니다.");
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({NoHandlerFoundException.class})
    public Object handleNotFound(final NoHandlerFoundException ex) {
        log.error("error", ex);
        return "해당페이지는 없습니다.";
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public Object handleHttpMethodNotSupport(final HttpRequestMethodNotSupportedException ex) {
        return ResponseEntity
                .internalServerError()
                .headers(headers)
                .body("해당 경로에는 " + ex.getMethod() + " 메소드는 지원하지 않습니다.");
    }

    @ExceptionHandler({Exception.class, RuntimeException.class, NullPointerException.class})
    public Object handleAll(final RuntimeException ex) {
        log.error("error", ex);
        return ResponseEntity
                .internalServerError()
                .headers(headers)
                .body("서버 오류 입니다. <br />개발자에게 문의 부탁드립니다. <br /> Email: gyeong5961@gmail.com<br />" + ex.getMessage());
    }

}
