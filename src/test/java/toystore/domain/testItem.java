package toystore.domain;

import toystore.conf.ItemFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import java.util.List;

public class testItem {

    private Item item;
    private List<Orderline> orderlines;

    @Before
    public void setUp()
    {
        item = ItemFactory.createItem("Teddybear","Kiddies",1,200,orderlines);
    }

    @Test
    public void testCreate()
    {
        Assert.assertEquals(item.getCategory(),"Kiddies");
    }

    @Test
    public void testUpdate()
    {
        Item newItem = new Item
                            .Builder(item.getName())
                            .copy(item)
                            .category("Teens")
                            .build();
        Assert.assertEquals(item.getName(),newItem.getName());
        Assert.assertNotEquals(item.getCategory(),newItem.getCategory());
    }

    @After
    public void tearDown()
    {

    }
}
