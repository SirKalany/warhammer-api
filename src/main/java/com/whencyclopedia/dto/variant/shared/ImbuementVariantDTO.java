package com.whencyclopedia.dto.variant.shared;

public record ImbuementVariantDTO(
        Long id,
        Long imbuementId,
        String name,
        String slug,
        Long gameVersionId,
        String description,
        String icon
) {}