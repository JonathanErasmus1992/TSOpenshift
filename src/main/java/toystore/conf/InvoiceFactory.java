package toystore.conf;

import java.util.List;

import toystore.domain.Invoice;
import toystore.domain.Orderline;

public class InvoiceFactory {

    public static Invoice createInvoice(Long orderID, float totalPrice, List<Orderline> orderlines)
    {
        Invoice invoice = new Invoice
                .Builder(orderID)
                .totalPrice(totalPrice)
                .orderlines(orderlines)
                .build();
        return invoice;
    }
}