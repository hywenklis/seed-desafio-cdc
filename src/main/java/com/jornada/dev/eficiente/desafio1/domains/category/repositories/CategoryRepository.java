package com.jornada.dev.eficiente.desafio1.domains.category.repositories;

import com.jornada.dev.eficiente.desafio1.domains.category.entities.CategoryEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {

    Optional<CategoryEntity> findByName(String name);
}
