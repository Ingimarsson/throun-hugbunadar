package is.hi.flugleit;

import javax.json.*;

public class Passenger {
    private String name;
    private int ssn;
    private String gender;
    private String email;
    private int phoneNumber;
    
    public Passenger(String name, int ssn, String gender, String email, int phoneNumber){
        this.name=name;
        this.ssn=ssn;
        this.gender=gender;
        this.email=email;
        this.phoneNumber=phoneNumber;
    }
    
    public String getName(){return name;}
    public void setName(String val){name=val;}
    public int getSsn(){return ssn;}
    public void setSsn(int val){ssn=val;}
    public String getGender(){return gender;}
    public void setGender(String val){gender=val;}
    public String getEmail(){return email;}
    public void setEmail(String val){email=val;}
    public int getPhoneNumber(){return phoneNumber;}
    public void setPhoneNumber(int val){phoneNumber=val;}
    
    /*
    Returns a json object for the web API.
    @return JsonObjectBuilder with the flight data.
    */
    public JsonObjectBuilder createJson() {
        JsonObjectBuilder result = Json.createObjectBuilder();

        result.add("name", this.getName());
        result.add("ssn", this.getSsn());
        result.add("gender", this.getGender());
        result.add("email", this.getEmail());
        result.add("phoneNumber", this.getPhoneNumber());
    
        return result;
    }
}
