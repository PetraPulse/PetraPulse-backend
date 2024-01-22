package com.petrapulse.PetraPulse.models;

import com.petrapulse.PetraPulse.enums.Roles;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleTypesEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private Roles roleName;
    public RoleTypesEntity(Long id) {
        this.id=id;
    }
}
