package com.petrapulse.PetraPulse.repositories;

import com.petrapulse.PetraPulse.entities.UsersDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailsJpaRepository extends JpaRepository<UsersDetailsEntity,Long> {

    Optional<UsersDetailsEntity> findByUsername(String username);

}
