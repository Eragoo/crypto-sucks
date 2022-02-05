package com.coinsucks.core.coin;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/coins")
@AllArgsConstructor
public class CoinResource {
    private final Map<Long, CoinOutputDto> db = Map.of(
            1L, new CoinOutputDto(1L, "BTC", "Bitcoin", "https://assets.coingecko.com/coins/images/1/small/bitcoin.png?1547033579", "42000"),
            2L, new CoinOutputDto(2L, "ETH", "Etherium", "https://assets.coingecko.com/coins/images/279/small/ethereum.png?1595348880", "3000"),
            3L, new CoinOutputDto(3L, "USDT", "Tether", "https://assets.coingecko.com/coins/images/325/small/Tether-logo.png?1598003707", "1"),
            4L, new CoinOutputDto(4L, "NEAR", "Near", "https://assets.coingecko.com/coins/images/10365/small/near_icon.png?1601359077", "14")
    );

    @GetMapping
    public Collection<CoinOutputDto> getAll() {
        return db.values();
    }

    @GetMapping("/{id}")
    public CoinOutputDto getById(Long id) {
        return db.get(id);
    }
}
