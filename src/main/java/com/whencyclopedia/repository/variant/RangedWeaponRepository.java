package com.whencyclopedia.repository.variant;

import com.whencyclopedia.domain.variant.RangedWeapon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RangedWeaponRepository extends JpaRepository<RangedWeapon, Long> {
    List<RangedWeapon> findByUnitVariantId(Long unitVariantId);
}