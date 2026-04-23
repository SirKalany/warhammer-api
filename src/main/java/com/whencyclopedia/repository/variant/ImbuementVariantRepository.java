package com.whencyclopedia.repository.variant;

import com.whencyclopedia.domain.variant.ImbuementVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ImbuementVariantRepository extends JpaRepository<ImbuementVariant, Long> {
    List<ImbuementVariant> findByGameVersionId(Long gameVersionId);
    Optional<ImbuementVariant> findByImbuementIdAndGameVersionId(Long imbuementId, Long gameVersionId);
}