package com.jornada.dev.eficiente.desafio1.domains.repositories;

import com.jornada.dev.eficiente.desafio1.domains.entities.BookEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, UUID> {
    Optional<BookEntity> findByTitle(String title);

    Optional<BookEntity> findByIsbn(String isbn);

}
