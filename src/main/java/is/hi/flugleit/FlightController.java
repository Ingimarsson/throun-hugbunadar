package is.hi.flugleit;

import java.util.*;

import javax.json.*;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
public class FlightController {
    private FlightDB flightDB;
    private Flight[] flights;

    public FlightController(FlightDB flightDB) {
        this.flightDB = flightDB;
    }
   
    @RequestMapping("/search")
    public String getSearch(@RequestParam String date, @RequestParam String to, @RequestParam String from) {
        Flight[] flights = this.flightDB.getFlights(date, to, from);

        JsonArrayBuilder results = Json.createArrayBuilder();

        for (Flight f : flights) {
            results.add(f.createJson());
        }

        return results.build().toString();
    }

    @RequestMapping("/flight")
    public String getFlight(@RequestParam String flightNumber) {
        Flight flight = this.flightDB.getFlight(flightNumber);
        
        return flight.createJson().build().toString();
    }

    @PostMapping("/flight")
    public String makeFlight(@RequestBody Flight flight){
        return "OK";
    }
}
