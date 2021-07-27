package com.github.gyeong5961.dinner.controller.advice;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    private final HttpHeaders headers = new HttpHeaders();

    public ExceptionControllerAdvice() {
        this.headers.add(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML_VALUE + ";charset=utf-8");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ConstraintViolationException.class})
    public String DataIntegrityViolationException(final ConstraintViolationException ex) {
        log.error("error", ex);
        return "ID 값이 중복되었습니다.";
    }

    @ExceptionHandler({AuthenticationException.class})
    public Object handleAuthenticationException(final AuthenticationException ex) {
        log.error("error", ex);
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .headers(headers).body("인증 실패입니다.");
    }


    @ExceptionHandler({AccessDeniedException.class})
    public Object handleAccessDeniedException(final AccessDeniedException ex) {
        log.error("error", ex);
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .headers(headers).body("권한이 없습니다.");
    }

    @ExceptionHandler({IllegalStateException.class})
    public ResponseEntity<Object> handleIllegalStateException(final IllegalStateException ex) {
        log.error("error", ex);
        return ResponseEntity
                .badRequest()
                .headers(headers)
                .body("요청이 잘못 되었습니다.");
    }

    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity<Object> handleNullPointerException(final NullPointerException ex) {
        log.error("error", ex);
        return ResponseEntity
                .badRequest()
                .headers(headers)
                .body("요청이 잘못 되었습니다.");
    }

    @ExceptionHandler({NoHandlerFoundException.class})
    public ResponseEntity<Object> handleNotFound(final NoHandlerFoundException ex) {
        log.error("error", ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .headers(headers)
                .body("접근 불가능합니다.");
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<Object> handleHttpMethodNotSupport(final HttpRequestMethodNotSupportedException ex) {
        log.error("error", ex);
        return ResponseEntity
                .internalServerError()
                .headers(headers)
                .body("해당 " + ex.getMethod() + " 메소드는 지원하지 않습니다.");
    }

    @ExceptionHandler({Exception.class, RuntimeException.class})
    public ResponseEntity<Object> handleAll(final RuntimeException ex) {
        log.error("error", ex);
        return ResponseEntity
                .internalServerError()
                .headers(headers)
                .body("서버 오류 입니다. 개발자에게 문의 부탁드립니다.");
    }

}
