package com.whencyclopedia.dto.building;

import com.whencyclopedia.domain.BuildingCategory;

public record BuildingDTO(
        Long id,
        Long raceId,
        Long buildingChainId,
        String name,
        String slug,
        String picture,
        Short tier,
        BuildingCategory category,
        String effect,
        Integer cost,
        String requirements
) {}