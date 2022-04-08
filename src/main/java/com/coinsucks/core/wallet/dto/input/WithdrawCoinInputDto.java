package com.coinsucks.core.wallet.dto.input;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class WithdrawCoinInputDto {
    private final Long coinId;
    private final BigDecimal amount;
    private final BigDecimal usdAmount;
    private final String comment;
}
