package models;

import javax.persistence.Entity;
import play.data.validation.Required;
import play.db.jpa.Model;

/**
 *
 * @author Jonathan Fernandez
 */
@Entity
public class Site extends Model{
    
    @Required
    public String city;
    
    private Site(String city) {
        this.city = city;
    }
    
    public static Site findOrCreateByName(String city) {
        Site site = Site.find("byName", city).first();
        if(site == null) {
            site = new Site(city);
        }
        return site;
    }
}
