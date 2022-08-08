package com.dokl57.simplewarehouseapi.service;

import com.dokl57.simplewarehouseapi.entity.Genre;
import com.dokl57.simplewarehouseapi.repository.GenreRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Transactional
    public Genre createGenre(String genreName) {
        Optional<Genre> genre = genreRepository.findByName(genreName);
        if(genre.isPresent()) {
            return genre.get();
        }
        return genreRepository.save(new Genre(genreName));
    }

}
