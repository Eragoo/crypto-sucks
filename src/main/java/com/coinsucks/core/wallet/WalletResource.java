package com.coinsucks.core.wallet;

import com.coinsucks.core.security.AuthenticatedUser;
import com.coinsucks.core.wallet.dto.input.BuyCoinInputDto;
import com.coinsucks.core.wallet.transaction.Transaction;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallets")
@AllArgsConstructor
public class WalletResource {

    @PostMapping("/{id}/buy")
    public void buy(
            @RequestBody BuyCoinInputDto buyCoinInputDto,
            @AuthenticationPrincipal AuthenticatedUser user,
            @PathVariable Long id
    ) {

    }
}
