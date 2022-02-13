package com.coinsucks.core.coin;

import com.coinsucks.core.coin.coingecko.CoinGeckoClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class CoinSyncService {
    private final CoinGeckoClient geckoClient;
    private final CoinRepository coinRepository;

    @Transactional
    public void syncCoins() {
        log.info("Starting sync coin process...");
        geckoClient.getCoins(1000, 1).forEach(geckoCoin -> {
            Optional<Coin> coin = coinRepository.findByCoinGeckoId(geckoCoin.getId());
            coin.ifPresent(c -> c.updatePriceInfo(geckoCoin));
            if (coin.isEmpty()) {
                Coin coin1 = new Coin(geckoCoin);
                coinRepository.save(coin1);
            }
        });
        log.info("Finished sync coin process!");
    }
}
