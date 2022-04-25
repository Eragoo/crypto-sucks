package com.coinsucks.core.wallet.transaction;

import com.coinsucks.core.coin.Coin;
import com.coinsucks.core.coin.dto.CoinOutputDto;
import com.coinsucks.core.security.AuthenticatedUser;
import com.coinsucks.core.wallet.Wallet;
import com.coinsucks.core.wallet.transaction.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Optional;

@AllArgsConstructor
@Getter
public class TransactionOutputDto {
    private final Long id;
    private final CoinOutputDto from;
    private final CoinOutputDto to;
    private final BigDecimal fromAmount;
    private final BigDecimal toAmount;
    private final BigDecimal usdAmount;
    private final TransactionType type;
    private final String comment;


    public TransactionOutputDto(Transaction transaction, AuthenticatedUser user) {
        this.id = transaction.getId();
        this.from = Optional.ofNullable(transaction.getFrom())
                .map(c -> new CoinOutputDto(c, user))
                .orElse(null);

        this.to = Optional.ofNullable(transaction.getTo())
                .map(c -> new CoinOutputDto(c, user))
                .orElse(null);

        this.fromAmount = transaction.getFromAmount();
        this.toAmount = transaction.getToAmount();
        this.comment = transaction.getComment();
        this.type = transaction.getType();
        this.usdAmount = transaction.getUsdAmount();
    }
}
