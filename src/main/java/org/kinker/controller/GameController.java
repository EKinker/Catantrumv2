package org.kinker.controller;

import org.kinker.entities.Game;
import org.kinker.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
//TODO Setup Global CORS configuration
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    GameRepository repository;

    @PostMapping("/add")
    public Game addGame(@RequestBody Game game){
        return repository.save(game);
    }

    @GetMapping("/owned")
    public List<Game> getOwnedGames(){
        return repository.findByOwned(true);
    }

}

