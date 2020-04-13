package is.hi.flugleit;
import java.util.*;

import javax.json.*;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
public class BookingController {
    private FlightDB flightDB;
    private Flight[] flights;

    public BookingController(BookingDB bookingDB) {
        this.bookingDB = bookingDB;
    }

    @RequestMapping("/booking")
   public String getBooking(@RequestParam String bookingNumber) {
      //Það þarf að bæta við að sækja booking úr bookingDB
      results.add(Json.createObjectBuilder()
         .add("passenger", Booking.getPassenger())
         .add("seat", Booking.getSeat())
         .add("luggage", Booking.getLuggage())
         .add("paid", Booking.getPaid())
      );
      
      return results.build().toString();
   }

   
   @PostMapping("/booking")
   public String makeBooking(@RequestBody Booking booking){
      
   }


}