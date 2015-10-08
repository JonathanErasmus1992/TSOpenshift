package toystore.conf;

import java.util.Date;
import java.util.List;

import toystore.domain.Orders;
import toystore.domain.Orderline;

/**
 * Created by Thawhir on 2015/10/05.
 */
public class OrderFactory {

    public static Orders createOrder(Date dateModified, float totalPrice, Boolean checkout, List<Orderline> orderlines)
    {
        Orders order = new Orders
                .Builder(dateModified)
                .totalPrice(totalPrice)
                .checkout(checkout)
                .orderlines(orderlines)
                .build();
        return order;
    }
}
