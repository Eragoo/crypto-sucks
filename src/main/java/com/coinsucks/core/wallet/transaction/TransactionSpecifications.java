package com.coinsucks.core.wallet.transaction;

import com.coinsucks.core.security.AuthenticatedUser;
import com.coinsucks.core.user.User;
import com.coinsucks.core.user.User_;
import com.coinsucks.core.wallet.Wallet_;
import org.springframework.data.jpa.domain.Specification;

public class TransactionSpecifications {
    public static Specification<Transaction> byWallet(Long walletId) {
        return (r, cq, cb) -> walletId != null ? cb.equal(r.get(Transaction_.wallet).get(Wallet_.id), walletId) : null;
    }

    public static Specification<Transaction> accessibleFor(AuthenticatedUser user) {
        return (r, cq, cb) -> user != null ?
                cb.equal(r.get(Transaction_.wallet).get(Wallet_.owner).get(User_.username), user.getUsername()) :
                null;
    }
}
