/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import utilities.*;
import java.util.*;

/**
 *
 * @author aryner
 */
public class MDTtest {
	private int id;
	private int confirmed;
	private String pictureName;
	private int userID;
	private String late;
	private String fp;
	private int lens;
	private String lens_y;
	private String dur;
	private String ptd;
	private String lu_one;
	private String ru_one;
	private String ll_one;
	private String rl_one;
	private int abnormal;

	private static final String slash = System.getProperty("file.separator");

	public MDTtest(int id, int confirmed, String pictureName, int userID, String late, String fp,
		       int lens, String lens_y, String dur, String ptd, String lu_one, String ru_one,
		       String ll_one, String rl_one, int abnormal) 
	{
		this.id = id;
		this.confirmed = confirmed;
		this.pictureName = pictureName;
		this.userID = userID;
		this.late = late;
		this.fp = fp;
		this.lens = lens;
		this.lens_y = lens_y;
		this.dur = dur;
		this.ptd = ptd;
		this.lu_one = lu_one;
		this.ru_one = ru_one;
		this.ll_one = ll_one;
		this.rl_one = rl_one;
		this.abnormal = abnormal;
	}

	public static Picture getNext(User user) {
		Picture result = null;
		String query = "";
		if (user.getAccess() == 0) {
			query = "SELECT * FROM picture WHERE name NOT IN (SELECT "+
				" pictureName FROM MDTtest WHERE userID="+user.getID()+") AND type='MDT'"+
				" AND name NOT IN (SELECT pictureName FROM MDTtest GROUP BY pictureName HAVING COUNT(*)>=2)";
		}
		else if (user.getAccess() == 1) {
			query = "SELECT * FROM picture WHERE name IN (SELECT pictureName FROM "+
				"MDTtest WHERE CONFIRMED=1) AND type='MDT'";
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
		String query = "SELECT * FROM MDTtest GROUP BY pictureName HAVING COUNT(*)=1";
		return SQLCommands.getCount(query);
	}

	public static Vector<MDTtest> getPair(String picName) {
		String query = "SELECT * FROM MDTtest WHERE pictureName='"+picName+"'";	
		return SQLCommands.queryMDTtest(query);
	}

	public static ArrayList<String> needPictures(){
		ArrayList<String> needPics = new ArrayList<String>();
		String query = "SELECT * FROM MDTtest WHERE pictureName NOT IN (SELECT name FROM picture)";
		Vector<FDTtest> mdt = SQLCommands.queryFDTtest(query);

		for(int i=0; i<mdt.size(); i++) {
			if(!needPics.contains(mdt.get(i).getPictureName())) {
				needPics.add(mdt.get(i).getPictureName());
			}
		}

		String ext;
		for(int i=0; i<needPics.size(); i++) {
			ext = needPics.get(i).substring(needPics.get(i).indexOf(".")+1, needPics.get(i).length());
			if(ext.equals("pdf")) {
				needPics.set(i, "MDT: "+needPics.get(i).substring(0,needPics.get(i).indexOf("."))+".jpg");
			}
		}

		return needPics;
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
	 * @return the late
	 */
	public String getLate() {
		return late;
	}

	/**
	 * @param late the late to set
	 */
	public void setLate(String late) {
		this.late = late;
	}

	/**
	 * @return the fp
	 */
	public String getFp() {
		return fp;
	}

	/**
	 * @param fp the fp to set
	 */
	public void setFp(String fp) {
		this.fp = fp;
	}

	/**
	 * @return the lens
	 */
	public int getLens() {
		return lens;
	}

	/**
	 * @param lens the lens to set
	 */
	public void setLens(int lens) {
		this.lens = lens;
	}

	/**
	 * @return the lens_y
	 */
	public String getLens_y() {
		return lens_y;
	}

	/**
	 * @param lens_y the lens_y to set
	 */
	public void setLens_y(String lens_y) {
		this.lens_y = lens_y;
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
	 * @return the ptd
	 */
	public String getPtd() {
		return ptd;
	}

	/**
	 * @param ptd the ptd to set
	 */
	public void setPtd(String ptd) {
		this.ptd = ptd;
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
	
}
