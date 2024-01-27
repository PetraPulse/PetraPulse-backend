package com.petrapulse.PetraPulse.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
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
@Table(name = "appusersentity")
@AllArgsConstructor
@Data
@Builder
public class AppUsersEntity implements UserDetails {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Setter(AccessLevel.NONE)
     @Column(name = "user_details_id")
     private Long id;
     private String username;
     private String email;
     private String password;
     private String confirmPassword;
     private String country;
     @DateTimeFormat(pattern = "yyyy-MM-dd")
     @Column(name = "date_of_birth")
     private LocalDate dateOfBirth;
     @CreatedBy
     @Column(name = "created_by")
     private String createdBy;
     @CreationTimestamp
     @DateTimeFormat(pattern = "yyyy-MM-dd")
     @Column(name = "created_at")
     private LocalDate createdAt;
     @LastModifiedBy
     @Column(name = "updated_by")
     private String updatedBy;
     @LastModifiedDate
     @DateTimeFormat(pattern = "yyyy-MM-dd")
     @Column(name = "updated_at")
     private LocalDate updatedAt;
     @PrePersist
     protected void onCreate() {
          this.createdAt = LocalDate.now();
          this.createdBy = (this.createdBy != null) ? this.createdBy : username;
     }
     @PreUpdate
     protected void onUpdate() {
          this.updatedAt = LocalDate.now();
          this.updatedBy=(this.updatedBy!=null)? this.updatedBy: username;
     }
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


     //this method translates the user's role into a collection of GrantedAuthority objects, which Spring Security uses to perform access control checks.
     // Each SimpleGrantedAuthority represents a role assigned to the user.
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

     public AppUsersEntity() {
          this.createdBy = "system";
          this.createdAt = LocalDate.now();
     }
     //JPA lifecycle callback annotations that are used in entities to perform actions automatically before an entity is persisted (saved)
     // or updated in the database. These callbacks are useful for tasks such as setting default values, updating timestamps, or performing other
     // actions before database operations.
     @PrePersist
     protected void onCreate() {
          this.createdAt = LocalDate.now();
          this.createdBy = (this.createdBy != null) ? this.createdBy : "system";
     }

     // JPA PreUpdate callback to set updatedAt before update
     @PreUpdate
     protected void onUpdate() {
          this.updatedAt = LocalDate.now();
     }
}
