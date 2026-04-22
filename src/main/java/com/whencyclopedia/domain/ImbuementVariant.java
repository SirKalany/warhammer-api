package com.whencyclopedia.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "imbuement_variant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImbuementVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imbuement_id", nullable = false)
    private Imbuement imbuement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_version_id", nullable = false)
    private GameVersion gameVersion;

    private String description;
    private String icon;
}