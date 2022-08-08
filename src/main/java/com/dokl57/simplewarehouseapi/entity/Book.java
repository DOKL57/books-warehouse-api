package com.dokl57.simplewarehouseapi.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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


}
