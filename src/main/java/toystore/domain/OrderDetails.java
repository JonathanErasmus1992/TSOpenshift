package toystore.domain;

import java.util.Date;
import java.util.List;

public interface OrderDetails {
    public Long getID();
    public Date getDateModified();
    public float getTotalPrice();
    public Boolean getCheckout();
    public List<Orderline> getOrderlines();
}
