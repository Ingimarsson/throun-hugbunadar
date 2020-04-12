package is.hi.flugleit;

import org.junit.*;
import static org.junit.Assert.*;

public class SeatTest {
    private Seat s1, s2;

    public SeatTest() {}

    @Before
    public void setUp() {
        s1 = new Seat("4A", 21000);
        s2 = new Seat("21F", 13000);
    }

    @After
    public void tearDown() {
        s1 = null;
        s2 = null;
    }

    @Test
    public void testPrice() {
        assertEquals(21000, s1.getPrice());
        assertEquals(13000, s2.getPrice());
    }

    @Test
    public void testSeatNumber() {
        assertEquals("4A", s1.getSeatNumber());
        assertEquals("21F", s2.getSeatNumber());
    }

    @Test
    public void testAvailability() {
        s1.setAvailability(false);
        assertEquals(false, s1.getAvailability());
        s1.setAvailability(true);
        assertEquals(true, s1.getAvailability());
    }
}
