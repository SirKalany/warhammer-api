package com.whencyclopedia.dto.unit;

import com.whencyclopedia.domain.UnitCategoryType;
import com.whencyclopedia.domain.UnitRole;

public record UnitSummaryDTO(
        Long id,
        Long raceId,
        String name,
        String slug,
        String picture,
        Short tier,
        String category,
        UnitRole role,
        UnitCategoryType categoryType
) {}