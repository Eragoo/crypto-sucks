package com.coinsucks.core.wallet;

import com.coinsucks.core.coin.Coin;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class WalletCoin {
    private static final String GENERATOR = "wallet_coin_generator";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GENERATOR)
    @SequenceGenerator(name = GENERATOR, sequenceName = "wallet_coin_sequence")
    private Long id;
    @ManyToOne
    private Coin coin;
    @ManyToOne
    private Wallet wallet;
    private BigDecimal amount;

    public WalletCoin(Coin coin, Wallet wallet, BigDecimal amount) {
        this.coin = coin;
        this.wallet = wallet;
        this.amount = amount;
    }

    public void addAmount(BigDecimal amount) {
        this.amount = this.amount.add(amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WalletCoin that = (WalletCoin) o;

        if (coin != null ? !coin.equals(that.coin) : that.coin != null) return false;
        return wallet != null ? wallet.equals(that.wallet) : that.wallet == null;
    }

    @Override
    public int hashCode() {
        int result = coin != null ? coin.hashCode() : 0;
        result = 31 * result + (wallet != null ? wallet.hashCode() : 0);
        return result;
    }
}
