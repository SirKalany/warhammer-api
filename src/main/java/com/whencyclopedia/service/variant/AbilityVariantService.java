package com.whencyclopedia.service.variant;

import com.whencyclopedia.domain.identity.Ability;
import com.whencyclopedia.domain.identity.GameVersion;
import com.whencyclopedia.domain.enums.AbilityType;
import com.whencyclopedia.domain.variant.AbilityVariant;
import com.whencyclopedia.dto.variant.ability.AbilityVariantDTO;
import com.whencyclopedia.dto.variant.ability.AbilityVariantSummaryDTO;
import com.whencyclopedia.repository.identity.AbilityRepository;
import com.whencyclopedia.repository.identity.GameVersionRepository;
import com.whencyclopedia.repository.variant.AbilityVariantRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AbilityVariantService {

    private final AbilityVariantRepository abilityVariantRepository;
    private final AbilityRepository abilityRepository;
    private final GameVersionRepository gameVersionRepository;

    public List<AbilityVariantSummaryDTO> findByVersion(Long versionId) {
        return abilityVariantRepository.findByGameVersionId(versionId)
                .stream()
                .map(av -> toSummaryDTO(av))
                .toList();
    }

    public List<AbilityVariantSummaryDTO> findByTypeAndVersion(AbilityType type, Long versionId) {
        return abilityVariantRepository.findByAbility_TypeAndGameVersionId(type, versionId)
                .stream()
                .map(av -> toSummaryDTO(av))
                .toList();
    }

    public Optional<AbilityVariantDTO> findByAbilityAndVersion(Long abilityId, Long versionId) {
        return abilityVariantRepository.findByAbilityIdAndGameVersionId(abilityId, versionId)
                .map(av -> toDTO(av));
    }

    public AbilityVariantDTO create(AbilityVariantDTO dto) {
        Ability ability = abilityRepository.findById(dto.abilityId())
                .orElseThrow(() -> new EntityNotFoundException("Ability not found: " + dto.abilityId()));
        GameVersion version = gameVersionRepository.findById(dto.gameVersionId())
                .orElseThrow(() -> new EntityNotFoundException("Version not found: " + dto.gameVersionId()));
        AbilityVariant variant = buildVariant(new AbilityVariant(), dto, ability, version);
        return toDTO(abilityVariantRepository.save(variant));
    }

    public AbilityVariantDTO update(Long id, AbilityVariantDTO dto) {
        AbilityVariant variant = abilityVariantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ability variant not found: " + id));
        Ability ability = abilityRepository.findById(dto.abilityId())
                .orElseThrow(() -> new EntityNotFoundException("Ability not found: " + dto.abilityId()));
        GameVersion version = gameVersionRepository.findById(dto.gameVersionId())
                .orElseThrow(() -> new EntityNotFoundException("Version not found: " + dto.gameVersionId()));
        buildVariant(variant, dto, ability, version);
        return toDTO(abilityVariantRepository.save(variant));
    }

    public void delete(Long id) {
        abilityVariantRepository.deleteById(id);
    }

    private AbilityVariant buildVariant(
            @NonNull AbilityVariant variant,
            @NonNull AbilityVariantDTO dto,
            @NonNull Ability ability,
            @NonNull GameVersion version) {
        variant.setAbility(ability);
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
        return variant;
    }

    private AbilityVariantDTO toDTO(@NonNull AbilityVariant av) {
        return new AbilityVariantDTO(
                av.getId(), av.getAbility().getId(), av.getAbility().getName(),
                av.getAbility().getSlug(), av.getAbility().getType(),
                av.getGameVersion().getId(), av.getDescription(), av.getEffect(),
                av.getTarget(), av.getRange(), av.getRadius(), av.getDuration(),
                av.getCooldown(), av.getAffectedUnits(), av.getUses(), av.getConditions(),
                av.getBaseDamage(), av.getExplosiveDamage(), av.getDamagePerSecond(),
                av.getMovementSpeed()
        );
    }

    private AbilityVariantSummaryDTO toSummaryDTO(@NonNull AbilityVariant av) {
        return new AbilityVariantSummaryDTO(
                av.getId(), av.getAbility().getId(),
                av.getAbility().getName(), av.getAbility().getSlug(),
                av.getAbility().getType(), av.getGameVersion().getId()
        );
    }
}