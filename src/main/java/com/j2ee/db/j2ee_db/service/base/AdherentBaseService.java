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

import com.j2ee.db.j2ee_db.entity.Adherent;
import com.j2ee.db.j2ee_db.service.AdherentService;

//IMPORT RELATIONS
import com.j2ee.db.j2ee_db.entity.Adherent;

@Service
public class AdherentBaseService {

	private static NamedParameterJdbcTemplate jdbcTemplate;
	
		@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	


    //CRUD METHODS
    
    //CRUD - CREATE
    	
	public Adherent insert(Adherent obj) {
		Long id = jdbcTemplate.queryForObject("select max(_id) from `adherent`", new MapSqlParameterSource(), Long.class);
		obj.set_id(id == null ? 1 : id + 1);
		String sql = "INSERT INTO `adherent` (`_id`, `email`,`name`,`phone`) VALUES (:id,:email,:name,:phone)";
			SqlParameterSource parameters = new MapSqlParameterSource()
		    .addValue("id", obj.get_id())
			.addValue("email", obj.getEmail())
			.addValue("name", obj.getName())
			.addValue("phone", obj.getPhone());
		
		jdbcTemplate.update(sql, parameters);
		return obj;
	}
	
    	
    //CRUD - REMOVE
    
	public void delete(Long id) {
		String sql = "DELETE FROM `adherent` WHERE `_id`=:id";
		SqlParameterSource parameters = new MapSqlParameterSource()
			.addValue("id", id);
		
		jdbcTemplate.update(sql, parameters);
	}

    	
    //CRUD - FIND BY Name
    	
	public List<Adherent> findByname(Long idname) {
		
		String sql = "select * from `Adherent` WHERE `name` = :idname";
		
	    SqlParameterSource parameters = new MapSqlParameterSource()
		.addValue("idname", idname);
	    
	    return jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(Adherent.class));
	}
    	
    //CRUD - GET ONE
    	
	public Adherent get(Long id) {
	    
		String sql = "select * from `adherent` where `_id` = :id";
		
	    SqlParameterSource parameters = new MapSqlParameterSource()
			.addValue("id", id);
	    
	    return (Adherent) jdbcTemplate.queryForObject(sql, parameters, new BeanPropertyRowMapper(Adherent.class));
	}


    	
        	
    //CRUD - GET LIST
    	
	public List<Adherent> getList() {
	    
		String sql = "select * from `adherent`";
		
	    SqlParameterSource parameters = new MapSqlParameterSource();
	    return jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(Adherent.class));
	    
	    
	}

    	
    //CRUD - EDIT
    	
	public Adherent update(Adherent obj, Long id) {

		String sql = "UPDATE `adherent` SET `email` = :email,`name` = :name,`phone` = :phone  WHERE `_id`=:id";
		SqlParameterSource parameters = new MapSqlParameterSource()
			.addValue("id", id)
			.addValue("email", obj.getEmail())
			.addValue("name", obj.getName())
			.addValue("phone", obj.getPhone());
		jdbcTemplate.update(sql, parameters);
	    return obj;
	}
	
    	
    
    
    
    

    
    /*
     * CUSTOM SERVICES
     * 
     *	These services will be overwritten and implemented in adherentService.java
     */
    

}
