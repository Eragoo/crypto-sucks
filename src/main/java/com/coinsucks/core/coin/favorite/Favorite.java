package com.coinsucks.core.coin.favorite;

import com.coinsucks.core.coin.Coin;
import com.coinsucks.core.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Favorite {
    private static final String GENERATOR = "coin_generator";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GENERATOR)
    @SequenceGenerator(name = GENERATOR, sequenceName = "favorite_sequence")
    private Long id;

    @ManyToOne
    private Coin coin;

    @ManyToOne
    private User user;

    public Favorite(Coin coin, User user) {
        this.coin = coin;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Favorite favorite = (Favorite) o;

        if (coin != null ? !coin.equals(favorite.coin) : favorite.coin != null) return false;
        return user != null ? user.equals(favorite.user) : favorite.user == null;
    }

    @Override
    public int hashCode() {
        int result = coin != null ? coin.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
