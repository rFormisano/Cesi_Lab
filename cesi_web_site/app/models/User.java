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
    
    @Required
    public String firstName;
    
    @Required
    public String lastName;
    
    public Date dateOfBirth;
	
    @Required
    @ManyToOne
    public Role role;
    
    @ManyToOne
    public Promotion promotion;
	
    public User(String email, String password, String firstName, String lastName, Role role) 
    {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }
    
    public User(String email, String password, String firstName, String lastName, Role role, Promotion promotion) 
    {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.promotion = promotion;
    }
    
    public static User connect(String email, String password) 
    {
        return find("byEmailAndPassword", email, password).first();
    }
	
    public static boolean checkRole(String email, String rolename)
    {
        return User.find
        (
            "select u from User u where u.email = ? and u.role in " +
            "(select r from Role r where id <= (select r2.id from Role r2 where name = ?))", email, rolename
        ).fetch().size() > 0;
    }
    
    @Override
    public String toString() 
    {
        return email;
    }
 
	
}