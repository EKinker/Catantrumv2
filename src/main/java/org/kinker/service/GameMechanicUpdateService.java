package org.kinker.service;

import org.kinker.api.GameDetail;
import org.kinker.entities.GameMechanic;
import org.kinker.repository.GameMechanicRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GameMechanicUpdateService {
    private final GameMechanicRepository gameMechanicRepository;

    public GameMechanicUpdateService(GameMechanicRepository gameMechanicRepository) {
        this.gameMechanicRepository = gameMechanicRepository;
    }

    public List<GameMechanic> updateGameMechanics(List<GameDetail.AdditionalDetail> details) {
        if (details == null || details.isEmpty()) {
            return List.of();
        }
        List<Long> ids = details.stream()
                .map(GameDetail.AdditionalDetail::getDetailId)
                .toList();
        List<GameMechanic> existingMechanics = gameMechanicRepository.findAllById(ids);
        Set<Long> existingMechanicIds = existingMechanics.stream().map(GameMechanic::getId).collect(Collectors.toSet());
        List<GameMechanic> mechanicsToAdd = details.stream()
                .filter(detail -> !existingMechanicIds.contains(detail.getDetailId()))
                .map(detail -> new GameMechanic(detail.getDetailId(), detail.getDetailValue()))
                .toList();
        List<GameMechanic> newMechanics = gameMechanicRepository.saveAll(mechanicsToAdd);
        List<GameMechanic> allMechanics = new ArrayList<>(existingMechanics);
        allMechanics.addAll(newMechanics);
        return allMechanics;
    }
}
