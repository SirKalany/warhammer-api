package com.whencyclopedia.domain.variant;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;

import com.whencyclopedia.domain.enums.RangedMode;
import com.whencyclopedia.domain.enums.UnitCategoryType;
import com.whencyclopedia.domain.enums.UnitRole;
import com.whencyclopedia.domain.identity.GameVersion;
import com.whencyclopedia.domain.identity.Unit;
import com.whencyclopedia.domain.identity.UnitAttribute;

@Entity
@Table(name = "unit_variant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnitVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id", nullable = false)
    private Unit unit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_version_id", nullable = false)
    private GameVersion gameVersion;

    // Display
    private String picture;

    @Column(nullable = false)
    private Short tier;

    private String category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UnitCategoryType categoryType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UnitRole role;

    @Column(columnDefinition = "TEXT")
    private String description;

    // Overview
    @Column(nullable = false)
    private String size;

    private Integer entities;
    private BigDecimal mass;
    private Integer campaignCost;
    private Integer baseUpkeep;
    private Integer multiplayerCost;

    // Survivability
    private Integer health;
    private Integer healthPerEntity;
    private Integer barrier;
    private Integer armour;
    private BigDecimal parry;
    private BigDecimal wardSave;
    private BigDecimal physicalResistance;
    private BigDecimal missileResistance;
    private BigDecimal spellResistance;
    private BigDecimal fireResistance;
    private Integer leadership;

    // Mobility
    private Integer speed;
    private Integer chargeSpeed;

    // Melee
    private Integer meleeAttack;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "melee_imbuement_id")
    private ImbuementVariant meleeImbuement;

    private BigDecimal attackInterval;
    private Boolean highThreat;
    private String splashTargetSize;
    private Integer splashMaxAttacks;
    private Integer meleeDefense;
    private Integer weaponStrength;
    private Integer meleeBaseDamage;
    private Integer meleeApDamage;
    private Integer meleeBonusVsLarge;
    private Integer meleeBonusVsInfantry;
    private Integer chargeBonus;

    // Ranged
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RangedMode rangedMode;

    @OneToMany(mappedBy = "unitVariant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RangedWeapon> rangedWeapons;

    // Building requirements
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unlock_building_id")
    private BuildingVariant unlockBuilding;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "allow_building_id")
    private BuildingVariant allowBuilding;

    // Attribute lines
    @OneToMany(mappedBy = "unitVariant", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("position ASC")
    private List<UnitVariantAttributeLine> attributeLines;

    // Unit attributes
    @ManyToMany
    @JoinTable(
        name = "unit_variant_unit_attribute",
        joinColumns = @JoinColumn(name = "unit_variant_id"),
        inverseJoinColumns = @JoinColumn(name = "unit_attribute_id")
    )
    private List<UnitAttribute> unitAttributes;

    // Active abilities
    @ManyToMany
    @JoinTable(
        name = "unit_variant_ability",
        joinColumns = @JoinColumn(name = "unit_variant_id"),
        inverseJoinColumns = @JoinColumn(name = "ability_variant_id")
    )
    private List<AbilityVariant> abilities;

    // Passive abilities
    @ManyToMany
    @JoinTable(
        name = "unit_variant_passive_ability",
        joinColumns = @JoinColumn(name = "unit_variant_id"),
        inverseJoinColumns = @JoinColumn(name = "ability_variant_id")
    )
    private List<AbilityVariant> passiveAbilities;

    // Spells
    @ManyToMany
    @JoinTable(
        name = "unit_variant_spell",
        joinColumns = @JoinColumn(name = "unit_variant_id"),
        inverseJoinColumns = @JoinColumn(name = "spell_variant_id")
    )
    private List<SpellVariant> spells;

    // Items
    @ManyToMany
    @JoinTable(
        name = "unit_variant_item",
        joinColumns = @JoinColumn(name = "unit_variant_id"),
        inverseJoinColumns = @JoinColumn(name = "item_variant_id")
    )
    private List<ItemVariant> items;
}