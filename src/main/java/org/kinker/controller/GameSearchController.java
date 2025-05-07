package org.kinker.controller;

import org.kinker.api.GameItem;
import org.kinker.service.GameSearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class GameSearchController {
    private final GameSearchService service;
    public GameSearchController(GameSearchService service) {
        this.service = service;
    }

    @GetMapping
    public Mono<List<GameItem>> search(@RequestParam(name="query") String query, @RequestParam(name = "exact", required = false, defaultValue = "false") boolean exactMatch) {
        return service.searchGames(query, exactMatch);
    }
}
