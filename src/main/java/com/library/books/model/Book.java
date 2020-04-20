package com.library.books.model;


import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Book extends RepresentationModel<Book> {
    private Integer id;

    private String title;
    private String author;
    private String titleSlug;
    private String authorSlug;
    private Long isbn13;
    private Long isbn10;
    private String format;
    private Date pubDate;
    private String subjects;
    private Integer pages;
    private String overview;
    private String synopsis;
}
