package com.maepo.church.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "condolence")
public class Condolence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String condolenceName;
}
