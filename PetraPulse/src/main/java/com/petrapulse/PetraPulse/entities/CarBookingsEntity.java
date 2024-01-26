package com.petrapulse.PetraPulse.entities;

import com.petrapulse.PetraPulse.enums.BookingStatus;
import com.petrapulse.PetraPulse.enums.CarBookingExtras;
import com.petrapulse.PetraPulse.enums.HotelRoomExtras;
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
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CarBookingsEntity {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickUpDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate  dropOffDate;
    private BookingStatus bookingStatus;
    private CarBookingExtras extras;
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
    @JoinColumn(name="car_id")
    private CarEntity car;
    @ManyToOne
    @JoinColumn(name="user_id")
    private UsersDetailsEntity carUser;
}
