package models;

import javax.persistence.*;
 
import play.db.jpa.*;
import play.data.validation.*;

/**
 *
 * @author joann
 */
@Entity
public class Block extends Model
{
    @Required
    public String name;
    
    @Required
    public String content;
    
    @ManyToOne
    public Cv cv;

    public Block(String name, String content) 
    {
        this.name = name;
        this.content = content;
    }
}
