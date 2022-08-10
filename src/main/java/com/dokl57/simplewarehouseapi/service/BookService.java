package com.dokl57.simplewarehouseapi.service;

import com.dokl57.simplewarehouseapi.entity.Book;
import com.dokl57.simplewarehouseapi.entity.Genre;
import com.dokl57.simplewarehouseapi.enums.Operation;
import com.dokl57.simplewarehouseapi.exception.ValidationException;
import com.dokl57.simplewarehouseapi.repository.BookRepository;
import com.dokl57.simplewarehouseapi.repository.GenreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookService {
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;

    public BookService(BookRepository bookRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
    }

    @Transactional
    public Book income(String title, String author, int pages, int quantity, String genreName) {
        Optional<Book> existingBook = bookRepository.findByPagesAndGenreNameAndAuthor(pages, genreName, author);
        if (existingBook.isPresent()) {
            Book book = existingBook.get();
            book.setQuantity(book.getQuantity() + quantity);
            return bookRepository.save(book);
        } else {
            Genre genre = genreRepository.findByName(genreName).orElseThrow(() -> new ValidationException("Genre with name " + genreName + " not found"));
            Book book = new Book(title, author, pages, quantity, genre);
            return bookRepository.save(book);
        }
    }

    @Transactional
public Book outcome(String title, String author, int pages, int quantity, String genreName) {
        Optional<Book> existingBook = bookRepository.findByPagesAndGenreNameAndAuthor(pages, genreName, author);
        if (existingBook.isPresent()) {
            Book book = existingBook.get();
            if (book.getQuantity() < quantity) {
                throw new ValidationException("Not enough books in stock");
            }
            book.setQuantity(book.getQuantity() - quantity);
            return bookRepository.save(book);
        } else {
            throw new ValidationException("Book with title " + title + " not found");
        }
    }

    public List<Book> getBooksByParams(String genreName, int pages, String operator){
        Operation operation = Operation.decode(operator);
        log.info("Get books with genre {}, {} than {} pages!!!!", genreName, operation, pages);
        switch (operation) {
            case EQUAL:
                return bookRepository.findByGenreNameAndPagesEquals(genreName, pages);
            case GREATER_THAN:
                return bookRepository.findByGenreNameAndPagesGreaterThan(genreName, pages);
            case LESS_THAN:
                return bookRepository.findByGenreNameAndPagesLessThan(genreName, pages);
            default:
                log.info("test");
                throw new ValidationException("Invalid operation");
        }
    }

}
