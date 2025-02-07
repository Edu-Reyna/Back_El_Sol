package com.ferreteria.repositories;

import com.ferreteria.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findByDateBetween(LocalDateTime start, LocalDateTime end);

    List<OrderEntity> findByUserId(Long userId);

}
