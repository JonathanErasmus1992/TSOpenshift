package toystore.conf;

import java.util.List;

import toystore.domain.Item;
import toystore.domain.Orderline;

public class ItemFactory {

    public static Item createItem(String name, String category,int quantity, float price, List<Orderline> orderlines)
    {
        Item item = new Item
                .Builder(name)
                .category(category)
                .quantity(quantity)
                .price(price)
                .orderlines(orderlines)
                .build();
        return item;
    }
}
