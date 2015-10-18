package toystore.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import toystore.domain.Customer;
import toystore.service.LoginService;
import toystore.service.RegistrationService;

@RestController
@RequestMapping("/**")
public class AllApiController {
    @Autowired
    RegistrationService registrationService;
    @Autowired
    LoginService loginService;


    // @RequestMapping(value = "/passenger/create",method = RequestMethod.POST)
    // public ResponseEntity<Void> createPassenger(@RequestBody Passenger passenger, UriComponentsBuilder ucBuilder)
    // {
    //     service.newPassenger();
    // }
//    public ResponseEntity<Void> createSubject(@RequestBody Subject subject,    UriComponentsBuilder ucBuilder) {
//        System.out.println("Creating Subject " + subject.getName());

//     USE THIS IF YOU WANT TO CHECK UNIQUE OBJECT
//      if (SubjectService.isSubjectExist(Subject)) {
//            System.out.println("A Subject with name " + Subject.getName() + " already exist");
//            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
//        }

    //       service.save(subject);

    //    HttpHeaders headers = new HttpHeaders();
    //   headers.setLocation(ucBuilder.path("/subject/{id}").buildAndExpand(subject.getId()).toUri());
    //   return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    //  }

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


}
