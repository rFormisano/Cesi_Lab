package models;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import play.data.validation.Required;
import play.db.jpa.Model;

/**
 *
 * @author Jonathan Fernandez
 */
@Entity
public class Site extends Model
{
    
    @Required
    public String city;
    
    @OneToMany(mappedBy="site", cascade=CascadeType.ALL)
    public List<User> users;
    
    private Site(String city) 
    {
        this.city = city;
    }
    
    public static Site findOrCreateByName(String city) 
    {
        Site site = Site.find("byName", city).first();
        if(site == null) 
        {
            site = new Site(city);
        }
        return site;
    }
    
    @Override
    public String toString() 
    {
        return city;
    }
}
