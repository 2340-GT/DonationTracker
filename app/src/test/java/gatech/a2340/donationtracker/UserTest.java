package gatech.a2340.donationtracker;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import gatech.a2340.donationtracker.models.UserType;
import gatech.a2340.donationtracker.models.user;


public class UserTest {
    /**
     * Test create new user by Kiet Tran
     */
    @Test
    public void testUser() {
        //test user
        user newUser = mock(user.class);
        when(newUser.getUsername()).thenReturn("email@gmail.com");
        when(newUser.getPassword()).thenReturn("12345678");
        when(newUser.getUserType()).thenReturn(UserType.values()[0]);

        assertNotNull(newUser);
        assertNotEquals("acv", newUser.getUsername());
        assertEquals("email@gmail.com", newUser.getUsername());
        assertEquals("12345678", newUser.getPassword());
        assertEquals(UserType.values()[0], newUser.getUserType());
        assertNull(newUser.getLocation());

        // test employee
        user newEmployee = mock(user.class);
        when(newEmployee.getUsername()).thenReturn("email1@gmail.com");
        when(newEmployee.getPassword()).thenReturn("12345678");
        when(newEmployee.getUserType()).thenReturn(UserType.values()[1]);
        when(newEmployee.getLocation()).thenReturn("location");

        assertNotNull(newEmployee);
        assertNotEquals("acv", newEmployee.getUsername());
        assertEquals("email1@gmail.com", newEmployee.getUsername());
        assertEquals("12345678", newEmployee.getPassword());
        assertEquals(UserType.values()[1], newEmployee.getUserType());
        assertEquals("location", newEmployee.getLocation());

        //test admin
        user newAdmin = mock(user.class);
        when(newAdmin.getUsername()).thenReturn("email2@gmail.com");
        when(newAdmin.getPassword()).thenReturn("12345678");
        when(newAdmin.getUserType()).thenReturn(UserType.values()[2]);

        assertNotNull(newAdmin);
        assertNotEquals("acv", newAdmin.getUsername());
        assertEquals("email2@gmail.com", newAdmin.getUsername());
        assertEquals("12345678", newAdmin.getPassword());
        assertEquals(UserType.values()[2], newAdmin.getUserType());
        assertNull(newUser.getLocation());
    }

}
