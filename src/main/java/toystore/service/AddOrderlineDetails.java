package toystore.service;


public interface AddOrderlineDetails {
    public boolean addOrderline(Long orderID, Long itemID, int quantity);
}
