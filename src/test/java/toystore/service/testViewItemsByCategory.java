package toystore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import toystore.App;
import toystore.conf.ItemFactory;
import toystore.domain.Item;
import toystore.domain.Orderline;
import toystore.repository.ItemRepository;

@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class testViewItemsByCategory extends AbstractTestNGSpringContextTests {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ViewItemsByCategoryService viewItemsByCategoryService;

    private Item item1;
    private Item item2;
    private Item item3;
    private List<Orderline> orderlines = new ArrayList<Orderline>();
    private List<Item> items = new ArrayList<Item>();
    private List<Item> serviceItems;

    @BeforeMethod
    public void setUp()
    {
        item1 = ItemFactory.createItem("Teddy Bear", "test", 2, 200, orderlines);
        item2 = ItemFactory.createItem("Hoola Hoop","dummy",2,50,orderlines);
        item3 = ItemFactory.createItem("Playstation","test",2,3000,orderlines);
        items.add(item1);
        items.add(item2);
        items.add(item3);
        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(item3);

    }

    @Test
    public void testViewItems()
    {
        serviceItems = viewItemsByCategoryService.viewItemsByCategory("test");
        Assert.assertEquals(serviceItems.get(serviceItems.size()-2).getName(),"Teddy Bear");
        Assert.assertEquals(serviceItems.get(serviceItems.size()-1).getName(),"Playstation");
    }

    @AfterMethod
    public void tearDown()
    {
        itemRepository.delete(item1);
        itemRepository.delete(item2);
        itemRepository.delete(item3);
    }
}
