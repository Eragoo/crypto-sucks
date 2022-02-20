package com.coinsucks.core.wallet;

import com.coinsucks.core.coin.Coin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static com.coinsucks.core.TestDataProvider.getCoin;

class WalletTest {

    @Test
    void walletAddToEmpty() {
        Coin coin = getCoin();

        Wallet wallet = new Wallet();
        wallet.setName("Wallet 1");

        wallet.add(coin, BigDecimal.ONE);

        Set<WalletCoin> coins = wallet.getCoins();
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, coins.size()),
                () -> Assertions.assertEquals(BigDecimal.ONE, wallet.getCoins().stream().findFirst().get().getAmount())
        );
    }

    @Test
    void walletAddSameCoinTwice() {
        Coin coin = getCoin();

        Wallet wallet = new Wallet();
        wallet.setName("Wallet 1");

        wallet.add(coin, BigDecimal.ONE);
        wallet.add(coin, BigDecimal.ONE);

        Set<WalletCoin> coins = wallet.getCoins();
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, coins.size()),
                () -> Assertions.assertEquals(BigDecimal.valueOf(2), wallet.getCoins().stream().findFirst().get().getAmount())
        );
    }

    @Test
    void removeJustAdded() {
        Coin coin = getCoin();

        Wallet wallet = new Wallet();
        wallet.setName("Wallet 1");

        wallet.add(coin, BigDecimal.ONE);
        wallet.remove(coin);

        Set<WalletCoin> coins = wallet.getCoins();
        Assertions.assertAll(
                () -> Assertions.assertEquals(0, coins.size())
        );
    }

    @Test
    void removeFromEmpty() {
        Coin coin = getCoin();

        Wallet wallet = new Wallet();
        wallet.setName("Wallet 1");

        wallet.remove(coin);

        Set<WalletCoin> coins = wallet.getCoins();
        Assertions.assertAll(
                () -> Assertions.assertEquals(0, coins.size())
        );
    }
}
