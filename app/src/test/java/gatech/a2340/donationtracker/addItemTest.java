package gatech.a2340.donationtracker;

import org.junit.Test;

import gatech.a2340.donationtracker.models.Item;
import gatech.a2340.donationtracker.models.ItemType;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
public class addItemTest {

    @Test
    public void testItem() {
        Item item = mock(Item.class);
        when(item.getLocation()).thenReturn("firstLocation");
        when(item.getCategory()).thenReturn(ItemType.Clothing);
        when(item.getDescription()).thenReturn("A really good table");
        when(item.getLongDescription()).thenReturn("A very good table which is barely used.");
        when(item.getValue()).thenReturn(2.0);

        assertEquals(item.getLocation(), "firstLocation");
        assertEquals(item.getCategory(), ItemType.Clothing);
        assertEquals(item.getDescription(), "A really good table");
        assertEquals(item.getLongDescription(), "A very good table which is barely used.");
        assertEquals(item.getValue(), 1.0);
        assertNull(item.getTimestamp(), null);


    }


}
