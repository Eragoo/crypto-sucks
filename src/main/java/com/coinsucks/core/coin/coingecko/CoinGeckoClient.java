package com.coinsucks.core.coin.coingecko;

import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class CoinGeckoClient {
    private final RestOperations restOperations;

    public Collection<GeckoCoin> getCoins(int perPage, int pageNumber) {
        String order = "market_cap_desc";
        String vsCurrency = "usd";

        String url = "https://api.coingecko.com/api/v3/coins/markets";
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("order", "{order}")
                .queryParam("vs_currency", "{vs_currency}")
                .queryParam("per_page", "{per_page}")
                .queryParam("page", "{page}")
                .queryParam("sparkline", "{sparkline}")
                .encode()
                .toUriString();

        Map<String, String> params = new HashMap<>();
        params.put("order", order);
        params.put("vs_currency", vsCurrency);
        params.put("per_page", String.valueOf(perPage));
        params.put("page", String.valueOf(pageNumber));
        params.put("sparkline", "false");

        ResponseEntity<GeckoCoin[]> response = restOperations.getForEntity(
                urlTemplate,
                GeckoCoin[].class,
                params
        );

        if (!response.getStatusCode().equals(HttpStatus.OK) || response.getBody() == null) {
            throw new RuntimeException("Error on getting crypto currencies data");
        }

        return List.of(response.getBody());
    }
}
