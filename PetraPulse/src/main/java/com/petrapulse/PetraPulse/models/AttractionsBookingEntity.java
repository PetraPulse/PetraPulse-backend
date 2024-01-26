package com.petrapulse.PetraPulse.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AttractionsBookingEntity {
    @Id
    @GeneratedValue
    private Long attractionBooking_id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UsersDetailsEntity usersDetailsEntity;
    @ManyToOne
    @JoinColumn(name = "attraction_id")
    private AttractionEntity attractionEntity;
}
