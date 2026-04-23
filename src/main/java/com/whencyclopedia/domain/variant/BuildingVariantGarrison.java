package com.whencyclopedia.domain.variant;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "building_variant_garrison")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BuildingVariantGarrison {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_variant_id", nullable = false)
    private BuildingVariant buildingVariant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_variant_id", nullable = false)
    private UnitVariant unitVariant;

    @Column(nullable = false)
    private Integer quantity;
}