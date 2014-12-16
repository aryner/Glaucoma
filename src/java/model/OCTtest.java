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
public class OCTtest {
	private int id;
	private int confirmed;
	private String pictureName;
	private int userID;
	private String length;
	private int type;
	private String type_oth;
	private String snum;
	private int scol;
	private String nnum;
	private int ncol;
	private String inum;
	private int icol;
	private String tnum;
	private int tcol;
	private String sig;
	private String isnum;
	private int iscol;
	private String sinum;
	private int sicol;
	private String stnum;
	private int stcol;
	private String itnum;
	private int itcol;
	private String snnum;
	private int sncol;
	private String mmnum;
	private int mmcol;
	private String smaxnum;
	private int smaxcol;
	private String imaxnum;
	private int imaxcol;
	private String savgnum;
	private int savgcol;
	private String iavgnum;
	private int iavgcol;
	private String atnum;
	private int atcol;

	private static final String slash = System.getProperty("file.separator");

	public OCTtest(String npictureName) {
		pictureName = npictureName;
	}

	public OCTtest(String npictureName, int nuserID) {
		String query = "INSERT INTO OCTtest (pictureName, userID) VALUES ('"+npictureName+"', '"+nuserID+"')";
		SQLCommands.update(query);

		query = "SELECT * FROM OCTtest WHERE pictureName='"+npictureName+"' AND userID="+nuserID;
		Vector<Integer> data = SQLCommands.queryNewGrade(query);

		pictureName = npictureName;
		userID = data.get(0);
		id = data.get(1);	
	}

	public OCTtest(int id, int confirmed, String pictureName, int userID, String length,
		       int type, String type_oth, String snum, int scol, String nnum, int ncol,
		       String inum, int icol, String tnum, int tcol, String sig, String isnum,
		       int iscol, String sinum, int sicol, String stnum, int stcol, String itnum,
		       int itcol, String snnum, int sncol, String mmnum, int mmcol, String smaxnum,
		       int smaxcol, String imaxnum, int imaxcol, String savgnum, int savgcol, 
		       String iavgnum, int iavgcol, String atnum, int atcol)
	{
		this.id = id;
		this.confirmed = confirmed;
		this.pictureName = pictureName;
		this.userID = userID;
		this.length = length;
		this.type = type;
		this.type_oth = type_oth;
		this.snum = snum;
		this.scol = scol;
		this.nnum = nnum;
		this.ncol = ncol;
		this.inum = inum; 
		this.icol = icol;
		this.tnum = tnum;
		this.tcol = tcol;
		this.sig = sig;
		this.isnum = isnum;
		this.iscol = iscol;
		this.sinum = sinum; 
		this.sicol = sicol;
		this.stnum = stnum;
		this.stcol  = stcol;
		this.itnum = itnum;
		this.itcol = itcol;
		this.snnum = snnum;
		this.sncol = sncol;
		this.mmnum = mmnum;
		this.mmcol = mmcol;
		this.smaxnum = smaxnum;
		this.smaxcol = smaxcol;
		this.imaxnum = imaxnum;
		this.imaxcol = imaxcol;
		this.savgnum = savgnum;
		this.savgcol = savgcol;
		this.iavgnum = iavgnum;
		this.iavgcol = iavgcol;
		this.atnum = atnum;
		this.atcol = atcol;
	}

