package com.coinsucks.core.coin;

import com.coinsucks.core.coin.dto.CoinOutputDto;
import com.coinsucks.core.coin.favorite.FavoriteService;
import com.coinsucks.core.security.AuthenticatedUser;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coins")
@AllArgsConstructor
public class CoinResource {
    private final CoinSyncService syncService;
    private final FavoriteService favoriteService;
    private final CoinCrudService coinCrudService;

    @GetMapping
    public Page<CoinOutputDto> getAll(@SortDefault(value = "marketCapRank") Pageable pageable) {
        return coinCrudService.getAll(pageable);
    }

    @GetMapping("/favorites")
    public Page<CoinOutputDto> getFavorites(
            @SortDefault(value = "marketCapRank") Pageable pageable,
            @AuthenticationPrincipal AuthenticatedUser authenticatedUser
    ) {
        return favoriteService.getAll(pageable, authenticatedUser);
    }

    @PutMapping("/{id}/add-to-favorites")
    public void addToFavorites(
            @PathVariable Long id,
            @AuthenticationPrincipal AuthenticatedUser authenticatedUser
    ) {
        favoriteService.addToFavorites(id, authenticatedUser);
    }

    @DeleteMapping("/{id}/delete-from-favorites")
    public void deleteFromFavorites(
            @PathVariable Long id,
            @AuthenticationPrincipal AuthenticatedUser authenticatedUser
    ) {
        favoriteService.deleteFromFavorites(id, authenticatedUser);
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
