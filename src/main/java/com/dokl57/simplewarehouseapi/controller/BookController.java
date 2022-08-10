package com.dokl57.simplewarehouseapi.controller;

import com.dokl57.simplewarehouseapi.dto.BookChangeDto;
import com.dokl57.simplewarehouseapi.dto.BookRequestDto;
import com.dokl57.simplewarehouseapi.entity.Book;
import com.dokl57.simplewarehouseapi.exception.ValidationException;
import com.dokl57.simplewarehouseapi.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;


    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/income")
    public void income(@Valid @RequestBody BookChangeDto bookChangeDto) {
        log.info("Income books with title {}", bookChangeDto.getTitle());
        bookService.income(bookChangeDto.getTitle(), bookChangeDto.getAuthor(), bookChangeDto.getPages(), bookChangeDto.getQuantity(), bookChangeDto.getGenre());
    }

@   PostMapping("/outcome")
    public void outcome(@Valid @RequestBody BookChangeDto bookChangeDto) {
        log.info("Outcome books with title {}", bookChangeDto.getTitle());
        bookService.outcome(bookChangeDto.getTitle(), bookChangeDto.getAuthor(), bookChangeDto.getPages(), bookChangeDto.getQuantity(), bookChangeDto.getGenre());
    }

    @GetMapping("/getBooksByParams")
    public List<Book> getBooksByParams(@Valid BookRequestDto bookRequestDto) {
        log.info("Get books with genre {}, {} than {} pages", bookRequestDto.getGenre(), bookRequestDto.getOperation(), bookRequestDto.getPages());
        return bookService.getBooksByParams(bookRequestDto.getOperation(), bookRequestDto.getPages(), bookRequestDto.getOperation());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>("{\n \"exception\" : \"" + e.getMessage() + "\"\n}", HttpStatus.BAD_REQUEST);
    }

}
