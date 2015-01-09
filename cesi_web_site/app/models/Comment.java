package models;
 
import java.util.*;
import javax.persistence.*;
 
import play.db.jpa.*;
import play.data.validation.*;
 
@Entity
public class Comment extends Model 
{
 
    @Required
    public String author;
    
    @Required
    public Date commentedAt;
     
    @Lob
    @Required
    @MaxSize(10000)
    public String content;
    
    @ManyToOne
    @Required
    public Post post;
    
    @ManyToOne
    public User user;
    
    public Comment(Post post, String author, String content) 
    {
        this.post = post;
        this.author = author;
        this.content = content;
        this.commentedAt = new Date();
    }
    
    @Override
    public String toString() 
    {
        return content.length() > 50 ? content.substring(0, 50) + "..." : content;
    }
 
}