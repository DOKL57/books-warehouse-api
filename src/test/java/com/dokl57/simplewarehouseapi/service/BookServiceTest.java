package com.dokl57.simplewarehouseapi.service;

import com.dokl57.simplewarehouseapi.entity.Book;
import com.dokl57.simplewarehouseapi.entity.Genre;
import com.dokl57.simplewarehouseapi.exception.ValidationException;
import com.dokl57.simplewarehouseapi.repository.BookRepository;
import com.dokl57.simplewarehouseapi.repository.GenreRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepository bookRepositoryMock;

    @MockBean
    private GenreRepository genreRepositoryMock;

    private final int PAGES = 101;
    private final int QUANTITY = 30;
    private final int INCOME_QUANTITY = 5;
    private final int OUTCOME_QUANTITY_LESS_THEN_QUANTITY = 15;
    private final int OUTCOME_QUANTITY_GREATER_THEN_QUANTITY = 150;
    private final String GENRE = "genre";
    private final String AUTHOR = "author";
    private final String TITLE = "title";

    private final String OPERATION_LESS_THAN = "<";
    private final String OPERATION_MORE_THAN = ">";
    private final String OPERATION_EQUAL = "=";
    private final String OPERATION_UNKNOWN = "unknown";

    @Test
    void income_whenBooksPresented(){
        Optional<Book> book = Optional.of(new Book(TITLE, AUTHOR, PAGES, QUANTITY, new Genre(GENRE)));
        doReturn(book).when(bookRepositoryMock).findByPagesAndGenreNameAndAuthor(PAGES, GENRE, AUTHOR);
        bookService.income(TITLE, AUTHOR, PAGES, INCOME_QUANTITY, GENRE);
        verify(bookRepositoryMock).findByPagesAndGenreNameAndAuthor(PAGES, GENRE, AUTHOR);
        assertEquals(QUANTITY + INCOME_QUANTITY, book.get().getQuantity());
        verify(bookRepositoryMock).save(book.get());

    }

    @Test
    void income_whenNotBooksPresented_thenColorFound(){
        Optional<Book> book = Optional.of(new Book(TITLE, AUTHOR, PAGES, QUANTITY, new Genre(GENRE)));
        doReturn(book).when(bookRepositoryMock).findByPagesAndGenreNameAndAuthor(PAGES, GENRE, AUTHOR);
        bookService.income(TITLE, AUTHOR, PAGES, INCOME_QUANTITY, GENRE);
        verify(bookRepositoryMock).findByPagesAndGenreNameAndAuthor(PAGES, GENRE, AUTHOR);
        verify(bookRepositoryMock).save(any(Book.class));
    }

    @Test
    void income_whenNotBooksPresented_thanColorNotFound(){
        Assertions.assertThrows(ValidationException.class, () -> {
            bookService.income(TITLE, AUTHOR, PAGES, INCOME_QUANTITY, GENRE);
        });
    }

    @Test
    void outcome_whenBooksNotFound(){
        Assertions.assertThrows(ValidationException.class, () -> {
            bookService.outcome(TITLE, AUTHOR, PAGES, INCOME_QUANTITY, GENRE);
        });
    }

    @Test
    void outcome_whenBooksFound_neededQuantityLessthanStored() {
        Optional<Book> books = Optional.of(new Book(TITLE, AUTHOR, PAGES, QUANTITY, new Genre(GENRE)));
        doReturn(books).when(bookRepositoryMock).findByPagesAndGenreNameAndAuthor(PAGES, GENRE, AUTHOR);
        bookService.outcome(TITLE, AUTHOR, PAGES, OUTCOME_QUANTITY_LESS_THEN_QUANTITY, GENRE);
        assertEquals(QUANTITY - OUTCOME_QUANTITY_LESS_THEN_QUANTITY, books.get().getQuantity());
        verify(bookRepositoryMock, times(1)).save(books.get());
    }

    @Test
    void outcome_whenBooksFound_neededQuantityEqualStored(){
        Optional<Book> books = Optional.of(new Book(TITLE, AUTHOR, PAGES, QUANTITY, new Genre(GENRE)));
        doReturn(books).when(bookRepositoryMock).findByPagesAndGenreNameAndAuthor(PAGES, GENRE, AUTHOR);
        bookService.outcome(TITLE, AUTHOR, PAGES, QUANTITY, GENRE);
        assertEquals(0, books.get().getQuantity());
        verify(bookRepositoryMock, times(1)).save(books.get());
    }

    @Test
    void outcome_whenBooksFound_neededQuantityGreaterthanStored(){
        Optional<Book> books = Optional.of(new Book(TITLE, AUTHOR, PAGES, QUANTITY, new Genre(GENRE)));
        doReturn(books).when(bookRepositoryMock).findByPagesAndGenreNameAndAuthor(PAGES, GENRE, AUTHOR);
        Assertions.assertThrows(ValidationException.class, () -> {
            bookService.outcome(TITLE, AUTHOR, PAGES, OUTCOME_QUANTITY_GREATER_THEN_QUANTITY, GENRE);
        });
    }
    @Test
    void findByGenreNameAndPagesLessThan(){
        bookService.getBooksByParams(GENRE, PAGES, OPERATION_LESS_THAN);
        verify(bookRepositoryMock, times(1)).findByGenreNameAndPagesLessThan(GENRE, PAGES);
    }
    @Test
    void findByGenreNameAndPagesMoreThanThan(){
        bookService.getBooksByParams(GENRE, PAGES, OPERATION_MORE_THAN);
        verify(bookRepositoryMock, times(1)).findByGenreNameAndPagesGreaterThan(GENRE, PAGES);
    }
    @Test
    void findByGenreNameAndPagesEqual(){
        bookService.getBooksByParams(GENRE, PAGES, OPERATION_EQUAL);
        verify(bookRepositoryMock, times(1)).findByGenreNameAndPagesEquals(GENRE, PAGES);
    }

    @Test
    void findByGenreNameAndPagesUnknown(){
        Assertions.assertThrows(ValidationException.class, () -> {
            bookService.getBooksByParams(GENRE, PAGES, OPERATION_UNKNOWN);
        });
    }






}