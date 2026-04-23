package com.whencyclopedia.domain.variant;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

import com.whencyclopedia.domain.identity.Ability;
import com.whencyclopedia.domain.identity.GameVersion;

@Entity
@Table(name = "ability_variant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AbilityVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ability_id", nullable = false)
    private Ability ability;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_version_id", nullable = false)
    private GameVersion gameVersion;

    private String description;
    private String effect;
    private String target;
    private BigDecimal range;
    private BigDecimal radius;
    private BigDecimal duration;
    private BigDecimal cooldown;
    private Integer affectedUnits;
    private Integer uses;
    private String conditions;
    private Integer baseDamage;
    private Integer explosiveDamage;
    private BigDecimal damagePerSecond;
    private BigDecimal movementSpeed;
}