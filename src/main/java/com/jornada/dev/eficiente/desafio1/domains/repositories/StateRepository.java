package com.jornada.dev.eficiente.desafio1.domains.repositories;

import com.jornada.dev.eficiente.desafio1.domains.entities.CategoryEntity;
import com.jornada.dev.eficiente.desafio1.domains.entities.StateEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<StateEntity, UUID> {

    Optional<StateEntity> findByName(String name);
}
