package com.petrapulse.PetraPulse.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarEntity {
    @Id
    @GeneratedValue
    private Long car_id;
    private int modelYear;
    private boolean availability;
    private String model;
    private float miles;
    private String carCompany;
    private String carImage;
    private int seatNumber;
    private float price;
    private String trunkSize;
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

    @OneToOne(mappedBy = "tripCarName")
    private TripDetailsEntity trip;
    @OneToOne(mappedBy = "car")
    private CarBookingsEntity carBooking;
}
