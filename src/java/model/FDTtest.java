/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import utilities.*;
import java.util.*;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author aryner
 */
public class FDTtest {
	private int id;
	private int confirmed;
	private String pictureName;
	private int userID;
	private int adjudicatorID;
	private String dur;
	private int targ;
	private String targ_oth;
	private int fixerr_num;
	private int fixerr_den;
	private int fp_num;
	private int fp_den;
	private int fn_num;
	private int fn_den;
	private int test;
	private String test_oth;
	private int speed;
	private String speed_oth;
	private String pupil;
	private String va_num;
	private String va_den;
	private int mdsign;
	private String mddb;
	private String mdp;
	private int psdsign;
	private String psdb;
	private String psdp;
	private String lu_one;
	private String lu_five;
	private String ru_one;
	private String ru_five;
	private String ll_one;
	private String ll_five;
	private String rl_one;
	private String rl_five;
	private int abnormal;

	private static final String slash = System.getProperty("file.separator");

	public FDTtest(String npictureName) {
		pictureName = npictureName;
	}

	public FDTtest(String npictureName, int nuserID) {
		String query = "INSERT INTO FDTtest (pictureName, userID) VALUES ('"+npictureName+"', '"+nuserID+"')";
		SQLCommands.update(query);

		query = "SELECT * FROM FDTtest WHERE pictureName='"+npictureName+"' AND userID="+nuserID;
		Vector<Integer> data = SQLCommands.queryNewGrade(query);

		pictureName = npictureName;
		userID = data.get(0);
		id = data.get(1);	
	}

	public FDTtest(int id, int confirmed, String pictureName, int userID, int adjudicatorID, String dur, int targ,
		       String targ_oth, int fixerr_num, int fixerr_den, int fp_num, int fp_den, int fn_num, int fn_den , int test,
		       String test_oth, int speed, String speed_oth, String pupil, String va_num, String va_den,
		       int mdsign, String mddb, String mdp, int psdisgn, String psdb, String psdp, String lu_one,
		       String lu_five, String ru_one, String ru_five, String ll_one, String ll_five, String rl_one,
		       String rl_five, int abnormal) 
	{
		this.id = id;
		this.confirmed = confirmed;
		this.pictureName = pictureName;
		this.userID = userID;
		this.adjudicatorID = adjudicatorID;
		this.dur = dur;
		this.targ = targ;
		this.targ_oth = targ_oth;
		this.fixerr_num = fixerr_num;
		this.fixerr_den = fixerr_den;
		this.fp_num = fp_num;
		this.fp_den = fp_den;
		this.fn_num = fn_num;
		this.fn_den = fn_den;
		this.test = test;
		this.test_oth = test_oth;
		this.speed = speed;
		this.speed_oth = speed_oth;
		this.pupil = pupil;
		this.va_num = va_num;
		this.va_den = va_den;
		this.mdsign = mdsign;
		this.mddb = mddb;
		this.mdp = mdp;
		this.psdsign = psdisgn;
		this.psdb = psdb;
		this.psdp = psdp;
		this.lu_one = lu_one;
		this.lu_five = lu_five;
		this.ru_one = ru_one;
		this.ru_five = ru_five;
		this.ll_one = ll_one;
		this.ll_five = ll_five;
		this.rl_one = rl_one;
		this.rl_five = rl_five;
		this.abnormal = abnormal;
	}

	public static Picture getNext(User user) {
		Picture result = null;
		String query = "";
		if (user.getAccess() == 0) {
			query = "SELECT * FROM picture WHERE name NOT IN (SELECT "+
				" pictureName FROM FDTtest WHERE userID="+user.getID()+") AND type='FDT'"+
				" AND name NOT IN (SELECT pictureName FROM FDTtest GROUP BY pictureName HAVING COUNT(*)>=2)";
		}
		else if (user.getAccess() == 1) {
			query = "SELECT * FROM picture WHERE name IN (SELECT pictureName FROM "+
				"FDTtest WHERE CONFIRMED=1) AND type='FDT'";
		}

		Vector<Picture> pictures = SQLCommands.queryPictures(query); 
		if(pictures.size() > 0) {
			Random rand = new Random(System.currentTimeMillis());
			result = pictures.get(rand.nextInt(pictures.size()));

			if(user.getAccess() == 0) {
				result = pictures.get(0);
			}
		}

		return result;
	}

