package com.ferreteria.repositories;

import com.ferreteria.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {

    @Override
    Optional<CategoryEntity> findById(Long aLong);
}
