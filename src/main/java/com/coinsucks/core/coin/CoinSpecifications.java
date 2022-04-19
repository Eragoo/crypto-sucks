package com.coinsucks.core.coin;

import org.springframework.data.jpa.domain.Specification;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class CoinSpecifications {
    public static Specification<Coin> byName(String name) {
        return (r, cq, cb) -> isBlank(name) ? null : cb.like(r.get(Coin_.name), "%"+name+"%");
    }
}
