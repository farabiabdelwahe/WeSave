package wesave.farabi.com.wesave.Data;

import java.io.Serializable;

/**
 * Created by GSC on 03/10/2017.
 */

public class CallForDonation implements Serializable{


    private String imgUrl ;
    private String location  ;
    private String bloodtype ;
    private String Date ;
    private String name ;
    private String callmessage ;
    private String phoneNumber ;

    public CallForDonation(String imgUrl, String location, String bloodtype, String date) {
        this.imgUrl = imgUrl;
        this.location = location;
        this.bloodtype = bloodtype;
        Date = date;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBloodtype() {
        return bloodtype;
    }

    public void setBloodtype(String bloodtype) {
        this.bloodtype = bloodtype;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    @Override
    public String toString() {
        return "CallForDonation{" +
                "imgUrl='" + imgUrl + '\'' +
                ", location='" + location + '\'' +
                ", bloodtype='" + bloodtype + '\'' +
                ", Date='" + Date + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCallmessage(String callmessage) {
        this.callmessage = callmessage;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
