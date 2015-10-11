package toystore.domain;

import java.util.HashMap;
import java.util.List;

public interface InvoiceDetails {
    public Long getID();
    public Long getOrderID();
    public float getTotalPrice();
    public List<HashMap<String,String>> getItems();
}
