package com.petrapulse.PetraPulse.entities;

import com.petrapulse.PetraPulse.enums.BookingStatus;
import com.petrapulse.PetraPulse.enums.HotelRoomExtras;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class HotelBookingsEntity {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate  endDate;
    private BookingStatus bookingStatus;
    private HotelRoomExtras extras;
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

    @ManyToOne
    @JoinColumn(name="user_id")
    private AppUsersEntity hotelUser;
    @OneToOne
    @JoinColumn(name="room_id")
    private HotelRoomsEntity bookedHotelRooms;
}
