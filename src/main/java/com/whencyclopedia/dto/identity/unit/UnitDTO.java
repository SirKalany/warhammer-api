package com.whencyclopedia.dto.identity.unit;

import java.util.List;

public record UnitDTO(
        Long id,
        String name,
        String slug,
        List<Long> raceIds
) {}