package com.library.books.repository;

import com.library.books.entity.Exemplaire;

public interface ExemplaireRepositoryCustom {
    Exemplaire findOneByBarcode(Integer barcode);
}
