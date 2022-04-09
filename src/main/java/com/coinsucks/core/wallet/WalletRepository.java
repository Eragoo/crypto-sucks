package com.coinsucks.core.wallet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.Collection;
import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Collection<Wallet> findAllByOwnerUsername(String owner);
    Optional<Wallet> findByOwnerUsernameAndId(String owner, Long walletId);
}
