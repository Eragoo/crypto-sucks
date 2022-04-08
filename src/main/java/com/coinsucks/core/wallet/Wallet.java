package com.coinsucks.core.wallet;


import com.coinsucks.core.coin.Coin;
import com.coinsucks.core.user.User;
import com.coinsucks.core.wallet.dto.input.BuyCoinInputDto;
import com.coinsucks.core.wallet.dto.input.SwapCoinInputDto;
import com.coinsucks.core.wallet.dto.input.WithdrawCoinInputDto;
import com.coinsucks.core.wallet.transaction.Transaction;
import com.coinsucks.core.wallet.transaction.TransactionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

import static com.coinsucks.core.wallet.transaction.TransactionType.BUY;

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
    private Set<Transaction> transactions = new HashSet<>();
    @ManyToOne
    private User owner;

    public Transaction buyCoin(BuyCoinInputDto buyDto, Coin coin) {
        Transaction transaction = Transaction.constructBuy(
                coin,
                buyDto.getAmount(),
                buyDto.getUsdAmount(),
                this,
                buyDto.getComment()
        );
        transactions.add(transaction);

        return transaction;
    }

    public Transaction swapCoin(SwapCoinInputDto swapDto, Coin fromCoin, Coin toCoin) {
        //todo add validation to check is wallet have required coin in needed amount
        Transaction transaction = Transaction.constructSwap(
                fromCoin,
                toCoin,
                this,
                swapDto.getFromAmount(),
                swapDto.getToAmount(),
                swapDto.getUsdAmount(),
                swapDto.getComment()
        );
        transactions.add(transaction);

        return transaction;
    }

    public Transaction withdrawCoin(WithdrawCoinInputDto withdrawDto, Coin coin) {
        //todo add validation to check is wallet have required coin in needed amount
        Transaction transaction = Transaction.constructWithdraw(
                coin,
                withdrawDto.getAmount(),
                withdrawDto.getUsdAmount(),
                this,
                withdrawDto.getComment()
        );
        transactions.add(transaction);

        return transaction;
    }

    public Set<CoinWalletStateDto> getCurrentState() {
        Map<Long, CoinWalletStateDto> coinIdToState = new HashMap<>();

        for (Transaction transaction : transactions) {

            switch (transaction.getType()) {
                case BUY: {
                    Long coinId = transaction.getTo().getId();
                    BigDecimal toAmount = transaction.getToAmount();
                    CoinWalletStateDto stateDto = Optional.ofNullable(coinIdToState.get(coinId))
                            .orElse(new CoinWalletStateDto(coinId, BigDecimal.ZERO));

                    stateDto.addValue(toAmount);
                    coinIdToState.put(coinId, stateDto);
                    break;
                }

                case WITHDRAW: {
                    Long withdrawCoinId = transaction.getFrom().getId();
                    BigDecimal withdrawAmount = transaction.getFromAmount();
                    CoinWalletStateDto stateDto = Optional.ofNullable(coinIdToState.get(withdrawCoinId))
                            .orElse(new CoinWalletStateDto(withdrawCoinId, BigDecimal.ZERO));

                    stateDto.addValue(toAmount);
                    coinIdToState.put(coinId, stateDto);
                    break;
                }
            }


        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Wallet wallet = (Wallet) o;

        if (id != null ? !id.equals(wallet.id) : wallet.id != null) return false;
        if (name != null ? !name.equals(wallet.name) : wallet.name != null) return false;
        return owner != null ? owner.equals(wallet.owner) : wallet.owner == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        return result;
    }
}
