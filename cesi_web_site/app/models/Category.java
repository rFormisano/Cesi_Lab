package models;

import javax.persistence.Entity;
import play.data.validation.Required;
import play.db.jpa.Model;

/**
 *
 * @author Jonathan Fernandez
 */
@Entity
public class Category extends Model{
    
    @Required
    public String name;
    
    private Category(String name) {
        this.name = name;
    }
    
    public static Category findOrCreateByName(String name) {
        Category category = Category.find("byName", name).first();
        if(category == null) {
            category = new Category(name);
        }
        return category;
    }
}
