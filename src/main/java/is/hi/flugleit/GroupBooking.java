package is.hi.flugleit;

import javax.json.*;

public class GroupBooking {
  private String groupBookingNumber;
  private Booking[] bookings;
  
  public GroupBooking(String groupBookingNumber, Booking[] bookings){
    this.groupBookingNumber = groupBookingNumber;

    bookings = new Booking[bookings.length];

    for(int i=0;i<bookings.length;i++)
      this.bookings[i] = bookings[i];
  }
  
  public String getGroupBookingNumber(){return groupBookingNumber;}
  public Booking[] getBookings() { return bookings; }
  public void setGroupBookingNumber(String val){groupBookingNumber=val;}
  public Booking[] getBookings(){return bookings;}
  public void setBookings(Booking[] val){
    int N=val.length;
    this.booking=new Booking[N];
    for(int i=0;i<N;i++)
      booking[i]=val[i];
  }
  
    /*
    Returns a json object for the web API.
    @return JsonObjectBuilder with the flight data.
    */
    public JsonObjectBuilder createJson() {
        JsonObjectBuilder result = Json.createObjectBuilder();

        result.add("groupBookingNumber", this.getGroupBookingNumber());
        result.add("bookings", this.getBookings().getJson());
    
        return result;
    }

}
