package com.j2ee.db.j2ee_db.service.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import com.j2ee.db.j2ee_db.entity.Pret;
import com.j2ee.db.j2ee_db.service.PretService;

//IMPORT RELATIONS
import com.j2ee.db.j2ee_db.entity.Adherent;
import com.j2ee.db.j2ee_db.entity.Livre;

@Service
public class PretBaseService {

	private static NamedParameterJdbcTemplate jdbcTemplate;
	
		@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	


    //CRUD METHODS
    
    //CRUD - CREATE
    	
	public Pret insert(Pret obj) {
		Long id = jdbcTemplate.queryForObject("select max(_id) from `pret`", new MapSqlParameterSource(), Long.class);
		obj.set_id(id == null ? 1 : id + 1);
		String sql = "INSERT INTO `pret` (`_id`, `date`,`date_retour`,`id_adherent`,`id_livre`,`adherent_pret`,`livre_pret`) VALUES (:id,:date,:date_retour,:id_adherent,:id_livre, :adherent_pret , :livre_pret )";
			SqlParameterSource parameters = new MapSqlParameterSource()
		    .addValue("id", obj.get_id())
			.addValue("date", obj.getDate())
			.addValue("date_retour", obj.getDate_retour())
			.addValue("id_adherent", obj.getId_adherent())
			.addValue("id_livre", obj.getId_livre())
			.addValue("adherent_pret", obj.getAdherent_pret())
			.addValue("livre_pret", obj.getLivre_pret());
		
		jdbcTemplate.update(sql, parameters);
		return obj;
	}
	
    	
    //CRUD - REMOVE
    
	public void delete(Long id) {
		String sql = "DELETE FROM `pret` WHERE `_id`=:id";
		SqlParameterSource parameters = new MapSqlParameterSource()
			.addValue("id", id);
		
		jdbcTemplate.update(sql, parameters);
	}

    	
    //CRUD - FIND BY Adherent_pret
    	
	public List<Pret> findByadherent_pret(Long idadherent_pret) {
		
		String sql = "select * from `Pret` WHERE `adherent_pret` = :idadherent_pret";
		
	    SqlParameterSource parameters = new MapSqlParameterSource()
		.addValue("idadherent_pret", idadherent_pret);
	    
	    return jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(Pret.class));
	}
    	
    //CRUD - FIND BY Date
    	
	public List<Pret> findBydate(Long iddate) {
		
		String sql = "select * from `Pret` WHERE `date` = :iddate";
		
	    SqlParameterSource parameters = new MapSqlParameterSource()
		.addValue("iddate", iddate);
	    
	    return jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(Pret.class));
	}
    	
    //CRUD - FIND BY Livre_pret
    	
	public List<Pret> findBylivre_pret(Long idlivre_pret) {
		
		String sql = "select * from `Pret` WHERE `livre_pret` = :idlivre_pret";
		
	    SqlParameterSource parameters = new MapSqlParameterSource()
		.addValue("idlivre_pret", idlivre_pret);
	    
	    return jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(Pret.class));
	}
    	
    //CRUD - GET ONE
    	
	public Pret get(Long id) {
	    
		String sql = "select * from `pret` where `_id` = :id";
		
	    SqlParameterSource parameters = new MapSqlParameterSource()
			.addValue("id", id);
	    
	    return (Pret) jdbcTemplate.queryForObject(sql, parameters, new BeanPropertyRowMapper(Pret.class));
	}


    	
        	
    //CRUD - GET LIST
    	
	public List<Pret> getList() {
	    
		String sql = "select * from `pret`";
		
	    SqlParameterSource parameters = new MapSqlParameterSource();
	    return jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(Pret.class));
	    
	    
	}

    	
    //CRUD - EDIT
    	
	public Pret update(Pret obj, Long id) {

		String sql = "UPDATE `pret` SET `date` = :date,`date_retour` = :date_retour,`id_adherent` = :id_adherent,`id_livre` = :id_livre , `adherent_pret` = :adherent_pret , `livre_pret` = :livre_pret  WHERE `_id`=:id";
		SqlParameterSource parameters = new MapSqlParameterSource()
			.addValue("id", id)
			.addValue("date", obj.getDate())
			.addValue("date_retour", obj.getDate_retour())
			.addValue("id_adherent", obj.getId_adherent())
			.addValue("id_livre", obj.getId_livre())
			.addValue("adherent_pret", obj.getAdherent_pret())
			.addValue("livre_pret", obj.getLivre_pret());
		jdbcTemplate.update(sql, parameters);
	    return obj;
	}
	
    	
    
    
    
        
    

    
    /*
     * CUSTOM SERVICES
     * 
     *	These services will be overwritten and implemented in pretService.java
     */
    

}
