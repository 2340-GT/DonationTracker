//Author: Nguyen Nguyen
package gatech.a2340.donationtracker;
import org.junit.Test;

import gatech.a2340.donationtracker.models.user;
import static junit.framework.TestCase.assertEquals;


public class PasswordStrengthTest {
    @Test
    // Since I have 4 conditions in my function, I will need 15 test cases to achieve branch coverage
    public void testPassword() {
        assertEquals(0, user.checkPasswordStrength("a"));
        assertEquals(1, user.checkPasswordStrength("qwertyuiop"));
        assertEquals(1, user.checkPasswordStrength("@"));
        assertEquals(1, user.checkPasswordStrength("42"));
        assertEquals(1, user.checkPasswordStrength("ABm"));
        assertEquals(2, user.checkPasswordStrength("$g)fg*n,"));
        assertEquals(2, user.checkPasswordStrength("aaaaa4ab"));
        assertEquals(2, user.checkPasswordStrength("aAaAaAaA"));
        assertEquals(2, user.checkPasswordStrength("13Aabde"));
        assertEquals(2, user.checkPasswordStrength("<5>"));
        assertEquals(2, user.checkPasswordStrength(";Aa,"));
        assertEquals(3, user.checkPasswordStrength("ab*{}.^%4"));
        assertEquals(3, user.checkPasswordStrength(":+~5970&"));
        assertEquals(3, user.checkPasswordStrength("86eE#"));
        assertEquals(4, user.checkPasswordStrength("tHd8395&$%"));
    }
}
