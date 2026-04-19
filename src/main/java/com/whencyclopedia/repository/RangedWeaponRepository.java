package com.whencyclopedia.repository;

import com.whencyclopedia.domain.RangedWeapon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RangedWeaponRepository extends JpaRepository<RangedWeapon, Long> {
    List<RangedWeapon> findByUnitId(Long unitId);
}