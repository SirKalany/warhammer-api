package com.whencyclopedia.domain;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ability")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String slug;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AbilityType type;

    private String effect;
    private String target;

    private BigDecimal range;
    private BigDecimal radius;
    private BigDecimal duration;
    private BigDecimal cooldown;

    private Integer affectedUnits;
    private Integer uses;

    private String conditions;

    // Offensive fields
    private Integer baseDamage;
    private Integer explosiveDamage;
    private BigDecimal damagePerSecond;

    // Movement types (Vortex, Wind, Breath)
    private BigDecimal movementSpeed;
}