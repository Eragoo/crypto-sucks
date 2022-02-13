package com.coinsucks.core.coin;

import com.coinsucks.core.coin.dto.CoinOutputDto;
import com.coinsucks.core.error.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
public class CoinCrudService {
    private final CoinRepository coinRepository;

    @Transactional(readOnly = true)
    public CoinOutputDto getById(Long id) {
        return coinRepository.findById(id)
                .map(CoinOutputDto::new)
                .orElseThrow(() -> new NotFoundException("Coin not found by id : " + id));
    }

    @Transactional(readOnly = true)
    public Page<CoinOutputDto> getAll(Pageable pageable) {
        return coinRepository.findAll(pageable)
                .map(CoinOutputDto::new);
    }
}
