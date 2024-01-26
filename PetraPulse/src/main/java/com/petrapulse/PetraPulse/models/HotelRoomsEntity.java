package com.petrapulse.PetraPulse.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HotelRoomsEntity {
    @Id
    @GeneratedValue
    private Long hotelRooms_id;
    private String roomsType;
    private int bedsNumber;
    private float hotelRoomsPrice;
    private boolean availableRoom;
    private boolean breakfast;
    private int roomCapacity;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UsersDetailsEntity usersDetailsEntity;
    @OneToOne(mappedBy = "hotelRoomsEntity")
    private HotelBookingsEntity hotelBookingsEntity;
}
