package com.coinsucks.core.coin;

import com.coinsucks.core.coin.dto.CoinOutputDto;
import com.coinsucks.core.error.exception.NotFoundException;
import com.coinsucks.core.security.AuthenticatedUser;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
public class CoinCrudService {
    private final CoinRepository coinRepository;

    @Transactional(readOnly = true)
    public CoinOutputDto getById(Long id, AuthenticatedUser user) {
        return coinRepository.findById(id)
                .map(c -> new CoinOutputDto(c, user))
                .orElseThrow(() -> new NotFoundException("Coin not found by id : " + id));
    }

    @Transactional(readOnly = true)
    public Page<CoinOutputDto> getAll(String name, Pageable pageable, AuthenticatedUser user) {
        Specification<Coin> coinSpecification = CoinSpecifications.byName(name);
        return coinRepository.findAll(coinSpecification, pageable)
                .map(c -> new CoinOutputDto(c, user));
    }
}
