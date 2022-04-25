package com.coinsucks.core.wallet.dto.input;

import com.coinsucks.core.coin.Coin;
import com.coinsucks.core.wallet.Wallet;
import com.coinsucks.core.wallet.transaction.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class SwapCoinInputDto {
    @NotNull
    private final Long fromCoinId;
    @NotNull
    private final Long toCoinId;
    @NotNull
    private final BigDecimal fromAmount;
    @NotNull
    private final BigDecimal toAmount;
    @NotNull
    private final BigDecimal usdAmount;
    private final String comment;
}
