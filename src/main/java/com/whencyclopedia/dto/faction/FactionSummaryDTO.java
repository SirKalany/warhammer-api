package com.whencyclopedia.dto.faction;

public record FactionSummaryDTO(
        Long id,
        Long raceId,
        String name,
        String slug,
        String banner
) {}