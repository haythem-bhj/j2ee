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

import com.j2ee.db.j2ee_db.entity.Livre;
import com.j2ee.db.j2ee_db.service.LivreService;

//IMPORT RELATIONS
import com.j2ee.db.j2ee_db.entity.Livre;

@Service
public class LivreBaseService {

	private static NamedParameterJdbcTemplate jdbcTemplate;
	
		@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	


    //CRUD METHODS
    
    //CRUD - CREATE
    	
	public Livre insert(Livre obj) {
		Long id = jdbcTemplate.queryForObject("select max(_id) from `livre`", new MapSqlParameterSource(), Long.class);
		obj.set_id(id == null ? 1 : id + 1);
		String sql = "INSERT INTO `livre` (`_id`, `auteur`,`titre`) VALUES (:id,:auteur,:titre)";
			SqlParameterSource parameters = new MapSqlParameterSource()
		    .addValue("id", obj.get_id())
			.addValue("auteur", obj.getAuteur())
			.addValue("titre", obj.getTitre());
		
		jdbcTemplate.update(sql, parameters);
		return obj;
	}
	
    	
    //CRUD - REMOVE
    
	public void delete(Long id) {
		String sql = "DELETE FROM `livre` WHERE `_id`=:id";
		SqlParameterSource parameters = new MapSqlParameterSource()
			.addValue("id", id);
		
		jdbcTemplate.update(sql, parameters);
	}

    	
    //CRUD - REMOVE
    
	public void delete(Long id) {
		String sql = "DELETE FROM `livre` WHERE `_id`=:id";
		SqlParameterSource parameters = new MapSqlParameterSource()
			.addValue("id", id);
		
		jdbcTemplate.update(sql, parameters);
	}

    	
    //CRUD - GET ONE
    	
	public Livre get(Long id) {
	    
		String sql = "select * from `livre` where `_id` = :id";
		
	    SqlParameterSource parameters = new MapSqlParameterSource()
			.addValue("id", id);
	    
	    return (Livre) jdbcTemplate.queryForObject(sql, parameters, new BeanPropertyRowMapper(Livre.class));
	}


    	
        	
    //CRUD - GET LIST
    	
	public List<Livre> getList() {
	    
		String sql = "select * from `livre`";
		
	    SqlParameterSource parameters = new MapSqlParameterSource();
	    return jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(Livre.class));
	    
	    
	}

    	
    //CRUD - EDIT
    	
	public Livre update(Livre obj, Long id) {

		String sql = "UPDATE `livre` SET `auteur` = :auteur,`titre` = :titre  WHERE `_id`=:id";
		SqlParameterSource parameters = new MapSqlParameterSource()
			.addValue("id", id)
			.addValue("auteur", obj.getAuteur())
			.addValue("titre", obj.getTitre());
		jdbcTemplate.update(sql, parameters);
	    return obj;
	}
	
    	
    
    
    
    

    
    /*
     * CUSTOM SERVICES
     * 
     *	These services will be overwritten and implemented in livreService.java
     */
    

}