	public static Picture getNext(User user) {
		Picture result = null;
		String query = "";
		if (user.getAccess() == 0) {
			query = "SELECT * FROM picture WHERE name NOT IN (SELECT "+
				" pictureName FROM OCTtest WHERE userID="+user.getID()+") AND type='OCT'"+
				" AND name NOT IN (SELECT pictureName FROM OCTtest GROUP BY pictureName HAVING COUNT(*)>=2)";
		}
		else if (user.getAccess() == 1) {
			query = "SELECT * FROM picture WHERE name IN (SELECT pictureName FROM "+
				"OCTtest WHERE CONFIRMED=1) AND type='OCT'";
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
		String query = "SELECT * FROM OCTtest GROUP BY pictureName HAVING COUNT(*)=1";
		return SQLCommands.getCount(query);
	}

	public static Vector<OCTtest> getPair(String picName) {
		String query = "SELECT * FROM OCTtest WHERE pictureName='"+picName+"'";	
		return SQLCommands.queryOCTtest(query);
	}

	public static ArrayList<String> needPictures(){
		ArrayList<String> needPics = new ArrayList<String>();
		String query = "SELECT * FROM OCTtest WHERE pictureName NOT IN (SELECT name FROM picture)";
		Vector<FDTtest> oct = SQLCommands.queryFDTtest(query);

		for(int i=0; i<oct.size(); i++) {
			if(!needPics.contains(oct.get(i).getPictureName())) {
				needPics.add(oct.get(i).getPictureName());
			}
		}

		String ext;
		for(int i=0; i<needPics.size(); i++) {
			ext = needPics.get(i).substring(needPics.get(i).indexOf(".")+1, needPics.get(i).length());
			if(ext.equals("pdf")) {
				needPics.set(i, "OCT: "+needPics.get(i).substring(0,needPics.get(i).indexOf("."))+".jpg");
			}
		}

		return needPics;
	}

	public static int assignOCT(HttpServletRequest request, User user) {
		int result = 0;

		int uID = user.getID();
		String picName = request.getParameter("pictureName");
		OCTtest oct = null;
		if(user.getAccess() == 0) {
			oct = new OCTtest(picName, uID);
		}
		else if (user.getAccess() == 1) {
			oct = new OCTtest(picName);
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
	 * @return the confrimed
	 */
	public int getConfrimed() {
		return confirmed;
	}

	/**
	 * @param confrimed the confrimed to set
	 */
	public void setConfrimed(int confrimed) {
		this.confirmed = confrimed;
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
	 * @return the length
	 */
	public String getLength() {
		return length;
	}

	/**
	 * @param length the length to set
	 */
	public void setLength(String length) {
		this.length = length;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the type_oth
	 */
	public String getType_oth() {
		return type_oth;
	}

	/**
	 * @param type_oth the type_oth to set
	 */
	public void setType_oth(String type_oth) {
		this.type_oth = type_oth;
	}

	/**
	 * @return the snum
	 */
	public String getSnum() {
		return snum;
	}

	/**
	 * @param snum the snum to set
	 */
	public void setSnum(String snum) {
		this.snum = snum;
	}

	/**
	 * @return the scol
	 */
	public int getScol() {
		return scol;
	}

	/**
	 * @param scol the scol to set
	 */
	public void setScol(int scol) {
		this.scol = scol;
	}

	/**
	 * @return the nnum
	 */
	public String getNnum() {
		return nnum;
	}

	/**
	 * @param nnum the nnum to set
	 */
	public void setNnum(String nnum) {
		this.nnum = nnum;
	}

	/**
	 * @return the ncol
	 */
	public int getNcol() {
		return ncol;
	}

	/**
	 * @param ncol the ncol to set
	 */
	public void setNcol(int ncol) {
		this.ncol = ncol;
	}

	/**
	 * @return the inum
	 */
	public String getInum() {
		return inum;
	}

	/**
	 * @param inum the inum to set
	 */
	public void setInum(String inum) {
		this.inum = inum;
	}

	/**
	 * @return the icol
	 */
	public int getIcol() {
		return icol;
	}

	/**
	 * @param icol the icol to set
	 */
	public void setIcol(int icol) {
		this.icol = icol;
	}

	/**
	 * @return the tnum
	 */
	public String getTnum() {
		return tnum;
	}

	/**
	 * @param tnum the tnum to set
	 */
	public void setTnum(String tnum) {
		this.tnum = tnum;
	}

	/**
	 * @return the tcol
	 */
	public int getTcol() {
		return tcol;
	}

	/**
	 * @param tcol the tcol to set
	 */
	public void setTcol(int tcol) {
		this.tcol = tcol;
	}

	/**
	 * @return the sig
	 */
	public String getSig() {
		return sig;
	}

	/**
	 * @param sig the sig to set
	 */
	public void setSig(String sig) {
		this.sig = sig;
	}

	/**
	 * @return the isnum
	 */
	public String getIsnum() {
		return isnum;
	}

	/**
	 * @param isnum the isnum to set
	 */
	public void setIsnum(String isnum) {
		this.isnum = isnum;
	}

	/**
	 * @return the iscol
	 */
	public int getIscol() {
		return iscol;
	}

	/**
	 * @param iscol the iscol to set
	 */
	public void setIscol(int iscol) {
		this.iscol = iscol;
	}

	/**
	 * @return the sinum
	 */
	public String getSinum() {
		return sinum;
	}

	/**
	 * @param sinum the sinum to set
	 */
	public void setSinum(String sinum) {
		this.sinum = sinum;
	}

	/**
	 * @return the sicol
	 */
	public int getSicol() {
		return sicol;
	}

	/**
	 * @param sicol the sicol to set
	 */
	public void setSicol(int sicol) {
		this.sicol = sicol;
	}

	/**
	 * @return the stnum
	 */
	public String getStnum() {
		return stnum;
	}

	/**
	 * @param stnum the stnum to set
	 */
	public void setStnum(String stnum) {
		this.stnum = stnum;
	}

	/**
	 * @return the stcol
	 */
	public int getStcol() {
		return stcol;
	}

	/**
	 * @param stcol the stcol to set
	 */
	public void setStcol(int stcol) {
		this.stcol = stcol;
	}

	/**
	 * @return the itnum
	 */
	public String getItnum() {
		return itnum;
	}

	/**
	 * @param itnum the itnum to set
	 */
	public void setItnum(String itnum) {
		this.itnum = itnum;
	}

	/**
	 * @return the itcol
	 */
	public int getItcol() {
		return itcol;
	}

	/**
	 * @param itcol the itcol to set
	 */
	public void setItcol(int itcol) {
		this.itcol = itcol;
	}

	/**
	 * @return the snnum
	 */
	public String getSnnum() {
		return snnum;
	}

	/**
	 * @param snnum the snnum to set
	 */
	public void setSnnum(String snnum) {
		this.snnum = snnum;
	}

	/**
	 * @return the sncol
	 */
	public int getSncol() {
		return sncol;
	}

	/**
	 * @param sncol the sncol to set
	 */
	public void setSncol(int sncol) {
		this.sncol = sncol;
	}

	/**
	 * @return the mmnum
	 */
	public String getMmnum() {
		return mmnum;
	}

	/**
	 * @param mmnum the mmnum to set
	 */
	public void setMmnum(String mmnum) {
		this.mmnum = mmnum;
	}

	/**
	 * @return the mmcol
	 */
	public int getMmcol() {
		return mmcol;
	}

	/**
	 * @param mmcol the mmcol to set
	 */
	public void setMmcol(int mmcol) {
		this.mmcol = mmcol;
	}

	/**
	 * @return the smaxnum
	 */
	public String getSmaxnum() {
		return smaxnum;
	}

	/**
	 * @param smaxnum the smaxnum to set
	 */
	public void setSmaxnum(String smaxnum) {
		this.smaxnum = smaxnum;
	}

	/**
	 * @return the smaxcol
	 */
	public int getSmaxcol() {
		return smaxcol;
	}

	/**
	 * @param smaxcol the smaxcol to set
	 */
	public void setSmaxcol(int smaxcol) {
		this.smaxcol = smaxcol;
	}

	/**
	 * @return the imaxnum
	 */
	public String getImaxnum() {
		return imaxnum;
	}

	/**
	 * @param imaxnum the imaxnum to set
	 */
	public void setImaxnum(String imaxnum) {
		this.imaxnum = imaxnum;
	}

	/**
	 * @return the imaxcol
	 */
	public int getImaxcol() {
		return imaxcol;
	}

	/**
	 * @param imaxcol the imaxcol to set
	 */
	public void setImaxcol(int imaxcol) {
		this.imaxcol = imaxcol;
	}

	/**
	 * @return the savgnum
	 */
	public String getSavgnum() {
		return savgnum;
	}

	/**
	 * @param savgnum the savgnum to set
	 */
	public void setSavgnum(String savgnum) {
		this.savgnum = savgnum;
	}

	/**
	 * @return the savgcol
	 */
	public int getSavgcol() {
		return savgcol;
	}

	/**
	 * @param savgcol the savgcol to set
	 */
	public void setSavgcol(int savgcol) {
		this.savgcol = savgcol;
	}

	/**
	 * @return the iavgnum
	 */
	public String getIavgnum() {
		return iavgnum;
	}

	/**
	 * @param iavgnum the iavgnum to set
	 */
	public void setIavgnum(String iavgnum) {
		this.iavgnum = iavgnum;
	}

	/**
	 * @return the iavgcol
	 */
	public int getIavgcol() {
		return iavgcol;
	}

	/**
	 * @param iavgcol the iavgcol to set
	 */
	public void setIavgcol(int iavgcol) {
		this.iavgcol = iavgcol;
	}

	/**
	 * @return the atnum
	 */
	public String getAtnum() {
		return atnum;
	}

	/**
	 * @param atnum the atnum to set
	 */
	public void setAtnum(String atnum) {
		this.atnum = atnum;
	}

	/**
	 * @return the atcol
	 */
	public int getAtcol() {
		return atcol;
	}

	/**
	 * @param atcol the atcol to set
	 */
	public void setAtcol(int atcol) {
		this.atcol = atcol;
	}
}
