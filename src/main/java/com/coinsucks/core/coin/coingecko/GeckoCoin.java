package com.coinsucks.core.coin.coingecko;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;


@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@AllArgsConstructor
public class GeckoCoin {
    private final String id;
    private final String symbol;
    private final String name;
    private final String image;
    private final Double currentPrice;
    private final String marketCap;
    private final Integer marketCapRank;
    private final Long fullyDilutedValuation;
    private final Long totalVolume;
    private final Double high24h;
    private final Double low24h;
    private final Double priceChange24h;
    private final Double priceChangePercentage24h;
    private final Double marketCapChange24h;
    private final Double marketCapChangePercentage24h;
    private final Long circulatingSupply;
    private final Long totalSupply;
    private final Long maxSupply;
}
