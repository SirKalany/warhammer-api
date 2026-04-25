package com.whencyclopedia.repository.identity;

import com.whencyclopedia.domain.identity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {
    Optional<Unit> findBySlug(String slug);

    // Find all units belonging to a race via junction table
    List<Unit> findByRaces_Id(Long raceId);
}