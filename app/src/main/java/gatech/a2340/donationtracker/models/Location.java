package gatech.a2340.donationtracker.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Location implements Parcelable {
    private String Key;
    private String Name;

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
    public Location(String key, String name, double latitude, double longitude, String streetAddress, String city, String state, String zip, String type, String phone, String website) {
        this.Key = Key;
        this.Name = Name;
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
    public String getKey() {
        return Key;
    }

    public String getName() {
        return Name;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        this.Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        this.Longitude = longitude;
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


    private Location(Parcel in) {
        this.Key = in.readString();
        this.Name = in.readString();
        this.Latitude = in.readDouble();
        this.Longitude = in.readDouble();
        this.streetAddress = in.readString();
        this.City = in.readString();
        this.State = in.readString();
        this.Zip = in.readString();
        this.Type = in.readString();
        this.Phone = in.readString();
        this.Website = in.readString();
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.Key);
        parcel.writeString(this.Name);
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