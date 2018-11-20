package gatech.a2340.donationtracker;

import org.junit.Test;
import gatech .a2340.donationtracker.models.Location;

import static junit.framework.TestCase.assertEquals;

import static org.mockito.Mockito.mock;

import static org.mockito.Mockito.when;

public class DucLeUnitTest {
    @Test
    public void TestLocation() {
        Location location = mock(Location.class);
        when(location.getName()).thenReturn("name");
        when(location.getLatitude()).thenReturn(2.0);
        when(location.getLongitude()).thenReturn(3.0);
        when(location.getStreetAddress()).thenReturn("StreetAddress");
        when(location.getCity()).thenReturn("City");
        when(location.getState()).thenReturn("State");
        when(location.getZip()).thenReturn("Zip");
        when(location.getType()).thenReturn("Type");
        when(location.getPhone()).thenReturn("phone");
        when(location.getWebsite()).thenReturn("Website");

        assertEquals(location.getName(), "name");
        assertEquals(location.getLatitude(), 2.0);
        assertEquals(location.getLongitude(), 3.0);
        assertEquals(location.getStreetAddress(), "StreetAddress");
        assertEquals(location.getCity(), "City");
        assertEquals(location.getState(), "State");
        assertEquals(location.getZip(), "Zip");
        assertEquals(location.getType(), "Type");
        assertEquals(location.getPhone(), "phone");
        assertEquals(location.getWebsite(), "Website");

    }
}