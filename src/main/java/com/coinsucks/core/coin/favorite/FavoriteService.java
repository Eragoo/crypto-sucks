package com.coinsucks.core.coin.favorite;

import com.coinsucks.core.coin.Coin;
import com.coinsucks.core.coin.CoinRepository;
import com.coinsucks.core.coin.dto.CoinOutputDto;
import com.coinsucks.core.error.exception.NotFoundException;
import com.coinsucks.core.security.AuthenticatedUser;
import com.coinsucks.core.user.User;
import com.coinsucks.core.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FavoriteService {
    private final UserRepository userRepository;
    private final FavoriteRepository favoriteRepository;
    private final CoinRepository coinRepository;

    @Transactional(readOnly = true)
    public Page<CoinOutputDto> getAll(Pageable pageable, AuthenticatedUser user) {
        Specification<Favorite> specification = FavoriteSpecification.byUsername(user.getUsername())
                .and(FavoriteSpecification.sort(pageable));

        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.unsorted());

        return favoriteRepository.findAll(specification, pageRequest)
                .map(Favorite::getCoin)
                .map(CoinOutputDto::new);
    }

    @Transactional
    public void addToFavorites(Long coinId, AuthenticatedUser user) {
        Coin coin = coinRepository.findById(coinId)
                .orElseThrow(() -> new NotFoundException("Coin with id " + coinId + " not found!"));

        User user1 = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new NotFoundException("User with username: " + user.getUsername() + " not found!"));
        Optional<Favorite> favorite = coin.addToFavorite(user1);
        favorite.ifPresent(favoriteRepository::save);
    }

    @Transactional
    public void deleteFromFavorites(Long coinId, AuthenticatedUser user) {
        Specification<Favorite> favoriteSpecification = FavoriteSpecification.byCoinId(coinId)
                .and(FavoriteSpecification.byUsername(user.getUsername()));

        List<Favorite> favorites = favoriteRepository.findAll(favoriteSpecification);
        assert favorites.size() <= 1;
        favoriteRepository.deleteAll(favorites);
    }
}
