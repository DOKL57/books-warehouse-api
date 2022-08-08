package com.dokl57.simplewarehouseapi.controller;

import com.dokl57.simplewarehouseapi.service.GenreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/api/genre")
public class GenreController {

    private final GenreService genreService;


    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping
    public void createGenre(String genreName) {
        log.info("Creating genre with name {}", genreName);
        genreService.createGenre(genreName);
    }

}
