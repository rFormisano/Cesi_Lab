package models;

import javax.persistence.*;
 
import play.db.jpa.*;

/**
 *
 * @author joann
 */
@Entity
public class ServerFile extends Model
{
    public String originalName;
    
    public String serverName;
    
    public String path;
    
    @ManyToOne
    public Post post;
    
    @ManyToOne
    public Cv cv;

    public ServerFile(String originalName, String serverName, String path) 
    {
        this.originalName = originalName;
        this.serverName = serverName;
        this.path = path;
    }
    
    @Override
    public String toString()
    {
        return this.originalName;
    }
}
