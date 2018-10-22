package gatech.a2340.donationtracker.models

import android.content.SharedPreferences
import android.os.Parcel
import android.os.Parcelable

class user(val username: String, val password: String, val userType: UserType) : Parcelable {

    constructor(): this("","",UserType.ADMIN);
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readSerializable() as UserType
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(username)
        parcel.writeString(password)
        parcel.writeSerializable(userType)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<user?>{
        override fun createFromParcel(parcel: Parcel): user {
            return user(parcel)
        }
        override fun newArray(size: Int): Array<user?> {
            return arrayOfNulls(size)
        }
    }
}