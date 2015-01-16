package models;
 
import java.util.*;
import javax.persistence.*;
 
import play.data.validation.*;
import play.db.jpa.*;
import play.libs.Codec;
 
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
    
    @ManyToOne
    public Site site;
    
    @OneToOne
    public Cv cv;
    
    public String favoriteLanguage;
    
    public String needConfirmation;
	
    public User(String email, String password, String firstName, String lastName, Role role) 
    {
        this.email = email;
        this.password = Codec.hexMD5(password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.needConfirmation = Codec.UUID();
    }
    
    /*
    public User(String email, String password, String firstName, String lastName, Role role, Promotion promotion) 
    {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.promotion = promotion;
    }
    
    public User(String email, String password, String firstName, String lastName, Role role, Promotion promotion, Site site) 
    {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.promotion = promotion;
        this.site = site;
    }
    */
    
    public static User connect(String email, String password) 
    {
        return find("byEmailAndPassword", email, Codec.hexMD5(password)).first();
    }
	
    public static boolean checkRole(String email, String rolename)
    {
        return User.find
        (
            "select u from User u where u.email = ? and u.role in " +
            "(select r from Role r where id <= (select r2.id from Role r2 where name = ?))", email, rolename
        ).fetch().size() > 0;
    }
    
    
    public boolean checkPassword(String password) 
    {
        return this.password.equals(Codec.hexMD5(password));
    }
    
    public static User findByEmail(String email) 
    {
        return find("email", email).first();
    }

    public static User findByRegistrationUUID(String uuid) 
    {
        return find("needConfirmation", uuid).first();
    }

    public static boolean isEmailAvailable(String email) 
    {
        return findByEmail(email) == null;
    }
    
    @Override
    public String toString() 
    {
        return email;
    }
 
	
}