package com.coinsucks.core.wallet.dto.output;

import com.coinsucks.core.coin.Coin;
import com.coinsucks.core.coin.dto.CoinOutputDto;
import com.coinsucks.core.security.AuthenticatedUser;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CoinWalletStateOutputDto {
    private final CoinOutputDto coin;
    private final BigDecimal amount;

    public CoinWalletStateOutputDto(Coin coin, BigDecimal amount, AuthenticatedUser user) {
        this.coin = new CoinOutputDto(coin, user);
        this.amount = amount;
    }
}
