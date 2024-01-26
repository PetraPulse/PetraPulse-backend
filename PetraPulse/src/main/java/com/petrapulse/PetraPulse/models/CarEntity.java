package com.petrapulse.PetraPulse.models;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarEntity {
    @Id
    @GeneratedValue
    private Long car_id;
    private int year;
    private boolean availability;
    private String model;
    private float miles;
    private String carCompany;
    private String carImage;
    private int seatNumber;
    private float price;
    private String trunkSize;
    @OneToMany(mappedBy = "carEntity")
    private List<CarBookingsEntity>carBookingsEntities;

}
