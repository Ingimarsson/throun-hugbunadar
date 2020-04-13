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
    public String getSearch(@RequestParam String date, @RequestParam String to, @RequestParam String from, @RequestParam String sort) {
        Flight[] flights = this.flightDB.getFlights(date, to, from);

        JsonArrayBuilder results = Json.createArrayBuilder();

        for (Flight f : flights) {
            results.add(f.createJson());
        }

        return results.build().toString();
    }

    @RequestMapping("/flight")
    public String getFlight(@RequestParam String flightNumber) {
        //Þarf að sækja flight úr flightDB
        
        results.add(Json.createObjectBuilder()
            .add("airline", flight.getAirline())
            .add("destTo", flight.getDestTo())
            .add("destFrom", flight.getDestFrom())
            .add("departureTime", flight.getDepartureTime())
            .add("arrivalTime", flight.getArrivalTime())
            .add("price", flight.getPrice())
        );
        return results.build().toString();
    }

    @PostMapping("/flight")
    public String makeFlight(@RequestBody Flight flight){
        return "OK";
    }
}
