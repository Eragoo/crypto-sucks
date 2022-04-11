package com.coinsucks.core.wallet;

import com.coinsucks.core.security.AuthenticatedUser;
import com.coinsucks.core.user.User_;
import org.springframework.data.jpa.domain.Specification;

public class WalletSpecifications {
    public static Specification<Wallet> accessibleFor(AuthenticatedUser user) {
        return (r, cq, cb) -> user != null ?
                cb.equal(r.get(Wallet_.owner).get(User_.username), user.getUsername()) :
                null;
    }
}
