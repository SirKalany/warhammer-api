package com.whencyclopedia.repository.variant;

import com.whencyclopedia.domain.variant.UnitAttributeVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UnitAttributeVariantRepository extends JpaRepository<UnitAttributeVariant, Long> {
    List<UnitAttributeVariant> findByGameVersionId(Long gameVersionId);
    Optional<UnitAttributeVariant> findByUnitAttributeIdAndGameVersionId(Long unitAttributeId, Long gameVersionId);
}