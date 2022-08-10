package com.dokl57.simplewarehouseapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestDto {


    @NotBlank(message = "Genre can't be blank")
    @NotNull(message = "Genre is a required parameter")
    private String genre;


    @Min(value = 100, message = "Pages must be greater than 100")
    private int pages;

    @NotBlank(message = "Operation can't be blank")
    @NotNull(message = "Operation is a required parameter")
    private String operation;

}
