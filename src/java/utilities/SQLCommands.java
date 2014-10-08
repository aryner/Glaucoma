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
	public static Vector<HVFtest> queryHVFtestMaster(String query){
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
				result.add(new HVFtest(
					resultSet.getInt("id"),resultSet.getInt("confirmed"),resultSet.getInt("opthCheck"),
					resultSet.getInt("pictureID"),resultSet.getInt("userID"),resultSet.getInt("hvf_vf_loss"),
					resultSet.getInt("hvf_vf_defect"),resultSet.getInt("hvf_glau"),resultSet.getString("hvf_vf_loss_oth"),
					resultSet.getString("hvf_vf_defect_oth"),resultSet.getInt("hvf_mon"),resultSet.getString("hvf_mon_oth2_c47"),
					resultSet.getInt("hvf_tar"),resultSet.getString("hvf_tar_oth"),resultSet.getInt("hvf_lossnum"),
					resultSet.getInt("hvf_lossden"),resultSet.getInt("hvf_fp"),resultSet.getInt("hvf_fn"),
					resultSet.getString("hvf_dur"),resultSet.getInt("hvf_fov"),resultSet.getInt("hvf_stimintens"),
					resultSet.getInt("hvf_stimcol"),resultSet.getString("hvf_stimcol_oth"),resultSet.getString("hvf_back"),
					resultSet.getInt("hvf_strategy"),resultSet.getString("hvf_strategy_oth"),resultSet.getString("hvf_pup"),
					resultSet.getInt("hvf_vanum"),resultSet.getInt("hvf_vaden"),resultSet.getInt("hvf_sph_sign"),
					resultSet.getString("hvf_sph_num"),resultSet.getInt("hvf_cyl_sign"),resultSet.getString("hvf_cyl_num"),
					resultSet.getInt("hvf_axis"),resultSet.getInt("hvf_ght"),resultSet.getString("hvf_vfi"),
					resultSet.getInt("hvf_mdsign"),resultSet.getString("hvf_mddb"),resultSet.getInt("hvf_mdp"),
					resultSet.getInt("hvf_psdsign"),resultSet.getString("hvf_psddb"),resultSet.getInt("hvf_psdp"),
					resultSet.getInt("hvf_central_15"),resultSet.getInt("hvf_central_0"),resultSet.getInt("hvf_sup_hem"),
					resultSet.getInt("hvf_inf_hem"),resultSet.getInt("hvf_sup_hem2"),resultSet.getInt("hvf_inf_hem2"),
					resultSet.getInt("hvf_pts_five"),resultSet.getInt("hvf_pts_contig"),resultSet.getInt("hvf_pts_one"),
					resultSet.getInt("hvf_cluster"),resultSet.getInt("hvf_severe"),resultSet.getInt("hvf_reliable_review")
				));
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

	public static Vector<HVFtest> queryHVFtestForAdjudication(String query){
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
				result.add(new HVFtest(resultSet.getInt("hvf_mon"), resultSet.getString("hvf_mon_oth2_c47"), resultSet.getInt("hvf_tar"), 
					resultSet.getString("hvf_tar_oth"), resultSet.getInt("hvf_lossnum"), resultSet.getInt("hvf_lossden"), 
					resultSet.getInt("hvf_fp"), resultSet.getInt("hvf_fn"), resultSet.getString("hvf_dur"),
					resultSet.getInt("hvf_fov"), resultSet.getInt("hvf_stimintens"), resultSet.getInt("hvf_stimcol"), 
					resultSet.getString("hvf_stimcol_oth"), resultSet.getString("hvf_back"), resultSet.getInt("hvf_strategy"), 
					resultSet.getString("hvf_strategy_oth"), resultSet.getString("hvf_pup"), resultSet.getInt("hvf_vanum"), 
					resultSet.getInt("hvf_vaden"), resultSet.getInt("hvf_sph_sign"), resultSet.getString("hvf_sph_num"), 
					resultSet.getInt("hvf_cyl_sign"), resultSet.getString("hvf_cyl_num"), resultSet.getInt("hvf_axis"),
					resultSet.getInt("hvf_ght"), resultSet.getString("hvf_vfi"), resultSet.getInt("hvf_mdsign"), 
					resultSet.getString("hvf_mddb"), resultSet.getInt("hvf_mdp"), resultSet.getInt("hvf_psdsign"), resultSet.getString("hvf_psddb"), 
					resultSet.getInt("hvf_psdp"), resultSet.getInt("hvf_central_15"), resultSet.getInt("hvf_central_0"), 
					resultSet.getInt("hvf_sup_hem"), resultSet.getInt("hvf_inf_hem"), resultSet.getInt("hvf_sup_hem2"), 
					resultSet.getInt("hvf_inf_hem2"), resultSet.getInt("hvf_pts_five"), resultSet.getInt("hvf_pts_contig"),
					resultSet.getInt("hvf_pts_one"), resultSet.getInt("hvf_cluster")
				 ));
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

	public static Vector<HVFtest> queryHVFtestForOp(String query){
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
				result.add(new HVFtest(resultSet.getInt("hvf_fp"), resultSet.getInt("hvf_fn"),resultSet.getInt("hvf_ght"),
					resultSet.getInt("hvf_psdp"),resultSet.getInt("hvf_cluster"), resultSet.getInt("hvf_glau"), 
					resultSet.getInt("hvf_mdsign"),resultSet.getString("hvf_mddb"),resultSet.getInt("hvf_mdp"),
					resultSet.getInt("hvf_central_15"),resultSet.getInt("hvf_central_0"),resultSet.getInt("hvf_sup_hem"),
					resultSet.getInt("hvf_inf_hem"),resultSet.getInt("hvf_sup_hem2"),resultSet.getInt("hvf_inf_hem2"),
					resultSet.getInt("hvf_pts_five"), resultSet.getInt("hvf_pts_contig"),resultSet.getInt("hvf_pts_one"),
					resultSet.getInt("hvf_severe")
				));
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
			return count;
		}
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
