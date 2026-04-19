package com.whencyclopedia.repository;

import com.whencyclopedia.domain.UnitAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UnitAttributeRepository extends JpaRepository<UnitAttribute, Long> {
    Optional<UnitAttribute> findByName(String name);
}