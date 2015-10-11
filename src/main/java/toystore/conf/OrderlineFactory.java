package toystore.conf;

import toystore.domain.Orderline;

public class OrderlineFactory {

    public static Orderline createOrderline(int quantity)
    {
        Orderline orderline = new Orderline
                .Builder(quantity)
                .build();
        return orderline;
    }
}
