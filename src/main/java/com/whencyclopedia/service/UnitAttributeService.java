package com.whencyclopedia.service;

import com.whencyclopedia.domain.identity.UnitAttribute;
import com.whencyclopedia.dto.shared.UnitAttributeDTO;
import com.whencyclopedia.repository.identity.UnitAttributeRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UnitAttributeService {

    private final UnitAttributeRepository unitAttributeRepository;

    public List<UnitAttributeDTO> findAll() {
        return unitAttributeRepository.findAll()
                .stream()
                .map(ua -> toDTO(ua))
                .toList();
    }

    public UnitAttributeDTO findById(Long id) {
        return unitAttributeRepository.findById(id)
                .map(ua -> toDTO(ua))
                .orElseThrow(() -> new EntityNotFoundException("Unit attribute not found: " + id));
    }

    public UnitAttributeDTO findByName(String name) {
        return unitAttributeRepository.findByName(name)
                .map(ua -> toDTO(ua))
                .orElseThrow(() -> new EntityNotFoundException("Unit attribute not found: " + name));
    }

    public UnitAttributeDTO create(UnitAttributeDTO dto) {
        UnitAttribute attribute = UnitAttribute.builder()
                .name(dto.name())
                .description(dto.description())
                .build();
        return toDTO(unitAttributeRepository.save(attribute));
    }

    public UnitAttributeDTO update(Long id, UnitAttributeDTO dto) {
        UnitAttribute attribute = unitAttributeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unit attribute not found: " + id));
        attribute.setName(dto.name());
        attribute.setDescription(dto.description());
        return toDTO(unitAttributeRepository.save(attribute));
    }

    public void delete(Long id) {
        unitAttributeRepository.deleteById(id);
    }

    private UnitAttributeDTO toDTO(@NonNull UnitAttribute ua) {
        return new UnitAttributeDTO(ua.getId(), ua.getName(), ua.getDescription());
    }
}