package toystore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.Order;

import toystore.domain.Orderline;
import toystore.domain.Orders;
import toystore.repository.OrderRepository;
import toystore.repository.OrderlineRepository;

@Service
public class EmptyOrderService implements EmptyOrderDetails{
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderlineRepository orderlineRepository;

    private Orders orders;
    private List<Orderline> orderlines;
    @Override
    public boolean emptyOrder(Long id)
    {
        orders = orderRepository.findOne(id);
        if(orders.getCheckout())
            return false;
        for(Orderline orderline: orders.getOrderlines())
            orderlineRepository.delete(orderline);
        //need to add a dateModified option to Orders
        orders = new Orders
                    .Builder(new Date())
                    .copy(orders)
                    .orderlines(orderlines)
                    .build();
        return true;
    }
}
