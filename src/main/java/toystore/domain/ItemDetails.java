package toystore.domain;

import java.util.List;

/**
 * Created by Thawhir on 2015/10/05.
 */
public interface ItemDetails {
    public Long getID();
    public String getName();
    public String getCategory();
    public int getQuantity();
    public float getPrice();
    public List<Orderline> getOrderlines();

}
