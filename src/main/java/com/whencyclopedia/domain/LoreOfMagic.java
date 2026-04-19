package com.whencyclopedia.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "lore_of_magic")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoreOfMagic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String slug;

    private String description;

    @OneToMany(mappedBy = "lore", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Spell> spells;
}