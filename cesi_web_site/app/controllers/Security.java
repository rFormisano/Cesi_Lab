package controllers;

import models.*;

public class Security extends Secure.Security 
{

    static boolean authentify(String email, String password) 
	{
        return User.connect(email, password) != null;
    }
    
    static boolean check(String profile) 
	{
        if(User.checkRole(connected(), profile)) 
		{
            return true;
        }
		else
		{
			return false;
		}
    }
    
    static void onDisconnected() 
	{
        Application.index();
    }
    
    static void onAuthenticated() 
	{
        Admin.index();
    }
    
}

