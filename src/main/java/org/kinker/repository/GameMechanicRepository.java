package org.kinker.repository;

import org.kinker.entities.GameMechanic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameMechanicRepository extends JpaRepository<GameMechanic, Long> {
}
