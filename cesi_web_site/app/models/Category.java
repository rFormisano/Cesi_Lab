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
public class Category extends Model
{
    
    @Required
    public String name;
    
    @OneToMany(mappedBy="category", cascade=CascadeType.ALL)
    public List<Tag> tags;
    
    private Category(String name) 
    {
        this.name = name;
    }
    
    public static Category findOrCreateByName(String name) 
    {
        Category category = Category.find("byName", name).first();
        if(category == null) 
        {
            category = new Category(name);
        }
        return category;
    }
    
    @Override
    public String toString() 
    {
        return name;
    }
}
