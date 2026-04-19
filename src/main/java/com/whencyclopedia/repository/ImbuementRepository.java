package com.whencyclopedia.repository;

import com.whencyclopedia.domain.Imbuement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ImbuementRepository extends JpaRepository<Imbuement, Long> {
    Optional<Imbuement> findByName(String name);
}