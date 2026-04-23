package com.whencyclopedia.domain.identity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "faction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Faction {

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
}