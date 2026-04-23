package com.whencyclopedia.repository.variant;

import com.whencyclopedia.domain.variant.SpellVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SpellVariantRepository extends JpaRepository<SpellVariant, Long> {
    List<SpellVariant> findByGameVersionId(Long gameVersionId);
    List<SpellVariant> findBySpell_LoreIdAndGameVersionId(Long loreId, Long gameVersionId);
    Optional<SpellVariant> findBySpellIdAndGameVersionId(Long spellId, Long gameVersionId);
}