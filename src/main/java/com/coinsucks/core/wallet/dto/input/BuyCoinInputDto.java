package com.coinsucks.core.wallet.dto.input;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class BuyCoinInputDto {
    private final Long coinId;
    private final BigDecimal amount;
    private final BigDecimal usdAmount;
    private final String comment;
}
