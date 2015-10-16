package toystore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

import toystore.App;
import toystore.conf.ItemFactory;
import toystore.domain.Item;
import toystore.domain.Orderline;
import toystore.repository.ItemRepository;

@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class testAddItemStockService extends AbstractTestNGSpringContextTests{
    @Autowired
    AddItemStockService addItemStockService;
    @Autowired
    ItemRepository itemRepository;

    private boolean bool;
    private Item item;
    private Item updatedItem;
    private List<Orderline> orderlines;
    @BeforeMethod
    public void setUp()
    {
        bool = false;
        item = ItemFactory.createItem("Teddybear","Kiddies",1,200,orderlines);
        itemRepository.save(item);
    }

    @Test
    public void testAddItemStock()
    {
        bool = addItemStockService.addItemStock(item.getID(),5);
        Assert.assertTrue(bool);
        updatedItem = itemRepository.findOne(item.getID());
        Assert.assertEquals(updatedItem.getQuantity(),6);
        bool = addItemStockService.addItemStock(item.getID()+1,6);
        Assert.assertFalse(bool);
    }

    @AfterMethod
    public void tearDown()
    {
        itemRepository.delete(updatedItem);
    }
}
