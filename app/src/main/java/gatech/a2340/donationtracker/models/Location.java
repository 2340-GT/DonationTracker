package gatech.a2340.donationtracker.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Location implements Parcelable {


    private double Latitude;
    private double Longitude;
    private String streetAddress;
    private String City;
    private String State;
    private String Zip;
    private String Type;
    private String Phone;
    private String Website;

    public  Location() {

    }
    public Location(double latitude, double longitude, String streetAddress, String city, String state, String zip, String type, String phone, String website) {
        this.Latitude = latitude;
        this.Longitude = longitude;
        this.streetAddress = streetAddress;
        this.City = city;
        this.State = state;
        this.Zip = zip;
        this.Type = type;
        this.Phone = phone;
        this.Website = website;
    }

    /* **********************
     * Getters and setters
     */
    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        this.Latitude = latitude;
    }

    public double getLongtitude() {
        return Longitude;
    }

    public void setLongtitude(double longtitude) {
        this.Longitude = longtitude;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        this.City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        this.State = state;
    }

    public String getZip() {
        return Zip;
    }

    public void setZip(String zip) {
        this.Zip = zip;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        this.Type = type;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        this.Phone = phone;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        this.Website = website;
    }

    /* *********************************
     * These methods are required by the Parcelable interface
     * I just wanted to demo how to pass information from one activity
     * to another through an intent.   If this were a real project, I
     * would probably have the facade maintain information about the
     * currently selected student which would remove the need to
     * pass the student object in an intent, which would remove the need
     * to implement the Parcelable interface and these methods.
     */

    /**
     * Constructor used by Parcel to make a new student out of the
     * parceled information
     *
     * @param in  the parcel containing the student information
     */
    private Location(Parcel in) {
        this.Latitude = in.readDouble();
        this.Longitude = in.readDouble();
        this.streetAddress = in.readString();
        this.City = in.readString();;
        this.State = in.readString();;
        this.Zip = in.readString();;
        this.Type = in.readString();;
        this.Phone = in.readString();;
        this.Website = in.readString();;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.Latitude);
        parcel.writeDouble(this.Longitude);
        parcel.writeString(this.streetAddress);
        parcel.writeString(this.City);
        parcel.writeString(this.State);
        parcel.writeString(this.Zip);
        parcel.writeString(this.Type);
        parcel.writeString(this.Phone);
        parcel.writeString(this.Website);
    }

    /**
     * Should not have to edit this method if the constructor and write method are
     * working correctly.
     */
    public static final Parcelable.Creator<Location> CREATOR
            = new Parcelable.Creator<Location>() {
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        public Location[] newArray(int size) {
            return new Location[size];
        }
    };
}
