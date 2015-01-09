package controllers;

import play.mvc.*;

@Check("admin")
@With(Secure.class)
public class Promotions extends CRUD {}
