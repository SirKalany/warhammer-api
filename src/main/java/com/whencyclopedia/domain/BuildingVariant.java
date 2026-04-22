package com.whencyclopedia.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "building_variant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BuildingVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id", nullable = false)
    private Building building;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_version_id", nullable = false)
    private GameVersion gameVersion;

    private String picture;
    private Short tier;

    @Column(columnDefinition = "TEXT")
    private String effect;

    private Integer cost;
    private String requirements;

    @OneToMany(mappedBy = "buildingVariant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BuildingVariantGarrison> garrison;
}