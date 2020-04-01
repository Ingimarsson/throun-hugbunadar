package is.hi.flugleit;

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
}
