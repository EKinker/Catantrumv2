package org.kinker.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class GameMechanic {
    @Id
    private long id;
    private String name;

    public GameMechanic() {}

    public GameMechanic(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
