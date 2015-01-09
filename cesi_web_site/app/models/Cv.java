package models;

import java.util.*;
import javax.persistence.*;
 
import play.db.jpa.*;
import play.data.validation.*;

@Entity
public class Cv extends Model
{
    @Required
    public String title;
    
    @OneToMany(mappedBy="cv", cascade=CascadeType.ALL)
    public List<Block> blocks;
    
    @OneToMany(mappedBy="cv", cascade=CascadeType.ALL)
    public List<File> files;
    
    @OneToOne
    public User user;
    
    @ManyToMany(cascade=CascadeType.PERSIST)
    public List<Category> categories;
    
    @ManyToMany(cascade=CascadeType.PERSIST)
    public List<Tag> tags;

    public Cv(String title) 
    {
        this.title = title;
    }
}
