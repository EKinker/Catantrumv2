package org.kinker.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class GameDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String thumbnail;
    private String description;
    private Integer yearPublished;
    private Integer minPlayers;
    private Integer maxPlayers;
    private Integer minTime;
    private Integer maxTime;
    private Integer minAge;
    @ManyToMany
    @JoinTable(name="game_category_assoc",
    joinColumns = @JoinColumn(name = "game_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<GameCategory> categories;
    @ManyToMany
    @JoinTable(name = "game_mechanic_assoc",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "mechanic_id"))
    private List<GameMechanic> mechanics;
    private int usersRated;
    private boolean owned;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(Integer yearPublished) {
        this.yearPublished = yearPublished;
    }

    public Integer getMinPlayers() {
        return minPlayers;
    }

    public void setMinPlayers(Integer minPlayers) {
        this.minPlayers = minPlayers;
    }

    public Integer getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(Integer maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public Integer getMinTime() {
        return minTime;
    }

    public void setMinTime(Integer minTime) {
        this.minTime = minTime;
    }

    public Integer getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(Integer maxTime) {
        this.maxTime = maxTime;
    }


    public List<GameCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<GameCategory> categories) {
        this.categories = categories;
    }

    public List<GameMechanic> getMechanics() {
        return mechanics;
    }

    public void setMechanics(List<GameMechanic> mechanics) {
        this.mechanics = mechanics;
    }

    public int getUsersRated() {
        return usersRated;
    }

    public void setUsersRated(int usersRated) {
        this.usersRated = usersRated;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public boolean isOwned() {
        return owned;
    }

    public void setOwned(boolean owned) {
        this.owned = owned;
    }
}
