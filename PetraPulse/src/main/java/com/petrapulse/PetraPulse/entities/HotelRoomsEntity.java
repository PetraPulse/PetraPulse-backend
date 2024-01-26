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

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HotelRoomsEntity {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long hotelRooms_id;
    private String roomsType;
    private float hotelRoomsPrice;
    private boolean availableRoom;
    private int roomCapacity;
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
    @JoinColumn(name="hotel_id")
    private HotelEntity hotelName;
    @OneToOne(mappedBy = "bookedHotelRooms")
    private HotelBookingsEntity roomBooking;
}
