package com.jornada.dev.eficiente.desafio1.domains.address.country.repositories;

import com.jornada.dev.eficiente.desafio1.domains.address.country.entities.CountryEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, UUID> {

    Optional<CountryEntity> findByName(String name);
}
