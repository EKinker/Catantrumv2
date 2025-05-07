package org.kinker.client;


import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import org.kinker.api.GameItem;
import org.kinker.api.GameItemList;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.StringReader;
import java.util.List;
import java.util.Optional;

@Service
public class BGGClient {

    private final WebClient webClient = WebClient.create("https://boardgamegeek.com/xmlapi2");

    public Mono<List<GameItem>> searchGames(String query, Boolean exactMatch) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search")
                        .queryParam("query", query)
                        .queryParamIfPresent("exact", exactMatch ? Optional.of(1) : Optional.empty())
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .map(this::parseXml);
    }

    private List<GameItem> parseXml(String xml){
        try {
            JAXBContext context = JAXBContext.newInstance(GameItemList.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader reader = new StringReader(xml);
            GameItemList items = (GameItemList) unmarshaller.unmarshal(reader);
            return items.getItems();
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse XML", e);
        }
    }

}
