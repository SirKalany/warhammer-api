package com.whencyclopedia.repository.identity;

import com.whencyclopedia.domain.identity.LoreOfMagic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface LoreOfMagicRepository extends JpaRepository<LoreOfMagic, Long> {
    Optional<LoreOfMagic> findBySlug(String slug);
}