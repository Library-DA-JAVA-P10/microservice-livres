package com.library.books.controller;

import com.library.books.assemblers.BookModelAssembler;
import com.library.books.entity.BookEntity;
import com.library.books.model.Book;
import com.library.books.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookModelAssembler bookModelAssembler;


    @Autowired
    private PagedResourcesAssembler<BookEntity> pagedResourcesAssembler;

    @GetMapping("/book-list")
    public ResponseEntity<PagedModel<Book>> getAllBooks(Pageable pageable){
        Page<BookEntity> books = bookRepository.findAll(pageable);
        PagedModel<Book> collModel = pagedResourcesAssembler.toModel(books, bookModelAssembler);

        return new ResponseEntity<>(collModel,HttpStatus.OK);
    }
    @GetMapping("/books")
    public ResponseEntity<CollectionModel<Book>> getAllBooks()
    {
        List<BookEntity> bookEntities = (List<BookEntity>) bookRepository.findAll();

        return new ResponseEntity<>(
                bookModelAssembler.toCollectionModel(bookEntities),
                HttpStatus.OK);
    }




    @GetMapping(value = "/book/{id}")
    public ResponseEntity<Book> showOneBook(@PathVariable int id) {
        return bookRepository.findById(id)
                .map(bookModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
