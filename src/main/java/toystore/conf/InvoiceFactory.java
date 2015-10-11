package toystore.conf;

import java.util.HashMap;
import java.util.List;

import toystore.domain.Invoice;

public class InvoiceFactory {

    public static Invoice createInvoice(Long orderID, float totalPrice, List<HashMap<String,String>> items)
    {
        Invoice invoice = new Invoice
                .Builder(orderID)
                .totalPrice(totalPrice)
                .items(items)
                .build();
        return invoice;
    }
}