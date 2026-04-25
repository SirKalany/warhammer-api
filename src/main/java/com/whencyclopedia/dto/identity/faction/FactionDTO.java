package com.whencyclopedia.dto.identity.faction;

public record FactionDTO(
        Long id,
        String name,
        String slug,
        Long raceId
) {}