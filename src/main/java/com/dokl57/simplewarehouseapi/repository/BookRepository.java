package com.dokl57.simplewarehouseapi.repository;

import com.dokl57.simplewarehouseapi.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    Optional<Book> findByPagesAndGenreNameAAndAuthor(int pages, String genreName, String author);

    List<Book> findByGenreNameAndPagesGreaterThan(String genreName, int pages);

    List<Book> findByGenreNameAndPagesLessThan(String genreName, int pages);

    List<Book> findByGenreNameAndPagesEquals(String genreName, int pages);


}
