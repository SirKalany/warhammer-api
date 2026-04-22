package com.whencyclopedia.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "unit_variant_attribute_line")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnitVariantAttributeLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_variant_id", nullable = false)
    private UnitVariant unitVariant;

    @Column(nullable = false)
    private Short position;

    @Column(nullable = false)
    private String content;
}