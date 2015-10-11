package toystore.domain;

import java.util.List;

public interface ItemDetails {
    public Long getID();
    public String getName();
    public String getCategory();
    public int getQuantity();
    public float getPrice();
    public List<Orderline> getOrderlines();

}