	public static int getNeedToPairCount() {
		String query = "SELECT * FROM FDTtest GROUP BY pictureName HAVING COUNT(*)=1";
		return SQLCommands.getCount(query);
	}

	public static Vector<FDTtest> getPair(String picName) {
		String query = "SELECT * FROM FDTtest WHERE pictureName='"+picName+"'";	
		return SQLCommands.queryFDTtest(query);
	}

	public static ArrayList<String> needPictures(){
		ArrayList<String> needPics = new ArrayList<String>();
		String query = "SELECT * FROM FDTtest WHERE pictureName NOT IN (SELECT name FROM picture)";
		Vector<FDTtest> fdt = SQLCommands.queryFDTtest(query);

		for(int i=0; i<fdt.size(); i++) {
			if(!needPics.contains(fdt.get(i).getPictureName())) {
				needPics.add(fdt.get(i).getPictureName());
			}
		}

		String ext;
		for(int i=0; i<needPics.size(); i++) {
			ext = needPics.get(i).substring(needPics.get(i).indexOf(".")+1, needPics.get(i).length());
			if(ext.equals("pdf")) {
				needPics.set(i, "FDT: "+needPics.get(i).substring(0,needPics.get(i).indexOf("."))+".jpg");
			}
		}

		return needPics;
	}

	public static int assignFDT(HttpServletRequest request, User user) {
		int result = 0;

		int uID = user.getID();
		String picName = request.getParameter("pictureName");
		FDTtest fdt = null;
		if(user.getAccess() == 0) {
			fdt = new FDTtest(picName, uID);
		}
		else if (user.getAccess() == 1) {
			fdt = new FDTtest(picName);
		}

		String attr = request.getParameter("dur");
		fdt.setDur(attr);
		attr = request.getParameter("targ");
		fdt.setTarg(Integer.parseInt(attr));
		attr = request.getParameter("targ_oth");
		fdt.setTarg_oth(attr);
		attr = request.getParameter("fixerr_num");
		fdt.setFixerr_num(Integer.parseInt(attr));
		attr = request.getParameter("fixerr_den");
		fdt.setFixerr_den(Integer.parseInt(attr));
		attr = request.getParameter("fp_num");
		fdt.setFp_num(Integer.parseInt(attr));
		attr = request.getParameter("fp_den");
		fdt.setFp_den(Integer.parseInt(attr));
		attr = request.getParameter("fn_num");
		fdt.setFn_num(Integer.parseInt(attr));
		attr = request.getParameter("fn_den");
		fdt.setFn_den(Integer.parseInt(attr));
		attr = request.getParameter("test");
		fdt.setTest(Integer.parseInt(attr));
		attr = request.getParameter("test_oth");
		fdt.setTest_oth(attr);
		attr = request.getParameter("speed");
		fdt.setSpeed(Integer.parseInt(attr));
		attr = request.getParameter("speed_oth");
		fdt.setSpeed_oth(attr);
		attr = request.getParameter("pupil");
		fdt.setPupil(attr);
		attr = request.getParameter("va_num");
		fdt.setVa_num(attr);
		attr = request.getParameter("va_den");
		fdt.setVa_den(attr);
		attr = request.getParameter("mdsign");
		fdt.setMdsign(Integer.parseInt(attr));
		attr = request.getParameter("mddb");
		fdt.setMddb(attr);
		attr = request.getParameter("mdp");
		fdt.setMdp(attr);
		attr = request.getParameter("psdsign");
		fdt.setPsdsign(Integer.parseInt(attr));
		attr = request.getParameter("psdb");
		fdt.setPsdb(attr);
		attr = request.getParameter("psdp");
		fdt.setPsdp(attr);
		attr = request.getParameter("lu_one");
		fdt.setLu_one(attr);
		attr = request.getParameter("lu_five");
		fdt.setLu_five(attr);
		attr = request.getParameter("ru_one");
		fdt.setRu_one(attr);
		attr = request.getParameter("ru_five");
		fdt.setRu_five(attr);
		attr = request.getParameter("ll_one");
		fdt.setLl_one(attr);
		attr = request.getParameter("ll_five");
		fdt.setLl_five(attr);
		attr = request.getParameter("rl_one");
		fdt.setRl_one(attr);
		attr = request.getParameter("rl_five");
		fdt.setRl_five(attr);

		String query = "UPDATE FDTtest SET fdt_dur='"+fdt.getDur()+"', fdt_targ='"+fdt.getTarg()+"', fdt_targ_oth='"+fdt.getTarg_oth()+"', "+
			       "fdt_fixerr_num='"+fdt.getFixerr_num()+"', fdt_fixerr_den='"+fdt.getFixerr_den()+"', fdt_fp_num='"+fdt.getFp_num()+"', "+
			       "fdt_fp_den='"+fdt.getFp_den()+"', fdt_fn_num='"+fdt.getFn_num()+"', fdt_fn_den='"+fdt.getFn_den()+"', fdt_test='"+fdt.getTest()+"', "+
			       "fdt_test_oth='"+fdt.getTest_oth()+"', fdt_speed='"+fdt.getSpeed()+"', fdt_speed_oth='"+fdt.getSpeed_oth()+"', "+
			       "fdt_pupil='"+fdt.getPupil()+"', fdt_va_num='"+fdt.getVa_num()+"', fdt_va_den='"+fdt.getVa_den()+"', fdt_mdsign='"+fdt.getMdsign()+"', "+
			       "fdt_mddb='"+fdt.getMddb()+"', fdt_mdp='"+fdt.getMdp()+"', fdt_psdsign='"+fdt.getPsdsign()+"', fdt_psdb='"+fdt.getPsdb()+"', "+
			       "fdt_psdp='"+fdt.getPsdp()+"', fdt_lu_one='"+fdt.getLu_one()+"', fdt_lu_five='"+fdt.getLu_five()+"', fdt_ru_one='"+fdt.getRu_one()+"', "+
			       "fdt_ru_five='"+fdt.getRu_five()+"', fdt_ll_one='"+fdt.getLl_one()+"', fdt_ll_five='"+fdt.getLl_five()+"', "+
			       "fdt_rl_one='"+fdt.getRl_one()+"', fdt_rl_five='"+fdt.getRl_five()+"' ";

		if(user.getAccess() == 0) {
			query += " WHERE id='"+fdt.getId()+"'";
		} else if(user.getAccess() ==1) { 
			query += ", confirmed=2, adjudicatorID="+user.getID()+" WHERE pictureName='"+request.getParameter("pictureName")+"'";
			if(request.getParameter("alreadyConfirmed").equals("true")) {
				result = 2;
			}
		}
		SQLCommands.update(query);
		if(user.getAccess() == 0) {
			setForAdjudication(picName);
		}

		return result;
	}

