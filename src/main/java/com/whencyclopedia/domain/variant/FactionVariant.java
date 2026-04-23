package com.whencyclopedia.domain.variant;

import com.whencyclopedia.domain.enums.ClimateStatus;
import com.whencyclopedia.domain.identity.GameVersion;
import com.whencyclopedia.domain.identity.Faction;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "faction_variant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FactionVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faction_id", nullable = false)
    private Faction faction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_version_id", nullable = false)
    private GameVersion gameVersion;

    private String banner;
    private String leader;

    @Column(columnDefinition = "TEXT")
    private String factionEffect;

    @Column(nullable = false)
    private Boolean isHorde;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClimateStatus climateChaoticWasteland;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClimateStatus climateFrozen;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClimateStatus climateMountain;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClimateStatus climateTemperate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClimateStatus climateTemperateIsland;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClimateStatus climateMagicalForest;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClimateStatus climateJungle;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClimateStatus climateSavannah;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClimateStatus climateDesert;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClimateStatus climateWasteland;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClimateStatus climateOcean;
}