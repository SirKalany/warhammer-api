package com.whencyclopedia.domain.identity;

import com.whencyclopedia.domain.enums.BuildingCategory;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "building")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String slug;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "race_id", nullable = false)
    private Race race;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_chain_id")
    private BuildingChain buildingChain;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BuildingCategory category;
}