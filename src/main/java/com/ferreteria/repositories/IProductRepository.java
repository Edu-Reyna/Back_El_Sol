package com.ferreteria.repositories;

import com.ferreteria.entities.ProductEntity;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findByStockLessThanEqual(int stockIsLessThan);

    List<ProductEntity> findAllByStatus(boolean status);
}
