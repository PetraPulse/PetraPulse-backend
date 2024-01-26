package com.petrapulse.PetraPulse.entities;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Setter(AccessLevel.NONE)
     private Long id;
     private String username;
     private String email;
     private String password;
     private String country;
     @DateTimeFormat(pattern = "yyyy-MM-dd")
     private LocalDate dateOfBirth;
     @CreatedBy
     private String createdBy;
     @CreatedDate
     @DateTimeFormat(pattern = "yyyy-MM-dd")
     private LocalDate createdAt;
     @LastModifiedBy
     private String updatedBy;
     @LastModifiedDate
     @DateTimeFormat(pattern = "yyyy-MM-dd")
     private LocalDate updatedAt;
     @OneToMany(mappedBy = "user")
     private List<TokenEntity> tokens;

     @OneToOne
     @JoinColumn(name = "role_id")
     private RoleTypesEntity role;
     @OneToMany(mappedBy="attractionUser")
     private List<AttractionsBookingEntity> attractionsBookings;
     @OneToMany(mappedBy="carUser")
     private List<CarBookingsEntity> carsBookings;
     @OneToMany(mappedBy="hotelUser")
     private List<HotelBookingsEntity> hotelBookings;
     @OneToMany(mappedBy="tripUser")
     private List<TripsBookingsEntity> tripBookings;


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
}
