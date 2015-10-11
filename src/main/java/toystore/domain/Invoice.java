package toystore.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

@Entity
public class Invoice implements InvoiceDetails, Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;
    private Long orderID;
    private float totalPrice;
    private List<HashMap<String,String>> items;


    private Invoice()
    {

    }

    public Invoice(Builder builder)
    {
        this.ID=builder.ID;
        this.orderID=builder.orderID;
        this.totalPrice=builder.totalPrice;
        this.items=builder.items;

    }
    @Override
    public Long getID()
    {
        return this.ID;
    }
    @Override
    public Long getOrderID()
    {
        return this.orderID;
    }
    @Override
    public float getTotalPrice()
    {
        return this.totalPrice;
    }
    @Override
    public List<HashMap<String,String>> getItems()
    {
        return this.items;
    }


    public static class Builder
    {
        private Long ID;
        private Long orderID;
        private float totalPrice;
        private List<HashMap<String,String>> items;

        public Builder(Long orderID)
        {
            this.orderID=orderID;
        }

        public Builder orderID(Long orderID)
        {
            this.orderID=orderID;
            return this;
        }

        public Builder ID(Long ID)
        {
            this.ID=ID;
            return this;
        }

        public Builder totalPrice(float totalPrice)
        {
            this.totalPrice=totalPrice;
            return this;
        }

        public Builder items(List<HashMap<String,String>> items)
        {
            this.items=items;
            return this;
        }

        public Builder copy(Invoice invoice)
        {
            this.ID=invoice.getID();
            this.orderID=invoice.getOrderID();
            this.totalPrice=invoice.getTotalPrice();
            this.items=invoice.getItems();
            return this;
        }

        public Invoice build()
        {
            return new Invoice(this);
        }
    }
}

