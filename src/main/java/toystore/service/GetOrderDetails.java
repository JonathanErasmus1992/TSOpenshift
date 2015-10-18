package toystore.service;

import toystore.domain.Orders;

public interface GetOrderDetails {
    public Orders getOrder(Long customerID);
}
