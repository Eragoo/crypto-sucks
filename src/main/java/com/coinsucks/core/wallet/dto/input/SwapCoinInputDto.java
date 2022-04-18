package com.coinsucks.core.wallet.dto.input;

import com.coinsucks.core.coin.Coin;
import com.coinsucks.core.wallet.Wallet;
import com.coinsucks.core.wallet.transaction.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class SwapCoinInputDto {
    private final Long fromCoinId;
    private final Long toCoinId;
    private final BigDecimal fromAmount;
    private final BigDecimal toAmount;
    private final BigDecimal usdAmount;
    private final String comment;
}