	public static void setForAdjudication(String picName) {
		String query = "SELECT * FROM FDTtest WHERE pictureName='"+picName+"'";
		Vector<FDTtest> hvf = SQLCommands.queryFDTtest(query);

		if(hvf.size() > 1) {
			//get the ones that don't need adjudication
			query = "SELECT * FROM FDTtest GROUP BY pictureName, "+
				"fdt_dur, fdt_targ, fdt_targ_oth, fdt_fixerr_num, fdt_fixerr_den, fdt_fp_num, fdt_fp_den, "+
				"fdt_fn_num, fdt_fn_den, fdt_test, fdt_test_oth, fdt_speed, fdt_speed_oth, fdt_pupil, "+
				"fdt_va_num, fdt_va_den, fdt_mdsign, fdt_mddb, fdt_mdp, fdt_psdsign, fdt_psdb, fdt_psdp, "+
				"fdt_lu_one, fdt_lu_five, fdt_ru_one, fdt_ru_five, fdt_ll_one, fdt_ll_five, "+
				"fdt_rl_one, fdt_rl_five "+
				"HAVING COUNT(*)=2";
			Vector<FDTtest> set = SQLCommands.queryFDTtest(query);
			//get the ones that need adjudication
			query = "SELECT * FROM FDTtest GROUP BY pictureName, "+
				"fdt_dur, fdt_targ, fdt_targ_oth, fdt_fixerr_num, fdt_fixerr_den, fdt_fp_num, fdt_fp_den, "+
				"fdt_fn_num, fdt_fn_den, fdt_test, fdt_test_oth, fdt_speed, fdt_speed_oth, fdt_pupil, "+
				"fdt_va_num, fdt_va_den, fdt_mdsign, fdt_mddb, fdt_mdp, fdt_psdsign, fdt_psdb, fdt_psdp, "+
				"fdt_lu_one, fdt_lu_five, fdt_ru_one, fdt_ru_five, fdt_ll_one, fdt_ll_five, "+
				"fdt_rl_one, fdt_rl_five "+
				"HAVING COUNT(*)=1";
			Vector<FDTtest> notSet = SQLCommands.queryFDTtest(query);

			for(int i=set.size()-1; i>=0; i--) {
				if (!set.get(i).getPictureName().equals(picName)) {
					set.remove(i);
				}
			}
			for(int i=notSet.size()-1; i>=0; i--) {
				if (!notSet.get(i).getPictureName().equals(picName)) {
					notSet.remove(i);
				}
			}
			
			//update the confirmed ones
			query = "UPDATE FDTtest SET confirmed=2";
			query += " WHERE pictureName='"+picName+"'";
			if(set.size() > 0) {
				SQLCommands.update(query);
			}

			//update the ones that need confirming
			query = "UPDATE FDTtest SET confirmed=1 WHERE pictureName='"+picName+"'";
			if(notSet.size() > 0) {
				SQLCommands.update(query);
			}
		}
	}

