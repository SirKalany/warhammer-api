package com.whencyclopedia.repository.identity;

import com.whencyclopedia.domain.identity.Item;
import com.whencyclopedia.domain.enums.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findBySlug(String slug);
    List<Item> findByCategory(ItemCategory category);
}