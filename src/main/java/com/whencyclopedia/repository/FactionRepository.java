package com.whencyclopedia.repository;

import com.whencyclopedia.domain.Faction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface FactionRepository extends JpaRepository<Faction, Long> {
    Optional<Faction> findBySlug(String slug);
    List<Faction> findByRaceId(Long raceId);
    List<Faction> findByIsHorde(Boolean isHorde);
}