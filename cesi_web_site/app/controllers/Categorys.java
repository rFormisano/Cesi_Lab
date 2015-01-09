package controllers;

import play.mvc.With;

/**
 *
 * @author joann
 */
@Check("admin")
@With(Secure.class)
public class Categorys extends CRUD {}
