package toystore.conf;

import java.util.List;

import toystore.domain.Item;
import toystore.domain.Orderline;

/**
 * Created by Thawhir on 2015/10/05.
 */
public class ItemFactory {

    public static Item createItem(Long ID,String name, String category,int quantity, float price, List<Orderline> orderlines)
    {
        Item item = new Item
                .Builder(ID)
                .name(name)
                .category(category)
                .quantity(quantity)
                .price(price)
                .orderlines(orderlines)
                .build();
        return item;
    }
}
