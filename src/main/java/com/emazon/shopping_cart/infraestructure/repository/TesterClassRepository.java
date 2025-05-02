package com.emazon.shopping_cart.infraestructure.repository;

import com.emazon.shopping_cart.infraestructure.entities.TesterClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TesterClassRepository extends JpaRepository<TesterClassEntity, Long> {

    Optional<TesterClassEntity> findByUuid(String uuid);
}