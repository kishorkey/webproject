package com.demo.app.controller;

import java.lang.annotation.Annotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.app.Model.Admin;
import com.demo.app.Repository.webappRepository;
import com.demo.app.configuration.CustomCookie;
import com.demo.app.configuration.ResponsePayload;
import com.demo.app.configuration.ServiceException;


@RestController
@CrossOrigin
@RequestMapping(value= "webapp")
public class webappController {
	
	@Autowired
	webappRepository webapprepo;
	
	@GetMapping(value = "getAdmin")
	public ResponsePayload getAdmin(HttpServletRequest request,HttpServletResponse response) {
		
		try {
			CustomCookie.setCookieOption(request.getSession().getId(), response);
//			response.setHeader(CONT, null);
			return new ResponsePayload("Admin details recived successfully.",
					webapprepo.getAdminData(),HttpStatus.OK,true) ;
			} catch (ServiceException e) {
				System.out.println(e);
			}
		return null;
		}
	

	@PostMapping(value = "insertAdmin")
    public ResponsePayload insetAdmin(@RequestBody Admin admin, HttpServletRequest request,HttpServletResponse response) {
	
	try {
		CustomCookie.setCookieOption(request.getSession().getId(), response);
		webapprepo.insertAdmin(admin);
		return new ResponsePayload("Admin details added successfully.") ;
		} catch (ServiceException e) {
			System.out.println(e);
		}
	return null;
	}
	
	@DeleteMapping(value = "deleteAdmin")
    public ResponsePayload deleteAdmin(@RequestParam int id, HttpServletRequest request,HttpServletResponse response) {
	
	try {
		CustomCookie.setCookieOption(request.getSession().getId(), response);
		webapprepo.deleteAdmin(id);
		return new ResponsePayload("Admin id  :-" +id+ "  is deleted.") ;
		} catch (ServiceException e) {
			System.out.println(e);
		}
	return null;
	}
}
	
	

