package com.whencyclopedia.domain.identity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "imbuement")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Imbuement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String slug;
}