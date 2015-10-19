package toystore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import toystore.domain.Orders;
import toystore.repository.OrderRepository;

@Service
public class CheckoutOrderService implements CheckoutOrderDetails{
    @Autowired
    OrderRepository orderRepository;

    private Orders order;
    @Override
    public boolean checkoutOrder(Long orderID)
    {
        order = orderRepository.findOne(orderID);
        if(order==null)
            return false;

        Orders checkedoutOrder = new Orders
                                    .Builder(order.getDateModified())
                                    .copy(order)
                                    .dateModified(new Date())
                                    .checkout(true)
                                    .build();

        orderRepository.save(checkedoutOrder);
        return true;
    }
}
