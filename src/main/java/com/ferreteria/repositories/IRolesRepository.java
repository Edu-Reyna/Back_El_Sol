package com.ferreteria.repositories;

import com.ferreteria.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRolesRepository extends JpaRepository<RoleEntity,Long> {

    Optional<RoleEntity> findRoleEntityById(Long id);
}
