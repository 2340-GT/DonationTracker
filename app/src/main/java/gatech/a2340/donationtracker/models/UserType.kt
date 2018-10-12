package gatech.a2340.donationtracker.models

enum class UserType(val userTypeId: Int) {
    USER(1),
    LOCATION_EMPLOYEE(2),
    ADMIN(3)
}