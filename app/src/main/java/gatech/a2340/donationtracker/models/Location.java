package gatech.a2340.donationtracker.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Location implements Parcelable {


    private double latitude;
    private double longitude;
    private String streetAddress;
    private String city;
    private String state;
    private String zip;
    private String type;
    private String phone;
    private String website;

    public Location(double latitude, double longitude, String streetAddress, String city, String state, String zip, String type, String phone, String website) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.type = type;
        this.phone = phone;
        this.website = website;
    }

    /* **********************
     * Getters and setters
     */
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longitude;
    }

    public void setLongtitude(double longtitude) {
        this.longitude = longtitude;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
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
        this.latitude = in.readDouble();
        this.longitude = in.readDouble();
        this.streetAddress = in.readString();
        this.city = in.readString();;
        this.state = in.readString();;
        this.zip = in.readString();;
        this.type = in.readString();;
        this.phone = in.readString();;
        this.website = in.readString();;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.latitude);
        parcel.writeDouble(this.longitude);
        parcel.writeString(this.streetAddress);
        parcel.writeString(this.city);
        parcel.writeString(this.state);
        parcel.writeString(this.zip);
        parcel.writeString(this.type);
        parcel.writeString(this.phone);
        parcel.writeString(this.website);
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
