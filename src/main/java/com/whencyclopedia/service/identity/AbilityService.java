package com.whencyclopedia.service.identity;

import com.whencyclopedia.domain.identity.Ability;
import com.whencyclopedia.domain.enums.AbilityType;
import com.whencyclopedia.dto.identity.ability.AbilityDTO;
import com.whencyclopedia.dto.identity.ability.AbilitySummaryDTO;
import com.whencyclopedia.repository.identity.AbilityRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AbilityService {

    private final AbilityRepository abilityRepository;

    public List<AbilitySummaryDTO> findAll() {
        return abilityRepository.findAll()
                .stream()
                .map(a -> toSummaryDTO(a))
                .toList();
    }

    public List<AbilitySummaryDTO> findByType(AbilityType type) {
        return abilityRepository.findByType(type)
                .stream()
                .map(a -> toSummaryDTO(a))
                .toList();
    }

    public AbilityDTO findBySlug(String slug) {
        Ability ability = abilityRepository.findBySlug(slug)
                .orElseThrow(() -> new EntityNotFoundException("Ability not found: " + slug));
        return toDTO(ability);
    }

    public AbilityDTO create(AbilityDTO dto) {
        Ability ability = Ability.builder()
                .name(dto.name())
                .slug(dto.slug())
                .type(dto.type())
                .build();
        return toDTO(abilityRepository.save(ability));
    }

    public AbilityDTO update(Long id, AbilityDTO dto) {
        Ability ability = abilityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ability not found: " + id));
        ability.setName(dto.name());
        ability.setSlug(dto.slug());
        ability.setType(dto.type());
        return toDTO(abilityRepository.save(ability));
    }

    public void delete(Long id) {
        abilityRepository.deleteById(id);
    }

    private AbilityDTO toDTO(@NonNull Ability a) {
        return new AbilityDTO(a.getId(), a.getName(), a.getSlug(), a.getType());
    }

    private AbilitySummaryDTO toSummaryDTO(@NonNull Ability a) {
        return new AbilitySummaryDTO(a.getId(), a.getName(), a.getSlug(), a.getType());
    }
}