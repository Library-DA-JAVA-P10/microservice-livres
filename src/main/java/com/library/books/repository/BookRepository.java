package com.library.books.repository;

import com.library.books.entity.Book;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "books")
public interface BookRepository extends PagingAndSortingRepository<Book, Integer> {
}
