package toystore.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

import toystore.domain.Customer;
import toystore.domain.Item;
import toystore.domain.Orders;
import toystore.service.AddItemService;
import toystore.service.AddOrderService;
import toystore.service.ChangePasswordService;
import toystore.service.EmptyOrderService;
import toystore.service.GetOrderService;
import toystore.service.LoginService;
import toystore.service.RegistrationService;
import toystore.service.ViewItemsByCategoryService;
import toystore.service.ViewItemsService;

@RestController
@RequestMapping("/**")
public class AllApiController {
    @Autowired
    RegistrationService registrationService;
    @Autowired
    LoginService loginService;
    @Autowired
    AddOrderService addOrderService;
    @Autowired
    GetOrderService getOrderService;
    @Autowired
    EmptyOrderService emptyOrderService;
    @Autowired
    AddItemService addItemService;
    @Autowired
    ViewItemsService viewItemsService;
    @Autowired
    ViewItemsByCategoryService viewItemsByCategoryService;
    @Autowired
    ChangePasswordService changePasswordService;

    //USER REGISTRATION
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ResponseEntity<Boolean> createCustomer(@RequestParam String username,
                                                  @RequestParam String password,
                                                  @RequestParam String firstname,
                                                  @RequestParam String lastname,
                                                  @RequestParam String idnumber,
                                                  @RequestParam String contact)
    {
        boolean bool = registrationService.Register(username, password, firstname, lastname, idnumber, contact);
        if(!bool)
            return new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    //USER LOGIN
    @RequestMapping(value ="/login", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getCustomer(@RequestParam String username,
                                                @RequestParam String password)
    {
        Customer customer = loginService.Login(username, password);
        //the following code should be added into a service to check if customer exists
        if(customer==null)
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Customer>(customer, HttpStatus.FOUND);
    }
    //GET EXISTING OPEN ORDER ON CUSTOMER OR CREATE A NEW ONE IF ONE DOES NOT EXIST
    @RequestMapping(value = "order/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Orders> getOrder(@RequestParam Long customerID)
    {
        Orders order = getOrderService.getOrder(customerID);
        if(order==null)
        {
            boolean bool = addOrderService.addOrder(customerID);
                if(!bool)
                    return new ResponseEntity<Orders>(HttpStatus.FORBIDDEN);
            order = getOrderService.getOrder(customerID);
            return new ResponseEntity<Orders>(order, HttpStatus.CREATED);
        }
        return new ResponseEntity<Orders>(order, HttpStatus.FOUND);
    }
    //DELETE EXISTING ORDER
    @RequestMapping(value = "order/delete", method = RequestMethod.GET)
    public ResponseEntity<Boolean> deleteOrder(@RequestParam Long orderID)
    {
        boolean bool = emptyOrderService.emptyOrder(orderID);
        if(!bool)
            return new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);//order id not found - has already been deleted
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }
    //ADD NEW ITEM
    @RequestMapping(value = "item/add", method = RequestMethod.GET)
    public ResponseEntity<Boolean> addItem(@RequestParam String name,
                                           @RequestParam String category,
                                           @RequestParam int stock,
                                           @RequestParam float price)
    {
        boolean bool = addItemService.addItem(name, category, stock, price);
        if(!bool)
            return new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);//Item with that name already exists
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }
    //VIEW ALL ITEMS
    @RequestMapping(value = "item/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Item>> viewAllItems()
    {
        List<Item> items = viewItemsService.viewAllItems();
        if(items.size()==0)
            return new ResponseEntity<List<Item>>(HttpStatus.NOT_FOUND);//No items exist in the database - or something else went wrong
        return new ResponseEntity<List<Item>>(items, HttpStatus.FOUND);
    }
    //VIEW ITEMS BY CATEGORY
    @RequestMapping(value = "item/category", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Item>> viewAllItemsByCategory(@RequestParam String category)
    {
        List<Item> items = viewItemsByCategoryService.viewItemsByCategory(category);
        if(items.size()==0)
            return new ResponseEntity<List<Item>>(HttpStatus.NOT_FOUND);//No items with that category exist
        return new ResponseEntity<List<Item>>(items, HttpStatus.FOUND);
    }
    //CHANGE PASSWORD
    @RequestMapping(value = "customer/changepassword", method = RequestMethod.GET)
    public ResponseEntity<Boolean> changePassword(@RequestParam Long customerID,
                                                  @RequestParam String newPassword)
    {
        boolean bool = changePasswordService.changePassword(customerID, newPassword);
        if(!bool)
            return new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);//customer ID is invalid
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }


}
