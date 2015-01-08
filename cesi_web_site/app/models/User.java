package models;
 
import java.util.*;
import javax.persistence.*;
 
import play.db.jpa.*;
import play.data.validation.*;
 
@Entity
public class User extends Model 
{
    @Email
    @Required
    public String email;
    
    @Required
    public String password;
    
    public String fullname;
	
	@Required
	@ManyToOne
	public Role role;
	
    public User(String email, String password, String fullname, Role role) 
	{
        this.email = email;
        this.password = password;
        this.fullname = fullname;
		this.role = role;
    }
    
    public static User connect(String email, String password) 
	{
        return find("byEmailAndPassword", email, password).first();
    }
	
	public static boolean checkRole(String email, String rolename)
	{
		return User.find(
            "select u from User u where u.email = ? and u.role in (select r from Role r where id <= (select r2.id from Role r2 where name = ?))", email, rolename
        ).fetch().size() > 0;
	}
    
    public String toString() 
	{
        return email;
    }
 
	
}