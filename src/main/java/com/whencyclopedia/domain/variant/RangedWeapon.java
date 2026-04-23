package com.whencyclopedia.domain.variant;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ranged_weapon")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RangedWeapon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_variant_id", nullable = false)
    private UnitVariant unitVariant;

    @Column(nullable = false)
    private String weaponSlot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imbuement_variant_id")
    private ImbuementVariant imbuementVariant;

    private Integer ammunition;
    private Integer range;
    private Integer missileBaseDamage;
    private Integer missileApDamage;
    private Integer missileBonusVsLarge;
    private Integer missileBonusVsInfantry;
    private Integer explosionDamage;
    private Integer explosionApDamage;
    private BigDecimal detonationRadius;
    private Integer shotsPerVolley;
    private Integer projectileNumber;
    private String projectileCategory;
    private BigDecimal reloadTime;
    private BigDecimal totalAccuracy;
    private BigDecimal calibrationDistance;
    private BigDecimal calibrationArea;
    private String penetrationSizeCap;
    private Integer maxPenetration;
}