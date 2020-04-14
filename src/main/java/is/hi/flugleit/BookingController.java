package is.hi.flugleit;

import java.util.*;

import javax.json.*;
import java.io.StringReader;

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
        JsonReader jsonReader = Json.createReader(new StringReader(raw));
        JsonArray bookingsJson = jsonReader.readArray();
        jsonReader.close();

        List<Booking> bookings = new ArrayList<Booking>();

        for (int i=0; i < bookingsJson.size(); i++) {
            JsonObject b = bookingsJson.getJsonObject(i);

            Passenger p = new Passenger(b.getString("name"), b.getInt("ssn"), b.getString("gender"), b.getString("email"), b.getInt("phoneNumber"));

            Booking booking = new Booking(b.getString("flightNumber"), b.getString("seatNumber"), p);
            booking.setLuggage(b.getBoolean("luggage"));
            booking.generateBookingNumber();
            bookings.add(booking);
        }

        GroupBooking g = new GroupBooking("", bookings.toArray(new Booking[bookings.size()]));
        g.generateGroupBookingNumber();

        this.bookingDB.createGroupBooking(g);

        return g.getGroupBookingNumber();
    }
}
