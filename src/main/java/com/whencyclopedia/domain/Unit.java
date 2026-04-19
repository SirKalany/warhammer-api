package com.whencyclopedia.domain;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "unit")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "race_id", nullable = false)
    private Race race;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String slug;

    private String picture;

    @Column(nullable = false)
    private Short tier;

    private String category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UnitRole role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UnitCategoryType categoryType;

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

    // Melee Combat
    private Integer meleeAttack;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "melee_imbuement_id")
    private Imbuement meleeImbuement;

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

    @OneToMany(mappedBy = "unit", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RangedWeapon> rangedWeapons;

    // Building requirements
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unlock_building_id")
    private Building unlockBuilding;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "allow_building_id")
    private Building allowBuilding;

    // Attribute lines (strength/weakness descriptions)
    @OneToMany(mappedBy = "unit", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("position ASC")
    private List<UnitAttributeLine> attributeLines;

    // Unit Attributes (Flying, Daemonic, etc.)
    @ManyToMany
    @JoinTable(
        name = "unit_unit_attribute",
        joinColumns = @JoinColumn(name = "unit_id"),
        inverseJoinColumns = @JoinColumn(name = "unit_attribute_id")
    )
    private List<UnitAttribute> unitAttributes;

    // Active Abilities
    @ManyToMany
    @JoinTable(
        name = "unit_ability",
        joinColumns = @JoinColumn(name = "unit_id"),
        inverseJoinColumns = @JoinColumn(name = "ability_id")
    )
    private List<Ability> abilities;

    // Passive Abilities
    @ManyToMany
    @JoinTable(
        name = "unit_passive_ability",
        joinColumns = @JoinColumn(name = "unit_id"),
        inverseJoinColumns = @JoinColumn(name = "ability_id")
    )
    private List<Ability> passiveAbilities;

    // Spells
    @ManyToMany
    @JoinTable(
        name = "unit_spell",
        joinColumns = @JoinColumn(name = "unit_id"),
        inverseJoinColumns = @JoinColumn(name = "spell_id")
    )
    private List<Spell> spells;

    // Items (unit-specific restrictions)
    @ManyToMany
    @JoinTable(
        name = "unit_item",
        joinColumns = @JoinColumn(name = "unit_id"),
        inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> items;
}