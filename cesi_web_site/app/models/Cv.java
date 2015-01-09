package models;

import java.util.*;
import javax.persistence.*;
 
import play.db.jpa.*;
import play.data.validation.*;

@Entity
public class Cv extends Model
{
    public String professionalExperience;
    
    public String schooling;
    
    public String recreation;
    
    public String customContent;

    public Cv(String professionalExperience, String schooling, String recreation, String customContent) 
    {
        this.professionalExperience = professionalExperience;
        this.schooling = schooling;
        this.recreation = recreation;
        this.customContent = customContent;
    }
    
    
}
