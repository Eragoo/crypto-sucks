package com.coinsucks.core.coin;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CoinSyncScheduler {
    private final CoinSyncService coinSyncService;

    @Scheduled(cron = "0 */5 * * * ?")
    public void syncCoins() {
        coinSyncService.syncCoins();
    }
}
