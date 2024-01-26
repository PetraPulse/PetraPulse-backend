package com.petrapulse.PetraPulse.entities;
import com.petrapulse.PetraPulse.enums.BookingStatus;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TripsBookingsEntity {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate  endDate;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime tripTime;
    private BookingStatus bookingStatus;
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
    @ManyToOne
    @JoinColumn(name="user_id")
    private UsersDetailsEntity tripUser;
    @ManyToOne
    @JoinColumn(name="trip_id")
    private TripDetailsEntity trip;

}
