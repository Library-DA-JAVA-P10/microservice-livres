package com.library.books.assemblers;

import com.library.books.controller.BookController;
import com.library.books.entity.BookEntity;
import com.library.books.model.Book;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BookModelAssembler extends RepresentationModelAssemblerSupport<BookEntity, Book> {
    public BookModelAssembler() {
        super(BookController.class, Book.class);
    }

    @Override
    public Book toModel(BookEntity entity) {
        Book book = instantiateModel(entity);
        book.add(linkTo(methodOn(BookController.class)
                .showOneBook(entity.getId())).withSelfRel());

        book.setId(entity.getId());
        book.setTitle(entity.getTitle());
        book.setAuthor(entity.getAuthor());
        book.setTitleSlug(entity.getTitleSlug());
        book.setAuthorSlug(entity.getAuthorSlug());
        book.setIsbn13(entity.getIsbn13());
        book.setIsbn10(entity.getIsbn10());
        book.setFormat(entity.getFormat());
        book.setPubDate(entity.getPubDate());
        book.setSubjects(entity.getSubjects());
        book.setPages(entity.getPages());
        book.setOverview(entity.getOverview());
        book.setSynopsis(entity.getSynopsis());
        return book;
    }

    @Override
    public CollectionModel<Book> toCollectionModel(Iterable<? extends BookEntity> entities) {
        CollectionModel<Book> bookModels = super.toCollectionModel(entities);
        bookModels.add(linkTo(methodOn(BookController.class).getAllBooks()).withSelfRel());
        return super.toCollectionModel(entities);
    }

    private List<Book> toBookModel(List<BookEntity> bookEntities){
        if (bookEntities.isEmpty())
            return Collections.emptyList();
        return bookEntities.stream()
                .map(bookEntity -> Book.builder()
                .id(bookEntity.getId())
                        .title(bookEntity.getTitle())
                        .author(bookEntity.getAuthor())
                        .titleSlug(bookEntity.getTitleSlug())
                        .authorSlug(bookEntity.getAuthorSlug())
                        .isbn13(bookEntity.getIsbn13())
                        .isbn10(bookEntity.getIsbn10())
                        .format(bookEntity.getFormat())
                        .pubDate(bookEntity.getPubDate())
                        .subjects(bookEntity.getSubjects())
                        .pages(bookEntity.getPages())
                        .overview(bookEntity.getOverview())
                        .synopsis(bookEntity.getSynopsis()).build().add(linkTo(methodOn(BookController.class).showOneBook(bookEntity.getId())).withSelfRel())).collect(Collectors.toList());
    }


}
