package org.kinker.api;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "items")
@XmlAccessorType(XmlAccessType.FIELD)
public class GameDetailList {
    @XmlElement(name="item")
    private List<GameDetail> items;

    public List<GameDetail> getItems() {
        return items;
    }
}
