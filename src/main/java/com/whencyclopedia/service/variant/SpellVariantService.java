package com.whencyclopedia.service.variant;

import com.whencyclopedia.domain.identity.GameVersion;
import com.whencyclopedia.domain.identity.Spell;
import com.whencyclopedia.domain.variant.SpellVariant;
import com.whencyclopedia.dto.variant.spell.SpellVariantDTO;
import com.whencyclopedia.dto.variant.spell.SpellVariantSummaryDTO;
import com.whencyclopedia.repository.identity.GameVersionRepository;
import com.whencyclopedia.repository.identity.SpellRepository;
import com.whencyclopedia.repository.variant.SpellVariantRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SpellVariantService {

    private final SpellVariantRepository spellVariantRepository;
    private final SpellRepository spellRepository;
    private final GameVersionRepository gameVersionRepository;

    public List<SpellVariantSummaryDTO> findByVersion(Long versionId) {
        return spellVariantRepository.findByGameVersionId(versionId)
                .stream()
                .map(sv -> toSummaryDTO(sv))
                .toList();
    }

    public List<SpellVariantSummaryDTO> findByLoreAndVersion(Long loreId, Long versionId) {
        return spellVariantRepository.findBySpell_LoreIdAndGameVersionId(loreId, versionId)
                .stream()
                .map(sv -> toSummaryDTO(sv))
                .toList();
    }

    public Optional<SpellVariantDTO> findBySpellAndVersion(Long spellId, Long versionId) {
        return spellVariantRepository.findBySpellIdAndGameVersionId(spellId, versionId)
                .map(sv -> toDTO(sv));
    }

    public SpellVariantDTO create(SpellVariantDTO dto) {
        Spell spell = spellRepository.findById(dto.spellId())
                .orElseThrow(() -> new EntityNotFoundException("Spell not found: " + dto.spellId()));
        GameVersion version = gameVersionRepository.findById(dto.gameVersionId())
                .orElseThrow(() -> new EntityNotFoundException("Version not found: " + dto.gameVersionId()));
        SpellVariant variant = buildVariant(new SpellVariant(), dto, spell, version);
        return toDTO(spellVariantRepository.save(variant));
    }

    public SpellVariantDTO update(Long id, SpellVariantDTO dto) {
        SpellVariant variant = spellVariantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Spell variant not found: " + id));
        Spell spell = spellRepository.findById(dto.spellId())
                .orElseThrow(() -> new EntityNotFoundException("Spell not found: " + dto.spellId()));
        GameVersion version = gameVersionRepository.findById(dto.gameVersionId())
                .orElseThrow(() -> new EntityNotFoundException("Version not found: " + dto.gameVersionId()));
        buildVariant(variant, dto, spell, version);
        return toDTO(spellVariantRepository.save(variant));
    }

    public void delete(Long id) {
        spellVariantRepository.deleteById(id);
    }

    private SpellVariant buildVariant(
            @NonNull SpellVariant variant,
            @NonNull SpellVariantDTO dto,
            @NonNull Spell spell,
            @NonNull GameVersion version) {
        variant.setSpell(spell);
        variant.setGameVersion(version);
        variant.setDescription(dto.description());
        variant.setEffect(dto.effect());
        variant.setTarget(dto.target());
        variant.setRange(dto.range());
        variant.setRadius(dto.radius());
        variant.setDuration(dto.duration());
        variant.setCooldown(dto.cooldown());
        variant.setAffectedUnits(dto.affectedUnits());
        variant.setUses(dto.uses());
        variant.setConditions(dto.conditions());
        variant.setBaseDamage(dto.baseDamage());
        variant.setExplosiveDamage(dto.explosiveDamage());
        variant.setDamagePerSecond(dto.damagePerSecond());
        variant.setMovementSpeed(dto.movementSpeed());
        variant.setCost(dto.cost());
        variant.setMiscastChance(dto.miscastChance());
        variant.setOvercastEffect(dto.overcastEffect());
        variant.setOvercastTarget(dto.overcastTarget());
        variant.setOvercastRange(dto.overcastRange());
        variant.setOvercastRadius(dto.overcastRadius());
        variant.setOvercastDuration(dto.overcastDuration());
        variant.setOvercastCooldown(dto.overcastCooldown());
        variant.setOvercastAffectedUnits(dto.overcastAffectedUnits());
        variant.setOvercastBaseDamage(dto.overcastBaseDamage());
        variant.setOvercastExplosiveDamage(dto.overcastExplosiveDamage());
        variant.setOvercastDamagePerSecond(dto.overcastDamagePerSecond());
        variant.setOvercastMovementSpeed(dto.overcastMovementSpeed());
        variant.setOvercastCost(dto.overcastCost());
        variant.setOvercastMiscastChance(dto.overcastMiscastChance());
        return variant;
    }

    private SpellVariantDTO toDTO(@NonNull SpellVariant sv) {
        return new SpellVariantDTO(
                sv.getId(), sv.getSpell().getId(), sv.getSpell().getName(),
                sv.getSpell().getSlug(), sv.getSpell().getType(),
                sv.getSpell().getLore().getId(), sv.getGameVersion().getId(),
                sv.getDescription(), sv.getEffect(), sv.getTarget(),
                sv.getRange(), sv.getRadius(), sv.getDuration(), sv.getCooldown(),
                sv.getAffectedUnits(), sv.getUses(), sv.getConditions(),
                sv.getBaseDamage(), sv.getExplosiveDamage(), sv.getDamagePerSecond(),
                sv.getMovementSpeed(), sv.getCost(), sv.getMiscastChance(),
                sv.getOvercastEffect(), sv.getOvercastTarget(), sv.getOvercastRange(),
                sv.getOvercastRadius(), sv.getOvercastDuration(), sv.getOvercastCooldown(),
                sv.getOvercastAffectedUnits(), sv.getOvercastBaseDamage(),
                sv.getOvercastExplosiveDamage(), sv.getOvercastDamagePerSecond(),
                sv.getOvercastMovementSpeed(), sv.getOvercastCost(), sv.getOvercastMiscastChance()
        );
    }

    private SpellVariantSummaryDTO toSummaryDTO(@NonNull SpellVariant sv) {
        return new SpellVariantSummaryDTO(
                sv.getId(), sv.getSpell().getId(), sv.getSpell().getName(),
                sv.getSpell().getSlug(), sv.getSpell().getType(),
                sv.getSpell().getLore().getId(), sv.getGameVersion().getId()
        );
    }
}