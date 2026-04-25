package com.whencyclopedia.dto.identity.unit;

import java.util.List;

public record UnitSummaryDTO(
        Long id,
        String name,
        String slug,
        List<Long> raceIds
) {}