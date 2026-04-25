package com.whencyclopedia.dto.version;

public record GameVersionDTO(
        Long id,
        String name,
        String slug,
        String icon
) {}