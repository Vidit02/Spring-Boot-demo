package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.UserBean;

@Repository
public class UserDao {
	
	@Autowired
	JdbcTemplate stmt;
	
	public void addUser(UserBean user) {
		stmt.update("insert into users (firstname,email,password) values (?,?,?)" , user.getFirstname(),user.getEmail(),user.getPassword());
	}
	
	public List<UserBean> getAllUsers(){
		return stmt.query("select * from users", new BeanPropertyRowMapper<>(UserBean.class));
	}
	
	public UserBean authenticate(UserBean user) {
//		return stmt.queryForObject("select * from users where email = ? and password = ? ", new BeanPropertyRowMapper<>(UserBean.class), new Object[] {user.getEmail() , user.getPassword()});
		List<UserBean> users = stmt.query("select * from users where email = ? and password = ? ", new BeanPropertyRowMapper<>(UserBean.class),new Object[] {user.getEmail() , user.getPassword()});
		if(users.size() == 0) {
			return null;
		} else {
			return users.get(0);
		}
	}
	
	public UserBean addAuthtoken(UserBean user) {
		stmt.update("update users set authtoken = ? where email = ? and password = ?",user.getAuthtoken(),user.getEmail(), user.getPassword());
		return user;
	}
}
