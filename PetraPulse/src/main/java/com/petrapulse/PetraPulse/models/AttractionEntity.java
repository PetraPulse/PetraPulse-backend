package com.petrapulse.PetraPulse.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttractionEntity {
    @Id
    @GeneratedValue
    private Long attraction_id;
    private String attractionName;
    private String attractionDescription;
    private String attractionPlace;
    private boolean availability;
    private String attractionCompany;
    private float attractionPrice;
    private int duration;
    private String attractionImage;
}
