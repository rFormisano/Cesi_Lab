import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;
 
public class BasicTest extends UnitTest 
{
    @Before
    @After
    public void setup() 
    {
        Fixtures.deleteAll();
    }
    
    @Test
    public void fullTest() 
    {
        Fixtures.load("data.yml");

        // Count things
        assertEquals(2, User.count());
        assertEquals(3, Post.count());
        assertEquals(3, Comment.count());

        // Try to connect as users
        assertNotNull(User.connect("bob@gmail.com", "secret"));
        assertNotNull(User.connect("jeff@gmail.com", "secret"));
        assertNull(User.connect("jeff@gmail.com", "badpassword"));
        assertNull(User.connect("tom@gmail.com", "secret"));

        // Find all bob posts
        List<Post> bobPosts = Post.find("author.email", "bob@gmail.com").fetch();
        assertEquals(2, bobPosts.size());

        // Find all comments related to bob posts
        List<Comment> bobComments = Comment.find("post.author.email", "bob@gmail.com").fetch();
        assertEquals(3, bobComments.size());

        // Find the most recent post
        Post frontPost = Post.find("order by postedAt desc").first();
        assertNotNull(frontPost);
        assertEquals("About the model layer", frontPost.title);

        // Check that this post has two comments
        assertEquals(2, frontPost.comments.size());

        // Post a new comment
        frontPost.addComment("Jim", "Hello guys");
        assertEquals(3, frontPost.comments.size());
        assertEquals(4, Comment.count());
    }
    
    @Test
    public void testTags() 
    {
        // Create a new user and save it
        User bob = new User("bob@gmail.com", "secret", "Bob", "Bob", Role.findOrCreateByName("Admin")).save();

        // Create a new post
        Post bobPost = new Post(bob, "My first post", "Hello world").save();
        Post anotherBobPost = new Post(bob, "My second post post", "Hello world").save();
        
        // Well
        assertEquals(0, Post.findTaggedWith("Red").size());
        
        // Tag it now
        bobPost.tagItWith("Red").tagItWith("Blue").save();
        anotherBobPost.tagItWith("Red").tagItWith("Green").save();
        
        // Check
        assertEquals(2, Post.findTaggedWith("Red").size());        
        assertEquals(1, Post.findTaggedWith("Blue").size());
        assertEquals(1, Post.findTaggedWith("Green").size());
        
        assertEquals(1, Post.findTaggedWith("Red", "Blue").size());   
        assertEquals(1, Post.findTaggedWith("Red", "Green").size());   
        assertEquals(0, Post.findTaggedWith("Red", "Green", "Blue").size());  
        assertEquals(0, Post.findTaggedWith("Green", "Blue").size());    
        
        List<Map> cloud = Tag.getCloud();
        Collections.sort(cloud, new Comparator<Map>() 
		{
            public int compare(Map m1, Map m2) 
			{
                return m1.get("tag").toString().compareTo(m2.get("tag").toString());
            }
        });
        assertEquals("[{tag=Blue, pound=1}, {tag=Green, pound=1}, {tag=Red, pound=2}]", cloud.toString());
    }
 
}