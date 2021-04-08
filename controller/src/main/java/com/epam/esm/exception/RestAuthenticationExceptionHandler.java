package com.epam.esm.exception;

import com.epam.esm.dto.ApiErrorDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class RestAuthenticationExceptionHandler implements AuthenticationEntryPoint {


    public void commence(HttpServletRequest request,
        HttpServletResponse response, AuthenticationException e) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        String errorMessage = "Access Denied, please authorise!";

        ApiErrorDTO apiError = new ApiErrorDTO(
            HttpStatus.UNAUTHORIZED.toString(),errorMessage , request.getRequestURI());

        OutputStream out = response.getOutputStream();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        com.fasterxml.jackson.databind.ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, apiError);

        out.flush();
    }
}
