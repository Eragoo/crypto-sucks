package com.coinsucks.core.coin.favorite;

import com.coinsucks.core.coin.Coin;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.criteria.internal.OrderImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class FavoriteSpecification {
    public static Specification<Favorite> byUsername(String username) {
        return (r, cq, cb) -> StringUtils.isBlank(username) ? null : cb.equal(r.get("user").get("username"), username);
    }

    public static Specification<Favorite> byCoinId(Long coindId) {
        return (r, cq, cb) -> coindId == null ? null : cb.equal(r.get("coin").get("id"), coindId);
    }

    public static Specification<Favorite> sort(Pageable pageable) {
        return (r, cq, cb) -> {
            if (pageable != null) {
                List<Order> orders = new ArrayList<>();
                Path<Coin> coin = r.get("coin");
                Predicate predicate = cb.isNotNull(coin.get("id"));
                List<Expression<?>> grouping = new ArrayList<>();
                grouping.add(r.get("id"));

                for (Sort.Order order : pageable.getSort()) {
                        orders.add(new OrderImpl(coin.get(order.getProperty()), order.isAscending()));
                        grouping.add(coin.get(order.getProperty()));
                }

                cq.orderBy(orders);
                cq.groupBy(grouping);

                return predicate;
            }

            return null;
        };
    }
}
