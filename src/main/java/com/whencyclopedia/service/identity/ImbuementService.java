package com.whencyclopedia.service.identity;

import com.whencyclopedia.domain.identity.Imbuement;
import com.whencyclopedia.dto.identity.shared.ImbuementDTO;
import com.whencyclopedia.repository.identity.ImbuementRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImbuementService {

    private final ImbuementRepository imbuementRepository;

    public List<ImbuementDTO> findAll() {
        return imbuementRepository.findAll()
                .stream()
                .map(i -> toDTO(i))
                .toList();
    }

    public ImbuementDTO findBySlug(String slug) {
        Imbuement imbuement = imbuementRepository.findBySlug(slug)
                .orElseThrow(() -> new EntityNotFoundException("Imbuement not found: " + slug));
        return toDTO(imbuement);
    }

    public ImbuementDTO create(ImbuementDTO dto) {
        Imbuement imbuement = Imbuement.builder()
                .name(dto.name())
                .slug(dto.slug())
                .build();
        return toDTO(imbuementRepository.save(imbuement));
    }

    public ImbuementDTO update(Long id, ImbuementDTO dto) {
        Imbuement imbuement = imbuementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Imbuement not found: " + id));
        imbuement.setName(dto.name());
        imbuement.setSlug(dto.slug());
        return toDTO(imbuementRepository.save(imbuement));
    }

    public void delete(Long id) {
        imbuementRepository.deleteById(id);
    }

    private ImbuementDTO toDTO(@NonNull Imbuement i) {
        return new ImbuementDTO(i.getId(), i.getName(), i.getSlug());
    }
}