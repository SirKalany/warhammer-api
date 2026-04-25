package com.whencyclopedia.service.variant;

import com.whencyclopedia.domain.identity.GameVersion;
import com.whencyclopedia.domain.identity.UnitAttribute;
import com.whencyclopedia.domain.variant.UnitAttributeVariant;
import com.whencyclopedia.dto.variant.shared.UnitAttributeVariantDTO;
import com.whencyclopedia.repository.identity.GameVersionRepository;
import com.whencyclopedia.repository.identity.UnitAttributeRepository;
import com.whencyclopedia.repository.variant.UnitAttributeVariantRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UnitAttributeVariantService {

    private final UnitAttributeVariantRepository unitAttributeVariantRepository;
    private final UnitAttributeRepository unitAttributeRepository;
    private final GameVersionRepository gameVersionRepository;

    public List<UnitAttributeVariantDTO> findByVersion(Long versionId) {
        return unitAttributeVariantRepository.findByGameVersionId(versionId)
                .stream()
                .map(av -> toDTO(av))
                .toList();
    }

    public Optional<UnitAttributeVariantDTO> findByAttributeAndVersion(Long attributeId, Long versionId) {
        return unitAttributeVariantRepository.findByUnitAttributeIdAndGameVersionId(attributeId, versionId)
                .map(av -> toDTO(av));
    }

    public UnitAttributeVariantDTO create(UnitAttributeVariantDTO dto) {
        UnitAttribute attribute = unitAttributeRepository.findById(dto.unitAttributeId())
                .orElseThrow(() -> new EntityNotFoundException("Unit attribute not found: " + dto.unitAttributeId()));
        GameVersion version = gameVersionRepository.findById(dto.gameVersionId())
                .orElseThrow(() -> new EntityNotFoundException("Version not found: " + dto.gameVersionId()));
        UnitAttributeVariant variant = UnitAttributeVariant.builder()
                .unitAttribute(attribute)
                .gameVersion(version)
                .description(dto.description())
                .build();
        return toDTO(unitAttributeVariantRepository.save(variant));
    }

    public UnitAttributeVariantDTO update(Long id, UnitAttributeVariantDTO dto) {
        UnitAttributeVariant variant = unitAttributeVariantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unit attribute variant not found: " + id));
        variant.setDescription(dto.description());
        return toDTO(unitAttributeVariantRepository.save(variant));
    }

    public void delete(Long id) {
        unitAttributeVariantRepository.deleteById(id);
    }

    private UnitAttributeVariantDTO toDTO(@NonNull UnitAttributeVariant av) {
        return new UnitAttributeVariantDTO(
                av.getId(), av.getUnitAttribute().getId(),
                av.getUnitAttribute().getName(),
                av.getGameVersion().getId(),
                av.getDescription()
        );
    }
}