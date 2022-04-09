package com.coinsucks.core.wallet.dto.output;

import com.coinsucks.core.security.AuthenticatedUser;
import com.coinsucks.core.wallet.Wallet;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class WalletOutputDto {
    private final Long id;
    private final String name;
    private final BigDecimal currentUsdPrice;
    private final BigDecimal startUsdPrice;
    private final List<CoinWalletStateOutputDto> coins;

    public WalletOutputDto(Wallet wallet, AuthenticatedUser user) {
        this.id = wallet.getId();
        this.name = wallet.getName();
        this.currentUsdPrice = wallet.getCurrentPrice();
        this.startUsdPrice = wallet.getStartPrice();
        this.coins = wallet.getCurrentStateSorted()
                .map(c -> new CoinWalletStateOutputDto(c.getCoin(), c.getAmount(), user))
                .collect(Collectors.toList());
    }
}
