package com.whencyclopedia.repository.variant;

import com.whencyclopedia.domain.variant.AbilityVariant;
import com.whencyclopedia.domain.enums.AbilityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AbilityVariantRepository extends JpaRepository<AbilityVariant, Long> {
    List<AbilityVariant> findByGameVersionId(Long gameVersionId);
    List<AbilityVariant> findByAbility_TypeAndGameVersionId(AbilityType type, Long gameVersionId);
    Optional<AbilityVariant> findByAbilityIdAndGameVersionId(Long abilityId, Long gameVersionId);
}