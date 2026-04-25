package com.whencyclopedia.dto.variant.unit;

import com.whencyclopedia.domain.enums.RangedMode;
import com.whencyclopedia.domain.enums.UnitCategoryType;
import com.whencyclopedia.domain.enums.UnitRole;
import com.whencyclopedia.dto.variant.ability.AbilityVariantSummaryDTO;
import com.whencyclopedia.dto.variant.item.ItemVariantSummaryDTO;
import com.whencyclopedia.dto.variant.shared.*;
import com.whencyclopedia.dto.variant.spell.SpellVariantSummaryDTO;

import java.math.BigDecimal;
import java.util.List;

public record UnitVariantDTO(
        Long id,
        Long unitId,
        String name,
        String slug,
        Long gameVersionId,
        String displayName,
        List<Long> raceIds,

        // Display
        String picture,
        Short tier,
        String category,
        UnitCategoryType categoryType,
        UnitRole role,
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
        ImbuementVariantDTO meleeImbuement,
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

        // Building requirements
        Long unlockBuildingVariantId,
        Long allowBuildingVariantId,

        // Relations (display)
        List<UnitVariantAttributeLineDTO> attributeLines,
        List<UnitAttributeVariantDTO> unitAttributes,
        List<AbilityVariantSummaryDTO> abilities,
        List<AbilityVariantSummaryDTO> passiveAbilities,
        List<SpellVariantSummaryDTO> spells,
        List<ItemVariantSummaryDTO> items,

        // Relations (ids for create/update)
        List<Long> abilityVariantIds,
        List<Long> passiveAbilityVariantIds,
        List<Long> spellVariantIds,
        List<Long> itemVariantIds,
        List<Long> unitAttributeIds,
        Long meleeImbuementVariantId,
        Long unlockBuildingId,
        Long allowBuildingId
) {}