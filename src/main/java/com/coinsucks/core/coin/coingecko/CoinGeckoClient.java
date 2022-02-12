package com.coinsucks.core.coin.coingecko;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class CoinGeckoClient {
    private final RestOperations restOperations;

    public Collection<GeckoCoin> getCoins(int perPage, int pageNumber) {
        String order = "market_cap_desc";
        String vsCurrency = "usd";

        ResponseEntity<GeckoCoin[]> response = restOperations.getForEntity(
                "https://api.coingecko.com/api/v3/coins/markets",
                GeckoCoin[].class,
                Map.of("order", order, "vs_currency", vsCurrency, "per_page", perPage, "page", pageNumber, "sparkline", false)
        );

        if (!response.getStatusCode().equals(HttpStatus.OK) || response.getBody() == null) {
            throw new RuntimeException("Error on getting crypto currencies data");
        }

        return List.of(response.getBody());
    }
}
