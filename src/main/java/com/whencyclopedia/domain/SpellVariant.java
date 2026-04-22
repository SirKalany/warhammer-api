package com.whencyclopedia.domain;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "spell_variant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpellVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spell_id", nullable = false)
    private Spell spell;

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
    private Integer cost;
    private BigDecimal miscastChance;
    private String overcastEffect;
    private String overcastTarget;
    private BigDecimal overcastRange;
    private BigDecimal overcastRadius;
    private BigDecimal overcastDuration;
    private BigDecimal overcastCooldown;
    private Integer overcastAffectedUnits;
    private Integer overcastBaseDamage;
    private Integer overcastExplosiveDamage;
    private BigDecimal overcastDamagePerSecond;
    private BigDecimal overcastMovementSpeed;
    private Integer overcastCost;
    private BigDecimal overcastMiscastChance;
}