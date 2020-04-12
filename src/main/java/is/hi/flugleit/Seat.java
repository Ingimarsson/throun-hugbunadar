package is.hi.flugleit;

import javax.json.*;

public class Seat {
    private String seatNumber;
    private int price;
    private boolean availability;

    public Seat(String seatNumber, int price) {
        this.seatNumber = seatNumber;
        this.price = price;
    }
    public String getSeatNumber(){return seatNumber;}
    public void setSeatNumber(String val){seatNumber=val;}
    public boolean getAvailability(){return availability;}
    public void setAvailability(boolean val){availability=val;}
    public int getPrice(){return price;}
    public void setPrice(int val){price=val;}
    
        /*
    Returns a json object for the web API.

    @return JsonObjectBuilder with the flight data.
    */
    public JsonObjectBuilder createJson() {
        JsonObjectBuilder result = Json.createObjectBuilder();

        result.add("seatNumber", this.getSeatNumber());
        result.add("price", this.getPrice());
        result.add("availability", this.getAvailability());
    
        return result;
    }
}
