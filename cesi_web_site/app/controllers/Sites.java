package controllers;

import play.mvc.*;

/**
 *
 * @author joann
 */
@Check("admin")
@With(Secure.class)
public class Sites extends CRUD {}

