package com.whencyclopedia.service;

import com.whencyclopedia.domain.Ability;
import com.whencyclopedia.domain.AbilityType;
import com.whencyclopedia.dto.ability.AbilityDTO;
import com.whencyclopedia.dto.ability.AbilitySummaryDTO;
import com.whencyclopedia.repository.AbilityRepository;
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
                .map(ability -> toSummaryDTO(ability))
                .toList();
    }

    public List<AbilitySummaryDTO> findByType(AbilityType type) {
        return abilityRepository.findByType(type)
                .stream()
                .map(ability -> toSummaryDTO(ability))
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
                .effect(dto.effect())
                .target(dto.target())
                .range(dto.range())
                .radius(dto.radius())
                .duration(dto.duration())
                .cooldown(dto.cooldown())
                .affectedUnits(dto.affectedUnits())
                .uses(dto.uses())
                .conditions(dto.conditions())
                .baseDamage(dto.baseDamage())
                .explosiveDamage(dto.explosiveDamage())
                .damagePerSecond(dto.damagePerSecond())
                .movementSpeed(dto.movementSpeed())
                .build();
        return toDTO(abilityRepository.save(ability));
    }

    public AbilityDTO update(Long id, AbilityDTO dto) {
        Ability ability = abilityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ability not found: " + id));
        ability.setName(dto.name());
        ability.setSlug(dto.slug());
        ability.setType(dto.type());
        ability.setEffect(dto.effect());
        ability.setTarget(dto.target());
        ability.setRange(dto.range());
        ability.setRadius(dto.radius());
        ability.setDuration(dto.duration());
        ability.setCooldown(dto.cooldown());
        ability.setAffectedUnits(dto.affectedUnits());
        ability.setUses(dto.uses());
        ability.setConditions(dto.conditions());
        ability.setBaseDamage(dto.baseDamage());
        ability.setExplosiveDamage(dto.explosiveDamage());
        ability.setDamagePerSecond(dto.damagePerSecond());
        ability.setMovementSpeed(dto.movementSpeed());
        return toDTO(abilityRepository.save(ability));
    }

    public void delete(Long id) {
        abilityRepository.deleteById(id);
    }

    private AbilityDTO toDTO(@NonNull Ability a) {
        return new AbilityDTO(
                a.getId(), a.getName(), a.getSlug(), a.getType(),
                a.getEffect(), a.getTarget(), a.getRange(), a.getRadius(),
                a.getDuration(), a.getCooldown(), a.getAffectedUnits(),
                a.getUses(), a.getConditions(), a.getBaseDamage(),
                a.getExplosiveDamage(), a.getDamagePerSecond(), a.getMovementSpeed()
        );
    }

    private AbilitySummaryDTO toSummaryDTO(@NonNull Ability a) {
        return new AbilitySummaryDTO(a.getId(), a.getName(), a.getSlug(), a.getType());
    }
}