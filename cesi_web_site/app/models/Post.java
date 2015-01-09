package models;
 
import java.util.*;
import javax.persistence.*;

import play.data.binding.*;
import play.data.validation.*;
import play.db.jpa.Model;

@Entity
public class Post extends Model 
{
 
    @Required
    public String title;
    
    @Required @As("yyyy-MM-dd")
    public Date postedAt;
    
    @Lob
    @Required
    @MaxSize(10000)
    public String content;
    
    @Required
    @ManyToOne
    public User author;
    
    @OneToMany(mappedBy="post", cascade=CascadeType.ALL)
    public List<Comment> comments;
    
    @OneToMany(mappedBy="post", cascade=CascadeType.ALL)
    public List<File> files;
    
    @ManyToMany(cascade=CascadeType.PERSIST)
    public Set<Tag> tags;
    
    @ManyToMany(cascade=CascadeType.PERSIST)
    public List<Category> categories;
    
    public Post(User author, String title, String content) 
    { 
        this.comments = new ArrayList<Comment>();
        this.tags = new TreeSet();  
        this.author = author;
        this.title = title;
        this.content = content;
        this.postedAt = new Date();
    }
    
    public Post addComment(String author, String content) 
    {
        Comment newComment = new Comment(this, author, content);
        this.comments.add(newComment);
        this.save();
        return this;
    }
    
    public Post previous() 
    {
        return Post.find("postedAt < ?1 order by postedAt desc", postedAt).first();
    }

    public Post next() 
    {
        return Post.find("postedAt > ?1 order by postedAt asc", postedAt).first();
    }
    
    public Post tagItWith(String name) 
    {
        tags.add(Tag.findOrCreateByName(name));
        return this;
    }
    
    public static List<Post> findTaggedWith(String tag) 
    {
        return Post.find(
            "select distinct p from Post p join p.tags as t where t.name = ?",
            tag
        ).fetch();
    }
    
    public static List<Post> findTaggedWith(String... tags) 
    {
        return Post.find(
            "select distinct p.id from Post p join p.tags as t where t.name in (:tags) group by p.id having count(t.id) = :size"
        ).bind("tags", tags).bind("size", tags.length).fetch();
    }
    
    @Override
    public String toString() 
    {
        return title;
    }
 
}
