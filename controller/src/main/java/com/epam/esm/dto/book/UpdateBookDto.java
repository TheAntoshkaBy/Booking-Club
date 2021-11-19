package com.epam.esm.dto.book;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateBookDto {
    @ToString.Exclude
    protected Long id;
    protected String name;
    protected String description;
    protected int count;
    protected String yearTheBookWasPrinted;
}
