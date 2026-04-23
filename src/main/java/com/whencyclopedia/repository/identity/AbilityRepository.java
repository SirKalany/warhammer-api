package com.whencyclopedia.repository.identity;

import com.whencyclopedia.domain.identity.Ability;
import com.whencyclopedia.domain.enums.AbilityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AbilityRepository extends JpaRepository<Ability, Long> {
    Optional<Ability> findBySlug(String slug);
    List<Ability> findByType(AbilityType type);
}