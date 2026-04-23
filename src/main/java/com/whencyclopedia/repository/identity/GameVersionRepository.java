package com.whencyclopedia.repository.identity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whencyclopedia.domain.identity.GameVersion;

import java.util.Optional;

@Repository
public interface GameVersionRepository extends JpaRepository<GameVersion, Long> {
    Optional<GameVersion> findBySlug(String slug);
}