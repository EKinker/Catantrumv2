package org.kinker.api;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class GameItem {
    @XmlAttribute(name = "id")
    private long id;

    @XmlAttribute(name = "type")
    private String type;
    @XmlElement(name = "name")
    private Name name;
    @XmlElement(name = "yearpublished")
    private YearPublished yearPublished;

    public long getId(){
        return id;
    }
    public String getType(){
        return type;
    }
    public String getNameType(){
        return name != null ? name.type : null;
    }
    public String getNameValue(){
        return name != null ? name.value : null;
    }
    public Integer getYearPublished(){
        return yearPublished != null ? yearPublished.value : null;
    }
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Name {
        @XmlAttribute
        String type;
        @XmlAttribute
        String value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class YearPublished {
        @XmlAttribute
        Integer value;
    }
}
