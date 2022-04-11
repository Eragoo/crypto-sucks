package com.coinsucks.core.wallet.transaction;

import com.coinsucks.core.coin.Coin;
import com.coinsucks.core.wallet.Wallet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private static final String GENERATOR = "wallet_coin_generator";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GENERATOR)
    @SequenceGenerator(name = GENERATOR, sequenceName = "wallet_coin_sequence")
    private Long id;
    @ManyToOne
    private Coin from;
    @ManyToOne
    private Coin to;
    @ManyToOne
    private Wallet wallet;
    private BigDecimal fromAmount;
    private BigDecimal toAmount;
    private BigDecimal usdAmount;
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    private String comment;
    private Instant createdAt;

    public static Transaction constructBuy(Coin coin, BigDecimal amount, BigDecimal usdAmount, Wallet wallet, String comment) {
        return new Transaction(
                null,
                null,
                coin,
                wallet,
                null,
                amount,
                usdAmount,
                TransactionType.BUY,
                comment,
                Instant.now()
        );
    }

    public static Transaction constructSwap(Coin from, Coin to, Wallet wallet, BigDecimal fromAmount, BigDecimal toAmount, BigDecimal usdAmount, String comment) {
        return new Transaction(
                null,
                from,
                to,
                wallet,
                fromAmount,
                toAmount,
                usdAmount,
                TransactionType.SWAP,
                comment,
                Instant.now()
        );
    }

    public static Transaction constructWithdraw(Coin coin, BigDecimal amount, BigDecimal usdAmount, Wallet wallet, String comment) {
        return new Transaction(
                null,
                coin,
                null,
                wallet,
                amount,
                null,
                usdAmount,
                TransactionType.WITHDRAW,
                comment,
                Instant.now()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (from != null ? !from.equals(that.from) : that.from != null) return false;
        if (to != null ? !to.equals(that.to) : that.to != null) return false;
        if (wallet != null ? !wallet.equals(that.wallet) : that.wallet != null) return false;
        if (fromAmount != null ? !fromAmount.equals(that.fromAmount) : that.fromAmount != null) return false;
        if (toAmount != null ? !toAmount.equals(that.toAmount) : that.toAmount != null) return false;
        if (usdAmount != null ? !usdAmount.equals(that.usdAmount) : that.usdAmount != null) return false;
        return type == that.type;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (from != null ? from.hashCode() : 0);
        result = 31 * result + (to != null ? to.hashCode() : 0);
        result = 31 * result + (wallet != null ? wallet.hashCode() : 0);
        result = 31 * result + (fromAmount != null ? fromAmount.hashCode() : 0);
        result = 31 * result + (toAmount != null ? toAmount.hashCode() : 0);
        result = 31 * result + (usdAmount != null ? usdAmount.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
