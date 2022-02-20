package com.coinsucks.core.coin.dto;

import com.coinsucks.core.coin.Coin;
import com.coinsucks.core.security.AuthenticatedUser;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class CoinOutputDto {
    private final Long id;
    private final String symbol;
    private final String image;
    private final String name;
    private String coinGeckoId;
    private final Long maxSupply;
    private final BigDecimal currentPrice;
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
    private final boolean favorite;

    public CoinOutputDto(Coin coin, AuthenticatedUser user) {
        this.id = coin.getId();
        this.symbol = coin.getSymbol();
        this.maxSupply = coin.getMaxSupply();
        this.image = coin.getImage();
        this.name = coin.getName();
        this.currentPrice = coin.getCurrentPrice();
        this.marketCap = coin.getMarketCap();
        this.marketCapRank = coin.getMarketCapRank();
        this.fullyDilutedValuation = coin.getFullyDilutedValuation();
        this.totalVolume = coin.getTotalVolume();
        this.high24h = coin.getHigh24h();
        this.low24h = coin.getLow24h();
        this.priceChange24h = coin.getPriceChange24h();
        this.priceChangePercentage24h = coin.getPriceChangePercentage24h();
        this.marketCapChange24h = coin.getMarketCapChange24h();
        this.marketCapChangePercentage24h = coin.getMarketCapChangePercentage24h();
        this.circulatingSupply = coin.getCirculatingSupply();
        this.totalSupply = coin.getTotalSupply();
        this.favorite = coin.isFavorite(user);
        this.coinGeckoId = coin.getCoinGeckoId();
    }
}
