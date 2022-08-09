package com.dokl57.simplewarehouseapi.controller;

import com.dokl57.simplewarehouseapi.dto.BookChangeDto;
import com.dokl57.simplewarehouseapi.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

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

    @GetMapping("/getTotalBooksByParams")
    public void getTotalBooksByParams(@Valid @RequestBody BookRequestDto bookRequestDto) {
        log.info("Get total books with title {} by param {} than {}", bookRequestDto.getTitle(), bookRequestDto.getOperation(), bookRequestDto.getQuantity());
        bookService.getTotalBooksByParams(bookRequestDto.getTitle(), bookRequestDto.getQuantity(), bookRequestDto.getOperation());
    }


}
