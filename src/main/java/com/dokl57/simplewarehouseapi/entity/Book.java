package com.dokl57.simplewarehouseapi.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @NotBlank
    @Column(name = "title")
    private String title;

    @NotNull
    @NotBlank
    @Column(name = "author")
    private String author;

    @NotNull
    @Min(100)
    @Column(name = "pages")
    private int pages;

    @NotNull
    @Min(0)
    @Column(name = "quantity")
    private int quantity;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    public Book(String title, String author, int pages, int quantity, Genre genre) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.quantity = quantity;
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (pages != book.pages) return false;
        if (quantity != book.quantity) return false;
        if (!Objects.equals(id, book.id)) return false;
        if (!title.equals(book.title)) return false;
        if (!author.equals(book.author)) return false;
        return genre.equals(book.genre);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + title.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + pages;
        result = 31 * result + quantity;
        result = 31 * result + genre.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", pages=" + pages +
                ", quantity=" + quantity +
                ", genre=" + genre +
                '}';
    }
}
