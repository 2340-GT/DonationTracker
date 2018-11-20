package gatech.a2340.donationtracker.models
import android.os.Parcel
import android.os.Parcelable



class user(val username: String, val password: String, val userType: UserType, val location: String) : Parcelable {

    fun checkPasswordStrength(): Int {
        return checkPasswordStrength(password)
    }

    constructor(): this("","",UserType.ADMIN,"")

    constructor(username: String, password: String, userType: UserType): this(username,password,userType,"")

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readSerializable() as UserType,
            parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(username)
        parcel.writeString(password)
        parcel.writeSerializable(userType)
        parcel.writeString(location)
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
        @JvmStatic
        fun checkPasswordStrength(password: String):Int {
            var passwordStrength = 0
            if (password.length >= 8) {
                passwordStrength++
            }

            if ((Regex("[~@#$%^&*:;<>.,/}{+]")).containsMatchIn(password)) {
                passwordStrength++
            }

            if ((Regex("[0-9]")).containsMatchIn(password)) {
                passwordStrength++
            }

            if (password.compareTo(password.toLowerCase()) != 0 && password.compareTo(password.toUpperCase()) != 0) {
                passwordStrength++
            }

            return passwordStrength

        }
    }
}