package com.petrapulse.PetraPulse.repositories;

import com.petrapulse.PetraPulse.models.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<TokenEntity, Integer> {
//    @Query(value = "select t from TokenEntity t inner join UserDetailsEntity u on t.user.id = u.id where u.id = :id and (t.expired = false or t.revoked = false)")
//    List<TokenEntity> findAllValidTokenByUser(Integer id);

    Optional<TokenEntity> findByToken(String token);
}
