package com.osdnu.project.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.logging.Level;
import java.util.logging.Logger;

@ControllerAdvice
public class ExceptionHandle {
    private static final Logger LOGGER = Logger.getLogger(ExceptionHandle.class.getName());

    @ExceptionHandler({ MissingServletRequestParameterException.class, ServletRequestBindingException.class  })
    public ResponseEntity<ResponseException> handleServletError400(ServletException ex) {
        LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseException responseException = new ResponseException();
        responseException.setErrorMessage(ex.getMessage());
        responseException.setErrorCode("0");
        return new ResponseEntity<>(responseException, headers, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler({ BadRequestException.class })
    public ResponseEntity<ResponseException> handleError400(BadRequestException ex) {
        LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseException responseException = new ResponseException();
        responseException.setErrorMessage(ex.getMessage());
        responseException.setErrorCode(ex.getErrorCode());
        return new ResponseEntity<>(responseException, headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ NotFoundException.class  })
    public ResponseEntity<ResponseException> handleError404(NotFoundException ex) {
        LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseException responseException = new ResponseException();
        responseException.setErrorMessage(ex.getMessage());
        responseException.setErrorCode(ex.getErrorCode());
        return new ResponseEntity<>(responseException, headers, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ UnAuthorizationException.class  })
    public ResponseEntity<ResponseException> handleError401(UnAuthorizationException ex) {
        LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseException responseException = new ResponseException();
        responseException.setErrorMessage(ex.getMessage());
        responseException.setErrorCode(ex.getErrorCode());
        return new ResponseEntity<>(responseException, headers, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({ UnProcessableException.class  })
    public ResponseEntity<ResponseException> handleError422(UnProcessableException ex) {
        LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseException responseException = new ResponseException();
        responseException.setErrorMessage(ex.getMessage());
        responseException.setErrorCode(ex.getErrorCode());
        return new ResponseEntity<>(responseException, headers, HttpStatus.UNPROCESSABLE_ENTITY);
    }

}


