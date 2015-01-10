package controllers;
 

import play.mvc.*;

import java.util.*;
import java.io.File;
import java.text.SimpleDateFormat;
 
import models.*;
 
@With(Secure.class)
public class Admin extends Controller 
{
    
    @Before
    static void setConnectedUser() 
    {
        if(Security.isConnected()) 
        {
            User user = User.find("byEmail", Security.connected()).first();
            renderArgs.put("user", user.firstName);
        }
    }
 
    public static void index() 
    {
        List<Post> posts = Post.find("author.email", Security.connected()).fetch();
        render(posts);
    }
    
    public static void form(Long id) 
    {
        if(id != null) 
        {
            Post post = Post.findById(id);
            render(post);
        }
        render();
    }
    
    public static void save(Long id, String title, String content, String tags, File fichier) 
    {
        Post post;
		
        if(id == null) 
        {
            // Create post
            User author = User.find("byEmail", Security.connected()).first();
            post = new Post(author, title, content);
        } 
        else 
        {
            // Retrieve post
            post = Post.findById(id);
            post.title = title;
            post.content = content;
            post.tags.clear();
        }
        
        // Set tags list
        for(String tag : tags.split("\\s+")) 
        {
            if(tag.trim().length() > 0) 
            {
                post.tags.add(Tag.findOrCreateByName(tag));
            }
        }
        
        if (fichier != null)
        {
            // TODO JJ : je ne retrouve pas le fichier physiquement sur ma machine
            //           mais les infos en base sont bien enregistrés
            String fileName = fichier.getName();
            String date = new SimpleDateFormat("yyyy-MM-dd-hhmmss").format(new Date());
            String serverFileName = date + fileName;
            ServerFile file = new ServerFile(fileName, serverFileName, "./tmp/");
            post.files.add(file);
            file.post = post;
            fichier.renameTo(new File(file.path + file.serverName));
            file.save();
        }
        
        // Validate
        validation.valid(post);
        if(validation.hasErrors()) 
        {
            render("@form", post);
        }
        
        // Save
        post.save();
        index();
    }
    
}
