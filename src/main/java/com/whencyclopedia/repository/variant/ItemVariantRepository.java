package com.whencyclopedia.repository.variant;

import com.whencyclopedia.domain.variant.ItemVariant;
import com.whencyclopedia.domain.enums.ItemCategory;
import com.whencyclopedia.domain.enums.ItemRarity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ItemVariantRepository extends JpaRepository<ItemVariant, Long> {
    List<ItemVariant> findByGameVersionId(Long gameVersionId);
    List<ItemVariant> findByItem_CategoryAndGameVersionId(ItemCategory category, Long gameVersionId);
    List<ItemVariant> findByItem_RarityAndGameVersionId(ItemRarity rarity, Long gameVersionId);
    List<ItemVariant> findByRaceIdAndGameVersionId(Long raceId, Long gameVersionId);
    Optional<ItemVariant> findByItemIdAndGameVersionId(Long itemId, Long gameVersionId);
}