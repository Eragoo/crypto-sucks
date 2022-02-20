package com.coinsucks.core.coin;

import com.coinsucks.core.coin.coingecko.GeckoCoin;
import com.coinsucks.core.coin.favorite.Favorite;
import com.coinsucks.core.security.AuthenticatedUser;
import com.coinsucks.core.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
    private BigDecimal currentPrice;
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

    @OneToMany(mappedBy = "coin")
    private Set<Favorite> favorites = new HashSet<>();

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



    public Optional<Favorite> addToFavorite(User user) {
        Favorite favorite = new Favorite(this, user);
        if (favorites.contains(favorite)) {
            return Optional.empty();
        }

        favorites.add(favorite);
        return Optional.of(favorite);
    }

    public boolean isFavorite(AuthenticatedUser user) {
        return favorites.stream().anyMatch(f -> f.getUser().getUsername().equals(user.getUsername()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coin coin = (Coin) o;

        return id != null ? id.equals(coin.id) : coin.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
