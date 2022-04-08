package com.coinsucks.core.wallet;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class CoinWalletStateDto {
    private final Long coinId;
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

        CoinWalletStateDto that = (CoinWalletStateDto) o;

        return coinId.equals(that.coinId);
    }

    @Override
    public int hashCode() {
        return coinId.hashCode();
    }
}
