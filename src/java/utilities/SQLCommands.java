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
	public static Vector<String> queryNames(String query){
		Vector<String> result = new Vector<String>();
		Connection con = null;
		Statement stmt = null;
		ResultSet resultSet = null;

		try{
			InitialContext initialContext = new InitialContext();
			Context context = (Context)initialContext.lookup("java:comp/env");
			DataSource dataSource = (DataSource)context.lookup("hvf_grader");
			con = dataSource.getConnection();

			stmt = con.createStatement();

			resultSet = stmt.executeQuery(query); 

			while(resultSet.next()) {
				result.add(resultSet.getString("pictureName"));
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

	public static Vector<FDTtest> queryFDTtest(String query){
		Vector<FDTtest> result = new Vector<FDTtest>();
		Connection con = null;
		Statement stmt = null;
		ResultSet resultSet = null;

		try{
			InitialContext initialContext = new InitialContext();
			Context context = (Context)initialContext.lookup("java:comp/env");
			DataSource dataSource = (DataSource)context.lookup("hvf_grader");
			con = dataSource.getConnection();

			stmt = con.createStatement();

			resultSet = stmt.executeQuery(query); 

			while(resultSet.next()) {
				result.add(new FDTtest(resultSet.getInt("id"),resultSet.getInt("confirmed"),resultSet.getString("pictureName"),
					   resultSet.getInt("userID"),resultSet.getString("fdt_dur"),resultSet.getInt("fdt_targ"),
					   resultSet.getString("fdt_targ_oth"),resultSet.getInt("fdt_fixerr_num"),resultSet.getInt("fdt_fixerr_den"),
					   resultSet.getInt("fdt_fp_num"),resultSet.getInt("fdt_fp_den"),resultSet.getInt("fdt_fn_num"),resultSet.getInt("fdt_fn_den"),
					   resultSet.getInt("fdt_test"),resultSet.getString("fdt_test_oth"),
					   resultSet.getInt("fdt_speed"),resultSet.getString("fdt_speed_oth"),resultSet.getString("fdt_pupil"),
					   resultSet.getString("fdt_va_num"),resultSet.getString("fdt_va_den"),resultSet.getInt("fdt_mdsign"),
					   resultSet.getString("fdt_mddb"),resultSet.getString("fdt_mdp"),resultSet.getInt("fdt_psdsign"),
					   resultSet.getString("fdt_psdb"),resultSet.getString("fdt_psdp"),resultSet.getString("fdt_lu_one"),
					   resultSet.getString("fdt_lu_five"),resultSet.getString("fdt_ru_one"),resultSet.getString("fdt_ru_five"),
					   resultSet.getString("fdt_ll_one"),resultSet.getString("fdt_ll_five"),resultSet.getString("fdt_rl_one"),
					   resultSet.getString("fdt_rl_five"),resultSet.getInt("fdt_abnormal")));
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

	public static Vector<MDTtest> queryMDTtest(String query){
		Vector<MDTtest> result = new Vector<MDTtest>();
		Connection con = null;
		Statement stmt = null;
		ResultSet resultSet = null;

		try{
			InitialContext initialContext = new InitialContext();
			Context context = (Context)initialContext.lookup("java:comp/env");
			DataSource dataSource = (DataSource)context.lookup("hvf_grader");
			con = dataSource.getConnection();

			stmt = con.createStatement();

			resultSet = stmt.executeQuery(query); 

			while(resultSet.next()) {
				result.add(new MDTtest(resultSet.getInt("id"),resultSet.getInt("confirmed"),resultSet.getString("pictureName"),
						       resultSet.getInt("userID"),resultSet.getString("mdt_late"),resultSet.getString("mdt_fp"),
						       resultSet.getInt("mdt_lens"),resultSet.getString("mdt_lens_y"),resultSet.getString("mdt_dur"),
						       resultSet.getString("mdt_ptd"),resultSet.getString("mdt_lu_one"),resultSet.getString("mdt_ru_one"),
						       resultSet.getString("mdt_ll_one"),resultSet.getString("mdt_rl_one"),resultSet.getInt("mdt_abnomrmal")));
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

	public static Vector<OCTtest> queryOCTtest(String query){
		Vector<OCTtest> result = new Vector<OCTtest>();
		Connection con = null;
		Statement stmt = null;
		ResultSet resultSet = null;

		try{
			InitialContext initialContext = new InitialContext();
			Context context = (Context)initialContext.lookup("java:comp/env");
			DataSource dataSource = (DataSource)context.lookup("hvf_grader");
			con = dataSource.getConnection();

			stmt = con.createStatement();

			resultSet = stmt.executeQuery(query); 

			while(resultSet.next()) {
				result.add(new OCTtest(resultSet.getInt("id"),resultSet.getInt("confirmed"),resultSet.getString("pictureName"),
						       resultSet.getInt("userID"),resultSet.getString("oct_length"),resultSet.getInt("oct_type"),
						       resultSet.getString("oct_type_oth"),resultSet.getString("oct_snum"),resultSet.getInt("oct_scol"),
						       resultSet.getString("oct_nnum"),resultSet.getInt("oct_ncol"),resultSet.getString("oct_inum"),
						       resultSet.getInt("oct_icol"),resultSet.getString("oct_tnum"),resultSet.getInt("oct_tcol"),
						       resultSet.getString("oct_sig"),resultSet.getString("oct_isnum"),resultSet.getInt("oct_iscol"),
						       resultSet.getString("oct_sinum"),resultSet.getInt("oct_sicol"),resultSet.getString("oct_stnum"),
						       resultSet.getInt("oct_stcol"),resultSet.getString("oct_itnum"),resultSet.getInt("oct_itcol"),
						       resultSet.getString("oct_snnum"),resultSet.getInt("oct_sncol"),resultSet.getString("oct_mmnum"),
						       resultSet.getInt("oct_mmcol"),resultSet.getString("oct_smaxnum"),resultSet.getInt("oct_smaxcol"),
						       resultSet.getString("oct_imaxnum"),resultSet.getInt("oct_imaxcol"),resultSet.getString("oct_savgnum"),
						       resultSet.getInt("oct_savgcol"),resultSet.getString("oct_iavgnum"),resultSet.getInt("oct_iavgcol"),
						       resultSet.getString("oct_atnum"),resultSet.getInt("oct_atcol")));
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

	public static Vector<Photos> queryPhotos(String query){
		Vector<Photos> result = new Vector<Photos>();
		Connection con = null;
		Statement stmt = null;
		ResultSet resultSet = null;

		try{
			InitialContext initialContext = new InitialContext();
			Context context = (Context)initialContext.lookup("java:comp/env");
			DataSource dataSource = (DataSource)context.lookup("hvf_grader");
			con = dataSource.getConnection();

			stmt = con.createStatement();

			resultSet = stmt.executeQuery(query); 

			while(resultSet.next()) {
				result.add(new Photos(resultSet.getInt("id"),resultSet.getInt("confirmed"),resultSet.getString("pictureName"),
						      resultSet.getInt("userID"),resultSet.getInt("type"),resultSet.getInt("photo_qual"),
						      resultSet.getString("photo_cdr"),resultSet.getInt("photo_notch"),resultSet.getString("notch_hrs_one"),
						      resultSet.getString("notch_hrs_two"),resultSet.getInt("photo_erosion"),resultSet.getString("eros_hrs_one"),
						      resultSet.getString("eros_hrs_two"),resultSet.getInt("photo_disc"),resultSet.getString("disc_hrs_one"),
						      resultSet.getString("disc_hrs_two"),resultSet.getInt("photo_rnfl"),resultSet.getString("rnfl_hrs_one"),
						      resultSet.getString("rnfl_hrs_two")));
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

	public static Vector<HVFtest> queryHVFtestMaster(String query){
		Vector<HVFtest> result = new Vector<HVFtest>();
		Connection con = null;
		Statement stmt = null;
		ResultSet resultSet = null;

		try{
			InitialContext initialContext = new InitialContext();
			Context context = (Context)initialContext.lookup("java:comp/env");
			DataSource dataSource = (DataSource)context.lookup("hvf_grader");
			con = dataSource.getConnection();

			stmt = con.createStatement();

			resultSet = stmt.executeQuery(query); 

			while(resultSet.next()) {
				result.add(new HVFtest(
					resultSet.getInt("id"), resultSet.getString("opthName"), resultSet.getInt("adjudicatorID"), 
					resultSet.getInt("confirmed"),resultSet.getInt("opthCheck"), resultSet.getString("pictureName"),
					resultSet.getInt("userID"),resultSet.getInt("hvf_notes"),resultSet.getString("hvf_notes_other"),
					resultSet.getString("hvf_vf_loss"), resultSet.getString("hvf_vf_defect"),
					resultSet.getInt("hvf_glau"),resultSet.getString("hvf_vf_loss_oth"),
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

	private static final String slash = System.getProperty("file.separator");

	public static int getCount(String query){ 
		int count = 0;
		Connection con = null;
		Statement stmt = null;
		ResultSet resultSet = null;

		try{
			InitialContext initialContext = new InitialContext();
			Context context = (Context)initialContext.lookup("java:comp/env");
			DataSource dataSource = (DataSource)context.lookup("hvf_grader");
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
			Context context = (Context)initialContext.lookup("java:comp/env");
			DataSource dataSource = (DataSource)context.lookup("hvf_grader");
			con = dataSource.getConnection();

			stmt = con.createStatement();

			resultSet = stmt.executeQuery(query); 

			while(resultSet.next()) {
				result.add(new HVFtest(resultSet.getString("pictureName")));
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
	
	public static String querySeverityChartName(){
		String query = "SELECT * FROM picture WHERE type='severity' ORDER BY id DESC LIMIT 1";
		Connection con = null;
		Statement stmt = null;
		ResultSet resultSet = null;
		String result = null;

		try{
			InitialContext initialContext = new InitialContext();
			Context context = (Context)initialContext.lookup("java:comp/env");
			DataSource dataSource = (DataSource)context.lookup("hvf_grader");
			con = dataSource.getConnection();

			stmt = con.createStatement();

			resultSet = stmt.executeQuery(query); 

			while(resultSet.next()) {
				result = resultSet.getString("name");
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

	public static String queryGradingChartName(){
		String query = "SELECT * FROM picture WHERE type='grading' ORDER BY id DESC LIMIT 1";
		Connection con = null;
		Statement stmt = null;
		ResultSet resultSet = null;
		String result = null;

		try{
			InitialContext initialContext = new InitialContext();
			Context context = (Context)initialContext.lookup("java:comp/env");
			DataSource dataSource = (DataSource)context.lookup("hvf_grader");
			con = dataSource.getConnection();

			stmt = con.createStatement();

			resultSet = stmt.executeQuery(query); 

			while(resultSet.next()) {
				result = resultSet.getString("name");
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
			Context context = (Context)initialContext.lookup("java:comp/env");
			DataSource dataSource = (DataSource)context.lookup("hvf_grader");
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
			Context context = (Context)initialContext.lookup("java:comp/env");
			DataSource dataSource = (DataSource)context.lookup("hvf_grader");
			con = dataSource.getConnection();

			stmt = con.createStatement();

			resultSet = stmt.executeQuery(query); 

			while(resultSet.next()) {
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
			Context context = (Context)initialContext.lookup("java:comp/env");
			DataSource dataSource = (DataSource)context.lookup("hvf_grader");
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
			Context context = (Context)initialContext.lookup("java:comp/env");
			DataSource dataSource = (DataSource)context.lookup("hvf_grader");
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
