package com.whencyclopedia.domain.identity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "game_version")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String slug;

    private String icon;
}