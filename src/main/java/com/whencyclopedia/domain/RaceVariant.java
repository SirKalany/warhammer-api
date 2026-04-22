package com.whencyclopedia.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "race_variant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RaceVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "race_id", nullable = false)
    private Race race;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_version_id", nullable = false)
    private GameVersion gameVersion;
}