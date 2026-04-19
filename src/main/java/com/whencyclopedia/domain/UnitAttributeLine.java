package com.whencyclopedia.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "unit_attribute_line")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnitAttributeLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id", nullable = false)
    private Unit unit;

    @Column(nullable = false)
    private Short position;

    @Column(nullable = false)
    private String content;
}