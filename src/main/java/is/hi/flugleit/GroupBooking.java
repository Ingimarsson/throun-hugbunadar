package is.hi.flugleit;

import javax.json.*;

public class GroupBooking {
    private String groupBookingNumber;
    private Booking[] bookings;
    
    public GroupBooking(String groupBookingNumber, Booking[] bookings){
        this.groupBookingNumber = groupBookingNumber;
        this.bookings = bookings;
    }
    
    public String getGroupBookingNumber(){return groupBookingNumber;}
    public void setGroupBookingNumber(String val){groupBookingNumber=val;}
    public Booking[] getBookings(){return bookings;}
    public void setBookings(Booking[] val){bookings=val; }
  
    /*
    Returns a json object for the web API.
    @return JsonObjectBuilder with the flight data.
    */
    public JsonObjectBuilder createJson() {
        JsonObjectBuilder result = Json.createObjectBuilder();
        JsonArrayBuilder bookingsJson = Json.createArrayBuilder();

        for (Booking b : this.getBookings()) {
            bookingsJson.add(b.createJson());
        }

        result.add("groupBookingNumber", this.getGroupBookingNumber());
        result.add("bookings", bookingsJson);
    
        return result;
    }
}
