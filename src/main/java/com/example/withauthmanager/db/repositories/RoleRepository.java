package com.example.withauthmanager.db.repositories;

import com.example.withauthmanager.db.models.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    long ID_OF_DEFAULT_ROLE = 1;

    Optional<RoleEntity> findById(Long aLong);
}
