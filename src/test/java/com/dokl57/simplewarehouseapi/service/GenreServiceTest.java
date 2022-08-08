package com.dokl57.simplewarehouseapi.service;

import com.dokl57.simplewarehouseapi.entity.Genre;
import com.dokl57.simplewarehouseapi.repository.GenreRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class GenreServiceTest {

    @Autowired
    private GenreService genreService;

    @MockBean
    private GenreRepository genreRepositoryMock;

    private final String genreName = "genreName";

    @Test
    void createGenre() {
        Genre genre = new Genre(genreName);
        when(genreRepositoryMock.save(genre)).thenReturn(genre);
        Genre genreCreated = genreService.createGenre(genreName);
        assertEquals(genre, genreCreated);
        verify(genreRepositoryMock).save(genre);
    }
}