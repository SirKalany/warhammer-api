package com.whencyclopedia.repository.identity;

import com.whencyclopedia.domain.identity.Imbuement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ImbuementRepository extends JpaRepository<Imbuement, Long> {
    Optional<Imbuement> findBySlug(String slug);
}