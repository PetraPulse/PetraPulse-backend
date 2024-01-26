package com.petrapulse.PetraPulse.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CarBookingsEntity {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UsersDetailsEntity usersDetailsEntity;
    @ManyToOne
    @JoinColumn(name = "car_id")
    private CarEntity carEntity;
}
