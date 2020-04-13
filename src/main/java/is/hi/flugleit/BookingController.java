package is.hi.flugleit;

import java.util.*;

import javax.json.*;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
public class BookingController {
    private BookingDB bookingDB;
    private Flight[] flights;

    public BookingController(BookingDB bookingDB) {
        this.bookingDB = bookingDB;
    }

    @RequestMapping("/booking")
    public String getBooking(@RequestParam String bookingNumber) {
        GroupBooking g = this.bookingDB.getGroupBooking(bookingNumber);

        return g.createJson().build().toString();
    }
   
    @RequestMapping(method=RequestMethod.POST, value="/booking")
    public String makeBooking(@RequestBody String raw){
        return "json = "+raw;
    }
}
