package com.whencyclopedia.service.variant;

import com.whencyclopedia.domain.identity.GameVersion;
import com.whencyclopedia.domain.identity.Unit;
import com.whencyclopedia.domain.identity.UnitAttribute;
import com.whencyclopedia.domain.variant.*;
import com.whencyclopedia.domain.enums.UnitCategoryType;
import com.whencyclopedia.domain.enums.UnitRole;
import com.whencyclopedia.dto.variant.unit.UnitVariantDTO;
import com.whencyclopedia.dto.variant.unit.UnitVariantSummaryDTO;
import com.whencyclopedia.dto.variant.shared.*;
import com.whencyclopedia.dto.variant.ability.AbilityVariantSummaryDTO;
import com.whencyclopedia.dto.variant.spell.SpellVariantSummaryDTO;
import com.whencyclopedia.dto.variant.item.ItemVariantSummaryDTO;
import com.whencyclopedia.repository.identity.*;
import com.whencyclopedia.repository.variant.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UnitVariantService {

    private final UnitVariantRepository unitVariantRepository;
    private final UnitRepository unitRepository;
    private final GameVersionRepository gameVersionRepository;
    private final ImbuementVariantRepository imbuementVariantRepository;
    private final BuildingVariantRepository buildingVariantRepository;
    private final UnitAttributeRepository unitAttributeRepository;
    private final AbilityVariantRepository abilityVariantRepository;
    private final SpellVariantRepository spellVariantRepository;
    private final ItemVariantRepository itemVariantRepository;

    public List<UnitVariantSummaryDTO> findByVersion(Long versionId) {
        return unitVariantRepository.findByGameVersionId(versionId)
                .stream()
                .map(uv -> toSummaryDTO(uv))
                .toList();
    }

    public List<UnitVariantSummaryDTO> findByRaceAndVersion(Long raceId, Long versionId) {
        return unitVariantRepository.findByUnit_RaceIdAndGameVersionId(raceId, versionId)
                .stream()
                .map(uv -> toSummaryDTO(uv))
                .toList();
    }

    public List<UnitVariantSummaryDTO> findByRaceRoleAndVersion(Long raceId, UnitRole role, Long versionId) {
        return unitVariantRepository.findByUnit_RaceIdAndRoleAndGameVersionId(raceId, role, versionId)
                .stream()
                .map(uv -> toSummaryDTO(uv))
                .toList();
    }

    public List<UnitVariantSummaryDTO> findByRaceCategoryTypeAndVersion(Long raceId, UnitCategoryType categoryType, Long versionId) {
        return unitVariantRepository.findByUnit_RaceIdAndCategoryTypeAndGameVersionId(raceId, categoryType, versionId)
                .stream()
                .map(uv -> toSummaryDTO(uv))
                .toList();
    }

    public Optional<UnitVariantDTO> findByUnitAndVersion(Long unitId, Long versionId) {
        return unitVariantRepository.findByUnitIdAndGameVersionId(unitId, versionId)
                .map(uv -> toDTO(uv));
    }

    public List<UnitVariantSummaryDTO> findByUnlockBuilding(Long buildingVariantId) {
        return unitVariantRepository.findByUnlockBuildingId(buildingVariantId)
                .stream()
                .map(uv -> toSummaryDTO(uv))
                .toList();
    }

    public List<UnitVariantSummaryDTO> findByAllowBuilding(Long buildingVariantId) {
        return unitVariantRepository.findByAllowBuildingId(buildingVariantId)
                .stream()
                .map(uv -> toSummaryDTO(uv))
                .toList();
    }

    @Transactional
    public UnitVariantDTO create(UnitVariantDTO dto) {
        UnitVariant variant = buildVariantFromDTO(new UnitVariant(), dto);
        return toDTO(unitVariantRepository.save(variant));
    }

    @Transactional
    public UnitVariantDTO update(Long id, UnitVariantDTO dto) {
        UnitVariant variant = unitVariantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unit variant not found: " + id));
        buildVariantFromDTO(variant, dto);
        return toDTO(unitVariantRepository.save(variant));
    }

    public void delete(Long id) {
        unitVariantRepository.deleteById(id);
    }

    private UnitVariant buildVariantFromDTO(@NonNull UnitVariant variant, @NonNull UnitVariantDTO dto) {
        Unit unit = unitRepository.findById(dto.unitId())
                .orElseThrow(() -> new EntityNotFoundException("Unit not found: " + dto.unitId()));
        GameVersion version = gameVersionRepository.findById(dto.gameVersionId())
                .orElseThrow(() -> new EntityNotFoundException("Version not found: " + dto.gameVersionId()));
        ImbuementVariant meleeImbuement = dto.meleeImbuementVariantId() != null
                ? imbuementVariantRepository.findById(dto.meleeImbuementVariantId())
                        .orElseThrow(() -> new EntityNotFoundException("Imbuement variant not found: " + dto.meleeImbuementVariantId()))
                : null;
        BuildingVariant unlockBuilding = dto.unlockBuildingVariantId() != null
                ? buildingVariantRepository.findById(dto.unlockBuildingVariantId())
                        .orElseThrow(() -> new EntityNotFoundException("Building variant not found: " + dto.unlockBuildingVariantId()))
                : null;
        BuildingVariant allowBuilding = dto.allowBuildingVariantId() != null
                ? buildingVariantRepository.findById(dto.allowBuildingVariantId())
                        .orElseThrow(() -> new EntityNotFoundException("Building variant not found: " + dto.allowBuildingVariantId()))
                : null;
        List<UnitAttribute> unitAttributes = unitAttributeRepository.findAllById(dto.unitAttributeIds());
        List<AbilityVariant> abilities = abilityVariantRepository.findAllById(dto.abilityVariantIds());
        List<AbilityVariant> passiveAbilities = abilityVariantRepository.findAllById(dto.passiveAbilityVariantIds());
        List<SpellVariant> spells = spellVariantRepository.findAllById(dto.spellVariantIds());
        List<ItemVariant> items = itemVariantRepository.findAllById(dto.itemVariantIds());

        variant.setUnit(unit);
        variant.setGameVersion(version);
        variant.setDisplayName(dto.displayName());
        variant.setPicture(dto.picture());
        variant.setTier(dto.tier());
        variant.setCategory(dto.category());
        variant.setCategoryType(dto.categoryType());
        variant.setRole(dto.role());
        variant.setDescription(dto.description());
        variant.setSize(dto.size());
        variant.setEntities(dto.entities());
        variant.setMass(dto.mass());
        variant.setCampaignCost(dto.campaignCost());
        variant.setBaseUpkeep(dto.baseUpkeep());
        variant.setMultiplayerCost(dto.multiplayerCost());
        variant.setHealth(dto.health());
        variant.setHealthPerEntity(dto.healthPerEntity());
        variant.setBarrier(dto.barrier());
        variant.setArmour(dto.armour());
        variant.setParry(dto.parry());
        variant.setWardSave(dto.wardSave());
        variant.setPhysicalResistance(dto.physicalResistance());
        variant.setMissileResistance(dto.missileResistance());
        variant.setSpellResistance(dto.spellResistance());
        variant.setFireResistance(dto.fireResistance());
        variant.setLeadership(dto.leadership());
        variant.setSpeed(dto.speed());
        variant.setChargeSpeed(dto.chargeSpeed());
        variant.setMeleeAttack(dto.meleeAttack());
        variant.setMeleeImbuement(meleeImbuement);
        variant.setAttackInterval(dto.attackInterval());
        variant.setHighThreat(dto.highThreat());
        variant.setSplashTargetSize(dto.splashTargetSize());
        variant.setSplashMaxAttacks(dto.splashMaxAttacks());
        variant.setMeleeDefense(dto.meleeDefense());
        variant.setWeaponStrength(dto.weaponStrength());
        variant.setMeleeBaseDamage(dto.meleeBaseDamage());
        variant.setMeleeApDamage(dto.meleeApDamage());
        variant.setMeleeBonusVsLarge(dto.meleeBonusVsLarge());
        variant.setMeleeBonusVsInfantry(dto.meleeBonusVsInfantry());
        variant.setChargeBonus(dto.chargeBonus());
        variant.setRangedMode(dto.rangedMode());
        variant.setUnlockBuilding(unlockBuilding);
        variant.setAllowBuilding(allowBuilding);
        variant.setUnitAttributes(unitAttributes);
        variant.setAbilities(abilities);
        variant.setPassiveAbilities(passiveAbilities);
        variant.setSpells(spells);
        variant.setItems(items);

        // Handle attribute lines
        if (variant.getAttributeLines() != null) {
            variant.getAttributeLines().clear();
        }
        if (dto.attributeLines() != null) {
            dto.attributeLines().forEach(lineDTO -> {
                UnitVariantAttributeLine line = new UnitVariantAttributeLine();
                line.setUnitVariant(variant);
                line.setPosition(lineDTO.position());
                line.setContent(lineDTO.content());
                if (variant.getAttributeLines() != null) {
                    variant.getAttributeLines().add(line);
                }
            });
        }

        return variant;
    }

    private UnitVariantDTO toDTO(@NonNull UnitVariant uv) {
        String name = uv.getDisplayName() != null ? uv.getDisplayName() : uv.getUnit().getName();
        return new UnitVariantDTO(
                uv.getId(), uv.getUnit().getId(), name, uv.getUnit().getSlug(),
                uv.getGameVersion().getId(), uv.getDisplayName(),
                uv.getUnit().getRaces().stream().map(r -> r.getId()).toList(),
                uv.getPicture(), uv.getTier(), uv.getCategory(),
                uv.getCategoryType(), uv.getRole(), uv.getDescription(),
                uv.getSize(), uv.getEntities(), uv.getMass(),
                uv.getCampaignCost(), uv.getBaseUpkeep(), uv.getMultiplayerCost(),
                uv.getHealth(), uv.getHealthPerEntity(), uv.getBarrier(),
                uv.getArmour(), uv.getParry(), uv.getWardSave(),
                uv.getPhysicalResistance(), uv.getMissileResistance(),
                uv.getSpellResistance(), uv.getFireResistance(), uv.getLeadership(),
                uv.getSpeed(), uv.getChargeSpeed(), uv.getMeleeAttack(),
                uv.getMeleeImbuement() != null ? toImbuementVariantDTO(uv.getMeleeImbuement()) : null,
                uv.getAttackInterval(), uv.getHighThreat(),
                uv.getSplashTargetSize(), uv.getSplashMaxAttacks(),
                uv.getMeleeDefense(), uv.getWeaponStrength(),
                uv.getMeleeBaseDamage(), uv.getMeleeApDamage(),
                uv.getMeleeBonusVsLarge(), uv.getMeleeBonusVsInfantry(), uv.getChargeBonus(),
                uv.getRangedMode(),
                uv.getRangedWeapons() != null
                        ? uv.getRangedWeapons().stream().map(rw -> toRangedWeaponDTO(rw)).toList()
                        : List.of(),
                uv.getUnlockBuilding() != null ? uv.getUnlockBuilding().getId() : null,
                uv.getAllowBuilding() != null ? uv.getAllowBuilding().getId() : null,
                uv.getAttributeLines() != null
                        ? uv.getAttributeLines().stream().map(al -> toAttributeLineDTO(al)).toList()
                        : List.of(),
                uv.getUnitAttributes() != null
                        ? uv.getUnitAttributes().stream().map(ua -> new UnitAttributeVariantDTO(null, ua.getId(), ua.getName(), uv.getGameVersion().getId(), null)).toList()
                        : List.of(),
                uv.getAbilities() != null
                        ? uv.getAbilities().stream().map(av -> toAbilityVariantSummaryDTO(av)).toList()
                        : List.of(),
                uv.getPassiveAbilities() != null
                        ? uv.getPassiveAbilities().stream().map(av -> toAbilityVariantSummaryDTO(av)).toList()
                        : List.of(),
                uv.getSpells() != null
                        ? uv.getSpells().stream().map(sv -> toSpellVariantSummaryDTO(sv)).toList()
                        : List.of(),
                uv.getItems() != null
                        ? uv.getItems().stream().map(iv -> toItemVariantSummaryDTO(iv)).toList()
                        : List.of(),
                uv.getAbilities() != null
                        ? uv.getAbilities().stream().map(AbilityVariant::getId).toList()
                        : List.of(),
                uv.getPassiveAbilities() != null
                        ? uv.getPassiveAbilities().stream().map(AbilityVariant::getId).toList()
                        : List.of(),
                uv.getSpells() != null
                        ? uv.getSpells().stream().map(SpellVariant::getId).toList()
                        : List.of(),
                uv.getItems() != null
                        ? uv.getItems().stream().map(ItemVariant::getId).toList()
                        : List.of(),
                uv.getUnitAttributes() != null
                        ? uv.getUnitAttributes().stream().map(UnitAttribute::getId).toList()
                        : List.of(),
                uv.getMeleeImbuement() != null ? uv.getMeleeImbuement().getId() : null,
                uv.getUnlockBuilding() != null ? uv.getUnlockBuilding().getId() : null,
                uv.getAllowBuilding() != null ? uv.getAllowBuilding().getId() : null
        );
    }

    private UnitVariantSummaryDTO toSummaryDTO(@NonNull UnitVariant uv) {
        String name = uv.getDisplayName() != null ? uv.getDisplayName() : uv.getUnit().getName();
        return new UnitVariantSummaryDTO(
                uv.getId(), uv.getUnit().getId(), name, uv.getUnit().getSlug(),
                uv.getGameVersion().getId(), uv.getPicture(), uv.getTier(),
                uv.getCategory(), uv.getCategoryType(), uv.getRole()
        );
    }

    private ImbuementVariantDTO toImbuementVariantDTO(@NonNull ImbuementVariant iv) {
        return new ImbuementVariantDTO(
                iv.getId(), iv.getImbuement().getId(),
                iv.getImbuement().getName(), iv.getImbuement().getSlug(),
                iv.getGameVersion().getId(), iv.getDescription(), iv.getIcon()
        );
    }

    private RangedWeaponDTO toRangedWeaponDTO(@NonNull RangedWeapon rw) {
        return new RangedWeaponDTO(
                rw.getId(), rw.getWeaponSlot(),
                rw.getImbuementVariant() != null ? rw.getImbuementVariant().getId() : null,
                rw.getAmmunition(), rw.getRange(), rw.getMissileBaseDamage(),
                rw.getMissileApDamage(), rw.getMissileBonusVsLarge(),
                rw.getMissileBonusVsInfantry(), rw.getExplosionDamage(),
                rw.getExplosionApDamage(), rw.getDetonationRadius(),
                rw.getShotsPerVolley(), rw.getProjectileNumber(),
                rw.getProjectileCategory(), rw.getReloadTime(),
                rw.getTotalAccuracy(), rw.getCalibrationDistance(),
                rw.getCalibrationArea(), rw.getPenetrationSizeCap(),
                rw.getMaxPenetration()
        );
    }

    private UnitVariantAttributeLineDTO toAttributeLineDTO(@NonNull UnitVariantAttributeLine al) {
        return new UnitVariantAttributeLineDTO(al.getId(), al.getPosition(), al.getContent());
    }

    private AbilityVariantSummaryDTO toAbilityVariantSummaryDTO(@NonNull AbilityVariant av) {
        return new AbilityVariantSummaryDTO(
                av.getId(), av.getAbility().getId(), av.getAbility().getName(),
                av.getAbility().getSlug(), av.getAbility().getType(), av.getGameVersion().getId()
        );
    }

    private SpellVariantSummaryDTO toSpellVariantSummaryDTO(@NonNull SpellVariant sv) {
        return new SpellVariantSummaryDTO(
                sv.getId(), sv.getSpell().getId(), sv.getSpell().getName(),
                sv.getSpell().getSlug(), sv.getSpell().getType(),
                sv.getSpell().getLore().getId(), sv.getGameVersion().getId()
        );
    }

    private ItemVariantSummaryDTO toItemVariantSummaryDTO(@NonNull ItemVariant iv) {
        return new ItemVariantSummaryDTO(
                iv.getId(), iv.getItem().getId(), iv.getItem().getName(),
                iv.getItem().getSlug(), iv.getItem().getCategory(), iv.getItem().getRarity(),
                iv.getGameVersion().getId(),
                iv.getRace() != null ? iv.getRace().getId() : null
        );
    }
}