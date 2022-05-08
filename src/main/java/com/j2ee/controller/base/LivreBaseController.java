package com.j2ee.controller.base;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.ArrayList;
import org.springframework.security.access.annotation.Secured;
import org.springframework.beans.factory.annotation.Autowired;
import com.j2ee.db.j2ee_db.service.LivreService;
import com.j2ee.db.j2ee_db.entity.Livre;

//IMPORT RELATIONS
import com.j2ee.db.j2ee_db.entity.Livre;

public class LivreBaseController {
    
    @Autowired
	LivreService livreService;



//CRUD METHODS


    //CRUD - CREATE
    @Secured({ "ROLE_PRIVATE_USER" })
		@RequestMapping(value = "/livre", method = RequestMethod.POST, headers = "Accept=application/json")
	public Livre insert(@RequestBody Livre obj) {
		Livre result = livreService.insert(obj);

	    
		
		return result;
	}

	
    //CRUD - REMOVE
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/livre/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void delete(@PathVariable("id") Long id) {
		livreService.delete(id);
	}
	
	
    //CRUD - REMOVE
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/livre/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void delete(@PathVariable("id") Long id) {
		livreService.delete(id);
	}
	
	
    //CRUD - GET ONE
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/livre/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Livre get(@PathVariable Long id) {
		Livre obj = livreService.get(id);
		
		
		
		return obj;
	}
	
	
    //CRUD - GET LIST
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/livre", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Livre> getList() {
		return livreService.getList();
	}
	
	

    //CRUD - EDIT
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/livre/{id}", method = RequestMethod.POST, headers = "Accept=application/json")
	public Livre update(@RequestBody Livre obj, @PathVariable("id") Long id) {
		Livre result = livreService.update(obj, id);

	    
		
		return result;
	}
	


/*
 * CUSTOM SERVICES
 * 
 *	These services will be overwritten and implemented in  Custom.js
 */


	
}
