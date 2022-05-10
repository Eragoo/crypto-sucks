package com.coinsucks.core.wallet.dto;

import com.coinsucks.core.coin.Coin;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@AllArgsConstructor
@Getter
public class CoinWalletState {
    private final Coin coin;
    private BigDecimal amount = BigDecimal.ZERO;

    private BigDecimal avgBuyPrice = BigDecimal.ZERO;

    public void addAmount(BigDecimal value, BigDecimal usdPrice) {
        BigDecimal a = avgBuyPrice.multiply(amount);
        BigDecimal b = a.add(usdPrice);
        BigDecimal c = amount.add(value);
        avgBuyPrice = b.divide(c, RoundingMode.UP);
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
