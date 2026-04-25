package com.whencyclopedia.service.identity;

import com.whencyclopedia.domain.identity.UnitAttribute;
import com.whencyclopedia.dto.identity.shared.UnitAttributeDTO;
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
                .map(a -> toDTO(a))
                .toList();
    }

    public UnitAttributeDTO findById(Long id) {
        return unitAttributeRepository.findById(id)
                .map(a -> toDTO(a))
                .orElseThrow(() -> new EntityNotFoundException("Unit attribute not found: " + id));
    }

    public UnitAttributeDTO create(UnitAttributeDTO dto) {
        UnitAttribute attribute = UnitAttribute.builder()
                .name(dto.name())
                .build();
        return toDTO(unitAttributeRepository.save(attribute));
    }

    public UnitAttributeDTO update(Long id, UnitAttributeDTO dto) {
        UnitAttribute attribute = unitAttributeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unit attribute not found: " + id));
        attribute.setName(dto.name());
        return toDTO(unitAttributeRepository.save(attribute));
    }

    public void delete(Long id) {
        unitAttributeRepository.deleteById(id);
    }

    private UnitAttributeDTO toDTO(@NonNull UnitAttribute a) {
        return new UnitAttributeDTO(a.getId(), a.getName());
    }
}