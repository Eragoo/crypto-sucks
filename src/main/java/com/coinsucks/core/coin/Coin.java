package com.coinsucks.core.coin;

import com.coinsucks.core.coin.coingecko.GeckoCoin;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Coin {
    private static final String GENERATOR = "coin_generator";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GENERATOR)
    @SequenceGenerator(name = GENERATOR, sequenceName = "coin_sequence")
    private Long id;
    private String symbol;
    private String image;
    private String name;
    private String coinGeckoId;
    private Long maxSupply;
    private Double currentPrice;
    private String marketCap;
    private Integer marketCapRank;
    private Long fullyDilutedValuation;
    private Long totalVolume;
    private Double high24h;
    private Double low24h;
    private Double priceChange24h;
    private Double priceChangePercentage24h;
    private Double marketCapChange24h;
    private Double marketCapChangePercentage24h;
    private Long circulatingSupply;
    private Long totalSupply;

    public void updatePriceInfo(GeckoCoin geckoCoin) {
        this.currentPrice = geckoCoin.getCurrentPrice();
        this.marketCap = geckoCoin.getMarketCap();
        this.marketCapRank = geckoCoin.getMarketCapRank();
        this.fullyDilutedValuation = geckoCoin.getFullyDilutedValuation();
        this.totalVolume = geckoCoin.getTotalVolume();
        this.high24h = geckoCoin.getHigh24h();
        this.low24h = geckoCoin.getLow24h();
        this.priceChange24h = geckoCoin.getPriceChange24h();
        this.priceChangePercentage24h = geckoCoin.getPriceChangePercentage24h();
        this.marketCapChange24h = geckoCoin.getMarketCapChange24h();
        this.marketCapChangePercentage24h = geckoCoin.getMarketCapChangePercentage24h();
        this.circulatingSupply = geckoCoin.getCirculatingSupply();
        this.totalSupply = geckoCoin.getTotalSupply();
    }

    public Coin(GeckoCoin geckoCoin) {
        this.symbol = geckoCoin.getSymbol();
        this.image = geckoCoin.getImage();
        this.name = geckoCoin.getName();
        this.coinGeckoId = geckoCoin.getId();
        this.maxSupply = geckoCoin.getMaxSupply();
        this.currentPrice = geckoCoin.getCurrentPrice();
        this.marketCap = geckoCoin.getMarketCap();
        this.marketCapRank = geckoCoin.getMarketCapRank();
        this.fullyDilutedValuation = geckoCoin.getFullyDilutedValuation();
        this.totalVolume = geckoCoin.getTotalVolume();
        this.high24h = geckoCoin.getHigh24h();
        this.low24h = geckoCoin.getLow24h();
        this.priceChange24h = geckoCoin.getPriceChange24h();
        this.priceChangePercentage24h = geckoCoin.getPriceChangePercentage24h();
        this.marketCapChange24h = geckoCoin.getMarketCapChange24h();
        this.marketCapChangePercentage24h = geckoCoin.getMarketCapChangePercentage24h();
        this.circulatingSupply = geckoCoin.getCirculatingSupply();
        this.totalSupply = geckoCoin.getTotalSupply();
    }
}
