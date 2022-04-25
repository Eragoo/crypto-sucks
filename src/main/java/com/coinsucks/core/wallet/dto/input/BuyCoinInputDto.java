package com.coinsucks.core.wallet.dto.input;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class BuyCoinInputDto {
    @NotNull
    private final Long coinId;
    @NotNull
    private final BigDecimal amount;
    @NotNull
    private final BigDecimal usdAmount;
    private final String comment;
}
