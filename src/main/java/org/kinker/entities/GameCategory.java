package org.kinker.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class GameCategory {
    @Id
    private long id;
    private String name;

    public GameCategory() {
    }

    public GameCategory(String name, long id) {
        this.name = name;
        this.id = id;
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
