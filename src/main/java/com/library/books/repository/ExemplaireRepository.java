package com.library.books.repository;

import com.library.books.entity.Exemplaire;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "exemplaires")
public interface ExemplaireRepository extends PagingAndSortingRepository<Exemplaire, Integer>, ExemplaireRepositoryCustom {
}
