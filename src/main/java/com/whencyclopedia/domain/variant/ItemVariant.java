package com.whencyclopedia.domain.variant;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

import com.whencyclopedia.domain.identity.GameVersion;
import com.whencyclopedia.domain.identity.Item;
import com.whencyclopedia.domain.identity.Race;

@Entity
@Table(name = "item_variant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_version_id", nullable = false)
    private GameVersion gameVersion;

    private String picture;

    @Column(columnDefinition = "TEXT")
    private String effect;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "race_id")
    private Race race;

    @ManyToMany
    @JoinTable(
        name = "item_variant_ability",
        joinColumns = @JoinColumn(name = "item_variant_id"),
        inverseJoinColumns = @JoinColumn(name = "ability_variant_id")
    )
    private List<AbilityVariant> abilities;

    @ManyToMany
    @JoinTable(
        name = "item_variant_spell",
        joinColumns = @JoinColumn(name = "item_variant_id"),
        inverseJoinColumns = @JoinColumn(name = "spell_variant_id")
    )
    private List<SpellVariant> spells;
}