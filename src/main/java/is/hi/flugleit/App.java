package is.hi.flugleit;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {

  /*
    @RequestMapping("/")
    String home() {
        FlightDB db = new FlightDB("jdbc:sqlite:database.sqlite3");
        FlightController ctrl = new FlightController(db);

        Flight[] results = ctrl.search("2019-03-28", "AEY", "RVK");

        return Integer.toString(results.length);
    }
*/

    @Bean
    public FlightDB flightDB() {
        return new FlightDB("jdbc:sqlite:./database.sqlite3");
    }

    public static void main( String[] args ) {
        SpringApplication.run(App.class, args);
    }
}
