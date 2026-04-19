package com.whencyclopedia.repository;

import com.whencyclopedia.domain.Spell;
import com.whencyclopedia.domain.AbilityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SpellRepository extends JpaRepository<Spell, Long> {
    Optional<Spell> findBySlug(String slug);
    List<Spell> findByLoreId(Long loreId);
    List<Spell> findByType(AbilityType type);
}