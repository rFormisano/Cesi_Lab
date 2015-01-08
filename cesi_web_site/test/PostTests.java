import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;
 
public class PostTests extends UnitTest 
{
    @Before
	@After
    public void setup() 
	{
        Fixtures.deleteAll();
    }

	@Test
    public void createPost() 
	{
        // Create a new user and save it
        User bob = new User("bob@gmail.com", "secret", "Bob", Role.findOrCreateByName("Admin")).save();

        // Create a new post
        new Post(bob, "My first post", "Hello world").save();

        // Test that the post has been created
        assertEquals(1, Post.count());

        // Retrieve all post created by bob
        List<Post> bobPosts = Post.find("byAuthor", bob).fetch();

        // Tests
        assertEquals(1, bobPosts.size());
        Post firstPost = bobPosts.get(0);
        assertNotNull(firstPost);
        assertEquals(bob, firstPost.author);
        assertEquals("My first post", firstPost.title);
        assertEquals("Hello world", firstPost.content);
        assertNotNull(firstPost.postedAt);
    }
}

