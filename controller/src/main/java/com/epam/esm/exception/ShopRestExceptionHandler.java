package com.epam.esm.exception;

import com.epam.esm.controller.security.jwt.JwtAuthenticationException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ShopRestExceptionHandler extends ResponseEntityExceptionHandler {
/*
    @ExceptionHandler(ServiceBadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public List<ApiErrorDTO> ShopServiceHandler(ServiceBadRequestException e,
                                                HttpServletRequest req) {
        List<ApiErrorDTO> errorList = new ArrayList<>();

        e.getMessages().forEach(invalidDataOutputMessage -> {
            String errorMessage = invalidDataOutputMessage.getMessage();
            ApiErrorDTO apiError = new ApiErrorDTO(e.getStatus(), errorMessage,
                req.getRequestURI());
            errorList.add(apiError);
        });

        return errorList;
    }

    @ExceptionHandler(RepositoryNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public List<ApiErrorDTO> ShopRepositoryHandler(RepositoryNotFoundException e,
                                                   HttpServletRequest req) {
        List<ApiErrorDTO> errorList = new ArrayList<>();

        e.getMessages().forEach(invalidDataOutputMessage -> {
            String errorMessage = invalidDataOutputMessage.getMessage();
            ApiErrorDTO apiError = new ApiErrorDTO(e.getStatus(), errorMessage,
                req.getRequestURI());
            errorList.add(apiError);
        });

        return errorList;
    }

    @ExceptionHandler(ControllerBadRequestException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public List<ApiErrorDTO> ShopControllerHandler(ControllerBadRequestException e,
        HttpServletRequest req) {
        List<ApiErrorDTO> errorList = new ArrayList<>();

        e.getMessages().forEach(invalidDataOutputMessage -> {
            String errorMessage = invalidDataOutputMessage.getMessage();
            ApiErrorDTO apiError = new ApiErrorDTO(HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                                                   errorMessage,
                req.getRequestURI());
            errorList.add(apiError);
        });

        return errorList;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
        HttpHeaders headers,
        HttpStatus status,
        WebRequest request
    ) {
        List<ApiErrorDTO> errorList = new ArrayList<>();

        e.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            String path = ((ServletWebRequest) request).getRequest().getRequestURI();
            ApiErrorDTO apiError = new ApiErrorDTO(status.toString(), errorMessage, path);
            errorList.add(apiError);
        });

        return new ResponseEntity<>(errorList, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object>
    defaultErrorHandler(HttpServletRequest req, Exception e) {
        List<ApiErrorDTO> errorList = new ArrayList<>();

        String errorMessage = e.getClass().getSimpleName();
        ApiErrorDTO apiError = new ApiErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
            errorMessage, req.getRequestURI());
        errorList.add(apiError);

        return new ResponseEntity<>(errorList, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = JwtAuthenticationException.class)
    public ResponseEntity<Object>
    JWTErrorHandler(HttpServletRequest req, Exception e) {
        List<ApiErrorDTO> errorList = new ArrayList<>();

        String errorMessage = e.getClass().getSimpleName();
        ApiErrorDTO apiError = new ApiErrorDTO(HttpStatus.BAD_REQUEST.toString(),
            errorMessage, req.getRequestURI());
        errorList.add(apiError);

        return new ResponseEntity<>(errorList, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex,
        HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ApiErrorDTO> errorList = new ArrayList<>();
        String errorMessage = HttpStatus.BAD_REQUEST.getReasonPhrase();
        String path = ((ServletWebRequest) request).getRequest().getRequestURI();

        ApiErrorDTO apiError = new ApiErrorDTO(status.toString(), errorMessage, path);
        errorList.add(apiError);

        return new ResponseEntity<>(errorList, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
        NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ApiErrorDTO> errorList = new ArrayList<>();
        String errorMessage = HttpStatus.NOT_FOUND.getReasonPhrase();
        String path = ((ServletWebRequest) request).getRequest().getRequestURI();

        ApiErrorDTO apiError = new ApiErrorDTO(status.toString(), errorMessage, path);
        errorList.add(apiError);

        return new ResponseEntity<>(errorList, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
        HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status,
        WebRequest request) {
        List<ApiErrorDTO> errorList = setDefaultExceptionData(status,request);
        return new ResponseEntity<>(errorList, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
        HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status,
        WebRequest request) {
        List<ApiErrorDTO> errorList = setDefaultExceptionData(status,request);
        return new ResponseEntity<>(errorList, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
        HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status,
        WebRequest request) {
        List<ApiErrorDTO> errorList = setDefaultExceptionData(status,request);
        return new ResponseEntity<>(errorList, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(
        HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status,
        WebRequest request) {
        List<ApiErrorDTO> errorList = setDefaultExceptionData(status,request);
        return new ResponseEntity<>(errorList, status);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex,
        HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ApiErrorDTO> errorList = setDefaultExceptionData(status,request);
        return new ResponseEntity<>(errorList, status);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
        MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status,
        WebRequest request) {
        List<ApiErrorDTO> errorList = setDefaultExceptionData(status,request);
        return new ResponseEntity<>(errorList, status);
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(
        ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status,
        WebRequest request) {
        List<ApiErrorDTO> errorList = setDefaultExceptionData(status,request);
        return new ResponseEntity<>(errorList, status);
    }

    @Override
    protected ResponseEntity<Object> handleConversionNotSupported(
        ConversionNotSupportedException ex, HttpHeaders headers, HttpStatus status,
        WebRequest request) {
        List<ApiErrorDTO> errorList = setDefaultExceptionData(status,request);
        return new ResponseEntity<>(errorList, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(
        HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status,
        WebRequest request) {
        List<ApiErrorDTO> errorList = setDefaultExceptionData(status,request);
        return new ResponseEntity<>(errorList, status);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(
        MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status,
        WebRequest request) {
        List<ApiErrorDTO> errorList = setDefaultExceptionData(status,request);
        return new ResponseEntity<>(errorList, status);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers,
        HttpStatus status, WebRequest request) {
        List<ApiErrorDTO> errorList = setDefaultExceptionData(status,request);
        return new ResponseEntity<>(errorList, status);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
        HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ApiErrorDTO> errorList = setDefaultExceptionData(status,request);
        return new ResponseEntity<>(errorList, status);
    }

    private List<ApiErrorDTO> setDefaultExceptionData(HttpStatus status, WebRequest request){
        List<ApiErrorDTO> errorList = new ArrayList<>();
        String errorMessage = status.getReasonPhrase();
        String path = ((ServletWebRequest) request).getRequest().getRequestURI();

        ApiErrorDTO apiError = new ApiErrorDTO(status.toString(), errorMessage, path);
        errorList.add(apiError);
        return errorList;
    }*/
}
