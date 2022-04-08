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
    private Coin fromCoinId;
    private Coin toCoinId;
    private BigDecimal fromAmount;
    private BigDecimal toAmount;
    private BigDecimal usdAmount;
    private String comment;
}
