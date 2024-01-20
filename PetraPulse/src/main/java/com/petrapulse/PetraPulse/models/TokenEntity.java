package com.petrapulse.PetraPulse.models;

import com.petrapulse.PetraPulse.enums.TokenType;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TokenEntity {
    @Id
    @GeneratedValue
    public Integer id;

    @Column(unique = true)
    public String token;

    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.BEARER;

    public boolean revoked;

    public boolean expired;

    //Many tokens are associated with one user for security reasons / Lazy loading: means that the associated UsersDetailsEntity will be loaded from the database only when it is explicitly accessed
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public UsersDetailsEntity user;
}
