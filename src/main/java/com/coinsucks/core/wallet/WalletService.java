package com.coinsucks.core.wallet;

import com.coinsucks.core.coin.Coin;
import com.coinsucks.core.coin.CoinRepository;
import com.coinsucks.core.error.exception.NotFoundException;
import com.coinsucks.core.security.AuthenticatedUser;
import com.coinsucks.core.user.User;
import com.coinsucks.core.user.UserRepository;
import com.coinsucks.core.user.UserService;
import com.coinsucks.core.wallet.dto.input.BuyCoinInputDto;
import com.coinsucks.core.wallet.dto.input.SwapCoinInputDto;
import com.coinsucks.core.wallet.dto.input.WalletInputDto;
import com.coinsucks.core.wallet.dto.input.WithdrawCoinInputDto;
import com.coinsucks.core.wallet.dto.output.CoinWalletStateOutputDto;
import com.coinsucks.core.wallet.dto.output.WalletOutputDto;
import com.coinsucks.core.wallet.transaction.Transaction;
import com.coinsucks.core.wallet.transaction.TransactionOutputDto;
import com.coinsucks.core.wallet.transaction.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WalletService {
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;
    private final CoinRepository coinRepository;
    private final UserRepository userRepository;

    @Transactional
    public TransactionOutputDto buy(BuyCoinInputDto buyCoinInputDto, Long walletId, AuthenticatedUser user) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new NotFoundException("Wallet not found on buy operation :("));

        Coin coin = coinRepository.findById(buyCoinInputDto.getCoinId())
                .orElseThrow(() -> new NotFoundException("Coin not found on buy operation :("));

        Transaction transaction = wallet.buyCoin(buyCoinInputDto, coin);
        transactionRepository.save(transaction);

        return new TransactionOutputDto(transaction, user);
    }

    @Transactional
    public TransactionOutputDto withdraw(WithdrawCoinInputDto coinInputDto, Long walletId, AuthenticatedUser user) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new NotFoundException("Wallet not found on withdraw operation :("));

        Coin coin = coinRepository.findById(coinInputDto.getCoinId())
                .orElseThrow(() -> new NotFoundException("Coin not found on withdraw operation :("));

        Transaction transaction = wallet.withdrawCoin(coinInputDto, coin);
        transactionRepository.save(transaction);

        return new TransactionOutputDto(transaction, user);
    }

    @Transactional
    public TransactionOutputDto swap(SwapCoinInputDto coinInputDto, Long walletId, AuthenticatedUser user) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new NotFoundException("Wallet not found on withdraw operation :("));

        Coin from = coinRepository.findById(coinInputDto.getFromCoinId())
                .orElseThrow(() -> new NotFoundException("Coin not found on swap operation :("));

        Coin to = coinRepository.findById(coinInputDto.getToCoinId())
                .orElseThrow(() -> new NotFoundException("Coin not found on swap operation :("));

        Transaction transaction = wallet.swapCoin(coinInputDto, from, to);
        transactionRepository.save(transaction);

        return new TransactionOutputDto(transaction, user);
    }

    @Transactional(readOnly = true)
    public List<CoinWalletStateOutputDto> state(Long walletId, AuthenticatedUser user) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new NotFoundException("Wallet not found on withdraw operation :("));

        return wallet.getCurrentStateSorted()
                .map(c -> new CoinWalletStateOutputDto(c.getCoin(), c.getAmount(), user))
                .collect(Collectors.toList());
    }

    @Transactional
    public WalletOutputDto create(WalletInputDto inputDto, AuthenticatedUser user) {
        User dbUser = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new NotFoundException(
                        "User with usename :" + user.getUsername() + " not found on wallet create :("
                ));

        Wallet wallet = new Wallet(
                inputDto.getName(),
                dbUser
        );
        walletRepository.save(wallet);

        return new WalletOutputDto(wallet, user);
    }

    @Transactional(readOnly = true)
    public WalletOutputDto getById(Long id, AuthenticatedUser user) {
        Wallet wallet = walletRepository.findByOwnerUsernameAndId(user.getUsername(), id)
                .orElseThrow(() -> new NotFoundException("Wallet not found for this user :("));
        return new WalletOutputDto(wallet, user);
    }

    @Transactional(readOnly = true)
    public List<WalletOutputDto> getAll(AuthenticatedUser user) {
        return walletRepository.findAllByOwnerUsername(user.getUsername()).stream()
                .map(w -> new WalletOutputDto(w, user))
                .collect(Collectors.toList());
    }
}
