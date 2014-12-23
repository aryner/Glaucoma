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
public class OCTtest implements BaseTest {
	private int baseType;
	private int id;
	private int confirmed;
	private String pictureName;
	private int userID;
	private int adjudicatorID;
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
	private String eye;

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

	public OCTtest(int id, int confirmed, String pictureName, int userID, int adjudicatorID, String length,
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
		this.adjudicatorID = adjudicatorID;
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
		this.sig = sig.substring(0,sig.indexOf("|"));
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
		this.eye = sig.substring(sig.indexOf("|")+1, sig.length());
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

	public static OCTtest getSingle(String name, int id, int access) {
		String query;
		if(access == 0) {
			query = "SELECT * FROM OCTtest WHERE pictureName='"+name+"' AND userID="+id;
		}
		else {
			query = "SELECT * FROM OCTtest WHERE pictureName='"+name+"' AND adjudicatorID="+id;
		}
		return SQLCommands.queryOCTtest(query).get(0);
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

	public static int updateOCT(HttpServletRequest request, User user) {
		int result = 0;

		int uID = user.getID();
		String picName = request.getParameter("pictureName");
		OCTtest oct = null;
		oct = new OCTtest(picName);

		String attr = request.getParameter("length");
		oct.setLength(attr);
		attr = request.getParameter("type");
		oct.setType(Integer.parseInt(attr));
		attr = request.getParameter("type_oth");
		oct.setType_oth(attr);
		attr = request.getParameter("snum");
		oct.setSnum(attr);
		attr = request.getParameter("scol");
		oct.setScol(Integer.parseInt(attr));
		attr = request.getParameter("nnum");
		oct.setNnum(attr);
		attr = request.getParameter("ncol");
		oct.setNcol(Integer.parseInt(attr));
		attr = request.getParameter("inum");
		oct.setInum(attr);
		attr = request.getParameter("icol");
		oct.setIcol(Integer.parseInt(attr));
		attr = request.getParameter("tnum");
		oct.setTnum(attr);
		attr = request.getParameter("tcol");
		oct.setTcol(Integer.parseInt(attr));
		attr = request.getParameter("sig");
		oct.setSig(attr);
		attr = request.getParameter("isnum");
		oct.setIsnum(attr);
		attr = request.getParameter("iscol");
		oct.setIscol(Integer.parseInt(attr));
		attr = request.getParameter("sinum");
		oct.setSinum(attr);
		attr = request.getParameter("sicol");
		oct.setSicol(Integer.parseInt(attr));
		attr = request.getParameter("stnum");
		oct.setStnum(attr);
		attr = request.getParameter("stcol");
		oct.setStcol(Integer.parseInt(attr));
		attr = request.getParameter("itnum");
		oct.setItnum(attr);
		attr = request.getParameter("itcol");
		oct.setItcol(Integer.parseInt(attr));
		attr = request.getParameter("snnum");
		oct.setSnnum(attr);
		attr = request.getParameter("sncol");
		oct.setSncol(Integer.parseInt(attr));
		attr = request.getParameter("mmnum");
		oct.setMmnum(attr);
		attr = request.getParameter("mmcol");
		oct.setMmcol(Integer.parseInt(attr));
		attr = request.getParameter("smaxnum");
		oct.setSmaxnum(attr);
		attr = request.getParameter("smaxcol");
		oct.setSmaxcol(Integer.parseInt(attr));
		attr = request.getParameter("imaxnum");
		oct.setImaxnum(attr);
		attr = request.getParameter("imaxcol");
		oct.setImaxcol(Integer.parseInt(attr));
		attr = request.getParameter("savgnum");
		oct.setSavgnum(attr);
		attr = request.getParameter("savgcol");
		oct.setSavgcol(Integer.parseInt(attr));
		attr = request.getParameter("iavgnum");
		oct.setIavgnum(attr);
		attr = request.getParameter("iavgcol");
		oct.setIavgcol(Integer.parseInt(attr));
		attr = request.getParameter("atnum");
		oct.setAtnum(attr);
		attr = request.getParameter("atcol");
		oct.setAtcol(Integer.parseInt(attr));
		attr = request.getParameter("eye");
		oct.setEye(attr);

		String query = "UPDATE OCTtest SET oct_length='"+oct.getLength()+"', oct_type='"+oct.getType()+"', "+
				"oct_type_oth='"+oct.getType_oth()+"', oct_snum='"+oct.getSnum()+"', "+
				"oct_scol='"+oct.getScol()+"', oct_nnum='"+oct.getNnum()+"', oct_ncol='"+oct.getNcol()+"', "+
				"oct_inum='"+oct.getInum()+"', oct_icol='"+oct.getIcol()+"', oct_tnum='"+oct.getTnum()+"', "+
				"oct_tcol='"+oct.getTcol()+"', oct_sig='"+oct.getSig()+"|"+oct.getEye()+"', oct_isnum='"+oct.getIsnum()+"', "+
				"oct_iscol='"+oct.getIscol()+"', oct_sinum='"+oct.getSinum()+"', oct_sicol='"+oct.getSicol()+"', "+
				"oct_stnum='"+oct.getStnum()+"', oct_stcol='"+oct.getStcol()+"', oct_itnum='"+oct.getItnum()+"', "+
				"oct_itcol='"+oct.getItcol()+"', oct_snnum='"+oct.getSnnum()+"', oct_sncol='"+oct.getSncol()+"', "+
				"oct_mmnum='"+oct.getMmnum()+"', oct_mmcol='"+oct.getMmcol()+"', oct_smaxnum='"+oct.getSmaxnum()+"', "+
				"oct_smaxcol='"+oct.getSmaxcol()+"', oct_imaxnum='"+oct.getImaxnum()+"', oct_imaxcol='"+oct.getImaxcol()+"', "+
				"oct_savgnum='"+oct.getSavgnum()+"', oct_savgcol='"+oct.getSavgcol()+"', oct_iavgnum='"+oct.getIavgnum()+"', "+
				"oct_iavgcol='"+oct.getIavgcol()+"', oct_atnum='"+oct.getAtnum()+"', oct_atcol='"+oct.getAtcol()+"' ";

		if(user.getAccess() == 0) {
			query += " WHERE id='"+oct.getId()+"' AND userID='"+user.getID()+"'";
		} else if(user.getAccess() ==1) { 
			query += " WHERE pictureName='"+request.getParameter("pictureName")+"' AND adjudicatorID='"+user.getID()+"'";
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

		String attr = request.getParameter("length");
		oct.setLength(attr);
		attr = request.getParameter("type");
		oct.setType(Integer.parseInt(attr));
		attr = request.getParameter("type_oth");
		oct.setType_oth(attr);
		attr = request.getParameter("snum");
		oct.setSnum(attr);
		attr = request.getParameter("scol");
		oct.setScol(Integer.parseInt(attr));
		attr = request.getParameter("nnum");
		oct.setNnum(attr);
		attr = request.getParameter("ncol");
		oct.setNcol(Integer.parseInt(attr));
		attr = request.getParameter("inum");
		oct.setInum(attr);
		attr = request.getParameter("icol");
		oct.setIcol(Integer.parseInt(attr));
		attr = request.getParameter("tnum");
		oct.setTnum(attr);
		attr = request.getParameter("tcol");
		oct.setTcol(Integer.parseInt(attr));
		attr = request.getParameter("sig");
		oct.setSig(attr);
		attr = request.getParameter("isnum");
		oct.setIsnum(attr);
		attr = request.getParameter("iscol");
		oct.setIscol(Integer.parseInt(attr));
		attr = request.getParameter("sinum");
		oct.setSinum(attr);
		attr = request.getParameter("sicol");
		oct.setSicol(Integer.parseInt(attr));
		attr = request.getParameter("stnum");
		oct.setStnum(attr);
		attr = request.getParameter("stcol");
		oct.setStcol(Integer.parseInt(attr));
		attr = request.getParameter("itnum");
		oct.setItnum(attr);
		attr = request.getParameter("itcol");
		oct.setItcol(Integer.parseInt(attr));
		attr = request.getParameter("snnum");
		oct.setSnnum(attr);
		attr = request.getParameter("sncol");
		oct.setSncol(Integer.parseInt(attr));
		attr = request.getParameter("mmnum");
		oct.setMmnum(attr);
		attr = request.getParameter("mmcol");
		oct.setMmcol(Integer.parseInt(attr));
		attr = request.getParameter("smaxnum");
		oct.setSmaxnum(attr);
		attr = request.getParameter("smaxcol");
		oct.setSmaxcol(Integer.parseInt(attr));
		attr = request.getParameter("imaxnum");
		oct.setImaxnum(attr);
		attr = request.getParameter("imaxcol");
		oct.setImaxcol(Integer.parseInt(attr));
		attr = request.getParameter("savgnum");
		oct.setSavgnum(attr);
		attr = request.getParameter("savgcol");
		oct.setSavgcol(Integer.parseInt(attr));
		attr = request.getParameter("iavgnum");
		oct.setIavgnum(attr);
		attr = request.getParameter("iavgcol");
		oct.setIavgcol(Integer.parseInt(attr));
		attr = request.getParameter("atnum");
		oct.setAtnum(attr);
		attr = request.getParameter("atcol");
		oct.setAtcol(Integer.parseInt(attr));
		attr = request.getParameter("eye");
		oct.setEye(attr);

		String query = "UPDATE OCTtest SET oct_length='"+oct.getLength()+"', oct_type='"+oct.getType()+"', "+
				"oct_type_oth='"+oct.getType_oth()+"', oct_snum='"+oct.getSnum()+"', "+
				"oct_scol='"+oct.getScol()+"', oct_nnum='"+oct.getNnum()+"', oct_ncol='"+oct.getNcol()+"', "+
				"oct_inum='"+oct.getInum()+"', oct_icol='"+oct.getIcol()+"', oct_tnum='"+oct.getTnum()+"', "+
				"oct_tcol='"+oct.getTcol()+"', oct_sig='"+oct.getSig()+"|"+oct.getEye()+"', oct_isnum='"+oct.getIsnum()+"', "+
				"oct_iscol='"+oct.getIscol()+"', oct_sinum='"+oct.getSinum()+"', oct_sicol='"+oct.getSicol()+"', "+
				"oct_stnum='"+oct.getStnum()+"', oct_stcol='"+oct.getStcol()+"', oct_itnum='"+oct.getItnum()+"', "+
				"oct_itcol='"+oct.getItcol()+"', oct_snnum='"+oct.getSnnum()+"', oct_sncol='"+oct.getSncol()+"', "+
				"oct_mmnum='"+oct.getMmnum()+"', oct_mmcol='"+oct.getMmcol()+"', oct_smaxnum='"+oct.getSmaxnum()+"', "+
				"oct_smaxcol='"+oct.getSmaxcol()+"', oct_imaxnum='"+oct.getImaxnum()+"', oct_imaxcol='"+oct.getImaxcol()+"', "+
				"oct_savgnum='"+oct.getSavgnum()+"', oct_savgcol='"+oct.getSavgcol()+"', oct_iavgnum='"+oct.getIavgnum()+"', "+
				"oct_iavgcol='"+oct.getIavgcol()+"', oct_atnum='"+oct.getAtnum()+"', oct_atcol='"+oct.getAtcol()+"' ";

		if(user.getAccess() == 0) {
			query += " WHERE id='"+oct.getId()+"'";
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
		String query = "SELECT * FROM OCTtest WHERE pictureName='"+picName+"'";
		Vector<OCTtest> oct = SQLCommands.queryOCTtest(query);

		if(oct.size() > 1) {
			//get the ones that don't need adjudication
			query = "SELECT * FROM OCTtest GROUP BY pictureName, "+
				"oct_length, oct_type, oct_type_oth, oct_snum, oct_scol, " +
				"oct_nnum, oct_ncol, oct_inum, oct_icol, oct_tnum, oct_tcol, " +
				"oct_sig, oct_isnum, oct_iscol, oct_sinum, oct_sicol, oct_stnum, " +
				"oct_stcol, oct_itnum, oct_itcol, oct_snnum, oct_sncol, oct_mmnum, " +
				"oct_mmcol, oct_smaxnum, oct_smaxcol, oct_imaxnum, oct_imaxcol, " +
				"oct_savgnum, oct_savgcol, oct_iavgnum, oct_iavgcol, oct_atnum, oct_atcol " +
				"HAVING COUNT(*)=2";
			Vector<OCTtest> set = SQLCommands.queryOCTtest(query);
			//get the ones that need adjudication
			query = "SELECT * FROM OCTtest GROUP BY pictureName, "+
				"oct_length, oct_type, oct_type_oth, oct_snum, oct_scol, " +
				"oct_nnum, oct_ncol, oct_inum, oct_icol, oct_tnum, oct_tcol, " +
				"oct_sig, oct_isnum, oct_iscol, oct_sinum, oct_sicol, oct_stnum, " +
				"oct_stcol, oct_itnum, oct_itcol, oct_snnum, oct_sncol, oct_mmnum, " +
				"oct_mmcol, oct_smaxnum, oct_smaxcol, oct_imaxnum, oct_imaxcol, " +
				"oct_savgnum, oct_savgcol, oct_iavgnum, oct_iavgcol, oct_atnum, oct_atcol " +
				"HAVING COUNT(*)=1";
			Vector<OCTtest> notSet = SQLCommands.queryOCTtest(query);

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
			query = "UPDATE OCTtest SET confirmed=2";
			query += " WHERE pictureName='"+picName+"'";
			if(set.size() > 0) {
				SQLCommands.update(query);
			}

			//update the ones that need confirming
			query = "UPDATE OCTtest SET confirmed=1 WHERE pictureName='"+picName+"'";
			if(notSet.size() > 0) {
				SQLCommands.update(query);
			}
		}
	}

	public static Vector<String> getUngradedNames() {
		Vector<String> result = new Vector<String>();
		String query = "SELECT * FROM picture WHERE name NOT IN (SELECT pictureName FROM OCTtest) AND type='OCT'";
		Vector<Picture> pictures = SQLCommands.queryPictures(query);

		for(int i=0; i<pictures.size(); i++) {
			result.add("OCT - " + pictures.get(i).getName());
		}

		return result;
	}

	public static Vector<String> getGradedOnceNames() {
		Vector<String> result = new Vector<String>();
		String query = "SELECT * FROM OCTtest GROUP BY pictureName HAVING COUNT(*)=1";
		Vector<OCTtest> oct = SQLCommands.queryOCTtest(query);

		for(int i=0; i<oct.size(); i++) {
			result.add("OCT - " + oct.get(i).getPictureName());
		}

		return result;
	}
	
	public static Vector<String> getNeedsAdjudication() {
		Vector<String> result = new Vector<String>();
		String query = "SELECT DISTINCT pictureName FROM OCTtest WHERE confirmed='1'";
		Vector<String> oct = SQLCommands.queryNames(query);

		for(int i=0; i<oct.size(); i++) {
			result.add("OCT - " + oct.get(i));
		}

		return result;
	}

	public static Vector<String> getAdjudicated() {
		Vector<String> result = new Vector<String>();
		String query = "SELECT DISTINCT pictureName FROM OCTtest WHERE confirmed='2'";
		Vector<String> oct = SQLCommands.queryNames(query);

		for(int i=0; i<oct.size(); i++) {
			result.add("OCT - " + oct.get(i));
		}

		return result;
	}
	public static Vector<BaseTest> getBaseTest(int id) {
		String query = "SELECT * FROM OCTtest WHERE (userID="+id+" OR adjudicatorID="+id+")";
		return SQLCommands.queryBaseTest(query,  BaseTest.OCT);
	}

	public static Vector<String> getCSVLines() {
		Vector<String> result = new Vector<String>();
		String query = "SELECT * FROM OCTtest ORDER BY pictureName";
		Vector<OCTtest> octs = SQLCommands.queryOCTtest(query);
		
		String currLine = "confirmed, picture, userID, adjudicatorID, oct_length, oct_type, oct_type_oth, "+
				  "oct_snum, oct_scol, oct_nnum, oct_ncol, oct_inum, oct_icol, oct_tnum, oct_tcol, "+
				  "oct_sig, oct_isnum, oct_iscol, oct_sinum, oct_sicol, oct_stnum, oct_stcol, oct_itnum, "+
				  "oct_itcol, oct_snnum, oct_sncol, oct_mmnum, oct_mmcol, oct_smaxnum, oct_smaxcol, "+
				  "imaxnum, imaxcol, oct_savgnum, oct_savgcol, oct_iavgnum, oct_iavgcol, oct_atnum, oct_atcol, oct_eye";
		result.add(currLine);

		for(OCTtest oct : octs) {
			currLine = oct.getConfirmed()+", "+oct.getPictureName()+", "+oct.getUserID()+", "+oct.getAdjudicatorID()+", "+
				   oct.getLength()+", "+oct.getType()+", "+oct.getType_oth()+", "+oct.getSnum()+", "+oct.getScol()+", "+
				   oct.getNnum()+", "+oct.getNcol()+", "+oct.getInum()+", "+oct.getIcol()+", "+oct.getTnum()+", "+
				   oct.getTcol()+", "+oct.getSig()+", "+oct.getIsnum()+", "+oct.getIscol()+", "+oct.getSinum()+", "+
				   oct.getSicol()+", "+oct.getStnum()+", "+oct.getStcol()+", "+oct.getItnum()+", "+oct.getItcol()+", "+
				   oct.getSnnum()+", "+oct.getSncol()+", "+oct.getMmnum()+", "+oct.getMmcol()+", "+oct.getSmaxnum()+", "+
				   oct.getSmaxcol()+", "+oct.getImaxnum()+", "+oct.getImaxcol()+", "+oct.getSavgnum()+", "+oct.getSavgcol()+", "+
				   oct.getIavgnum()+", "+oct.getIavgcol()+", "+oct.getAtnum()+", "+oct.getAtcol()+", "+oct.getEye();
			result.add(currLine);
		}

		return result;
	}

	public static Vector<String> getCSVFinishedLines() {
		Vector<String> result = new Vector<String>();
		String query = "SELECT * FROM OCTtest WHERE confirmed=2 GROUP BY pictureName";
		Vector<OCTtest> octs = SQLCommands.queryOCTtest(query);
		
		String currLine = "picture, adjudicatorID, oct_length, oct_type, oct_type_oth, "+
				  "oct_snum, oct_scol, oct_nnum, oct_ncol, oct_inum, oct_icol, oct_tnum, oct_tcol, "+
				  "oct_sig, oct_isnum, oct_iscol, oct_sinum, oct_sicol, oct_stnum, oct_stcol, oct_itnum, "+
				  "oct_itcol, oct_snnum, oct_sncol, oct_mmnum, oct_mmcol, oct_smaxnum, oct_smaxcol, "+
				  "imaxnum, imaxcol, oct_savgnum, oct_savgcol, oct_iavgnum, oct_iavgcol, oct_atnum, oct_atcol";
		result.add(currLine);

		for(OCTtest oct : octs) {
			currLine = oct.getPictureName()+", "+oct.getAdjudicatorID()+", "+
				   oct.getLength()+", "+oct.getType()+", "+oct.getType_oth()+", "+oct.getSnum()+", "+oct.getScol()+", "+
				   oct.getNnum()+", "+oct.getNcol()+", "+oct.getInum()+", "+oct.getIcol()+", "+oct.getTnum()+", "+
				   oct.getTcol()+", "+oct.getSig()+", "+oct.getIsnum()+", "+oct.getIscol()+", "+oct.getSinum()+", "+
				   oct.getSicol()+", "+oct.getStnum()+", "+oct.getStcol()+", "+oct.getItnum()+", "+oct.getItcol()+", "+
				   oct.getSnnum()+", "+oct.getSncol()+", "+oct.getMmnum()+", "+oct.getMmcol()+", "+oct.getSmaxnum()+", "+
				   oct.getSmaxcol()+", "+oct.getImaxnum()+", "+oct.getImaxcol()+", "+oct.getSavgnum()+", "+oct.getSavgcol()+", "+
				   oct.getIavgnum()+", "+oct.getIavgcol()+", "+oct.getAtnum()+", "+oct.getAtcol();
			result.add(currLine);
		}

		return result;
	}

	public int getBaseType() {
		return baseType;
	}
	public void setBaseType(int type) {
		baseType = type;
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
	 * @param confrimed the confirmed to set
	 */
	public void setConfirmed(int confrimed) {
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

	/**
	 * @return the eye
	 */
	public String getEye() {
		return eye;
	}

	/**
	 * @param eye the eye to set
	 */
	public void setEye(String eye) {
		this.eye = eye;
	}
}
