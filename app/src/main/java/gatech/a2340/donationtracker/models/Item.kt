package gatech.a2340.donationtracker.models

import android.os.Parcel
import android.os.Parcelable

class Item(val timestamp: String, val location: String, val description: String, val longDescription: String, val value: Double,
           val category: ItemType) : Parcelable {

    constructor(): this( "","","","",0.0,ItemType.Other)

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readDouble(),
            parcel.readSerializable() as ItemType
    )

    override fun writeToParcel(p: Parcel, p1: Int) {
        p.writeString(timestamp)
        p.writeString(location)
        p.writeString(description)
        p.writeString(longDescription)
        p.writeDouble(value)
        p.writeSerializable(category)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Item> {
        override fun createFromParcel(parcel: Parcel): Item {
            return Item(parcel)
        }

        override fun newArray(size: Int): Array<Item?> {
            return arrayOfNulls(size)
        }
    }

}