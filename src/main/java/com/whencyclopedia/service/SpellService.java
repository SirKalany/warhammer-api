package com.whencyclopedia.service;

import com.whencyclopedia.domain.identity.LoreOfMagic;
import com.whencyclopedia.domain.identity.Spell;
import com.whencyclopedia.dto.spell.SpellDTO;
import com.whencyclopedia.dto.spell.SpellSummaryDTO;
import com.whencyclopedia.repository.identity.LoreOfMagicRepository;
import com.whencyclopedia.repository.identity.SpellRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpellService {

    private final SpellRepository spellRepository;
    private final LoreOfMagicRepository loreOfMagicRepository;

    public List<SpellSummaryDTO> findAll() {
        return spellRepository.findAll()
                .stream()
                .map(this::toSummaryDTO)
                .toList();
    }

    public List<SpellSummaryDTO> findByLore(Long loreId) {
        return spellRepository.findByLoreId(loreId)
                .stream()
                .map(this::toSummaryDTO)
                .toList();
    }

    public SpellDTO findBySlug(String slug) {
        Spell spell = spellRepository.findBySlug(slug)
                .orElseThrow(() -> new EntityNotFoundException("Spell not found: " + slug));
        return toDTO(spell);
    }

    public SpellDTO create(SpellDTO dto) {
        LoreOfMagic lore = loreOfMagicRepository.findById(dto.loreId())
                .orElseThrow(() -> new EntityNotFoundException("Lore not found: " + dto.loreId()));
        Spell spell = Spell.builder()
                .lore(lore)
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
                .cost(dto.cost())
                .miscastChance(dto.miscastChance())
                .overcastEffect(dto.overcastEffect())
                .overcastTarget(dto.overcastTarget())
                .overcastRange(dto.overcastRange())
                .overcastRadius(dto.overcastRadius())
                .overcastDuration(dto.overcastDuration())
                .overcastCooldown(dto.overcastCooldown())
                .overcastAffectedUnits(dto.overcastAffectedUnits())
                .overcastBaseDamage(dto.overcastBaseDamage())
                .overcastExplosiveDamage(dto.overcastExplosiveDamage())
                .overcastDamagePerSecond(dto.overcastDamagePerSecond())
                .overcastMovementSpeed(dto.overcastMovementSpeed())
                .overcastCost(dto.overcastCost())
                .overcastMiscastChance(dto.overcastMiscastChance())
                .build();
        return toDTO(spellRepository.save(spell));
    }

    public SpellDTO update(Long id, SpellDTO dto) {
        Spell spell = spellRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Spell not found: " + id));
        LoreOfMagic lore = loreOfMagicRepository.findById(dto.loreId())
                .orElseThrow(() -> new EntityNotFoundException("Lore not found: " + dto.loreId()));
        spell.setLore(lore);
        spell.setName(dto.name());
        spell.setSlug(dto.slug());
        spell.setType(dto.type());
        spell.setEffect(dto.effect());
        spell.setTarget(dto.target());
        spell.setRange(dto.range());
        spell.setRadius(dto.radius());
        spell.setDuration(dto.duration());
        spell.setCooldown(dto.cooldown());
        spell.setAffectedUnits(dto.affectedUnits());
        spell.setUses(dto.uses());
        spell.setConditions(dto.conditions());
        spell.setBaseDamage(dto.baseDamage());
        spell.setExplosiveDamage(dto.explosiveDamage());
        spell.setDamagePerSecond(dto.damagePerSecond());
        spell.setMovementSpeed(dto.movementSpeed());
        spell.setCost(dto.cost());
        spell.setMiscastChance(dto.miscastChance());
        spell.setOvercastEffect(dto.overcastEffect());
        spell.setOvercastTarget(dto.overcastTarget());
        spell.setOvercastRange(dto.overcastRange());
        spell.setOvercastRadius(dto.overcastRadius());
        spell.setOvercastDuration(dto.overcastDuration());
        spell.setOvercastCooldown(dto.overcastCooldown());
        spell.setOvercastAffectedUnits(dto.overcastAffectedUnits());
        spell.setOvercastBaseDamage(dto.overcastBaseDamage());
        spell.setOvercastExplosiveDamage(dto.overcastExplosiveDamage());
        spell.setOvercastDamagePerSecond(dto.overcastDamagePerSecond());
        spell.setOvercastMovementSpeed(dto.overcastMovementSpeed());
        spell.setOvercastCost(dto.overcastCost());
        spell.setOvercastMiscastChance(dto.overcastMiscastChance());
        return toDTO(spellRepository.save(spell));
    }

    public void delete(Long id) {
        spellRepository.deleteById(id);
    }

    private SpellDTO toDTO(Spell s) {
        return new SpellDTO(
                s.getId(), s.getLore().getId(), s.getName(), s.getSlug(), s.getType(),
                s.getEffect(), s.getTarget(), s.getRange(), s.getRadius(),
                s.getDuration(), s.getCooldown(), s.getAffectedUnits(), s.getUses(),
                s.getConditions(), s.getBaseDamage(), s.getExplosiveDamage(),
                s.getDamagePerSecond(), s.getMovementSpeed(), s.getCost(), s.getMiscastChance(),
                s.getOvercastEffect(), s.getOvercastTarget(), s.getOvercastRange(),
                s.getOvercastRadius(), s.getOvercastDuration(), s.getOvercastCooldown(),
                s.getOvercastAffectedUnits(), s.getOvercastBaseDamage(),
                s.getOvercastExplosiveDamage(), s.getOvercastDamagePerSecond(),
                s.getOvercastMovementSpeed(), s.getOvercastCost(), s.getOvercastMiscastChance()
        );
    }

    private SpellSummaryDTO toSummaryDTO(Spell s) {
        return new SpellSummaryDTO(s.getId(), s.getLore().getId(), s.getName(), s.getSlug(), s.getType());
    }
}