package toystore.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
public class Order implements OrderDetails, Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;
    private Date dateModified;
    private float totalPrice;
    private Boolean checkout;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="order_id")
    private List<Orderline> orderlines;

    private Order()
    {

    }

    public Order(Builder builder)
    {
        this.ID=builder.ID;
        this.dateModified=builder.dateModified;
        this.totalPrice=builder.totalPrice;
        this.checkout=builder.checkout;
        this.orderlines=builder.orderlines;

    }
    @Override
    public Long getID()
    {
        return this.ID;
    }
    @Override
    public Date getDateModified()
    {
        return this.dateModified;
    }
    @Override
    public float getTotalPrice(){return this.totalPrice;}
    @Override
    public Boolean getCheckout(){return this.checkout;}
    @Override
    public List<Orderline> getOrderlines(){return this.orderlines;}


    public static class Builder
    {
        private Long ID;
        private Date dateModified;
        private float totalPrice;
        private Boolean checkout;
        private List<Orderline> orderlines;

        public Builder(Long ID)
        {
            this.ID=ID;
        }

        public Builder dateModified(Date dateModified)
        {
            this.dateModified=dateModified;
            return this;
        }

        public Builder totalPrice(float totalPrice)
        {
            this.totalPrice=totalPrice;
            return this;
        }

        public Builder checkout(Boolean checkout)
        {
            this.checkout=checkout;
            return this;
        }

        public Builder orderlines(List<Orderline> orderlines)
        {
            this.orderlines=orderlines;
            return this;
        }


        public Builder copy(Order order)
        {
            this.ID=order.getID();
            this.dateModified=order.getDateModified();
            this.totalPrice=order.getTotalPrice();
            this.checkout=order.getCheckout();
            this.orderlines=order.getOrderlines();
            return this;
        }

        public Order build()
        {
            return new Order(this);
        }
    }
}
