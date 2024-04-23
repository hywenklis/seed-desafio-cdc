package com.jornada.dev.eficiente.desafio1.domains.author.repositories;

import com.jornada.dev.eficiente.desafio1.domains.author.entities.AuthorEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, UUID> {

    Optional<AuthorEntity> findByEmail(String email);
}
