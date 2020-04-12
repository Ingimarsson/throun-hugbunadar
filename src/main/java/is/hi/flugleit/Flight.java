package is.hi.flugleit;

import javax.json.*;

public class Flight {

    private String flightNumber;
    private String airline;
    private String destTo;
    private String destFrom;
    private String departureTime;
    private String arrivalTime;
    private int price;
    private String duration;
    private int availableSeats;
    private Seat[] seats;
    private int luggagePrice;
    
    public Flight(String flightNumber, String airline, String destTo, String destFrom, String departureTime, String arrivalTime, int price){
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.destFrom = destFrom;
        this.destTo = destTo;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
    }
    //Þurfum kannski ekki alla þetta getters/setters?
    
    public String getFlightNumber(){return flightNumber;}
    public void setFlightNumber(String val){flightNumber=val;}
    public String getAirline(){return airline;}
    public void setAirline(String val){airline=val;}
    public String getDestTo(){return destTo;}
    public void setDestTo(String val){destTo=val;}
    public String getDestFrom(){return destFrom;}
    public void setDestFrom(String val){destFrom=val;}
    public String getDepartureTime(){return departureTime;}
    public void setDepartureTime(String val){departureTime=val;} 
    public String getArrivalTime(){return arrivalTime;}
    public void setArrivalTime(String val){arrivalTime=val;}    
    public int getPrice(){return price;}
    public void setFlightNumber(int val){price=val;}    
    public String getDuration(){return duration;}
    public void setDuration(String val){duration=val;}
    public int getAvailableSeats(){return availableSeats;}
    public void setAvailableSeats(int val){availableSeats=val;}
    public Seat[] getSeats(){return seats;}
    public void setSeats(Seat[] val){
        int N=val.length;
        seats =new Seat[N];
        for(int i=0;i<N;i++)
            seats[i]=val[i];
    }
    public int getLuggagePrice(){return luggagePrice;}
    public void setLuggagePrice(int val){luggagePrice=val;}
   
    //Updates the availability of a certain seat
    public void updateAvailability(Seat seat, boolean available){
        if (seat.getAvailability()==available) return;
        else {
            seat.setAvailability(available);
            if(available) availableSeats++;
            else availableSeats--;
        }
    }

    /*
    Returns a json object for the web API.

    @return JsonObjectBuilder with the flight data.
    */
    public JsonObjectBuilder createJson() {
        JsonObjectBuilder result = Json.createObjectBuilder();

        result.add("flightNumber", this.getFlightNumber());
        result.add("airline", this.getAirline());
        result.add("destTo", this.getDestTo());
        result.add("destFrom", this.getDestFrom());
        result.add("departureTime", this.getDepartureTime());
        result.add("arrivalTime", this.getArrivalTime());
        result.add("price", this.getPrice());
        result.add("availableSeats", this.getAvailableSeats());
        result.add("luggagePrice", this.getLuggagePrice());
    
        return result;
    }
}