	public static Vector<String> getUngradedNames() {
		Vector<String> result = new Vector<String>();
		String query = "SELECT * FROM picture WHERE name NOT IN (SELECT pictureName FROM FDTtest) AND type='FDT'";
		Vector<Picture> pictures = SQLCommands.queryPictures(query);

		for(int i=0; i<pictures.size(); i++) {
			result.add("FDT - " + pictures.get(i).getName());
		}

		return result;
	}

	public static Vector<String> getGradedOnceNames() {
		Vector<String> result = new Vector<String>();
		String query = "SELECT * FROM FDTtest GROUP BY pictureName HAVING COUNT(*)=1";
		Vector<FDTtest> fdt = SQLCommands.queryFDTtest(query);

		for(int i=0; i<fdt.size(); i++) {
			result.add("FDT - " + fdt.get(i).getPictureName());
		}

		return result;
	}
	
	public static Vector<String> getNeedsAdjudication() {
		Vector<String> result = new Vector<String>();
		String query = "SELECT DISTINCT pictureName FROM FDTtest WHERE confirmed='1'";
		Vector<String> fdt = SQLCommands.queryNames(query);

		for(int i=0; i<fdt.size(); i++) {
			result.add("FDT - " + fdt.get(i));
		}

		return result;
	}

	public static Vector<String> getAdjudicated() {
		Vector<String> result = new Vector<String>();
		String query = "SELECT DISTINCT pictureName FROM FDTtest WHERE confirmed='2'";
		Vector<String> fdt = SQLCommands.queryNames(query);

		for(int i=0; i<fdt.size(); i++) {
			result.add("FDT - " + fdt.get(i));
		}

		return result;
	}

