package com.coinsucks.core.wallet.dto;

import com.coinsucks.core.coin.Coin;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class CoinWalletState {
    private final Coin coin;
    private BigDecimal amount;

    public void addValue(BigDecimal value) {
        amount = amount.add(value);
    }
    public void subtractValue(BigDecimal value) {
        amount = amount.subtract(value);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CoinWalletState that = (CoinWalletState) o;

        return coin.equals(that.coin);
    }

    @Override
    public int hashCode() {
        return coin.hashCode();
    }
}
