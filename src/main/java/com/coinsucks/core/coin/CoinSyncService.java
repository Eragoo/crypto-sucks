package com.coinsucks.core.coin;

import com.coinsucks.core.coin.coingecko.CoinGeckoClient;
import com.coinsucks.core.coin.coingecko.GeckoCoin;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CoinSyncService {
    private final CoinGeckoClient geckoClient;
    private final CoinRepository coinRepository;

    @Transactional
    public void syncCoins() {
        geckoClient.getCoins(1000, 1).forEach(geckoCoin -> {
            Optional<Coin> coin = coinRepository.findByCoinGeckoId(geckoCoin.getId());
            coin.ifPresent(c -> c.updatePriceInfo(geckoCoin));
            if (coin.isEmpty()) {
                Coin coin1 = new Coin(geckoCoin);
                coinRepository.save(coin1);
            }
        });
    }
}
