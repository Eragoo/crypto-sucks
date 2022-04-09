package com.coinsucks.core.wallet;

import com.coinsucks.core.TestDataProvider;
import com.coinsucks.core.coin.Coin;
import com.coinsucks.core.error.exception.ConflictException;
import com.coinsucks.core.wallet.dto.input.BuyCoinInputDto;
import com.coinsucks.core.wallet.dto.input.SwapCoinInputDto;
import com.coinsucks.core.wallet.dto.input.WithdrawCoinInputDto;
import com.coinsucks.core.wallet.dto.CoinWalletState;
import com.coinsucks.core.wallet.transaction.Transaction;
import com.coinsucks.core.wallet.transaction.TransactionType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

class WalletTest {

    @Test
    public void buyCoin() {
        Wallet wallet = new Wallet(
                1L,
                "Test wallet",
                new HashSet<>(),
                TestDataProvider.getUser()
        );

        BuyCoinInputDto coinInputDto = new BuyCoinInputDto(1L, BigDecimal.ONE, BigDecimal.ONE, "Comment");
        Transaction transaction = wallet.buyCoin(
                coinInputDto,
                TestDataProvider.getCoin(1L, BigDecimal.ONE)
        );

        Assertions.assertAll(
                () -> Assertions.assertEquals(coinInputDto.getComment(), transaction.getComment()),
                () -> Assertions.assertEquals(coinInputDto.getAmount(), transaction.getToAmount()),
                () -> Assertions.assertEquals(coinInputDto.getCoinId(), transaction.getTo().getId()),
                () -> Assertions.assertEquals(1, wallet.getTransactions().size()),
                () -> Assertions.assertEquals(TransactionType.BUY, transaction.getType())
        );
    }

    @Test
    public void withdrawCoin() {
        Wallet wallet = new Wallet(
                1L,
                "Test wallet",
                new HashSet<>(),
                TestDataProvider.getUser()
        );

        BuyCoinInputDto butDto = new BuyCoinInputDto(1L, BigDecimal.ONE, BigDecimal.ONE, "Comment");
        wallet.buyCoin(
                butDto,
                TestDataProvider.getCoin(1L, BigDecimal.ONE)
        );

        WithdrawCoinInputDto coinInputDto = new WithdrawCoinInputDto(1L, BigDecimal.ONE, BigDecimal.ONE, "Comment");
        Transaction transaction = wallet.withdrawCoin(
                coinInputDto,
                TestDataProvider.getCoin(1L, BigDecimal.ONE)
        );

        Assertions.assertAll(
                () -> Assertions.assertEquals(coinInputDto.getComment(), transaction.getComment()),
                () -> Assertions.assertEquals(coinInputDto.getAmount(), transaction.getFromAmount()),
                () -> Assertions.assertEquals(coinInputDto.getCoinId(), transaction.getFrom().getId()),
                () -> Assertions.assertEquals(2, wallet.getTransactions().size()),
                () -> Assertions.assertEquals(TransactionType.WITHDRAW, transaction.getType())
        );
    }

    @Test
    public void getCurrentStateWhen2Buy1Swap1Withdraw() {
        Wallet wallet = new Wallet(
                1L,
                "Test wallet",
                new HashSet<>(),
                TestDataProvider.getUser()
        );

        BuyCoinInputDto buyDto = new BuyCoinInputDto(1L, BigDecimal.ONE, BigDecimal.ONE, "Comment");
        Coin coin1 = TestDataProvider.getCoin(1L, BigDecimal.ONE);
        wallet.buyCoin(
                buyDto,
                coin1
        );

        BuyCoinInputDto buyDto2 = new BuyCoinInputDto(2L, BigDecimal.ONE, BigDecimal.ONE, "Comment");
        Coin coin2 = TestDataProvider.getCoin(2L, BigDecimal.ONE);
        wallet.buyCoin(
                buyDto2,
                coin2
        );

        WithdrawCoinInputDto withdrawDto = new WithdrawCoinInputDto(1L, BigDecimal.ONE, BigDecimal.ONE, "Comment");
        wallet.withdrawCoin(
                withdrawDto,
                coin1
        );

        SwapCoinInputDto swapDto = new SwapCoinInputDto(
                2L,
                1L,
                BigDecimal.ONE,
                BigDecimal.ONE,
                BigDecimal.ONE,
                "Comment"
        );
        wallet.swapCoin(swapDto, coin2, coin1);

        Set<CoinWalletState> currentState = wallet.getCurrentState().collect(Collectors.toSet());

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, currentState.size()),
                () -> Assertions.assertEquals(1L, new ArrayList<>(currentState).get(0).getCoin().getId()),
                () -> Assertions.assertEquals(BigDecimal.ONE, new ArrayList<>(currentState).get(0).getAmount())
        );
    }

    //@Test
    //todo add checking
    public void emptyWalletWithdrawCoin() {
        Wallet wallet = new Wallet(
                1L,
                "Test wallet",
                new HashSet<>(),
                TestDataProvider.getUser()
        );

        WithdrawCoinInputDto coinInputDto = new WithdrawCoinInputDto(1L, BigDecimal.ONE, BigDecimal.ONE, "Comment");

        Assertions.assertThrows(
                ConflictException.class,
                () -> wallet.withdrawCoin(
                        coinInputDto,
                        TestDataProvider.getCoin(1L, BigDecimal.ONE)
                )
        );
    }
}
