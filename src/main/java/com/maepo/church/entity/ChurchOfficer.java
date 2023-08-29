package com.maepo.church.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "churchOfficer")
public class ChurchOfficer {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    private String roleDescription;
}
