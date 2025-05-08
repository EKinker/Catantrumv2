package org.kinker.service;

import org.kinker.api.GameDetail;
import org.kinker.api.GameItem;
import org.kinker.client.BGGClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class GameSearchService {
    private final BGGClient client;

    public GameSearchService(BGGClient client) {
        this.client = client;
    }

    public Mono<List<GameItem>> searchGames(String query, boolean exactMatch) {
        return client.searchGames(query, exactMatch);
    }

    public Mono<List<GameDetail>> searchGameDetail(List<Long> idList) {
        return client.searchGameDetail(idList);
    }
}
