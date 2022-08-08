package com.dokl57.simplewarehouseapi.controller;

import com.dokl57.simplewarehouseapi.service.GenreService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GenreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GenreService genreServiceMock;

    private final String common_part = "/api/genre";
    private final String genreName = "genreName";

    @Test
    void addGenre() throws Exception {
        mockMvc.perform(post(common_part)
                .param("genreName", genreName))
                .andExpect(status().isOk());
        verify(genreServiceMock).createGenre(genreName);
    }

}