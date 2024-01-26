package com.petrapulse.PetraPulse.repositories;

import com.petrapulse.PetraPulse.entities.AppUsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailsJpaRepository extends JpaRepository<AppUsersEntity,Long> {

    Optional<AppUsersEntity> findByUsername(String username);

}
