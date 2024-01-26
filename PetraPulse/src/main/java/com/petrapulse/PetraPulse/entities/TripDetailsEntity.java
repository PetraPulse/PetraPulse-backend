package com.petrapulse.PetraPulse.entities;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripDetailsEntity {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;
    private String name;
    private String location;
    private String duration;
    private int price;
    private boolean availability;
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
    @OneToOne
    @JoinColumn(name="hotel_id")
    private HotelEntity tripHotelName;
    @OneToOne
    @JoinColumn(name="car_id")
    private CarEntity tripCarName;
    @OneToOne
    @JoinColumn(name="attraction_id")
    private AttractionEntity tripAttractionName;
    @OneToMany(mappedBy = "trip")
    private List<TripsBookingsEntity> tripsBookings;


}
