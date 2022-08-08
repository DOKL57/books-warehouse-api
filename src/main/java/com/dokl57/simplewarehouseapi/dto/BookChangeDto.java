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
public class BookChangeDto {

    @NotNull(message = "Title is required")
    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Author is required")
    @NotNull(message = "Author is required")
    private String author;

    @Min(value = 100, message = "Pages must be greater than 100")
    private int pages;

    @Min(value = 0, message = "Quantity must be a positive number")
    @NotNull(message = "Quantity percent is a required parameter")
    private int quantity;

    @NotBlank(message = "Genre can't be blank")
    @NotNull(message = "Genre is a required parameter")
    private String genre;
}
