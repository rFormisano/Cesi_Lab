package models;
 
import java.util.*;
import javax.persistence.*;
 
import play.db.jpa.*;
import play.data.validation.*;
 
@Entity
public class Role extends Model
{
    @Required
    public String name;
    
    @OneToMany(mappedBy="role", cascade=CascadeType.ALL)
    public List<User> users;
    
    @ManyToMany
    public List<Permission> permissions;
    
    private Role(String name) 
    {
        this.name = name;
    }
    
    public static Role findOrCreateByName(String name) 
    {
        Role role = Role.find("byName", name).first();
        if(role == null) 
        {
            role = new Role(name);
            role.save();
        }
        return role;
    }
    
    @Override
    public String toString() 
    {
        return name;
    }
}