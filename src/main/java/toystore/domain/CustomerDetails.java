package toystore.domain;

import java.util.List;

/**
 * Created by Thawhir on 2015/10/05.
 */
public interface CustomerDetails {
    public Long getID();
    public String getUserName();
    public String getPassword();
    public String getFirstName();
    public String getLastName();
    public String getIdNumber();
    public String getContact();
    public List<Orders> getOrders();
}
