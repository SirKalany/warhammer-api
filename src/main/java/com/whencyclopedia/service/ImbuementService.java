package com.whencyclopedia.service;

import com.whencyclopedia.domain.Imbuement;
import com.whencyclopedia.dto.shared.ImbuementDTO;
import com.whencyclopedia.repository.ImbuementRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImbuementService {

    private final ImbuementRepository imbuementRepository;

    public List<ImbuementDTO> findAll() {
        return imbuementRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public ImbuementDTO findById(Long id) {
        return imbuementRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Imbuement not found: " + id));
    }

    public ImbuementDTO create(ImbuementDTO dto) {
        Imbuement imbuement = Imbuement.builder()
                .name(dto.name())
                .description(dto.description())
                .icon(dto.icon())
                .build();
        return toDTO(imbuementRepository.save(imbuement));
    }

    public ImbuementDTO update(Long id, ImbuementDTO dto) {
        Imbuement imbuement = imbuementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Imbuement not found: " + id));
        imbuement.setName(dto.name());
        imbuement.setDescription(dto.description());
        imbuement.setIcon(dto.icon());
        return toDTO(imbuementRepository.save(imbuement));
    }

    public void delete(Long id) {
        imbuementRepository.deleteById(id);
    }

    private ImbuementDTO toDTO(Imbuement i) {
        return new ImbuementDTO(i.getId(), i.getName(), i.getDescription(), i.getIcon());
    }
}