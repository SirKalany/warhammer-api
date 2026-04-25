package com.whencyclopedia.dto.variant.unit;

import com.whencyclopedia.domain.enums.UnitCategoryType;
import com.whencyclopedia.domain.enums.UnitRole;

public record UnitVariantSummaryDTO(
        Long id,
        Long unitId,
        String name,
        String slug,
        Long gameVersionId,
        String picture,
        Short tier,
        String category,
        UnitCategoryType categoryType,
        UnitRole role
) {}