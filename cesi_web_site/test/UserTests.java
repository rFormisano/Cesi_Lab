import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class UserTests extends UnitTest 
{	
    @Before
    @After
    public void setup() 
    {
        Fixtures.deleteAll();
    }
	
    @Test
    public void createAndRetrieveUser() 
    {
        // Create a new user and save it
        new User("toto@toto.com", "secret", "Toto", "Toto", Role.findOrCreateByName("Admin")).save();

        // Retrieve the user with bob username
        User bob = User.find("byEmail", "toto@toto.com").first();

        // Test 
        assertNotNull(bob);
        assertEquals("Toto", bob.firstName);
    }
	
    @Test
    public void tryConnectAsUser() 
    {
        // Create a new user and save it
        new User("bob@gmail.com", "secret", "Bob", "Bob", Role.findOrCreateByName("Admin")).save();

        // Test 
        assertNotNull(User.connect("bob@gmail.com", "secret"));
        assertNull(User.connect("bob@gmail.com", "badpassword"));
        assertNull(User.connect("tom@gmail.com", "secret"));
    }
}