/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import utilities.SQLCommands;
import java.util.*;

/**
 *
 * @author aryner
 */
public class User {
	private int id;
	private String userName;
	private int access;

	public User(int nid, String nname, int naccess) {
		id = nid;
		userName =nname;
		access = naccess;
	}

	public User(String userID) { 
		String query = "SELECT * FROM user WHERE id='" + userID + "'";
		Vector<User> users = SQLCommands.queryUsers(query);

		if (users.size() > 0){
			id = users.get(0).getID();
			userName = users.get(0).getUserName();
		} 
	}
	
	public User(String name, String pass) { 
		String query = "SELECT * FROM user WHERE userName='" + name +
				"' AND password='" + pass + "'";
		Vector<User> users = SQLCommands.queryUsers(query);

		if(users.size() > 0) {
			id = users.get(0).getID();
			userName = users.get(0).getUserName();
			access = users.get(0).getAccess();
		}
		else {
			userName = null;
			id = -1;
			access = -1;
		} 
	}

	public static User createUser(String name, String pass, String access) { 
		String query = "SELECT * FROM user WHERE userName='" + name + "'";
		Vector<User> users = SQLCommands.queryUsers(query);
		User result = null;

		if(users.size() > 0){ 
			return result;
		}
		else { 
			query = "INSERT INTO user (userName, password, access) VALUES('" + name +
				"', '" + pass + "', '"+access+"')";

			SQLCommands.update(query); 
		} 

		return new User(name, pass);
	}

	public int getID() {
		return id;
	}
	public String getUserName()  {
		return userName;
	}
	public int getAccess()  {
		return access;
	}
}
