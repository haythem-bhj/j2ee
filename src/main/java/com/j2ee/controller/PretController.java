package com.j2ee.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.context.annotation.PropertySource;

import com.j2ee.controller.base.PretBaseController;

@RestController
@PropertySource("classpath:${configfile.path}/j2ee.properties")
@RequestMapping(value="${endpoint.api}", headers = "Accept=application/json")
public class PretController extends PretBaseController {

	//OVERRIDE HERE YOUR CUSTOM CONTROLLER

}
