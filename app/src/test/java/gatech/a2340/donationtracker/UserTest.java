package gatech.a2340.donationtracker;

import org.junit.Test;

import static org.junit.Assert.*;

import gatech.a2340.donationtracker.models.UserType;
import gatech.a2340.donationtracker.models.user;


public class UserTest {
    /**
     * Test create new user by Kiet Tran
     */
    @Test
    public void testUser() {
        user newUser = new user("email@gmail.com", "12345", UserType.values()[0]);
        assertNotNull(newUser);
        assertNotEquals("acv", newUser.getUsername());
        assertEquals("email@gmail.com", newUser.getUsername());
        assertEquals("12345678", newUser.getPassword());
        assertEquals(UserType.values()[0], newUser.getPassword());
        assertTrue(newUser.getLocation().length() == 0);
    }

}
