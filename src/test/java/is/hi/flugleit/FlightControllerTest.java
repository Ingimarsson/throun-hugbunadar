package is.hi.flugleit;

import org.junit.*;
import static org.junit.Assert.*;

public class FlightControllerTest {
  private Flight[] flights;
  private FlightDB flightDB;
  private FlightController flightController;
  
  public FlightControllerTest() {}

/*
  @Before
  public void setUp() { 
    flightDB = new FlightDB("jdbc:sqlite:./database.sqlite3");
    flightController = new FlightController(flightDB);
 
    flights = flightController.search("27.03.2020","Akureyri", "Reykjavík");

    flights = new Flight[2];

    flights[0]= new Flight("A515", "Air Iceland Connect", "Akureyri", "Reykjavík", "27.03.2020-18:45", "27.03.2020-19:30", 15000);
    flights[1]= new Flight("A573", "Air Iceland Connect", "Akureyri", "Reykjavík", "28.03.2020-07:00", "28.03.2020-07:50", 12500);
  }

  @After
  public void tearDown() { 
    flights=null;
    flightDB=null;
    flightController=null;
  }

  /* Þetta test á að vera commentað út, viljum spyrja aðeins út í það.
  
  @Test
  public void testCreate() {
    for (int i=0; i < flights.length; i++) {
      flightController.createFlight(flights[i]);
    }
  }

  @Test
  public void testSearch() {
    System.out.println(flightController.toString());

    Flight[] flights2 = flightController.search("27.03.2020","Akureyri", "Reykjavík");

    assertEquals(1, flights2.length);
    assertEquals(flights[0], flights2[0]);
  }
  
  @Test
  public void testSortByPrice() {
    flightController.sortByPrice();
    Flight[] flights2 = flightController.getFlights();

    assertEquals(flights[1],flights2[0]);
    assertEquals(flights[0],flights2[1]);
  }
  
  @Test
  public void testSortByDuration() {
    flightController.sortByDuration();
    Flight[] flights2 = flightController.getFlights();

    assertEquals(flights[0],flights2[0]);
    assertEquals(flights[1],flights2[1]);
  }
  
  @Test
  public void testSearchFail() {
    Flight[] flights2 = flightController.search("27.03.2020","22", "Reykjavik");
    
    assertEquals(0, flights2.length);
  }
*/  
}
