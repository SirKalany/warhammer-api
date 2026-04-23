package com.whencyclopedia.service;

import com.whencyclopedia.domain.*;
import com.whencyclopedia.domain.enums.UnitCategoryType;
import com.whencyclopedia.domain.enums.UnitRole;
import com.whencyclopedia.domain.identity.Ability;
import com.whencyclopedia.domain.identity.Building;
import com.whencyclopedia.domain.identity.Imbuement;
import com.whencyclopedia.domain.identity.Item;
import com.whencyclopedia.domain.identity.Race;
import com.whencyclopedia.domain.identity.Spell;
import com.whencyclopedia.domain.identity.Unit;
import com.whencyclopedia.domain.identity.UnitAttribute;
import com.whencyclopedia.domain.variant.RangedWeapon;
import com.whencyclopedia.dto.ability.AbilitySummaryDTO;
import com.whencyclopedia.dto.item.ItemSummaryDTO;
import com.whencyclopedia.dto.shared.ImbuementDTO;
import com.whencyclopedia.dto.shared.UnitAttributeDTO;
import com.whencyclopedia.dto.spell.SpellSummaryDTO;
import com.whencyclopedia.dto.unit.*;
import com.whencyclopedia.repository.*;
import com.whencyclopedia.repository.identity.AbilityRepository;
import com.whencyclopedia.repository.identity.BuildingRepository;
import com.whencyclopedia.repository.identity.ImbuementRepository;
import com.whencyclopedia.repository.identity.ItemRepository;
import com.whencyclopedia.repository.identity.RaceRepository;
import com.whencyclopedia.repository.identity.SpellRepository;
import com.whencyclopedia.repository.identity.UnitAttributeRepository;
import com.whencyclopedia.repository.identity.UnitRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UnitService {

    private final UnitRepository unitRepository;
    private final RaceRepository raceRepository;
    private final ImbuementRepository imbuementRepository;
    private final BuildingRepository buildingRepository;
    private final AbilityRepository abilityRepository;
    private final SpellRepository spellRepository;
    private final ItemRepository itemRepository;
    private final UnitAttributeRepository unitAttributeRepository;

    public List<UnitSummaryDTO> findAll() {
        return unitRepository.findAll()
                .stream()
                .map(unit -> toSummaryDTO(unit))
                .toList();
    }

    public List<UnitSummaryDTO> findByRace(Long raceId) {
        return unitRepository.findByRaceId(raceId)
                .stream()
                .map(unit -> toSummaryDTO(unit))
                .toList();
    }

    public List<UnitSummaryDTO> findByRaceAndRole(Long raceId, UnitRole role) {
        return unitRepository.findByRaceIdAndRole(raceId, role)
                .stream()
                .map(unit -> toSummaryDTO(unit))
                .toList();
    }

    public List<UnitSummaryDTO> findByRaceAndCategoryType(Long raceId, UnitCategoryType categoryType) {
        return unitRepository.findByRaceIdAndCategoryType(raceId, categoryType)
                .stream()
                .map(unit -> toSummaryDTO(unit))
                .toList();
    }

    public List<UnitSummaryDTO> findByUnlockBuilding(Long buildingId) {
        return unitRepository.findByUnlockBuildingId(buildingId)
                .stream()
                .map(unit -> toSummaryDTO(unit))
                .toList();
    }

    public List<UnitSummaryDTO> findByAllowBuilding(Long buildingId) {
        return unitRepository.findByAllowBuildingId(buildingId)
                .stream()
                .map(unit -> toSummaryDTO(unit))
                .toList();
    }

    public UnitDTO findBySlug(String slug) {
        Unit unit = unitRepository.findBySlug(slug)
                .orElseThrow(() -> new EntityNotFoundException("Unit not found: " + slug));
        return toDTO(unit);
    }

    @Transactional
    public UnitDTO create(UnitDTO dto) {
        Unit unit = buildUnitFromDTO(new Unit(), dto);
        return toDTO(unitRepository.save(unit));
    }

    @Transactional
    public UnitDTO update(Long id, UnitDTO dto) {
        Unit unit = unitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unit not found: " + id));
        buildUnitFromDTO(unit, dto);
        return toDTO(unitRepository.save(unit));
    }

    public void delete(Long id) {
        unitRepository.deleteById(id);
    }

    // Shared logic for create and update
    private Unit buildUnitFromDTO(@NonNull Unit unit, @NonNull UnitDTO dto) {
        Race race = raceRepository.findById(dto.raceId())
                .orElseThrow(() -> new EntityNotFoundException("Race not found: " + dto.raceId()));

        Imbuement meleeImbuement = dto.meleeImbuement() != null
                ? imbuementRepository.findById(dto.meleeImbuement().id())
                        .orElseThrow(() -> new EntityNotFoundException("Imbuement not found: " + dto.meleeImbuement().id()))
                : null;

        Building unlockBuilding = dto.unlockBuildingId() != null
                ? buildingRepository.findById(dto.unlockBuildingId())
                        .orElseThrow(() -> new EntityNotFoundException("Building not found: " + dto.unlockBuildingId()))
                : null;

        Building allowBuilding = dto.allowBuildingId() != null
                ? buildingRepository.findById(dto.allowBuildingId())
                        .orElseThrow(() -> new EntityNotFoundException("Building not found: " + dto.allowBuildingId()))
                : null;

        List<UnitAttribute> unitAttributes = unitAttributeRepository.findAllById(
                dto.unitAttributes().stream().map(UnitAttributeDTO::id).toList()
        );
        List<Ability> abilities = abilityRepository.findAllById(
                dto.abilities().stream().map(AbilitySummaryDTO::id).toList()
        );
        List<Ability> passiveAbilities = abilityRepository.findAllById(
                dto.passiveAbilities().stream().map(AbilitySummaryDTO::id).toList()
        );
        List<Spell> spells = spellRepository.findAllById(
                dto.spells().stream().map(SpellSummaryDTO::id).toList()
        );
        List<Item> items = itemRepository.findAllById(
                dto.items().stream().map(ItemSummaryDTO::id).toList()
        );

        unit.setRace(race);
        unit.setName(dto.name());
        unit.setSlug(dto.slug());
        unit.setPicture(dto.picture());
        unit.setTier(dto.tier());
        unit.setCategory(dto.category());
        unit.setRole(dto.role());
        unit.setCategoryType(dto.categoryType());
        unit.setDescription(dto.description());
        unit.setSize(dto.size());
        unit.setEntities(dto.entities());
        unit.setMass(dto.mass());
        unit.setCampaignCost(dto.campaignCost());
        unit.setBaseUpkeep(dto.baseUpkeep());
        unit.setMultiplayerCost(dto.multiplayerCost());
        unit.setHealth(dto.health());
        unit.setHealthPerEntity(dto.healthPerEntity());
        unit.setBarrier(dto.barrier());
        unit.setArmour(dto.armour());
        unit.setParry(dto.parry());
        unit.setWardSave(dto.wardSave());
        unit.setPhysicalResistance(dto.physicalResistance());
        unit.setMissileResistance(dto.missileResistance());
        unit.setSpellResistance(dto.spellResistance());
        unit.setFireResistance(dto.fireResistance());
        unit.setLeadership(dto.leadership());
        unit.setSpeed(dto.speed());
        unit.setChargeSpeed(dto.chargeSpeed());
        unit.setMeleeAttack(dto.meleeAttack());
        unit.setMeleeImbuement(meleeImbuement);
        unit.setAttackInterval(dto.attackInterval());
        unit.setHighThreat(dto.highThreat());
        unit.setSplashTargetSize(dto.splashTargetSize());
        unit.setSplashMaxAttacks(dto.splashMaxAttacks());
        unit.setMeleeDefense(dto.meleeDefense());
        unit.setWeaponStrength(dto.weaponStrength());
        unit.setMeleeBaseDamage(dto.meleeBaseDamage());
        unit.setMeleeApDamage(dto.meleeApDamage());
        unit.setMeleeBonusVsLarge(dto.meleeBonusVsLarge());
        unit.setMeleeBonusVsInfantry(dto.meleeBonusVsInfantry());
        unit.setChargeBonus(dto.chargeBonus());
        unit.setRangedMode(dto.rangedMode());
        unit.setUnlockBuilding(unlockBuilding);
        unit.setAllowBuilding(allowBuilding);
        unit.setUnitAttributes(unitAttributes);
        unit.setAbilities(abilities);
        unit.setPassiveAbilities(passiveAbilities);
        unit.setSpells(spells);
        unit.setItems(items);

        // Handle attribute lines separately
        if (unit.getAttributeLines() != null) {
            unit.getAttributeLines().clear();
        }
        if (dto.attributeLines() != null) {
            dto.attributeLines().forEach(lineDTO -> {
                UnitAttributeLine line = new UnitAttributeLine();
                line.setUnit(unit);
                line.setPosition(lineDTO.position());
                line.setContent(lineDTO.content());
                if (unit.getAttributeLines() != null) {
                    unit.getAttributeLines().add(line);
                }
            });
        }

        return unit;
    }

    private UnitDTO toDTO(@NonNull Unit u) {
        return new UnitDTO(
                u.getId(), u.getRace().getId(), u.getName(), u.getSlug(),
                u.getPicture(), u.getTier(), u.getCategory(), u.getRole(),
                u.getCategoryType(), u.getDescription(),
                u.getSize(), u.getEntities(), u.getMass(),
                u.getCampaignCost(), u.getBaseUpkeep(), u.getMultiplayerCost(),
                u.getHealth(), u.getHealthPerEntity(), u.getBarrier(),
                u.getArmour(), u.getParry(), u.getWardSave(),
                u.getPhysicalResistance(), u.getMissileResistance(),
                u.getSpellResistance(), u.getFireResistance(), u.getLeadership(),
                u.getSpeed(), u.getChargeSpeed(),
                u.getMeleeAttack(),
                u.getMeleeImbuement() != null ? toImbuementDTO(u.getMeleeImbuement()) : null,
                u.getAttackInterval(), u.getHighThreat(),
                u.getSplashTargetSize(), u.getSplashMaxAttacks(),
                u.getMeleeDefense(), u.getWeaponStrength(),
                u.getMeleeBaseDamage(), u.getMeleeApDamage(),
                u.getMeleeBonusVsLarge(), u.getMeleeBonusVsInfantry(), u.getChargeBonus(),
                u.getRangedMode(),
                u.getRangedWeapons() != null
                        ? u.getRangedWeapons().stream().map(rw -> toRangedWeaponDTO(rw)).toList()
                        : List.of(),
                u.getUnlockBuilding() != null ? u.getUnlockBuilding().getId() : null,
                u.getAllowBuilding() != null ? u.getAllowBuilding().getId() : null,
                u.getAttributeLines() != null
                        ? u.getAttributeLines().stream().map(al -> toAttributeLineDTO(al)).toList()
                        : List.of(),
                u.getUnitAttributes() != null
                        ? u.getUnitAttributes().stream().map(ua -> toUnitAttributeDTO(ua)).toList()
                        : List.of(),
                u.getAbilities() != null
                        ? u.getAbilities().stream().map(a -> toAbilitySummaryDTO(a)).toList()
                        : List.of(),
                u.getPassiveAbilities() != null
                        ? u.getPassiveAbilities().stream().map(a -> toAbilitySummaryDTO(a)).toList()
                        : List.of(),
                u.getSpells() != null
                        ? u.getSpells().stream().map(s -> toSpellSummaryDTO(s)).toList()
                        : List.of(),
                u.getItems() != null
                        ? u.getItems().stream().map(i -> toItemSummaryDTO(i)).toList()
                        : List.of()
        );
    }

    private UnitSummaryDTO toSummaryDTO(@NonNull Unit u) {
        return new UnitSummaryDTO(
                u.getId(), u.getRace().getId(), u.getName(), u.getSlug(),
                u.getPicture(), u.getTier(), u.getCategory(),
                u.getRole(), u.getCategoryType()
        );
    }

    private ImbuementDTO toImbuementDTO(@NonNull Imbuement i) {
        return new ImbuementDTO(i.getId(), i.getName(), i.getDescription(), i.getIcon());
    }

    private RangedWeaponDTO toRangedWeaponDTO(@NonNull RangedWeapon rw) {
        return new RangedWeaponDTO(
                rw.getId(), rw.getWeaponSlot(),
                rw.getImbuement() != null ? rw.getImbuement().getId() : null,
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

    private UnitAttributeLineDTO toAttributeLineDTO(@NonNull UnitAttributeLine al) {
        return new UnitAttributeLineDTO(al.getId(), al.getPosition(), al.getContent());
    }

    private UnitAttributeDTO toUnitAttributeDTO(@NonNull UnitAttribute ua) {
        return new UnitAttributeDTO(ua.getId(), ua.getName(), ua.getDescription());
    }

    private AbilitySummaryDTO toAbilitySummaryDTO(@NonNull Ability a) {
        return new AbilitySummaryDTO(a.getId(), a.getName(), a.getSlug(), a.getType());
    }

    private SpellSummaryDTO toSpellSummaryDTO(@NonNull Spell s) {
        return new SpellSummaryDTO(s.getId(), s.getLore().getId(), s.getName(), s.getSlug(), s.getType());
    }

    private ItemSummaryDTO toItemSummaryDTO(@NonNull Item i) {
        return new ItemSummaryDTO(
                i.getId(), i.getName(), i.getSlug(),
                i.getCategory(), i.getRarity(),
                i.getRace() != null ? i.getRace().getId() : null
        );
    }
}