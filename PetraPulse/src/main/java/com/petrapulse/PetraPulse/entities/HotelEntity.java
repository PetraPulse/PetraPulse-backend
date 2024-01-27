package com.petrapulse.PetraPulse.entities;

import javax.persistence.*;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HotelEntity {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long hotel_id;
    private String hotelName;
    private String hotelLocation;
    private String hotelImage;
    private String hotelDescription;
    private boolean hotelAvailability;
    private int hotelRating;
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
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDate.now();
    }
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDate.now();
    }

    @OneToOne(mappedBy = "tripHotelName")
    private TripDetailsEntity trip;
    @OneToMany(mappedBy = "hotelName")
    private List<HotelRoomsEntity> hotelRooms;
}
