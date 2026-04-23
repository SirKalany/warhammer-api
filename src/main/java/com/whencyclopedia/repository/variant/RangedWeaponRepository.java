package com.whencyclopedia.repository.variant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whencyclopedia.domain.variant.RangedWeapon;

import java.util.List;

@Repository
public interface RangedWeaponRepository extends JpaRepository<RangedWeapon, Long> {
    List<RangedWeapon> findByUnitId(Long unitId);
}