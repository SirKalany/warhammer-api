package com.whencyclopedia.repository.identity;

import com.whencyclopedia.domain.identity.Race;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RaceRepository extends JpaRepository<Race, Long> {
    Optional<Race> findBySlug(String slug);
}