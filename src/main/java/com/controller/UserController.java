package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.UserBean;
import com.dao.UserDao;
import com.service.GenerateAuthtoken;

@RestController
@CrossOrigin
//@RequestMapping("/public")
public class UserController {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	GenerateAuthtoken authtoken;
	
	@PostMapping("/user")
	public UserBean addUser(@RequestBody UserBean user) {
		userDao.addUser(user);
		return user;
	}
	
	@GetMapping("/user")
	public List<UserBean> getAllUsers(){
		return userDao.getAllUsers();
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody UserBean user) {
//		return userDao.authenticate(user);
//		if(userDao.authenticate(user) == null) {
//			return null;
//		} else {
//			return userDao.authenticate(user);
//		}
		UserBean newuser = userDao.authenticate(user);
		if (newuser == null) {
			ResponseEntity<?> resp = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			return resp;
		} else {
			if(newuser.getAuthtoken() == null) {
				System.out.println("Authtoken not generated");
				newuser.setAuthtoken(authtoken.generateToken(16));
				newuser = userDao.addAuthtoken(newuser);
				System.out.println("auth token generated " + newuser.getAuthtoken());
			}
			return ResponseEntity.ok(newuser);
		}
	}

}
