package toystore.conf;

import java.util.Date;
import java.util.List;

import toystore.domain.Order;
import toystore.domain.Orderline;

/**
 * Created by Thawhir on 2015/10/05.
 */
public class OrderFactory {

    public static Order createOrder(Long ID,Date dateModified, float totalPrice, Boolean checkout, List<Orderline> orderlines)
    {
        Order order = new Order
                .Builder(ID)
                .dateModified(dateModified)
                .totalPrice(totalPrice)
                .checkout(checkout)
                .orderlines(orderlines)
                .build();
        return order;
    }
}
