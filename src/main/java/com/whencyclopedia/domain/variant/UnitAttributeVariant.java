package com.whencyclopedia.domain.variant;

import com.whencyclopedia.domain.identity.GameVersion;
import com.whencyclopedia.domain.identity.UnitAttribute;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "unit_attribute_variant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnitAttributeVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_attribute_id", nullable = false)
    private UnitAttribute unitAttribute;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_version_id", nullable = false)
    private GameVersion gameVersion;

    private String description;
}