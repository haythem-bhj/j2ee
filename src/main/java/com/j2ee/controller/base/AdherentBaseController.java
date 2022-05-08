package com.j2ee.controller.base;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.ArrayList;
import org.springframework.security.access.annotation.Secured;
import org.springframework.beans.factory.annotation.Autowired;
import com.j2ee.db.j2ee_db.service.AdherentService;
import com.j2ee.db.j2ee_db.entity.Adherent;

//IMPORT RELATIONS
import com.j2ee.db.j2ee_db.entity.Adherent;

public class AdherentBaseController {
    
    @Autowired
	AdherentService adherentService;



//CRUD METHODS


    //CRUD - CREATE
    @Secured({ "ROLE_PRIVATE_USER" })
		@RequestMapping(value = "/adherent", method = RequestMethod.POST, headers = "Accept=application/json")
	public Adherent insert(@RequestBody Adherent obj) {
		Adherent result = adherentService.insert(obj);

	    
		
		return result;
	}

	
    //CRUD - REMOVE
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/adherent/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void delete(@PathVariable("id") Long id) {
		adherentService.delete(id);
	}
	

    //CRUD - FIND BY Name
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/adherent/findByname/{key}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Adherent> findByname(@PathVariable("key") Long idname) {
		List<Adherent> list = adherentService.findByname(idname);
		return list;
	}
	
    //CRUD - GET ONE
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/adherent/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Adherent get(@PathVariable Long id) {
		Adherent obj = adherentService.get(id);
		
		
		
		return obj;
	}
	
	
    //CRUD - GET LIST
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/adherent", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Adherent> getList() {
		return adherentService.getList();
	}
	
	

    //CRUD - EDIT
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/adherent/{id}", method = RequestMethod.POST, headers = "Accept=application/json")
	public Adherent update(@RequestBody Adherent obj, @PathVariable("id") Long id) {
		Adherent result = adherentService.update(obj, id);

	    
		
		return result;
	}
	


/*
 * CUSTOM SERVICES
 * 
 *	These services will be overwritten and implemented in  Custom.js
 */


	
}
