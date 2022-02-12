package com.coinsucks.core.coin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CoinRepository extends JpaRepository<Coin, Long>, JpaSpecificationExecutor<Coin>  {
    Optional<Coin> findByCoinGeckoId(String coinGeckoId);
}
