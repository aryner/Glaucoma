/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import utilities.*;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import java.io.*;

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
	private String snum_os;
	private int scol_os;
	private String nnum_os;
	private int ncol_os;
	private String inum_os;
	private int icol_os;
	private String tnum_os;
	private int tcol_os;
	private String sig_os;
	private String isnum_os;
	private int iscol_os;
	private String sinum_os;
	private int sicol_os;
	private String stnum_os;
	private int stcol_os;
	private String itnum_os;
	private int itcol_os;
	private String snnum_os;
	private int sncol_os;
	private String mmnum_os;
	private int mmcol_os;
	private String smaxnum_os;
	private int smaxcol_os;
	private String imaxnum_os;
	private int imaxcol_os;
	private String savgnum_os;
	private int savgcol_os;
	private String iavgnum_os;
	private int iavgcol_os;
	private String atnum_os;
	private int atcol_os;

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
		       int type, String type_oth, String snum, String snum_os, int scol, int scol_os, String nnum, String nnum_os, int ncol,
		       int ncol_os, String inum, String inum_os, int icol, int icol_os, String tnum, String tnum_os, int tcol, 
		       int tcol_os, String sig, String sig_os, String isnum, String isnum_os, int iscol, int iscol_os,
		       String sinum, String sinum_os, int sicol, int sicol_os, String stnum, String stnum_os, int stcol, 
		       int stcol_os, String itnum, String itnum_os, int itcol, int itcol_os, String snnum, String snnum_os,
		       int sncol, int sncol_os, String mmnum, String mmnum_os, int mmcol, int mmcol_os, String smaxnum, String smaxnum_os,
		       int smaxcol, int smaxcol_os, String imaxnum, String imaxnum_os, int imaxcol, int imaxcol_os, String savgnum, 
		       String savgnum_os, int savgcol, int savgcol_os, String iavgnum, 
		       String iavgnum_os, int iavgcol, int iavgcol_os, String atnum, String atnum_os, int atcol, int atcol_os)
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
		this.snum_os = snum_os;
		this.scol = scol;
		this.scol_os = scol_os;
		this.nnum = nnum;
		this.nnum_os = nnum_os;
		this.ncol = ncol;
		this.ncol_os = ncol_os;
		this.inum = inum; 
		this.inum_os = inum_os; 
		this.icol = icol;
		this.icol_os = icol_os;
		this.tnum = tnum;
		this.tnum_os = tnum_os;
		this.tcol = tcol;
		this.tcol_os = tcol_os;
		this.sig = sig;
		this.sig_os = sig_os;
		this.isnum = isnum;
		this.isnum_os = isnum_os;
		this.iscol = iscol;
		this.iscol_os = iscol_os;
		this.sinum = sinum; 
		this.sinum_os = sinum_os; 
		this.sicol = sicol;
		this.sicol_os = sicol_os;
		this.stnum = stnum;
		this.stnum_os = stnum_os;
		this.stcol  = stcol;
		this.stcol_os  = stcol_os;
		this.itnum = itnum;
		this.itnum_os = itnum_os;
		this.itcol = itcol;
		this.itcol_os = itcol_os;
		this.snnum = snnum;
		this.snnum_os = snnum_os;
		this.sncol = sncol;
		this.sncol_os = sncol_os;
		this.mmnum = mmnum;
		this.mmnum_os = mmnum_os;
		this.mmcol = mmcol;
		this.mmcol_os = mmcol_os;
		this.smaxnum = smaxnum;
		this.smaxnum_os = smaxnum_os;
		this.smaxcol = smaxcol;
		this.smaxcol_os = smaxcol_os;
		this.imaxnum = imaxnum;
		this.imaxnum_os = imaxnum_os;
		this.imaxcol = imaxcol;
		this.imaxcol_os = imaxcol_os;
		this.savgnum = savgnum;
		this.savgnum_os = savgnum_os;
		this.savgcol = savgcol;
		this.savgcol_os = savgcol_os;
		this.iavgnum = iavgnum;
		this.iavgnum_os = iavgnum_os;
		this.iavgcol = iavgcol;
		this.iavgcol_os = iavgcol_os;
		this.atnum = atnum;
		this.atnum_os = atnum_os;
		this.atcol = atcol;
		this.atcol_os = atcol_os;
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

		attr = request.getParameter("snum_os");
		oct.setSnum_os(attr);
		attr = request.getParameter("scol_os");
		oct.setScol_os(Integer.parseInt(attr));
		attr = request.getParameter("nnum_os");
		oct.setNnum_os(attr);
		attr = request.getParameter("ncol_os");
		oct.setNcol_os(Integer.parseInt(attr));
		attr = request.getParameter("inum_os");
		oct.setInum_os(attr);
		attr = request.getParameter("icol_os");
		oct.setIcol_os(Integer.parseInt(attr));
		attr = request.getParameter("tnum_os");
		oct.setTnum_os(attr);
		attr = request.getParameter("tcol_os");
		oct.setTcol_os(Integer.parseInt(attr));
		attr = request.getParameter("sig_os");
		oct.setSig_os(attr);
		attr = request.getParameter("isnum_os");
		oct.setIsnum_os(attr);
		attr = request.getParameter("iscol_os");
		oct.setIscol_os(Integer.parseInt(attr));
		attr = request.getParameter("sinum_os");
		oct.setSinum_os(attr);
		attr = request.getParameter("sicol_os");
		oct.setSicol_os(Integer.parseInt(attr));
		attr = request.getParameter("stnum_os");
		oct.setStnum_os(attr);
		attr = request.getParameter("stcol_os");
		oct.setStcol_os(Integer.parseInt(attr));
		attr = request.getParameter("itnum_os");
		oct.setItnum_os(attr);
		attr = request.getParameter("itcol_os");
		oct.setItcol_os(Integer.parseInt(attr));
		attr = request.getParameter("snnum_os");
		oct.setSnnum_os(attr);
		attr = request.getParameter("sncol_os");
		oct.setSncol_os(Integer.parseInt(attr));
		attr = request.getParameter("mmnum_os");
		oct.setMmnum_os(attr);
		attr = request.getParameter("mmcol_os");
		oct.setMmcol_os(Integer.parseInt(attr));
		attr = request.getParameter("smaxnum_os");
		oct.setSmaxnum_os(attr);
		attr = request.getParameter("smaxcol_os");
		oct.setSmaxcol_os(Integer.parseInt(attr));
		attr = request.getParameter("imaxnum_os");
		oct.setImaxnum_os(attr);
		attr = request.getParameter("imaxcol_os");
		oct.setImaxcol_os(Integer.parseInt(attr));
		attr = request.getParameter("savgnum_os");
		oct.setSavgnum_os(attr);
		attr = request.getParameter("savgcol_os");
		oct.setSavgcol_os(Integer.parseInt(attr));
		attr = request.getParameter("iavgnum_os");
		oct.setIavgnum_os(attr);
		attr = request.getParameter("iavgcol_os");
		oct.setIavgcol_os(Integer.parseInt(attr));
		attr = request.getParameter("atnum_os");
		oct.setAtnum_os(attr);
		attr = request.getParameter("atcol_os");
		oct.setAtcol_os(Integer.parseInt(attr));

		String query = "UPDATE OCTtest SET oct_length='"+oct.getLength()+"', oct_type='"+oct.getType()+"', "+
				"oct_type_oth='"+oct.getType_oth()+"', oct_snum='"+oct.getSnum()+"', "+
				"oct_scol='"+oct.getScol()+"', oct_nnum='"+oct.getNnum()+"', oct_ncol='"+oct.getNcol()+"', "+
				"oct_inum='"+oct.getInum()+"', oct_icol='"+oct.getIcol()+"', oct_tnum='"+oct.getTnum()+"', "+
				"oct_tcol='"+oct.getTcol()+"', oct_sig='"+oct.getSig()+"', oct_isnum='"+oct.getIsnum()+"', "+
				"oct_iscol='"+oct.getIscol()+"', oct_sinum='"+oct.getSinum()+"', oct_sicol='"+oct.getSicol()+"', "+
				"oct_stnum='"+oct.getStnum()+"', oct_stcol='"+oct.getStcol()+"', oct_itnum='"+oct.getItnum()+"', "+
				"oct_itcol='"+oct.getItcol()+"', oct_snnum='"+oct.getSnnum()+"', oct_sncol='"+oct.getSncol()+"', "+
				"oct_mmnum='"+oct.getMmnum()+"', oct_mmcol='"+oct.getMmcol()+"', oct_smaxnum='"+oct.getSmaxnum()+"', "+
				"oct_smaxcol='"+oct.getSmaxcol()+"', oct_imaxnum='"+oct.getImaxnum()+"', oct_imaxcol='"+oct.getImaxcol()+"', "+
				"oct_savgnum='"+oct.getSavgnum()+"', oct_savgcol='"+oct.getSavgcol()+"', oct_iavgnum='"+oct.getIavgnum()+"', "+
				"oct_iavgcol='"+oct.getIavgcol()+"', oct_atnum='"+oct.getAtnum()+"', oct_atcol='"+oct.getAtcol()+"', "+
				"oct_snum_os='"+oct.getSnum_os()+"', "+
				"oct_scol_os='"+oct.getScol_os()+"', oct_nnum_os='"+oct.getNnum_os()+"', oct_ncol_os='"+oct.getNcol_os()+"', "+
				"oct_inum_os='"+oct.getInum_os()+"', oct_icol_os='"+oct.getIcol_os()+"', oct_tnum_os='"+oct.getTnum_os()+"', "+
				"oct_tcol_os='"+oct.getTcol_os()+"', oct_sig_os='"+oct.getSig_os()+"', oct_isnum_os='"+oct.getIsnum_os()+"', "+
				"oct_iscol_os='"+oct.getIscol_os()+"', oct_sinum_os='"+oct.getSinum_os()+"', oct_sicol_os='"+oct.getSicol_os()+"', "+
				"oct_stnum_os='"+oct.getStnum_os()+"', oct_stcol_os='"+oct.getStcol_os()+"', oct_itnum_os='"+oct.getItnum_os()+"', "+
				"oct_itcol_os='"+oct.getItcol_os()+"', oct_snnum_os='"+oct.getSnnum_os()+"', oct_sncol_os='"+oct.getSncol_os()+"', "+
				"oct_mmnum_os='"+oct.getMmnum_os()+"', oct_mmcol_os='"+oct.getMmcol_os()+"', oct_smaxnum_os='"+oct.getSmaxnum_os()+"', "+
				"oct_smaxcol_os='"+oct.getSmaxcol_os()+"', oct_imaxnum_os='"+oct.getImaxnum_os()+"', oct_imaxcol_os='"+oct.getImaxcol_os()+"', "+
				"oct_savgnum_os='"+oct.getSavgnum_os()+"', oct_savgcol_os='"+oct.getSavgcol_os()+"', oct_iavgnum_os='"+oct.getIavgnum_os()+"', "+
				"oct_iavgcol_os='"+oct.getIavgcol_os()+"', oct_atnum_os='"+oct.getAtnum_os()+"', oct_atcol_os='"+oct.getAtcol_os()+"' ";

		if(user.getAccess() == 0) {
			query += " WHERE pictureName='"+oct.getPictureName()+"' AND userID='"+user.getID()+"'";
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

		attr = request.getParameter("snum_os");
		oct.setSnum_os(attr);
		attr = request.getParameter("scol_os");
		oct.setScol_os(Integer.parseInt(attr));
		attr = request.getParameter("nnum_os");
		oct.setNnum_os(attr);
		attr = request.getParameter("ncol_os");
		oct.setNcol_os(Integer.parseInt(attr));
		attr = request.getParameter("inum_os");
		oct.setInum_os(attr);
		attr = request.getParameter("icol_os");
		oct.setIcol_os(Integer.parseInt(attr));
		attr = request.getParameter("tnum_os");
		oct.setTnum_os(attr);
		attr = request.getParameter("tcol_os");
		oct.setTcol_os(Integer.parseInt(attr));
		attr = request.getParameter("sig_os");
		oct.setSig_os(attr);
		attr = request.getParameter("isnum_os");
		oct.setIsnum_os(attr);
		attr = request.getParameter("iscol_os");
		oct.setIscol_os(Integer.parseInt(attr));
		attr = request.getParameter("sinum_os");
		oct.setSinum_os(attr);
		attr = request.getParameter("sicol_os");
		oct.setSicol_os(Integer.parseInt(attr));
		attr = request.getParameter("stnum_os");
		oct.setStnum_os(attr);
		attr = request.getParameter("stcol_os");
		oct.setStcol_os(Integer.parseInt(attr));
		attr = request.getParameter("itnum_os");
		oct.setItnum_os(attr);
		attr = request.getParameter("itcol_os");
		oct.setItcol_os(Integer.parseInt(attr));
		attr = request.getParameter("snnum_os");
		oct.setSnnum_os(attr);
		attr = request.getParameter("sncol_os");
		oct.setSncol_os(Integer.parseInt(attr));
		attr = request.getParameter("mmnum_os");
		oct.setMmnum_os(attr);
		attr = request.getParameter("mmcol_os");
		oct.setMmcol_os(Integer.parseInt(attr));
		attr = request.getParameter("smaxnum_os");
		oct.setSmaxnum_os(attr);
		attr = request.getParameter("smaxcol_os");
		oct.setSmaxcol_os(Integer.parseInt(attr));
		attr = request.getParameter("imaxnum_os");
		oct.setImaxnum_os(attr);
		attr = request.getParameter("imaxcol_os");
		oct.setImaxcol_os(Integer.parseInt(attr));
		attr = request.getParameter("savgnum_os");
		oct.setSavgnum_os(attr);
		attr = request.getParameter("savgcol_os");
		oct.setSavgcol_os(Integer.parseInt(attr));
		attr = request.getParameter("iavgnum_os");
		oct.setIavgnum_os(attr);
		attr = request.getParameter("iavgcol_os");
		oct.setIavgcol_os(Integer.parseInt(attr));
		attr = request.getParameter("atnum_os");
		oct.setAtnum_os(attr);
		attr = request.getParameter("atcol_os");
		oct.setAtcol_os(Integer.parseInt(attr));

		String query = "UPDATE OCTtest SET oct_length='"+oct.getLength()+"', oct_type='"+oct.getType()+"', "+
				"oct_type_oth='"+oct.getType_oth()+"', oct_snum='"+oct.getSnum()+"', "+
				"oct_scol='"+oct.getScol()+"', oct_nnum='"+oct.getNnum()+"', oct_ncol='"+oct.getNcol()+"', "+
				"oct_inum='"+oct.getInum()+"', oct_icol='"+oct.getIcol()+"', oct_tnum='"+oct.getTnum()+"', "+
				"oct_tcol='"+oct.getTcol()+"', oct_sig='"+oct.getSig()+"', oct_isnum='"+oct.getIsnum()+"', "+
				"oct_iscol='"+oct.getIscol()+"', oct_sinum='"+oct.getSinum()+"', oct_sicol='"+oct.getSicol()+"', "+
				"oct_stnum='"+oct.getStnum()+"', oct_stcol='"+oct.getStcol()+"', oct_itnum='"+oct.getItnum()+"', "+
				"oct_itcol='"+oct.getItcol()+"', oct_snnum='"+oct.getSnnum()+"', oct_sncol='"+oct.getSncol()+"', "+
				"oct_mmnum='"+oct.getMmnum()+"', oct_mmcol='"+oct.getMmcol()+"', oct_smaxnum='"+oct.getSmaxnum()+"', "+
				"oct_smaxcol='"+oct.getSmaxcol()+"', oct_imaxnum='"+oct.getImaxnum()+"', oct_imaxcol='"+oct.getImaxcol()+"', "+
				"oct_savgnum='"+oct.getSavgnum()+"', oct_savgcol='"+oct.getSavgcol()+"', oct_iavgnum='"+oct.getIavgnum()+"', "+
				"oct_iavgcol='"+oct.getIavgcol()+"', oct_atnum='"+oct.getAtnum()+"', oct_atcol='"+oct.getAtcol()+"', "+
				"oct_snum_os='"+oct.getSnum_os()+"', "+
				"oct_scol_os='"+oct.getScol_os()+"', oct_nnum_os='"+oct.getNnum_os()+"', oct_ncol_os='"+oct.getNcol_os()+"', "+
				"oct_inum_os='"+oct.getInum_os()+"', oct_icol_os='"+oct.getIcol_os()+"', oct_tnum_os='"+oct.getTnum_os()+"', "+
				"oct_tcol_os='"+oct.getTcol_os()+"', oct_sig_os='"+oct.getSig_os()+"', oct_isnum_os='"+oct.getIsnum_os()+"', "+
				"oct_iscol_os='"+oct.getIscol_os()+"', oct_sinum_os='"+oct.getSinum_os()+"', oct_sicol_os='"+oct.getSicol_os()+"', "+
				"oct_stnum_os='"+oct.getStnum_os()+"', oct_stcol_os='"+oct.getStcol_os()+"', oct_itnum_os='"+oct.getItnum_os()+"', "+
				"oct_itcol_os='"+oct.getItcol_os()+"', oct_snnum_os='"+oct.getSnnum_os()+"', oct_sncol_os='"+oct.getSncol_os()+"', "+
				"oct_mmnum_os='"+oct.getMmnum_os()+"', oct_mmcol_os='"+oct.getMmcol_os()+"', oct_smaxnum_os='"+oct.getSmaxnum_os()+"', "+
				"oct_smaxcol_os='"+oct.getSmaxcol_os()+"', oct_imaxnum_os='"+oct.getImaxnum_os()+"', oct_imaxcol_os='"+oct.getImaxcol_os()+"', "+
				"oct_savgnum_os='"+oct.getSavgnum_os()+"', oct_savgcol_os='"+oct.getSavgcol_os()+"', oct_iavgnum_os='"+oct.getIavgnum_os()+"', "+
				"oct_iavgcol_os='"+oct.getIavgcol_os()+"', oct_atnum_os='"+oct.getAtnum_os()+"', oct_atcol_os='"+oct.getAtcol_os()+"' ";

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
				"oct_length, oct_type, oct_type_oth, "+
				"oct_snum, oct_scol, " +
				"oct_nnum, oct_ncol, oct_inum, oct_icol, oct_tnum, oct_tcol, " +
				"oct_sig, oct_isnum, oct_iscol, oct_sinum, oct_sicol, oct_stnum, " +
				"oct_stcol, oct_itnum, oct_itcol, oct_snnum, oct_sncol, oct_mmnum, " +
				"oct_mmcol, oct_smaxnum, oct_smaxcol, oct_imaxnum, oct_imaxcol, " +
				"oct_savgnum, oct_savgcol, oct_iavgnum, oct_iavgcol, oct_atnum, oct_atcol, " +
				"oct_snum_os, oct_scol_os, " +
				"oct_nnum_os, oct_ncol_os, oct_inum_os, oct_icol_os, oct_tnum_os, oct_tcol_os, " +
				"oct_sig_os, oct_isnum_os, oct_iscol_os, oct_sinum_os, oct_sicol_os, oct_stnum_os, " +
				"oct_stcol_os, oct_itnum_os, oct_itcol_os, oct_snnum_os, oct_sncol_os, oct_mmnum_os, " +
				"oct_mmcol_os, oct_smaxnum_os, oct_smaxcol_os, oct_imaxnum_os, oct_imaxcol_os, " +
				"oct_savgnum_os, oct_savgcol_os, oct_iavgnum_os, oct_iavgcol_os, oct_atnum_os, oct_atcol_os " +
				"HAVING COUNT(*)=2";
			Vector<OCTtest> set = SQLCommands.queryOCTtest(query);
			//get the ones that need adjudication
			query = "SELECT * FROM OCTtest GROUP BY pictureName, "+
				"oct_length, oct_type, oct_type_oth, oct_snum, oct_scol, " +
				"oct_nnum, oct_ncol, oct_inum, oct_icol, oct_tnum, oct_tcol, " +
				"oct_sig, oct_isnum, oct_iscol, oct_sinum, oct_sicol, oct_stnum, " +
				"oct_stcol, oct_itnum, oct_itcol, oct_snnum, oct_sncol, oct_mmnum, " +
				"oct_mmcol, oct_smaxnum, oct_smaxcol, oct_imaxnum, oct_imaxcol, " +
				"oct_savgnum, oct_savgcol, oct_iavgnum, oct_iavgcol, oct_atnum, oct_atcol, " +
				"oct_snum_os, oct_scol_os, " +
				"oct_nnum_os, oct_ncol_os, oct_inum_os, oct_icol_os, oct_tnum_os, oct_tcol_os, " +
				"oct_sig_os, oct_isnum_os, oct_iscol_os, oct_sinum_os, oct_sicol_os, oct_stnum_os, " +
				"oct_stcol_os, oct_itnum_os, oct_itcol_os, oct_snnum_os, oct_sncol_os, oct_mmnum_os, " +
				"oct_mmcol_os, oct_smaxnum_os, oct_smaxcol_os, oct_imaxnum_os, oct_imaxcol_os, " +
				"oct_savgnum_os, oct_savgcol_os, oct_iavgnum_os, oct_iavgcol_os, oct_atnum_os, oct_atcol_os " +
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
		Vector<OCTtest> octsAll = SQLCommands.queryOCTtest(query);
		Vector<OCTtest> octs = new Vector<OCTtest>();

		for(int i=0; i<octsAll.size(); i++) {
			if(i<(octsAll.size()-1) && (octsAll.get(i).getConfirmed() == 2)) {
				i++;
			}
			octs.add(octsAll.get(i));
		}
		
		String currLine = "confirmed, picture, userID, adjudicatorID, "+
				  "oct_length, oct_type, oct_type_oth, "+
				  "oct_snum_od, oct_scol_od, oct_nnum_od, oct_ncol_od, oct_inum_od, oct_icol_od, oct_tnum_od, oct_tcol_od, "+
				  "oct_sig_od, oct_isnum_od, oct_iscol_od, oct_sinum_od, oct_sicol_od, oct_stnum_od, oct_stcol_od, oct_itnum_od, "+
				  "oct_itcol_od, oct_snnum_od, oct_sncol_od, oct_mmnum_od, oct_mmcol_od, oct_smaxnum_od, oct_smaxcol_od, "+
				  "oct_imaxnum_od, oct_imaxcol_od, oct_savgnum_od, oct_savgcol_od, oct_iavgnum_od, oct_iavgcol_od, oct_atnum_od, oct_atcol_od, "+
				  "oct_snum_os, oct_scol_os, oct_nnum_os, oct_ncol_os, oct_inum_os, oct_icol_os, oct_tnum_os, oct_tcol_os, "+
				  "oct_sig_os, oct_isnum_os, oct_iscol_os, oct_sinum_os, oct_sicol_os, oct_stnum_os, oct_stcol_os, oct_itnum_os, "+
				  "oct_itcol_os, oct_snnum_os, oct_sncol_os, oct_mmnum_os, oct_mmcol_os, oct_smaxnum_os, oct_smaxcol_os, "+
				  "imaxnum_os, imaxcol_os, oct_savgnum_os, oct_savgcol_os, oct_iavgnum_os, oct_iavgcol_os, oct_atnum_os, oct_atcol_os ";
		result.add(currLine);

		for(OCTtest oct : octs) {
			currLine = oct.getConfirmed()+", "+oct.getPictureName()+", "+oct.getUserID()+", "+oct.getAdjudicatorID()+", "+
				   oct.getLength()+", "+oct.getType()+", "+oct.getType_oth()+", "+oct.getSnum()+", "+oct.getScol()+", "+
				   oct.getNnum()+", "+oct.getNcol()+", "+oct.getInum()+", "+oct.getIcol()+", "+oct.getTnum()+", "+
				   oct.getTcol()+", "+oct.getSig()+", "+oct.getIsnum()+", "+oct.getIscol()+", "+oct.getSinum()+", "+
				   oct.getSicol()+", "+oct.getStnum()+", "+oct.getStcol()+", "+oct.getItnum()+", "+oct.getItcol()+", "+
				   oct.getSnnum()+", "+oct.getSncol()+", "+oct.getMmnum()+", "+oct.getMmcol()+", "+oct.getSmaxnum()+", "+
				   oct.getSmaxcol()+", "+oct.getImaxnum()+", "+oct.getImaxcol()+", "+oct.getSavgnum()+", "+oct.getSavgcol()+", "+
				   oct.getIavgnum()+", "+oct.getIavgcol()+", "+oct.getAtnum()+", "+oct.getAtcol()+", "+
				   oct.getSnum_os()+", "+oct.getScol_os()+", "+
				   oct.getNnum_os()+", "+oct.getNcol_os()+", "+oct.getInum_os()+", "+oct.getIcol_os()+", "+oct.getTnum_os()+", "+
				   oct.getTcol_os()+", "+oct.getSig_os()+", "+oct.getIsnum_os()+", "+oct.getIscol_os()+", "+oct.getSinum_os()+", "+
				   oct.getSicol_os()+", "+oct.getStnum_os()+", "+oct.getStcol_os()+", "+oct.getItnum_os()+", "+oct.getItcol_os()+", "+
				   oct.getSnnum_os()+", "+oct.getSncol_os()+", "+oct.getMmnum_os()+", "+oct.getMmcol_os()+", "+oct.getSmaxnum_os()+", "+
				   oct.getSmaxcol_os()+", "+oct.getImaxnum_os()+", "+oct.getImaxcol_os()+", "+oct.getSavgnum_os()+", "+oct.getSavgcol_os()+", "+
				   oct.getIavgnum_os()+", "+oct.getIavgcol_os()+", "+oct.getAtnum_os()+", "+oct.getAtcol_os();
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

	public static void readCSV(String fileName) {
		String query = "SELECT * FROM OCTtest";

		Vector<OCTtest> alreadyHere = SQLCommands.queryOCTtest(query);
		ArrayList<String> newLines = new ArrayList<String>();
		ArrayList<String> updateLines = new ArrayList<String>();
		ArrayList<String> toBeReplaced = new ArrayList<String>();
		File file = null;
		FileReader reader = null;
		BufferedReader fileReader = null;

		try {
			file = new File(".."+slash+"webapps"+slash+"Glaucoma"+slash+"temp"+slash+fileName);
			reader = new FileReader(file);
			fileReader = new BufferedReader(reader);

			String line = fileReader.readLine();
			int index;
			if(line != null && line.length() > 0) {
				line = fileReader.readLine();
			}

			while(line != null && line.length() > 0) {
				line = Tools.formatCSVLine(line);
				Vector<String> processedLine = Tools.processCSVLine(line);

				boolean duplicate = false;
				OCTtest oldTest = null;
				for(int i=0; i<alreadyHere.size() && !duplicate; i++) {
					if(processedLine.get(Tools.CSVPICNAME).equals(alreadyHere.get(i).getPictureName())) {
						oldTest = alreadyHere.get(i);
						duplicate = true;
					}
				}

				if(!duplicate) {
					newLines.add(line);
				}
				else {
					int confirmed = Integer.parseInt(processedLine.get(Tools.CSVCONFIRMED));
					if(confirmed > oldTest.getConfirmed()) {
						updateLines.add(line);
						toBeReplaced.add(oldTest.getPictureName());
					}
				}
				line = fileReader.readLine();
			}

			//add new records
			query = "INSERT INTO OCTtest (confirmed, pictureName, userID, adjudicatorID, "+
				"oct_length, oct_type, oct_type_oth, "+
				"oct_snum, oct_scol, oct_nnum, oct_ncol, oct_inum, oct_icol, oct_tnum, oct_tcol, "+
				"oct_sig, oct_isnum, oct_iscol, oct_sinum, oct_sicol, oct_stnum, oct_stcol, oct_itnum, "+
				"oct_itcol, oct_snnum, oct_sncol, oct_mmnum, oct_mmcol, oct_smaxnum, oct_smaxcol, "+
				"oct_imaxnum, oct_imaxcol, oct_savgnum, oct_savgcol, oct_iavgnum, oct_iavgcol, oct_atnum, oct_atcol, "+
				"oct_snum_os, oct_scol_os, oct_nnum_os, oct_ncol_os, oct_inum_os, oct_icol_os, oct_tnum_os, oct_tcol_os, "+
				"oct_sig_os, oct_isnum_os, oct_iscol_os, oct_sinum_os, oct_sicol_os, oct_stnum_os, oct_stcol_os, oct_itnum_os, "+
				"oct_itcol_os, oct_snnum_os, oct_sncol_os, oct_mmnum_os, oct_mmcol_os, oct_smaxnum_os, oct_smaxcol_os, "+
				"imaxnum_os, imaxcol_os, oct_savgnum_os, oct_savgcol_os, oct_iavgnum_os, oct_iavgcol_os, oct_atnum_os, oct_atcol_os"+
				") VALUES ";
			for(int i=0; i<newLines.size(); i++) {
				if(i > 0) { query += ", "; }
				query += "("+newLines.get(i)+")";
			}
			if(newLines.size() > 0) {
				SQLCommands.update(query);
			}

			//delete recors that will be replaced
			query = "DELETE FROM OCTtest WHERE ";
			for(int i=0; i<updateLines.size(); i++) {
				if(i>0) {query+=" OR ";}
				query += "pictureName'"+toBeReplaced.get(i)+"'";
			}
			if(updateLines.size() > 0) {
				SQLCommands.update(query);
			}

			//insert records to replace the deleted ones
			query = "INSERT INTO OCTtest (confirmed, pictureName, userID, adjudicatorID, "+
				"oct_length, oct_type, oct_type_oth, "+
				"oct_snum, oct_scol, oct_nnum, oct_ncol, oct_inum, oct_icol, oct_tnum, oct_tcol, "+
				"oct_sig, oct_isnum, oct_iscol, oct_sinum, oct_sicol, oct_stnum, oct_stcol, oct_itnum, "+
				"oct_itcol, oct_snnum, oct_sncol, oct_mmnum, oct_mmcol, oct_smaxnum, oct_smaxcol, "+
				"oct_imaxnum, oct_imaxcol, oct_savgnum, oct_savgcol, oct_iavgnum, oct_iavgcol, oct_atnum, oct_atcol, "+
				"oct_snum_os, oct_scol_os, oct_nnum_os, oct_ncol_os, oct_inum_os, oct_icol_os, oct_tnum_os, oct_tcol_os, "+
				"oct_sig_os, oct_isnum_os, oct_iscol_os, oct_sinum_os, oct_sicol_os, oct_stnum_os, oct_stcol_os, oct_itnum_os, "+
				"oct_itcol_os, oct_snnum_os, oct_sncol_os, oct_mmnum_os, oct_mmcol_os, oct_smaxnum_os, oct_smaxcol_os, "+
				"imaxnum_os, imaxcol_os, oct_savgnum_os, oct_savgcol_os, oct_iavgnum_os, oct_iavgcol_os, oct_atnum_os, oct_atcol_os"+
				") VALUES ";
			for(int i=0; i<updateLines.size(); i++) {
				if(i>0) {query += ", ";}
				query += "("+updateLines.get(i)+")";
			}
			if(updateLines.size() > 0) {
				SQLCommands.update(query);
			}

		}
		catch (Exception e) { 
			e.printStackTrace(); 
		}
		finally {
			try {
				reader.close();
				fileReader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		reduplicate();
	}

	private static void reduplicate() {
		String query = "SELECT * FROM OCTtest WHERE confirmed=2";
		Vector<OCTtest> octs = SQLCommands.queryOCTtest(query);
		Vector<OCTtest> needDuplicate = new Vector<OCTtest>();

		outerLoop:
		for(OCTtest oct : octs) {
			for(OCTtest check : octs) {
				if(check.getId() == oct.getId()) {
					continue;
				}
				if(check.getPictureName().equals(oct.getPictureName())) {
					continue outerLoop;
				}
			}
			needDuplicate.add(oct);
		}

		if(needDuplicate.size() == 0) return;
		
		query = "INSERT INTO OCTtest (confirmed, pictureName, userID, adjudicatorID, "+
				"oct_length, oct_type, oct_type_oth, "+
				"oct_snum, oct_scol, oct_nnum, oct_ncol, oct_inum, oct_icol, oct_tnum, oct_tcol, "+
				"oct_sig, oct_isnum, oct_iscol, oct_sinum, oct_sicol, oct_stnum, oct_stcol, oct_itnum, "+
				"oct_itcol, oct_snnum, oct_sncol, oct_mmnum, oct_mmcol, oct_smaxnum, oct_smaxcol, "+
				"oct_imaxnum, oct_imaxcol, oct_savgnum, oct_savgcol, oct_iavgnum, oct_iavgcol, oct_atnum, oct_atcol, "+
				"oct_snum_os, oct_scol_os, oct_nnum_os, oct_ncol_os, oct_inum_os, oct_icol_os, oct_tnum_os, oct_tcol_os, "+
				"oct_sig_os, oct_isnum_os, oct_iscol_os, oct_sinum_os, oct_sicol_os, oct_stnum_os, oct_stcol_os, oct_itnum_os, "+
				"oct_itcol_os, oct_snnum_os, oct_sncol_os, oct_mmnum_os, oct_mmcol_os, oct_smaxnum_os, oct_smaxcol_os, "+
				"imaxnum_os, imaxcol_os, oct_savgnum_os, oct_savgcol_os, oct_iavgnum_os, oct_iavgcol_os, oct_atnum_os, oct_atcol_os"+
				") VALUES ";
		for(int i=0; i<needDuplicate.size(); i++) {
			if(i>0) {query += ", ";}
			OCTtest oct = needDuplicate.get(i);
			query += "("+
				oct.getConfirmed()+", "+oct.getPictureName()+", "+oct.getUserID()+", "+oct.getAdjudicatorID()+", "+
				oct.getLength()+", "+oct.getType()+", "+oct.getType_oth()+", "+oct.getSnum()+", "+oct.getScol()+", "+
				oct.getNnum()+", "+oct.getNcol()+", "+oct.getInum()+", "+oct.getIcol()+", "+oct.getTnum()+", "+
				oct.getTcol()+", "+oct.getSig()+", "+oct.getIsnum()+", "+oct.getIscol()+", "+oct.getSinum()+", "+
				oct.getSicol()+", "+oct.getStnum()+", "+oct.getStcol()+", "+oct.getItnum()+", "+oct.getItcol()+", "+
				oct.getSnnum()+", "+oct.getSncol()+", "+oct.getMmnum()+", "+oct.getMmcol()+", "+oct.getSmaxnum()+", "+
				oct.getSmaxcol()+", "+oct.getImaxnum()+", "+oct.getImaxcol()+", "+oct.getSavgnum()+", "+oct.getSavgcol()+", "+
				oct.getIavgnum()+", "+oct.getIavgcol()+", "+oct.getAtnum()+", "+oct.getAtcol()+", "+
				oct.getSnum_os()+", "+oct.getScol_os()+", "+
				oct.getNnum_os()+", "+oct.getNcol_os()+", "+oct.getInum_os()+", "+oct.getIcol_os()+", "+oct.getTnum_os()+", "+
				oct.getTcol_os()+", "+oct.getSig_os()+", "+oct.getIsnum_os()+", "+oct.getIscol_os()+", "+oct.getSinum_os()+", "+
				oct.getSicol_os()+", "+oct.getStnum_os()+", "+oct.getStcol_os()+", "+oct.getItnum_os()+", "+oct.getItcol_os()+", "+
				oct.getSnnum_os()+", "+oct.getSncol_os()+", "+oct.getMmnum_os()+", "+oct.getMmcol_os()+", "+oct.getSmaxnum_os()+", "+
				oct.getSmaxcol_os()+", "+oct.getImaxnum_os()+", "+oct.getImaxcol_os()+", "+oct.getSavgnum_os()+", "+oct.getSavgcol_os()+", "+
				oct.getIavgnum_os()+", "+oct.getIavgcol_os()+", "+oct.getAtnum_os()+", "+oct.getAtcol_os()+
				")";
		}

		SQLCommands.update(query);
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
	 * @return the snum_os
	 */
	public String getSnum_os() {
		return snum_os;
	}

	/**
	 * @param snum_os the snum_os to set
	 */
	public void setSnum_os(String snum_os) {
		this.snum_os = snum_os;
	}

	/**
	 * @return the scol_os
	 */
	public int getScol_os() {
		return scol_os;
	}

	/**
	 * @param scol_os the scol_os to set
	 */
	public void setScol_os(int scol_os) {
		this.scol_os = scol_os;
	}

	/**
	 * @return the nnum_os
	 */
	public String getNnum_os() {
		return nnum_os;
	}

	/**
	 * @param nnum_os the nnum_os to set
	 */
	public void setNnum_os(String nnum_os) {
		this.nnum_os = nnum_os;
	}

	/**
	 * @return the ncol_os
	 */
	public int getNcol_os() {
		return ncol_os;
	}

	/**
	 * @param ncol_os the ncol_os to set
	 */
	public void setNcol_os(int ncol_os) {
		this.ncol_os = ncol_os;
	}

	/**
	 * @return the inum_os
	 */
	public String getInum_os() {
		return inum_os;
	}

	/**
	 * @param inum_os the inum_os to set
	 */
	public void setInum_os(String inum_os) {
		this.inum_os = inum_os;
	}

	/**
	 * @return the icol_os
	 */
	public int getIcol_os() {
		return icol_os;
	}

	/**
	 * @param icol_os the icol_os to set
	 */
	public void setIcol_os(int icol_os) {
		this.icol_os = icol_os;
	}

	/**
	 * @return the tnum_os
	 */
	public String getTnum_os() {
		return tnum_os;
	}

	/**
	 * @param tnum_os the tnum_os to set
	 */
	public void setTnum_os(String tnum_os) {
		this.tnum_os = tnum_os;
	}

	/**
	 * @return the tcol_os
	 */
	public int getTcol_os() {
		return tcol_os;
	}

	/**
	 * @param tcol_os the tcol_os to set
	 */
	public void setTcol_os(int tcol_os) {
		this.tcol_os = tcol_os;
	}

	/**
	 * @return the sig_os
	 */
	public String getSig_os() {
		return sig_os;
	}

	/**
	 * @param sig_os the sig_os to set
	 */
	public void setSig_os(String sig_os) {
		this.sig_os = sig_os;
	}

	/**
	 * @return the isnum_os
	 */
	public String getIsnum_os() {
		return isnum_os;
	}

	/**
	 * @param isnum_os the isnum_os to set
	 */
	public void setIsnum_os(String isnum_os) {
		this.isnum_os = isnum_os;
	}

	/**
	 * @return the iscol_os
	 */
	public int getIscol_os() {
		return iscol_os;
	}

	/**
	 * @param iscol_os the iscol_os to set
	 */
	public void setIscol_os(int iscol_os) {
		this.iscol_os = iscol_os;
	}

	/**
	 * @return the sinum_os
	 */
	public String getSinum_os() {
		return sinum_os;
	}

	/**
	 * @param sinum_os the sinum_os to set
	 */
	public void setSinum_os(String sinum_os) {
		this.sinum_os = sinum_os;
	}

	/**
	 * @return the sicol_os
	 */
	public int getSicol_os() {
		return sicol_os;
	}

	/**
	 * @param sicol_os the sicol_os to set
	 */
	public void setSicol_os(int sicol_os) {
		this.sicol_os = sicol_os;
	}

	/**
	 * @return the stnum_os
	 */
	public String getStnum_os() {
		return stnum_os;
	}

	/**
	 * @param stnum_os the stnum_os to set
	 */
	public void setStnum_os(String stnum_os) {
		this.stnum_os = stnum_os;
	}

	/**
	 * @return the stcol_os
	 */
	public int getStcol_os() {
		return stcol_os;
	}

	/**
	 * @param stcol_os the stcol_os to set
	 */
	public void setStcol_os(int stcol_os) {
		this.stcol_os = stcol_os;
	}

	/**
	 * @return the itnum_os
	 */
	public String getItnum_os() {
		return itnum_os;
	}

	/**
	 * @param itnum_os the itnum_os to set
	 */
	public void setItnum_os(String itnum_os) {
		this.itnum_os = itnum_os;
	}

	/**
	 * @return the itcol_os
	 */
	public int getItcol_os() {
		return itcol_os;
	}

	/**
	 * @param itcol_os the itcol_os to set
	 */
	public void setItcol_os(int itcol_os) {
		this.itcol_os = itcol_os;
	}

	/**
	 * @return the snnum_os
	 */
	public String getSnnum_os() {
		return snnum_os;
	}

	/**
	 * @param snnum_os the snnum_os to set
	 */
	public void setSnnum_os(String snnum_os) {
		this.snnum_os = snnum_os;
	}

	/**
	 * @return the sncol_os
	 */
	public int getSncol_os() {
		return sncol_os;
	}

	/**
	 * @param sncol_os the sncol_os to set
	 */
	public void setSncol_os(int sncol_os) {
		this.sncol_os = sncol_os;
	}

	/**
	 * @return the mmnum_os
	 */
	public String getMmnum_os() {
		return mmnum_os;
	}

	/**
	 * @param mmnum_os the mmnum_os to set
	 */
	public void setMmnum_os(String mmnum_os) {
		this.mmnum_os = mmnum_os;
	}

	/**
	 * @return the mmcol_os
	 */
	public int getMmcol_os() {
		return mmcol_os;
	}

	/**
	 * @param mmcol_os the mmcol_os to set
	 */
	public void setMmcol_os(int mmcol_os) {
		this.mmcol_os = mmcol_os;
	}

	/**
	 * @return the smaxnum_os
	 */
	public String getSmaxnum_os() {
		return smaxnum_os;
	}

	/**
	 * @param smaxnum_os the smaxnum_os to set
	 */
	public void setSmaxnum_os(String smaxnum_os) {
		this.smaxnum_os = smaxnum_os;
	}

	/**
	 * @return the smaxcol_os
	 */
	public int getSmaxcol_os() {
		return smaxcol_os;
	}

	/**
	 * @param smaxcol_os the smaxcol_os to set
	 */
	public void setSmaxcol_os(int smaxcol_os) {
		this.smaxcol_os = smaxcol_os;
	}

	/**
	 * @return the imaxnum_os
	 */
	public String getImaxnum_os() {
		return imaxnum_os;
	}

	/**
	 * @param imaxnum_os the imaxnum_os to set
	 */
	public void setImaxnum_os(String imaxnum_os) {
		this.imaxnum_os = imaxnum_os;
	}

	/**
	 * @return the imaxcol_os
	 */
	public int getImaxcol_os() {
		return imaxcol_os;
	}

	/**
	 * @param imaxcol_os the imaxcol_os to set
	 */
	public void setImaxcol_os(int imaxcol_os) {
		this.imaxcol_os = imaxcol_os;
	}

	/**
	 * @return the savgnum_os
	 */
	public String getSavgnum_os() {
		return savgnum_os;
	}

	/**
	 * @param savgnum_os the savgnum_os to set
	 */
	public void setSavgnum_os(String savgnum_os) {
		this.savgnum_os = savgnum_os;
	}

	/**
	 * @return the savgcol_os
	 */
	public int getSavgcol_os() {
		return savgcol_os;
	}

	/**
	 * @param savgcol_os the savgcol_os to set
	 */
	public void setSavgcol_os(int savgcol_os) {
		this.savgcol_os = savgcol_os;
	}

	/**
	 * @return the iavgnum_os
	 */
	public String getIavgnum_os() {
		return iavgnum_os;
	}

	/**
	 * @param iavgnum_os the iavgnum_os to set
	 */
	public void setIavgnum_os(String iavgnum_os) {
		this.iavgnum_os = iavgnum_os;
	}

	/**
	 * @return the iavgcol_os
	 */
	public int getIavgcol_os() {
		return iavgcol_os;
	}

	/**
	 * @param iavgcol_os the iavgcol_os to set
	 */
	public void setIavgcol_os(int iavgcol_os) {
		this.iavgcol_os = iavgcol_os;
	}

	/**
	 * @return the atnum_os
	 */
	public String getAtnum_os() {
		return atnum_os;
	}

	/**
	 * @param atnum_os the atnum_os to set
	 */
	public void setAtnum_os(String atnum_os) {
		this.atnum_os = atnum_os;
	}

	/**
	 * @return the atcol_os
	 */
	public int getAtcol_os() {
		return atcol_os;
	}

	/**
	 * @param atcol_os the atcol_os to set
	 */
	public void setAtcol_os(int atcol_os) {
		this.atcol_os = atcol_os;
	}
}
