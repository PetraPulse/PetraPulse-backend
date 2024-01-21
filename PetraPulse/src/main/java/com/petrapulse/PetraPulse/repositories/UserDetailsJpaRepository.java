package com.petrapulse.PetraPulse.repositories;

import com.petrapulse.PetraPulse.models.UsersDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailsJpaRepository extends JpaRepository<UsersDetailsEntity,Integer> {

    Optional<UsersDetailsEntity> findByUsername(String username);

}
