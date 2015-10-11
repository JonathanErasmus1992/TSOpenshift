package toystore.domain;

import toystore.conf.InvoiceFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import java.util.List;

public class testInvoice{
    private Invoice invoice;
    private List<Orderline> orderlines;
    private long lo = 1;
    private long updatedLo = 2;

    @Before
    public void setUp()
    {
        invoice = InvoiceFactory.createInvoice(lo, 200, orderlines);
    }

    @Test
    public void testCreate() throws Exception
    {
        Assert.assertEquals(invoice.getOrderlines(), orderlines);
    }

    @Test
    public void testUpdate()
    {
        Invoice newInvoice = new Invoice
                .Builder(invoice.getOrderID())
                .copy(invoice)
                .orderID(updatedLo)
                .build();
        Assert.assertEquals(newInvoice.getOrderlines(),invoice.getOrderlines());
        Assert.assertNotEquals(newInvoice.getOrderID(), invoice.getOrderID());
    }

    @After
    public void tearDown()
    {

    }

}