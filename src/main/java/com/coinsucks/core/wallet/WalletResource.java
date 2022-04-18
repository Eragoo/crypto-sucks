package com.coinsucks.core.wallet;

import com.coinsucks.core.security.AuthenticatedUser;
import com.coinsucks.core.wallet.dto.input.BuyCoinInputDto;
import com.coinsucks.core.wallet.dto.input.SwapCoinInputDto;
import com.coinsucks.core.wallet.dto.input.WalletInputDto;
import com.coinsucks.core.wallet.dto.input.WithdrawCoinInputDto;
import com.coinsucks.core.wallet.dto.output.CoinWalletStateOutputDto;
import com.coinsucks.core.wallet.dto.output.WalletOutputDto;
import com.coinsucks.core.wallet.transaction.TransactionOutputDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wallets")
@AllArgsConstructor
public class WalletResource {
    private final WalletService walletService;

    @PostMapping("/{id}/buy")
    public TransactionOutputDto buy(
            @RequestBody BuyCoinInputDto buyCoinInputDto,
            @AuthenticationPrincipal AuthenticatedUser user,
            @PathVariable Long id
    ) {
        return walletService.buy(buyCoinInputDto, id, user);
    }

    @GetMapping("/{id}")
    public WalletOutputDto getById(
            @AuthenticationPrincipal AuthenticatedUser user,
            @PathVariable Long id
    ) {
        return walletService.getById(id, user);
    }
    //todo add transactions get by wallet
    @GetMapping("/{id}/transactions")
    public List<TransactionOutputDto> getTransactions(
            @AuthenticationPrincipal AuthenticatedUser user,
            @PathVariable Long id
    ) {
        return walletService.getTransactions(id, user);
    }

    @GetMapping
    public List<WalletOutputDto> getAll(@AuthenticationPrincipal AuthenticatedUser user) {
        return walletService.getAll(user);
    }

    @PostMapping
    public WalletOutputDto create(@RequestBody WalletInputDto dto, @AuthenticationPrincipal AuthenticatedUser user) {
        return walletService.create(dto, user);
    }

    @PostMapping("/{id}/withdraw")
    public TransactionOutputDto withdraw(
            @RequestBody WithdrawCoinInputDto coinInputDto,
            @AuthenticationPrincipal AuthenticatedUser user,
            @PathVariable Long id
    ) {
        return walletService.withdraw(coinInputDto, id, user);
    }

    @PostMapping("/{id}/swap")
    public TransactionOutputDto swap(
            @RequestBody SwapCoinInputDto coinInputDto,
            @AuthenticationPrincipal AuthenticatedUser user,
            @PathVariable Long id
    ) {
        return walletService.swap(coinInputDto, id, user);
    }

    @GetMapping("/{id}/state")
    public List<CoinWalletStateOutputDto> state(
            @AuthenticationPrincipal AuthenticatedUser user,
            @PathVariable Long id
    ) {
        return walletService.state(id, user);
    }
}
