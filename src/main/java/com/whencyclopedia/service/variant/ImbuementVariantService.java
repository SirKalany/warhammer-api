package com.whencyclopedia.service.variant;

import com.whencyclopedia.domain.identity.GameVersion;
import com.whencyclopedia.domain.identity.Imbuement;
import com.whencyclopedia.domain.variant.ImbuementVariant;
import com.whencyclopedia.dto.variant.shared.ImbuementVariantDTO;
import com.whencyclopedia.repository.identity.GameVersionRepository;
import com.whencyclopedia.repository.identity.ImbuementRepository;
import com.whencyclopedia.repository.variant.ImbuementVariantRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImbuementVariantService {

    private final ImbuementVariantRepository imbuementVariantRepository;
    private final ImbuementRepository imbuementRepository;
    private final GameVersionRepository gameVersionRepository;

    public List<ImbuementVariantDTO> findByVersion(Long versionId) {
        return imbuementVariantRepository.findByGameVersionId(versionId)
                .stream()
                .map(iv -> toDTO(iv))
                .toList();
    }

    public Optional<ImbuementVariantDTO> findByImbuementAndVersion(Long imbuementId, Long versionId) {
        return imbuementVariantRepository.findByImbuementIdAndGameVersionId(imbuementId, versionId)
                .map(iv -> toDTO(iv));
    }

    public ImbuementVariantDTO create(ImbuementVariantDTO dto) {
        Imbuement imbuement = imbuementRepository.findById(dto.imbuementId())
                .orElseThrow(() -> new EntityNotFoundException("Imbuement not found: " + dto.imbuementId()));
        GameVersion version = gameVersionRepository.findById(dto.gameVersionId())
                .orElseThrow(() -> new EntityNotFoundException("Version not found: " + dto.gameVersionId()));
        ImbuementVariant variant = ImbuementVariant.builder()
                .imbuement(imbuement)
                .gameVersion(version)
                .description(dto.description())
                .icon(dto.icon())
                .build();
        return toDTO(imbuementVariantRepository.save(variant));
    }

    public ImbuementVariantDTO update(Long id, ImbuementVariantDTO dto) {
        ImbuementVariant variant = imbuementVariantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Imbuement variant not found: " + id));
        variant.setDescription(dto.description());
        variant.setIcon(dto.icon());
        return toDTO(imbuementVariantRepository.save(variant));
    }

    public void delete(Long id) {
        imbuementVariantRepository.deleteById(id);
    }

    private ImbuementVariantDTO toDTO(@NonNull ImbuementVariant iv) {
        return new ImbuementVariantDTO(
                iv.getId(), iv.getImbuement().getId(),
                iv.getImbuement().getName(), iv.getImbuement().getSlug(),
                iv.getGameVersion().getId(),
                iv.getDescription(), iv.getIcon()
        );
    }
}