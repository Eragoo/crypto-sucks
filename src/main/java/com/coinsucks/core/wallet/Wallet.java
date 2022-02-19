package com.coinsucks.core.wallet;


import com.coinsucks.core.coin.Coin;
import com.coinsucks.core.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Wallet {
    private static final String GENERATOR = "wallet_generator";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GENERATOR)
    @SequenceGenerator(name = GENERATOR, sequenceName = "wallet_sequence")
    private Long id;
    private String name;
    @OneToMany(mappedBy = "wallet")
    private Set<WalletCoin> coins = new HashSet<>();
    @ManyToOne
    private User owner;

    public WalletCoin add(Coin coin, BigDecimal amount) {

        Optional<WalletCoin> existingCoin = coins.stream().filter(wc -> wc.getCoin().equals(coin))
                .findFirst();

        if (existingCoin.isPresent()) {
            WalletCoin existingWC = existingCoin.get();
            existingWC.addAmount(amount);
            coins.add(existingWC);
            return existingWC;
        } else {
            WalletCoin walletCoin = new WalletCoin(coin, this, amount);
            coins.add(walletCoin);
            return walletCoin;
        }
    }
}
