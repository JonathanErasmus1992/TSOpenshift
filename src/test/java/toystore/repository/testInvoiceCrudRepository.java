package toystore.repository;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import org.junit.Assert;

import java.util.List;

import toystore.App;
import toystore.conf.InvoiceFactory;
import toystore.domain.Invoice;
import toystore.domain.Orderline;

@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class testInvoiceCrudRepository extends AbstractTestNGSpringContextTests{
    private Long id;
    private List<Orderline> orderlines;
    private long lo = 1;
    private long updatedLo = 2;
    @Autowired
    InvoiceRepository repository;

    @Test
    public void testCreate()
    {
        Invoice invoice = InvoiceFactory.createInvoice(lo, 200, orderlines);
        repository.save(invoice);
        id = invoice.getID();
        Assert.assertNotNull(invoice);
    }

    @Test(dependsOnMethods = "testCreate")
    public void testRead()
    {
        Invoice invoice = repository.findOne(id);
        Assert.assertEquals(invoice.getTotalPrice(), 200, 0.001);
    }

    @Test(dependsOnMethods = "testRead")
    public void testUpdate()
    {
        Invoice invoice = repository.findOne(id);
        Invoice newInvoice = new Invoice
                .Builder(invoice.getOrderID())
                .copy(invoice)
                .orderID(updatedLo)
                .build();
        repository.save(newInvoice);

        Invoice updatedInvoice = repository.findOne(id);
        Assert.assertNotEquals(invoice.getOrderID(),updatedInvoice.getOrderID());
    }

    @Test(dependsOnMethods = "testUpdate")
    public void testDelete()
    {
        Invoice invoice = repository.findOne(id);
        repository.delete(invoice);
        Invoice newInvoice = repository.findOne(id);
        Assert.assertNull(newInvoice);
    }

}