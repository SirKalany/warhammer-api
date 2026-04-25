package com.whencyclopedia.service.identity;

import com.whencyclopedia.domain.identity.Item;
import com.whencyclopedia.domain.enums.ItemCategory;
import com.whencyclopedia.dto.identity.item.ItemDTO;
import com.whencyclopedia.dto.identity.item.ItemSummaryDTO;
import com.whencyclopedia.repository.identity.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public List<ItemSummaryDTO> findAll() {
        return itemRepository.findAll()
                .stream()
                .map(i -> toSummaryDTO(i))
                .toList();
    }

    public List<ItemSummaryDTO> findByCategory(ItemCategory category) {
        return itemRepository.findByCategory(category)
                .stream()
                .map(i -> toSummaryDTO(i))
                .toList();
    }

    public ItemDTO findBySlug(String slug) {
        Item item = itemRepository.findBySlug(slug)
                .orElseThrow(() -> new EntityNotFoundException("Item not found: " + slug));
        return toDTO(item);
    }

    public ItemDTO create(ItemDTO dto) {
        Item item = Item.builder()
                .name(dto.name())
                .slug(dto.slug())
                .category(dto.category())
                .rarity(dto.rarity())
                .build();
        return toDTO(itemRepository.save(item));
    }

    public ItemDTO update(Long id, ItemDTO dto) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item not found: " + id));
        item.setName(dto.name());
        item.setSlug(dto.slug());
        item.setCategory(dto.category());
        item.setRarity(dto.rarity());
        return toDTO(itemRepository.save(item));
    }

    public void delete(Long id) {
        itemRepository.deleteById(id);
    }

    private ItemDTO toDTO(@NonNull Item i) {
        return new ItemDTO(i.getId(), i.getName(), i.getSlug(), i.getCategory(), i.getRarity());
    }

    private ItemSummaryDTO toSummaryDTO(@NonNull Item i) {
        return new ItemSummaryDTO(i.getId(), i.getName(), i.getSlug(), i.getCategory(), i.getRarity());
    }
}