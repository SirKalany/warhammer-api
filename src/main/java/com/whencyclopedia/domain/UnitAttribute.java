package com.whencyclopedia.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "unit_attribute")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnitAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;
}