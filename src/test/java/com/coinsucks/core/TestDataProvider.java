package com.coinsucks.core;

import com.coinsucks.core.coin.Coin;
import com.coinsucks.core.user.User;
import com.coinsucks.core.user.UserAuthType;

import java.math.BigDecimal;

public class TestDataProvider {
    public static Coin getCoin() {
        Coin coin = new Coin();
        coin.setId(1L);
        coin.setCurrentPrice(BigDecimal.ONE);
        coin.setName("COIN");

        return coin;
    }

    public static User getUser() {
        return new User("email", "username", "pass", UserAuthType.BASIC);
    }
}
