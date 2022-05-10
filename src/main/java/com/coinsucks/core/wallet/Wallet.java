package com.coinsucks.core.wallet;


import com.coinsucks.core.coin.Coin;
import com.coinsucks.core.user.User;
import com.coinsucks.core.wallet.dto.input.BuyCoinInputDto;
import com.coinsucks.core.wallet.dto.input.SwapCoinInputDto;
import com.coinsucks.core.wallet.dto.input.WithdrawCoinInputDto;
import com.coinsucks.core.wallet.dto.CoinWalletState;
import com.coinsucks.core.wallet.transaction.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Stream;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
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

    public Stream<CoinWalletState> getCurrentState() {
        Map<Long, CoinWalletState> coinIdToState = new HashMap<>();

        for (Transaction transaction : transactions) {
            BigDecimal usdAmount = transaction.getUsdAmount();
            switch (transaction.getType()) {
                case BUY: {
                    Coin coin = transaction.getTo();
                    BigDecimal toAmount = transaction.getToAmount();
                    buy(coinIdToState, coin, toAmount, usdAmount);
                    break;
                }

                case WITHDRAW: {
                    Coin withdrawCoin = transaction.getFrom();
                    BigDecimal withdrawAmount = transaction.getFromAmount();
                    withdraw(coinIdToState, withdrawCoin, withdrawAmount);
                    break;
                }

                case SWAP: {
                    //swap consist of 2 operations: withdraw & buy
                    Coin fromCoin = transaction.getFrom();
                    BigDecimal fromAmount = transaction.getFromAmount();
                    withdraw(coinIdToState, fromCoin, fromAmount);

                    Coin toCoin = transaction.getTo();
                    BigDecimal toAmount = transaction.getToAmount();
                    buy(coinIdToState, toCoin, toAmount, usdAmount);
                    break;
                }
            }
        }

        return coinIdToState.values()
                .stream()
                .filter(c -> !c.getAmount().equals(BigDecimal.ZERO));
    }

    public Stream<CoinWalletState> getCurrentStateSorted() {
        return getCurrentState()
                .sorted(Comparator.comparing(c -> c.getCoin().getMarketCapRank()));
    }

    //todo test cover
    public BigDecimal getCurrentPrice() {
        return getCurrentState()
                .map(c -> c.getCoin().getCurrentPrice().multiply(c.getAmount()))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    //todo test cover
    public BigDecimal getStartPrice() {
        Map<Long, BigDecimal> coinIdToUsdAmount = new HashMap<>();

        for (Transaction transaction : transactions) {

            switch (transaction.getType()) {
                case BUY: {
                    Coin coin = transaction.getTo();
                    BigDecimal usdAmount = Optional.ofNullable(coinIdToUsdAmount.get(coin.getId()))
                            .orElse(BigDecimal.ZERO);

                    usdAmount = usdAmount.add(transaction.getUsdAmount());
                    coinIdToUsdAmount.put(coin.getId(), usdAmount);

                    break;
                }

                case WITHDRAW: {
                    Coin withdrawCoin = transaction.getFrom();
                    BigDecimal usdAmount = Optional.ofNullable(coinIdToUsdAmount.get(withdrawCoin.getId()))
                            .orElse(BigDecimal.ZERO);

                    usdAmount = usdAmount.subtract(transaction.getUsdAmount());
                    coinIdToUsdAmount.put(withdrawCoin.getId(), usdAmount);
                    break;
                }
            }
        }

        return coinIdToUsdAmount.values()
                .stream()
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }


    private void withdraw(
            Map<Long, CoinWalletState> coinIdToState,
            Coin withdrawCoin,
            BigDecimal withdrawAmount
    ) {
        //todo add check is enough money
        CoinWalletState stateDto = Optional.ofNullable(coinIdToState.get(withdrawCoin.getId()))
                .orElse(new CoinWalletState(withdrawCoin, BigDecimal.ZERO, BigDecimal.ZERO));

        stateDto.subtractValue(withdrawAmount);
        coinIdToState.put(withdrawCoin.getId(), stateDto);
    }

    private void buy(
            Map<Long, CoinWalletState> coinIdToState,
            Coin coin,
            BigDecimal toAmount,
            BigDecimal usdAmount
    ) {
        CoinWalletState stateDto = Optional.ofNullable(coinIdToState.get(coin.getId()))
                .orElse(new CoinWalletState(coin, BigDecimal.ZERO, BigDecimal.ZERO));

        stateDto.addAmount(toAmount, usdAmount);
        coinIdToState.put(coin.getId(), stateDto);
    }

    public Wallet(String name, User owner) {
        this.name = name;
        this.transactions = new HashSet<>();
        this.owner = owner;
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
