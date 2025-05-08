package org.kinker.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class GameDetail {
    @XmlAttribute
    private long id;
    @XmlElement(name = "name")
    private List<Name> names;
    private String thumbnail;
    private String image;
    @XmlElement(name="description")
    private String description;
    @XmlElement(name = "yearpublished")
    private ItemValue yearPublished;

    @XmlElement(name = "minplayers")
    private ItemValue minPlayers;

    @XmlElement(name = "maxplayers")
    private ItemValue maxPlayers;

    @XmlElement(name = "minplaytime")
    private ItemValue minTime;

    @XmlElement(name = "maxplaytime")
    private ItemValue maxTime;

    @XmlElement(name = "minage")
    private ItemValue minAge;

    @XmlElement(name = "link")
    private List<AdditionalDetail> additionalDetails;

    @XmlElement(name = "statistics")
    private Statistics statistics;

    public long getBggId() {
        return id;
    }

    public String getName() {
        if (names != null) {
            return names.stream()
                    .filter(name -> "primary".equalsIgnoreCase(name.type))
                    .map(name -> name.value)
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public Integer getYearPublished() {
        return yearPublished != null ? yearPublished.value : null;
    }

    public Integer getMinPlayers() {
        return minPlayers != null ? minPlayers.value : null;
    }

    public Integer getMaxPlayers() {
        return maxPlayers != null ? maxPlayers.value : null;
    }

    public Integer getMinTime() {
        return minTime != null ? minTime.value : null;
    }

    public Integer getMaxTime() {
        return maxTime != null ? maxTime.value : null;
    }

    public Integer getMinAge() {
        return minAge != null ? minAge.value : null;
    }

    public List<AdditionalDetail> getGameCategories() {
        if(additionalDetails == null) return List.of();
        return additionalDetails.stream()
                .filter(link -> "boardgamecategory".equalsIgnoreCase(link.type))
                .toList();
    }

    public List<AdditionalDetail> getGameMechanics(){
        if(additionalDetails == null) return List.of();
        return additionalDetails.stream()
                .filter(link -> "boardgamemechanic".equalsIgnoreCase(link.type))
                .toList();
    }

    public int getUsersRated() {
        return statistics != null & statistics.ratings != null && statistics.ratings.usersRated != null
                ? statistics.ratings.usersRated.value : 0;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Name {
        @XmlAttribute
        String type;
        @XmlAttribute
        String value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class ItemValue {
        @XmlAttribute
        Integer value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class AdditionalDetail {
        @XmlAttribute
        @JsonIgnore
        private String type;
        @XmlAttribute
        private long id;
        @XmlAttribute
        private String value;

        public String getType() {
            return type;
        }

        public long getDetailId() {
            return id;
        }

        public String getDetailValue() {
            return value;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Statistics {
        @XmlElement(name = "ratings")
        private Ratings ratings;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Ratings {
        @XmlElement(name = "usersrated")
        private UsersRated usersRated;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class UsersRated {
        @XmlAttribute(name = "value")
        private int value;
    }

}
