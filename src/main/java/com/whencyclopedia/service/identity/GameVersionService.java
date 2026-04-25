package com.whencyclopedia.service.identity;

import com.whencyclopedia.domain.identity.GameVersion;
import com.whencyclopedia.dto.version.GameVersionDTO;
import com.whencyclopedia.repository.identity.GameVersionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameVersionService {

    private final GameVersionRepository gameVersionRepository;

    public List<GameVersionDTO> findAll() {
        return gameVersionRepository.findAll()
                .stream()
                .map(v -> toDTO(v))
                .toList();
    }

    public GameVersionDTO findBySlug(String slug) {
        return gameVersionRepository.findBySlug(slug)
                .map(v -> toDTO(v))
                .orElseThrow(() -> new EntityNotFoundException("Version not found: " + slug));
    }

    public GameVersionDTO create(GameVersionDTO dto) {
        GameVersion version = GameVersion.builder()
                .name(dto.name())
                .slug(dto.slug())
                .icon(dto.icon())
                .build();
        return toDTO(gameVersionRepository.save(version));
    }

    public GameVersionDTO update(Long id, GameVersionDTO dto) {
        GameVersion version = gameVersionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Version not found: " + id));
        version.setName(dto.name());
        version.setSlug(dto.slug());
        version.setIcon(dto.icon());
        return toDTO(gameVersionRepository.save(version));
    }

    public void delete(Long id) {
        gameVersionRepository.deleteById(id);
    }

    private GameVersionDTO toDTO(@NonNull GameVersion v) {
        return new GameVersionDTO(v.getId(), v.getName(), v.getSlug(), v.getIcon());
    }
}