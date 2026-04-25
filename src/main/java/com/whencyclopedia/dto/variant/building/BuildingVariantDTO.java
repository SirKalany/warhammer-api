package com.whencyclopedia.dto.variant.building;

import com.whencyclopedia.domain.enums.BuildingCategory;

public record BuildingVariantDTO(
        Long id,
        Long buildingId,
        String name,
        String slug,
        BuildingCategory category,
        Long raceId,
        Long buildingChainId,
        Long gameVersionId,
        String picture,
        Short tier,
        String effect,
        Integer cost,
        String requirements
) {}