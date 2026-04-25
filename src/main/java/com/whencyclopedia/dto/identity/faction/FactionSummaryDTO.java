package com.whencyclopedia.dto.identity.faction;

public record FactionSummaryDTO(
        Long id,
        String name,
        String slug,
        Long raceId
) {}