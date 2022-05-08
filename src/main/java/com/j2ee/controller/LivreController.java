package com.j2ee.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.context.annotation.PropertySource;

import com.j2ee.controller.base.LivreBaseController;

@RestController
@PropertySource("classpath:${configfile.path}/j2ee.properties")
@RequestMapping(value="${endpoint.api}", headers = "Accept=application/json")
public class LivreController extends LivreBaseController {

	//OVERRIDE HERE YOUR CUSTOM CONTROLLER

}
