package com.coinsucks.core.coin;

import com.coinsucks.core.coin.dto.CoinOutputDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coins")
@AllArgsConstructor
public class CoinResource {
    private final CoinSyncService syncService;
    private final CoinCrudService coinCrudService;

    @GetMapping
    public Page<CoinOutputDto> getAll(@SortDefault(value = "marketCapRank") Pageable pageable) {
        return coinCrudService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public CoinOutputDto getById(@PathVariable Long id) {
        return coinCrudService.getById(id);
    }

    @PostMapping("/sync")
    public void sync() {
        syncService.syncCoins();
    }
}
