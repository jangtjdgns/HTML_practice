package com.maepo.church.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "occasion")
public class Occasion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String occasionType;
}
