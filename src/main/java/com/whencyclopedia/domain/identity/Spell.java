package com.whencyclopedia.domain.identity;

import com.whencyclopedia.domain.enums.AbilityType;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "spell")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Spell {

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lore_id", nullable = false)
    private LoreOfMagic lore;
}