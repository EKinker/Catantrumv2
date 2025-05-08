package org.kinker.service;

import org.kinker.api.GameDetail;
import org.kinker.api.GameItem;
import org.kinker.client.BGGClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameSearchService {
    private final BGGClient client;

    public GameSearchService(BGGClient client) {
        this.client = client;
    }

    public Mono<List<GameDetail>> searchGamesAndDetails(String query, boolean exactMatch) {
        return searchGames(query, exactMatch)
                .flatMap(game -> {
                    List<Long> ids = game.stream()
                            .map(GameItem::getId)
                            .collect(Collectors.toList());
                    return searchGameDetail(ids);
                });
    }
    public Mono<List<GameItem>> searchGames(String query, boolean exactMatch) {
        return filterOutFanExpansions(client.searchGames(query, exactMatch));
    }

    public Mono<List<GameDetail>> searchGameDetail(List<Long> idList) {
        return client.searchGameDetail(idList);
    }

    private Mono<List<GameItem>> filterOutFanExpansions(Mono<List<GameItem>> fullList) {
        return fullList.map(list -> list.stream()
                .filter(game -> {
                    String name = game.getNameValue();
                    return name == null || !name.toLowerCase().contains("fan expansion");
                })
                .toList()
        );
    }
}
