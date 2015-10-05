package toystore.conf;

import toystore.domain.Orderline;

/**
 * Created by Thawhir on 2015/10/05.
 */
public class OrderlineFactory {

    public static Orderline createOrderline(int quantity)
    {
        Orderline orderline = new Orderline
                .Builder(quantity)
                .build();
        return orderline;
    }
}
