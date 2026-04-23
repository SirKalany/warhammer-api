package com.whencyclopedia.dto.unit;

import com.whencyclopedia.dto.shared.ImbuementDTO;
import com.whencyclopedia.dto.shared.UnitAttributeDTO;
import com.whencyclopedia.domain.enums.RangedMode;
import com.whencyclopedia.domain.enums.UnitCategoryType;
import com.whencyclopedia.domain.enums.UnitRole;
import com.whencyclopedia.dto.ability.AbilitySummaryDTO;
import com.whencyclopedia.dto.spell.SpellSummaryDTO;
import com.whencyclopedia.dto.item.ItemSummaryDTO;

import java.math.BigDecimal;
import java.util.List;

public record UnitDTO(
        Long id,
        Long raceId,
        String name,
        String slug,
        String picture,
        Short tier,
        String category,
        UnitRole role,
        UnitCategoryType categoryType,
        String description,

        // Overview
        String size,
        Integer entities,
        BigDecimal mass,
        Integer campaignCost,
        Integer baseUpkeep,
        Integer multiplayerCost,

        // Survivability
        Integer health,
        Integer healthPerEntity,
        Integer barrier,
        Integer armour,
        BigDecimal parry,
        BigDecimal wardSave,
        BigDecimal physicalResistance,
        BigDecimal missileResistance,
        BigDecimal spellResistance,
        BigDecimal fireResistance,
        Integer leadership,

        // Mobility
        Integer speed,
        Integer chargeSpeed,

        // Melee
        Integer meleeAttack,
        ImbuementDTO meleeImbuement,
        BigDecimal attackInterval,
        Boolean highThreat,
        String splashTargetSize,
        Integer splashMaxAttacks,
        Integer meleeDefense,
        Integer weaponStrength,
        Integer meleeBaseDamage,
        Integer meleeApDamage,
        Integer meleeBonusVsLarge,
        Integer meleeBonusVsInfantry,
        Integer chargeBonus,

        // Ranged
        RangedMode rangedMode,
        List<RangedWeaponDTO> rangedWeapons,

        // Buildings
        Long unlockBuildingId,
        Long allowBuildingId,

        // Relations
        List<UnitAttributeLineDTO> attributeLines,
        List<UnitAttributeDTO> unitAttributes,
        List<AbilitySummaryDTO> abilities,
        List<AbilitySummaryDTO> passiveAbilities,
        List<SpellSummaryDTO> spells,
        List<ItemSummaryDTO> items
) {}