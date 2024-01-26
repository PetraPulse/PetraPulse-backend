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
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttractionEntity {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long attraction_id;
    private String attractionName;
    private String attractionDescription;
    private String attractionPlace;
    private boolean availability;
    private String attractionCompany;
    private float attractionPrice;
    private int duration;
    private String attractionImage;
    private String attractionType;
    @CreatedBy
    private String createdBy;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @CreatedDate
    private LocalDate createdAt;
    @LastModifiedBy
    private String updatedBy;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @LastModifiedDate
    private LocalDate updatedAt;
    /* owning side: is the side that has the foreign key column that is used to establish the relationship. it typically contains the @JoinColumn annotation.
    *  Inverse Side: is the side that does not manage the foreign key column in the database. It is specified using the mappedBy attribute The mappedBy attribute is not used in @ManyToOne relationships. It is used in the inverse side of @OneToMany or @OneToOne relationships.*/
    @OneToMany(mappedBy = "attraction")
    private List<AttractionsBookingEntity> attractionsBooking;
    @OneToOne(mappedBy = "tripAttractionName")
    private TripDetailsEntity trip;

}
