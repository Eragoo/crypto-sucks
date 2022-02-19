package com.coinsucks.core.coin;

import com.coinsucks.core.coin.favorite.Favorite;
import com.coinsucks.core.user.User;
import com.coinsucks.core.user.UserAuthType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static com.coinsucks.core.TestDataProvider.getCoin;
import static com.coinsucks.core.TestDataProvider.getUser;
import static org.junit.jupiter.api.Assertions.*;

class CoinTest {

    @Test
    void setFavoritesToEmpty() {
        User user = getUser();
        Coin coin = getCoin();

        Optional<Favorite> favorite = coin.addToFavorite(user);

        assertAll(
                () -> assertFalse(favorite.isEmpty()),
                () -> assertEquals(1, coin.getFavorites().size())
        );
    }

    @Test
    void addSameFavoriteTwice() {
        User user = getUser();
        Coin coin = getCoin();

        Optional<Favorite> favorite1 = coin.addToFavorite(user);
        Optional<Favorite> favorite2 = coin.addToFavorite(user);

        assertAll(
                () -> assertFalse(favorite1.isEmpty()),
                () -> assertTrue(favorite2.isEmpty()),
                () -> assertEquals(1, coin.getFavorites().size())
        );
    }
}
