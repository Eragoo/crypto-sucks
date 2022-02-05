package com.coinsucks.core.coin;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CoinOutputDto {
    private final Long id;
    private final String ticker;
    private final String name;
    private final String logoUrl;
    private final String price;
}
