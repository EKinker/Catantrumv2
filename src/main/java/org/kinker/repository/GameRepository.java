package org.kinker.repository;

import org.kinker.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByOwned(boolean owned);
}
