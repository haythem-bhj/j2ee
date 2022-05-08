package com.j2ee.controller.base;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.ArrayList;
import org.springframework.security.access.annotation.Secured;
import org.springframework.beans.factory.annotation.Autowired;
import com.j2ee.db.j2ee_db.service.PretService;
import com.j2ee.db.j2ee_db.entity.Pret;

//IMPORT RELATIONS
import com.j2ee.db.j2ee_db.entity.Adherent;
import com.j2ee.db.j2ee_db.entity.Livre;

public class PretBaseController {
    
    @Autowired
	PretService pretService;



//CRUD METHODS


    //CRUD - CREATE
    @Secured({ "ROLE_PRIVATE_USER" })
		@RequestMapping(value = "/pret", method = RequestMethod.POST, headers = "Accept=application/json")
	public Pret insert(@RequestBody Pret obj) {
		Pret result = pretService.insert(obj);

	    
		
		return result;
	}

	
    //CRUD - REMOVE
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/pret/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void delete(@PathVariable("id") Long id) {
		pretService.delete(id);
	}
	

    //CRUD - FIND BY Adherent_pret
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/pret/findByadherent_pret/{key}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Pret> findByadherent_pret(@PathVariable("key") Long idadherent_pret) {
		List<Pret> list = pretService.findByadherent_pret(idadherent_pret);
		return list;
	}

    //CRUD - FIND BY Date
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/pret/findBydate/{key}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Pret> findBydate(@PathVariable("key") Long iddate) {
		List<Pret> list = pretService.findBydate(iddate);
		return list;
	}

    //CRUD - FIND BY Livre_pret
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/pret/findBylivre_pret/{key}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Pret> findBylivre_pret(@PathVariable("key") Long idlivre_pret) {
		List<Pret> list = pretService.findBylivre_pret(idlivre_pret);
		return list;
	}
	
    //CRUD - GET ONE
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/pret/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Pret get(@PathVariable Long id) {
		Pret obj = pretService.get(id);
		
		
		
		return obj;
	}
	
	
    //CRUD - GET LIST
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/pret", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Pret> getList() {
		return pretService.getList();
	}
	
	

    //CRUD - EDIT
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/pret/{id}", method = RequestMethod.POST, headers = "Accept=application/json")
	public Pret update(@RequestBody Pret obj, @PathVariable("id") Long id) {
		Pret result = pretService.update(obj, id);

	    
		
		return result;
	}
	


/*
 * CUSTOM SERVICES
 * 
 *	These services will be overwritten and implemented in  Custom.js
 */


	
}
