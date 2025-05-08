package org.kinker.controller;

import org.kinker.api.GameDetail;
import org.kinker.api.GameItem;
import org.kinker.service.GameSearchService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/search")
public class GameSearchController {
    private final GameSearchService service;

    public GameSearchController(GameSearchService service) {
        this.service = service;
    }

    @GetMapping("/games")
    public Mono<List<GameItem>> search(@RequestParam(name = "query") String query, @RequestParam(name = "exact", required = false, defaultValue = "false") boolean exactMatch) {
        return service.searchGames(query, exactMatch);
    }

    @GetMapping("/details")
    public Mono<List<GameDetail>> searchGameDetail(@RequestParam(name = "id") List<Long> idList) {
        return service.searchGameDetail(idList);
    }
}