	public static Vector<String> getCSVLines() {
		Vector<String> result = new Vector<String>();
		String query = "SELECT * FROM FDTtest";
		Vector<FDTtest> fdts = SQLCommands.queryFDTtest(query);

		String currLine = "confirmed, picture, userID, adjudicatorID, fdt_dur, fdt_targ, fdt_targ_oth, fdt_fixerr_num, "+
				  "fdt_fixerr_den, fdt_fp_num, fdt_fp_den, fdt_fn_num, fdt_fn_den, fdt_test, fdt_test_oth, fdt_speed, "+
				  "fdt_speed_oth, fdt_pupil, fdt_va_num, fdt_va_den, fdt_mdsign, fdt_mddb, fdt_mdp, fdt_psdsign, "+
				  "fdt_psdb, fdt_psdp, fdt_lu_one, fdt_lu_five, fdt_ru_one, fdt_ru_five, fdt_ll_one, fdt_ll_five, " +
				  "fdt_rl_one, fdt_rl_five, fdt_abnormal";
		result.add(currLine);

		for(FDTtest fdt : fdts) {
			currLine = fdt.getConfirmed()+", "+fdt.getPictureName()+", "+fdt.getUserID()+", "+fdt.getAdjudicatorID()+", "+
				   fdt.getDur()+", "+fdt.getTarg()+", "+fdt.getTarg_oth()+", "+fdt.getFixerr_num()+", "+fdt.getFixerr_den()+", "+
				   fdt.getFp_num()+", "+fdt.getFp_den()+", "+fdt.getFn_num()+", "+fdt.getFn_den()+", "+fdt.getTest()+", "+
				   fdt.getTest_oth()+", "+fdt.getSpeed()+", "+fdt.getSpeed_oth()+", "+fdt.getPupil()+", "+fdt.getVa_num()+", "+
				   fdt.getVa_den()+", "+fdt.getMdsign()+", "+fdt.getMddb()+", "+fdt.getPsdsign()+", "+fdt.getPsdb()+", "+
				   fdt.getPsdp()+", "+fdt.getLu_one()+", "+fdt.getLu_five()+", "+fdt.getRu_one()+", "+fdt.getRu_five()+", "+
				   fdt.getLl_one()+", "+fdt.getLl_five()+", "+fdt.getRl_one()+", "+fdt.getRl_five()+", "+fdt.getAbnormal();
			result.add(currLine);
		}

		return result;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the confirmed
	 */
	public int getConfirmed() {
		return confirmed;
	}

	/**
	 * @param confirmed the confirmed to set
	 */
	public void setConfirmed(int confirmed) {
		this.confirmed = confirmed;
	}

	/**
	 * @return the userID
	 */
	public int getUserID() {
		return userID;
	}

	/**
	 * @param userID the userID to set
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}

	/**
	 * @return the dur
	 */
	public String getDur() {
		return dur;
	}

	/**
	 * @param dur the dur to set
	 */
	public void setDur(String dur) {
		this.dur = dur;
	}

	/**
	 * @return the targ
	 */
	public int getTarg() {
		return targ;
	}

	/**
	 * @param targ the targ to set
	 */
	public void setTarg(int targ) {
		this.targ = targ;
	}

	/**
	 * @return the targ_oth
	 */
	public String getTarg_oth() {
		return targ_oth;
	}

	/**
	 * @param targ_oth the targ_oth to set
	 */
	public void setTarg_oth(String targ_oth) {
		this.targ_oth = targ_oth;
	}

	/**
	 * @return the fixerr_num
	 */
	public int getFixerr_num() {
		return fixerr_num;
	}

	/**
	 * @param fixerr_num the fixerr_num to set
	 */
	public void setFixerr_num(int fixerr_num) {
		this.fixerr_num = fixerr_num;
	}

	/**
	 * @return the fp_num
	 */
	public int getFp_num() {
		return fp_num;
	}

	/**
	 * @param fp_num the fp_num to set
	 */
	public void setFp_num(int fp_num) {
		this.fp_num = fp_num;
	}

	/**
	 * @return the fp_den
	 */
	public int getFp_den() {
		return fp_den;
	}

	/**
	 * @param fp_den the fp_den to set
	 */
	public void setFp_den(int fp_den) {
		this.fp_den = fp_den;
	}

	/**
	 * @return the fn_num
	 */
	public int getFn_num() {
		return fn_num;
	}

	/**
	 * @param fn_num the fn_num to set
	 */
	public void setFn_num(int fn_num) {
		this.fn_num = fn_num;
	}

	/**
	 * @return the fn_den
	 */
	public int getFn_den() {
		return fn_den;
	}

	/**
	 * @param fn_den the fn_den to set
	 */
	public void setFn_den(int fn_den) {
		this.fn_den = fn_den;
	}

	/**
	 * @return the test
	 */
	public int getTest() {
		return test;
	}

	/**
	 * @param test the test to set
	 */
	public void setTest(int test) {
		this.test = test;
	}

	/**
	 * @return the test_oth
	 */
	public String getTest_oth() {
		return test_oth;
	}

	/**
	 * @param test_oth the test_oth to set
	 */
	public void setTest_oth(String test_oth) {
		this.test_oth = test_oth;
	}

	/**
	 * @return the speed
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * @return the speed_oth
	 */
	public String getSpeed_oth() {
		return speed_oth;
	}

	/**
	 * @param speed_oth the speed_oth to set
	 */
	public void setSpeed_oth(String speed_oth) {
		this.speed_oth = speed_oth;
	}

	/**
	 * @return the pupil
	 */
	public String getPupil() {
		return pupil;
	}

	/**
	 * @param pupil the pupil to set
	 */
	public void setPupil(String pupil) {
		this.pupil = pupil;
	}

	/**
	 * @return the va_num
	 */
	public String getVa_num() {
		return va_num;
	}

	/**
	 * @param va_num the va_num to set
	 */
	public void setVa_num(String va_num) {
		this.va_num = va_num;
	}

	/**
	 * @return the va_den
	 */
	public String getVa_den() {
		return va_den;
	}

	/**
	 * @param va_den the va_den to set
	 */
	public void setVa_den(String va_den) {
		this.va_den = va_den;
	}

	/**
	 * @return the mdsign
	 */
	public int getMdsign() {
		return mdsign;
	}

	/**
	 * @param mdsign the mdsign to set
	 */
	public void setMdsign(int mdsign) {
		this.mdsign = mdsign;
	}

	/**
	 * @return the mddb
	 */
	public String getMddb() {
		return mddb;
	}

	/**
	 * @param mddb the mddb to set
	 */
	public void setMddb(String mddb) {
		this.mddb = mddb;
	}

	/**
	 * @return the mdp
	 */
	public String getMdp() {
		return mdp;
	}

	/**
	 * @param mdp the mdp to set
	 */
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	/**
	 * @return the psdisgn
	 */
	public int getPsdsign() {
		return psdsign;
	}

	/**
	 * @param psdsign the psdisgn to set
	 */
	public void setPsdsign(int psdsign) {
		this.psdsign = psdsign;
	}

	/**
	 * @return the psdp
	 */
	public String getPsdp() {
		return psdp;
	}

	/**
	 * @param psdp the psdp to set
	 */
	public void setPsdp(String psdp) {
		this.psdp = psdp;
	}

	/**
	 * @return the lu_one
	 */
	public String getLu_one() {
		return lu_one;
	}

	/**
	 * @param lu_one the lu_one to set
	 */
	public void setLu_one(String lu_one) {
		this.lu_one = lu_one;
	}

	/**
	 * @return the lu_five
	 */
	public String getLu_five() {
		return lu_five;
	}

	/**
	 * @param lu_five the lu_five to set
	 */
	public void setLu_five(String lu_five) {
		this.lu_five = lu_five;
	}

	/**
	 * @return the ru_one
	 */
	public String getRu_one() {
		return ru_one;
	}

	/**
	 * @param ru_one the ru_one to set
	 */
	public void setRu_one(String ru_one) {
		this.ru_one = ru_one;
	}

	/**
	 * @return the ru_five
	 */
	public String getRu_five() {
		return ru_five;
	}

	/**
	 * @param ru_five the ru_five to set
	 */
	public void setRu_five(String ru_five) {
		this.ru_five = ru_five;
	}

	/**
	 * @return the ll_one
	 */
	public String getLl_one() {
		return ll_one;
	}

	/**
	 * @param ll_one the ll_one to set
	 */
	public void setLl_one(String ll_one) {
		this.ll_one = ll_one;
	}

	/**
	 * @return the ll_five
	 */
	public String getLl_five() {
		return ll_five;
	}

	/**
	 * @param ll_five the ll_five to set
	 */
	public void setLl_five(String ll_five) {
		this.ll_five = ll_five;
	}

	/**
	 * @return the rl_one
	 */
	public String getRl_one() {
		return rl_one;
	}

	/**
	 * @param rl_one the rl_one to set
	 */
	public void setRl_one(String rl_one) {
		this.rl_one = rl_one;
	}

	/**
	 * @return the rl_five
	 */
	public String getRl_five() {
		return rl_five;
	}

	/**
	 * @param rl_five the rl_five to set
	 */
	public void setRl_five(String rl_five) {
		this.rl_five = rl_five;
	}

	/**
	 * @return the pictureName
	 */
	public String getPictureName() {
		return pictureName;
	}

	/**
	 * @param pictureName the pictureName to set
	 */
	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}

	/**
	 * @return the fixerr_den
	 */
	public int getFixerr_den() {
		return fixerr_den;
	}

	/**
	 * @param fixerr_den the fixerr_den to set
	 */
	public void setFixerr_den(int fixerr_den) {
		this.fixerr_den = fixerr_den;
	}

	/**
	 * @return the psdb
	 */
	public String getPsdb() {
		return psdb;
	}

	/**
	 * @param psdb the psdb to set
	 */
	public void setPsdb(String psdb) {
		this.psdb = psdb;
	}

	/**
	 * @return the abnormal
	 */
	public int getAbnormal() {
		return abnormal;
	}

	/**
	 * @param abnormal the abnormal to set
	 */
	public void setAbnormal(int abnormal) {
		this.abnormal = abnormal;
	}

	/**
	 * @return the adjudicatorID
	 */
	public int getAdjudicatorID() {
		return adjudicatorID;
	}

	/**
	 * @param adjudicatorID the adjudicatorID to set
	 */
	public void setAdjudicatorID(int adjudicatorID) {
		this.adjudicatorID = adjudicatorID;
	}
	
	
}
