package com.epam.esm.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Date;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiErrorDto {

    private String status;
    private String message;
    private String path;

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    private Date date;

    public ApiErrorDto(String status, String message, String path) {
        this.path = path;
        this.message = message;
        this.status = status;
        this.date = new Date();
    }
}