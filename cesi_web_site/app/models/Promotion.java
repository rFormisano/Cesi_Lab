package models;

import java.text.SimpleDateFormat;
import java.util.*;
import javax.persistence.*;
import play.data.binding.As;
import play.data.validation.*;
import play.db.jpa.*;

@Entity
public class Promotion extends Model
{
    @Required
    public String name;
    
    @Required
    @As("yyyy")
    public Date date;

    private Promotion(String name, Date date) 
    {
        this.name = name;
        this.date = date;
    }
    
    public static Promotion findOrCreateByName(String name, Date date) 
    {
        Promotion promotion = Promotion.find("byNameAndDate", name, date).first();
        if(promotion == null) 
        {
            promotion = new Promotion(name, date);
            promotion.save();
        }
        return promotion;
    }
    
    public String toString() 
    {
        return name + " " + new SimpleDateFormat("yy").format(date);
    }
}
