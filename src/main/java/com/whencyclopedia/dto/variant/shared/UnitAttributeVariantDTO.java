package com.whencyclopedia.dto.variant.shared;

public record UnitAttributeVariantDTO(
        Long id,
        Long unitAttributeId,
        String name,
        Long gameVersionId,
        String description
) {}