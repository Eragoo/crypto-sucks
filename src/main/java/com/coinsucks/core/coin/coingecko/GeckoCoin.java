package com.coinsucks.core.coin.coingecko;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class GeckoCoin {
    private String id;
    private String symbol;
    private String name;
    private String image;
    private Double currentPrice;
    private String marketCap;
    private Integer marketCapRank;
    private Long fullyDilutedValuation;
    private Long totalVolume;
    @JsonProperty("high_24h")
    private Double high24h;
    @JsonProperty("low_24h")
    private Double low24h;
    @JsonProperty("price_change_24h")
    private Double priceChange24h;
    @JsonProperty("price_change_percentage_24h")
    private Double priceChangePercentage24h;
    @JsonProperty("market_cap_change_24h")
    private Double marketCapChange24h;
    @JsonProperty("market_cap_change_percentage_24h")
    private Double marketCapChangePercentage24h;
    private Long circulatingSupply;
    private Long totalSupply;
    private Long maxSupply;
}
