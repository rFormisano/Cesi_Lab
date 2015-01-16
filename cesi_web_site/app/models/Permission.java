package models;

/**
 *
 * @author Jonathan Fernandez
 */
import java.util.*;
import javax.persistence.*;
 
import play.db.jpa.*;
import play.data.validation.*;

@Entity
public class Permission extends Model{
    
    @Required
    public String name;
    
    @ManyToMany(mappedBy="permissions", cascade=CascadeType.ALL)
    public List<Role> roles;
    
    private Permission(String name) 
    {
        this.name = name;
    }
    
    private Permission(String name, List<Role> roles) 
    {
        this.name = name;
        this.roles = roles;
    }
    
    public static Permission findOrCreateByName(String name) 
    {
        Permission permission = Permission.find("byName", name).first();
        if(permission == null) 
        {
            permission = new Permission(name);
            permission.save();
        }
        return permission;
    }
    
}
