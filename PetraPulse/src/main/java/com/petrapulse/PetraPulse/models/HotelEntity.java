package com.petrapulse.PetraPulse.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class HotelEntity {
    @Id
    @GeneratedValue
    private Long hotel_id;
    private String hotelName;
    private String hotelLocation;
    private String hotelImage;
    private String hotelDescription;
    private boolean hotelAvailability;
    private int hotelRating;
}
