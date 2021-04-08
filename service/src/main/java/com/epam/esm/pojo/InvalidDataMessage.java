package com.epam.esm.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Component
public class InvalidDataMessage {

    private String message;

    public InvalidDataMessage(String message) {
        this.message = message;
    }
}
