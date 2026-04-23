package com.whencyclopedia.repository.identity;

import com.whencyclopedia.domain.identity.Faction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface FactionRepository extends JpaRepository<Faction, Long> {
    Optional<Faction> findBySlug(String slug);
    List<Faction> findByRaceId(Long raceId);
}