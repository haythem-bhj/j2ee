package com.j2ee.controller;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;
import com.j2ee.db.j2ee_db.entity.User;
import com.j2ee.db.j2ee_db.service.UserService;


@RestController
@PropertySource("classpath:${configfile.path}/j2ee.properties")
@RequestMapping(value="${endpoint.api}", headers = "Accept=application/json")
public class SecurityController {
	
		@Autowired
	private UserService usersService ;
	
    /**
     * Login API
     * @param params Contains username and password
     * @return The user with generated JWT token or 401 error if credentials not valid
     */
	@RequestMapping(value = "login", method = RequestMethod.POST, headers = "Accept=application/json")
	public User login(HttpServletRequest request, HttpServletResponse response, @RequestBody User params) {
		// GET USER
		String username = params.getUsername();
		String password = params.getPassword();
		

        User user = usersService.login(username, password); 
        // GENERATE JWT TOKEN
        if (user != null) {
        	Gson gson = new Gson();
        	Algorithm algorithm;
			try {
				algorithm = Algorithm.HMAC256("secret");
	        	String token = JWT.create()
	        	        .withClaim("user", gson.toJson(user))
	        	        .sign(algorithm);
	        	
	        	// Set token in user
	        	user.setToken(token);
				user.setPassword(null);
	        	
			} catch (IllegalArgumentException | UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			return user;
        } else {
        	// return 401 error
        	response.setStatus(401);
        	return null;
        }
        
	}
	
    /**
     * Verify JWT token API
     * @param params Contains token
     * @return The user decoded or 401 error if token not valid
     */
	@RequestMapping(value = "verifyToken", method = RequestMethod.POST, headers = "Accept=application/json")
	public User verifyToken(HttpServletRequest request, HttpServletResponse response, @RequestBody User params) {
		
		String token = params.getToken();
		if(token == null) {
        	response.setStatus(401);
			return null;
		}
        
		token = token.replace("Bearer ", "");
		
		
		// Get user from token decode
		try {
			//decode token
			Algorithm algorithm = Algorithm.HMAC256("secret");
			JWTVerifier verifier = JWT.require(algorithm).build();
			DecodedJWT jwt = verifier.verify(token);
			String userJSON = jwt.getClaims().get("user").asString();

			// Set user in Authentication Service
        	Gson gson = new Gson();
			User user = gson.fromJson(userJSON, User.class);
			user.setPassword(null);
			return user;
			
		} catch (IllegalArgumentException | UnsupportedEncodingException e) {
        	response.setStatus(401);
			e.printStackTrace();
		}
		
		return null;
	}
	
    /**
     * Change password for current user
     * @param request
     * @param response
     * @param params
     * @param id
     */
    @Secured({"ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST, headers = "Accept=application/json")
	public boolean changePassword(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String, String> params) {
		
    	//AuthenticationService auth = (AuthenticationService) SecurityContextHolder.getContext().getAuthentication();

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		String password = params.get("passwordOld");
		String passwordNew= params.get("passwordNew");

		User user = usersService.login(username, password);
		if (user == null) {
			response.setStatus(500);
			return false;
		}
		
		return usersService.updatePassword(user.get_id(), passwordNew);
	}
}
