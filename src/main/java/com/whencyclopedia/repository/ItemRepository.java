package com.whencyclopedia.repository;

import com.whencyclopedia.domain.Item;
import com.whencyclopedia.domain.ItemCategory;
import com.whencyclopedia.domain.ItemRarity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findBySlug(String slug);
    List<Item> findByCategory(ItemCategory category);
    List<Item> findByRarity(ItemRarity rarity);
    List<Item> findByRaceId(Long raceId);
    List<Item> findByCategoryAndRarity(ItemCategory category, ItemRarity rarity);
}