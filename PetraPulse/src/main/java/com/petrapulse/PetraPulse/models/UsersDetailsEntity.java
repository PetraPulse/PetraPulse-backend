package com.petrapulse.PetraPulse.models;

import com.petrapulse.PetraPulse.enums.Roles;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UsersDetailsEntity implements UserDetails {
     @Id
     @GeneratedValue
     @Setter(AccessLevel.NONE)
     private int id;
     private String username;
     private String email;
     private String password;
     private String address;
     private int phoneNumber;
     @OneToMany(mappedBy = "user")
     private List<TokenEntity> tokens;

     @OneToOne
     @JoinColumn(name = "role_id")
     private RoleTypesEntity role;

     //this method translates the user's role into a collection of GrantedAuthority objects, which Spring Security uses to perform access control checks. Each SimpleGrantedAuthority represents a role assigned to the user.
     @Override
     public Collection<? extends GrantedAuthority> getAuthorities() {
          RoleTypesEntity role = getRole();
          List<SimpleGrantedAuthority> authorities = new ArrayList<>();

          if (role != null && role.getRoleName() != null) {
               authorities.add(new SimpleGrantedAuthority(role.getRoleName().name()));
          }

          return authorities;
     }

     @Override
     public String getUsername() {
          return username;
     }

     @Override
     public boolean isAccountNonExpired() {
          return true;
     }

     @Override
     public boolean isAccountNonLocked() {
          return true;
     }

     @Override
     public boolean isCredentialsNonExpired() {
          return true;
     }

     @Override
     public boolean isEnabled() {
          return true;
     }

     public UsersDetailsEntity(String username, String email, String password, String address, int phoneNumber) {
          this.username = username;
          this.email = email;
          this.password = password;
          this.address = address;
          this.phoneNumber = phoneNumber;
     }

}
