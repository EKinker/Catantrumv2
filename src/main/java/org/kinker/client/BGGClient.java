package org.kinker.client;


import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import org.kinker.api.GameDetail;
import org.kinker.api.GameDetailList;
import org.kinker.api.GameItem;
import org.kinker.api.GameItemList;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.StringReader;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BGGClient {

    private static final String BASE_URL = "https://boardgamegeek.com/xmlapi2";
    private final WebClient webClient;

    public BGGClient() {
        this.webClient = WebClient.builder()
                .baseUrl(BASE_URL)
                .build();
    }

    public Mono<List<GameItem>> searchGames(String query, boolean exactMatch) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search")
                        .queryParam("query", query)
                        .queryParam("type", "boardgame")
                        .queryParamIfPresent("exact", exactMatch ? Optional.of(1) : Optional.empty())
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .map(xml -> parseXml(xml, GameItemList.class))
                .map(GameItemList::getItems);
    }

    public Mono<List<GameDetail>> searchGameDetail(List<Long> idList) {
        String idString = idList.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/thing")
                        .queryParam("stats", 1)
                        .queryParam("id", idString)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .map(xml -> parseXml(xml, GameDetailList.class))
                .map(GameDetailList::getItems);
    }

    private <T> T parseXml(String xml, Class<T> tClass){
        try (StringReader reader = new StringReader(xml)){
            JAXBContext context = JAXBContext.newInstance(tClass);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return tClass.cast(unmarshaller.unmarshal(reader));
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse XML", e);
        }
    }
}
