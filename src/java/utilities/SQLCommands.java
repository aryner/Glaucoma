/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utilities;

import java.sql.*;
import java.util.*; 
import javax.naming.Context; 
import javax.sql.DataSource;
import javax.naming.InitialContext; 
import java.util.logging.Logger;
import java.util.logging.Level;
import model.*;
/**
 *
 * @author aryner
 */
public class SQLCommands {
	private static final String slash = System.getProperty("file.separator");
	public static int getCount(String query){ 
		int count = 0;
		Connection con = null;
		Statement stmt = null;
		ResultSet resultSet = null;

		try{
			InitialContext initialContext = new InitialContext();
			Context context = (Context)initialContext.lookup("java:comp"+slash+"env");
			DataSource dataSource = (DataSource)context.lookup("glaucoma_grader");
			con = dataSource.getConnection();

			stmt = con.createStatement();

			resultSet = stmt.executeQuery(query); 

			while(resultSet.next()) {
				count++;
			}
		} catch (SQLException ex) {
			Logger.getLogger(SQLCommands.class.getName()).log(Level.SEVERE,null,ex); 
		} catch (Exception e) { e.printStackTrace(); } 
		finally { 
			if(resultSet != null) try {resultSet.close();} catch(SQLException ignore) {}
			if(con != null) try {con.close();} catch(SQLException ignore) {}
			if(stmt != null) try {stmt.close();} catch(SQLException ignore) {}
		}

		return count;
	}

	public static Vector<HVFtest> queryHVFtest(String query){
		Vector<HVFtest> result = new Vector<HVFtest>();
		Connection con = null;
		Statement stmt = null;
		ResultSet resultSet = null;

		try{
			InitialContext initialContext = new InitialContext();
			Context context = (Context)initialContext.lookup("java:comp"+slash+"env");
			DataSource dataSource = (DataSource)context.lookup("glaucoma_grader");
			con = dataSource.getConnection();

			stmt = con.createStatement();

			resultSet = stmt.executeQuery(query); 

			while(resultSet.next()) {
				result.add(new HVFtest(resultSet.getInt("pictureID")));
			}
		} catch (SQLException ex) {
			Logger.getLogger(SQLCommands.class.getName()).log(Level.SEVERE,null,ex); 
		} catch (Exception e) { e.printStackTrace(); } 
		finally { 
			if(resultSet != null) try {resultSet.close();} catch(SQLException ignore) {}
			if(con != null) try {con.close();} catch(SQLException ignore) {}
			if(stmt != null) try {stmt.close();} catch(SQLException ignore) {}
		}

		return result;
	}

	public static Vector<User> queryUsers(String query){
		Vector<User> result = new Vector<User>();
		Connection con = null;
		Statement stmt = null;
		ResultSet resultSet = null;

		try{
			InitialContext initialContext = new InitialContext();
			Context context = (Context)initialContext.lookup("java:comp"+slash+"env");
			DataSource dataSource = (DataSource)context.lookup("glaucoma_grader");
			con = dataSource.getConnection();

			stmt = con.createStatement();

			resultSet = stmt.executeQuery(query); 

			while(resultSet.next()) {
				result.add(new User(resultSet.getInt("id"),resultSet.getString("userName"), resultSet.getInt("access")));
			}
		} catch (SQLException ex) {
			Logger.getLogger(SQLCommands.class.getName()).log(Level.SEVERE,null,ex); 
		} catch (Exception e) { e.printStackTrace(); } 
		finally { 
			if(resultSet != null) try {resultSet.close();} catch(SQLException ignore) {}
			if(con != null) try {con.close();} catch(SQLException ignore) {}
			if(stmt != null) try {stmt.close();} catch(SQLException ignore) {}
		}

		return result;
	}

	public static Vector<Integer> queryNewGrade(String query){
		Vector<Integer> result = new Vector<Integer>();
		Connection con = null;
		Statement stmt = null;
		ResultSet resultSet = null;

		try{
			InitialContext initialContext = new InitialContext();
			Context context = (Context)initialContext.lookup("java:comp"+slash+"env");
			DataSource dataSource = (DataSource)context.lookup("glaucoma_grader");
			con = dataSource.getConnection();

			stmt = con.createStatement();

			resultSet = stmt.executeQuery(query); 

			while(resultSet.next()) {
				result.add(resultSet.getInt("pictureID"));
				result.add(resultSet.getInt("userID"));
				result.add(resultSet.getInt("id"));
			}
		} catch (SQLException ex) {
			Logger.getLogger(SQLCommands.class.getName()).log(Level.SEVERE,null,ex); 
		} catch (Exception e) { e.printStackTrace(); } 
		finally { 
			if(resultSet != null) try {resultSet.close();} catch(SQLException ignore) {}
			if(con != null) try {con.close();} catch(SQLException ignore) {}
			if(stmt != null) try {stmt.close();} catch(SQLException ignore) {}
		}

		return result;
	}

	public static Vector<Picture> queryPictures(String query){
		Vector<Picture> result = new Vector<Picture>();
		Connection con = null;
		Statement stmt = null;
		ResultSet resultSet = null;

		try{
			InitialContext initialContext = new InitialContext();
			Context context = (Context)initialContext.lookup("java:comp"+slash+"env");
			DataSource dataSource = (DataSource)context.lookup("glaucoma_grader");
			con = dataSource.getConnection();

			stmt = con.createStatement();

			resultSet = stmt.executeQuery(query); 

			while(resultSet.next()) {
				result.add(new Picture(resultSet.getInt("id"),resultSet.getString("name"), resultSet.getString("type")));
			}
		} catch (SQLException ex) {
			Logger.getLogger(SQLCommands.class.getName()).log(Level.SEVERE,null,ex); 
		} catch (Exception e) { e.printStackTrace(); } 
		finally { 
			if(resultSet != null) try {resultSet.close();} catch(SQLException ignore) {}
			if(con != null) try {con.close();} catch(SQLException ignore) {}
			if(stmt != null) try {stmt.close();} catch(SQLException ignore) {}
		}

		return result;
	}
	
	public static int update(String query){ 
		Connection con = null;
		Statement stmt = null;
		int result = 0;
		try{
			InitialContext initialContext = new InitialContext();
			Context context = (Context)initialContext.lookup("java:comp"+slash+"env");
			DataSource dataSource = (DataSource)context.lookup("glaucoma_grader");
			con = dataSource.getConnection();

			stmt = con.createStatement();
			result = stmt.executeUpdate(query); 
		} catch (SQLException ex) {
			Logger.getLogger(SQLCommands.class.getName()).log(Level.SEVERE,null,ex); 
		} catch (Exception e) { } 
		finally {
			if(con != null) try {con.close();} catch(SQLException ignore) {}
			if(stmt != null) try {stmt.close();} catch(SQLException ignore) {}
		}

		return result; 
	} 
}
