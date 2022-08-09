package com.dokl57.simplewarehouseapi.controller;

import com.dokl57.simplewarehouseapi.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookServiceMock;

    private final String COMMON_PART = "/api/book";
    private final String TITLE = "bookName";
    private final String AUTHOR = "author";
    private final int PAGES = 100;
    private final String GENRE = "genre";
    private final int QUANTITY = 30;
    private final String OPERATION = "<";

    private final int INVALID_PAGES = -1;
    private final int INVALID_QUANTITY = -1;
    private final String INVALID_OPERATION = "invalidOperation";


    @Test
    void incomeValid() throws Exception {
        mockMvc.perform(
                post(COMMON_PART + "/income").contentType(MediaType.APPLICATION_JSON)
                        .content(incomeOutcomeContent(TITLE, AUTHOR, PAGES, QUANTITY, GENRE))).andExpect(status().isOk());


    }

    @Test
    void incomeInvalidPages() throws Exception {
        mockMvc.perform(
                post(COMMON_PART + "/income").contentType(MediaType.APPLICATION_JSON)
                        .content(incomeOutcomeContent(TITLE, AUTHOR, INVALID_PAGES, QUANTITY, GENRE))).andExpect(status().isBadRequest());
    }

    @Test
    void incomeInvalidQuantity() throws Exception {
        mockMvc.perform(
                post(COMMON_PART + "/income").contentType(MediaType.APPLICATION_JSON)
                        .content(incomeOutcomeContent(TITLE, AUTHOR, PAGES, INVALID_QUANTITY, GENRE))).andExpect(status().isBadRequest());
    }

    @Test
    void outcomeValid() throws Exception {
        mockMvc.perform(
                post(COMMON_PART + "/outcome").contentType(MediaType.APPLICATION_JSON)
                        .content(incomeOutcomeContent(TITLE, AUTHOR, PAGES, QUANTITY, GENRE))).andExpect(status().isOk());
    }

    @Test
    void outcomeInvalidPages() throws Exception {
        mockMvc.perform(
                post(COMMON_PART + "/outcome").contentType(MediaType.APPLICATION_JSON)
                        .content(incomeOutcomeContent(TITLE, AUTHOR, INVALID_PAGES, QUANTITY, GENRE))).andExpect(status().isBadRequest());
    }

    @Test
    void outcomeInvalidQuantity() throws Exception {
        mockMvc.perform(
                post(COMMON_PART + "/outcome").contentType(MediaType.APPLICATION_JSON)
                        .content(incomeOutcomeContent(TITLE, AUTHOR, PAGES, INVALID_QUANTITY, GENRE))).andExpect(status().isBadRequest());
    }

    // these test and later dont works
    @Test
    void getTotalBooksByParamValid() throws Exception {
        mockMvc.perform(
                get(COMMON_PART + "/getTotalBooksByParams")
                        .param("title", TITLE)
                        .param("author", AUTHOR)
                        .param("pages", String.valueOf(PAGES))
                        .param("quantity", String.valueOf(QUANTITY))
                        .param("genre", GENRE)
                        .param("operation", OPERATION)
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void getTotalBooksByParamInvalidOperation() throws Exception {
        mockMvc.perform(
                get(COMMON_PART + "/getTotalBooksByParams")
                        .param("title", TITLE)
                        .param("author", AUTHOR)
                        .param("pages", String.valueOf(PAGES))
                        .param("quantity", String.valueOf(QUANTITY))
                        .param("genre", GENRE)
                        .param("operation", INVALID_OPERATION)
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }
    

    private String incomeOutcomeContent(String title, String author, int pages, int quantity, String genre) {
        return String.format("{\"title\":\"%s\",\"author\":\"%s\",\"pages\":%d,\"quantity\":%d,\"genre\":\"%s\"}", title, author, pages, quantity, genre);
    }

}