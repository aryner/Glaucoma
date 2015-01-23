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
public class MDTtest implements BaseTest {
	private int baseType;
	private int id;
	private int confirmed;
	private String pictureName;
	private int userID;
	private int adjudicatorID;
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

	public MDTtest(String npictureName) {
		pictureName = npictureName;
	}

	public MDTtest(String npictureName, int nuserID) {
		String query = "INSERT INTO MDTtest (pictureName, userID) VALUES ('"+npictureName+"', '"+nuserID+"')";
		SQLCommands.update(query);

		query = "SELECT * FROM MDTtest WHERE pictureName='"+npictureName+"' AND userID="+nuserID;
		Vector<Integer> data = SQLCommands.queryNewGrade(query);

		pictureName = npictureName;
		userID = data.get(0);
		id = data.get(1);	
	}

	public MDTtest(int id, int confirmed, String pictureName, int userID, int adjudicatorID, String late, String fp,
		       int lens, String lens_y, String dur, String ptd, String lu_one, String ru_one,
		       String ll_one, String rl_one, int abnormal) 
	{
		this.id = id;
		this.confirmed = confirmed;
		this.pictureName = pictureName;
		this.userID = userID;
		this.adjudicatorID = adjudicatorID;
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

	public static MDTtest getSingle(String name, int id, int access) {
		String query;
		if(access == 0) {
			query = "SELECT * FROM MDTtest WHERE pictureName='"+name+"' AND userID="+id;
		}
		else {
			query = "SELECT * FROM MDTtest WHERE pictureName='"+name+"' AND adjudicatorID="+id;
		}
		return SQLCommands.queryMDTtest(query).get(0);
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
		String query = "SELECT * FROM MDTtest WHERE pictureName NOT IN (SELECT name FROM picture WHERE type='MDT')";
		Vector<MDTtest> mdt = SQLCommands.queryMDTtest(query);

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

	public static int updateMDT(HttpServletRequest request, User user) {
		int result = 0;

		int uID = user.getID();
		String picName = request.getParameter("pictureName");
		MDTtest mdt = null;
		mdt = new MDTtest(picName);

		String attr = request.getParameter("late");
		mdt.setLate(attr);
		attr = request.getParameter("fp");
		mdt.setFp(attr);
		attr = request.getParameter("lens");
		mdt.setLens(Integer.parseInt(attr));
		attr = request.getParameter("lens_y");
		mdt.setLens_y(attr);
		attr = request.getParameter("dur");
		mdt.setDur(attr);
		attr = request.getParameter("ptd");
		mdt.setPtd(attr);
		attr = request.getParameter("lu_one");
		mdt.setLu_one(attr);
		attr = request.getParameter("ru_one");
		mdt.setRu_one(attr);
		attr = request.getParameter("ll_one");
		mdt.setLl_one(attr);
		attr = request.getParameter("rl_one");
		mdt.setRl_one(attr);

		int abnormalCount = Integer.parseInt(mdt.getLu_one())+
				    Integer.parseInt(mdt.getRu_one())+
				    Integer.parseInt(mdt.getLl_one())+
			  	    Integer.parseInt(mdt.getRl_one());
		mdt.setAbnormal((abnormalCount>=3)?2:1);

		String query = "UPDATE MDTtest SET mdt_late='"+mdt.getLate()+"', mdt_fp='"+mdt.getFp()+"', "+
				"mdt_lens='"+mdt.getLens()+"', mdt_lens_y='"+mdt.getLens_y()+"', "+
				"mdt_dur='"+mdt.getDur()+"', mdt_ptd='"+mdt.getPtd()+"', "+
				"mdt_lu_one='"+mdt.getLu_one()+"', mdt_ru_one='"+mdt.getRu_one()+"', "+
				"mdt_ll_one='"+mdt.getLl_one()+"', mdt_rl_one='"+mdt.getRl_one()+
				"', mdt_abnormal='"+mdt.getAbnormal()+"' ";

		if(user.getAccess() == 0) {
			query += " WHERE pictureName='"+mdt.getPictureName()+"' AND userID='"+user.getID()+"'";
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

	public static int assignMDT(HttpServletRequest request, User user) {
		int result = 0;

		int uID = user.getID();
		String picName = request.getParameter("pictureName");
		MDTtest mdt = null;
		if(user.getAccess() == 0) {
			mdt = new MDTtest(picName, uID);
		}
		else if (user.getAccess() == 1) {
			mdt = new MDTtest(picName);
		}

		String attr = request.getParameter("late");
		mdt.setLate(attr);
		attr = request.getParameter("fp");
		mdt.setFp(attr);
		attr = request.getParameter("lens");
		mdt.setLens(Integer.parseInt(attr));
		attr = request.getParameter("lens_y");
		mdt.setLens_y(attr);
		attr = request.getParameter("dur");
		mdt.setDur(attr);
		attr = request.getParameter("ptd");
		mdt.setPtd(attr);
		attr = request.getParameter("lu_one");
		mdt.setLu_one(attr);
		attr = request.getParameter("ru_one");
		mdt.setRu_one(attr);
		attr = request.getParameter("ll_one");
		mdt.setLl_one(attr);
		attr = request.getParameter("rl_one");
		mdt.setRl_one(attr);

		int abnormalCount = Integer.parseInt(mdt.getLu_one())+
				    Integer.parseInt(mdt.getRu_one())+
				    Integer.parseInt(mdt.getLl_one())+
			  	    Integer.parseInt(mdt.getRl_one());
		mdt.setAbnormal((abnormalCount>=3)?2:1);

		String query = "UPDATE MDTtest SET mdt_late='"+mdt.getLate()+"', mdt_fp='"+mdt.getFp()+"', "+
				"mdt_lens='"+mdt.getLens()+"', mdt_lens_y='"+mdt.getLens_y()+"', "+
				"mdt_dur='"+mdt.getDur()+"', mdt_ptd='"+mdt.getPtd()+"', "+
				"mdt_lu_one='"+mdt.getLu_one()+"', mdt_ru_one='"+mdt.getRu_one()+"', "+
				"mdt_ll_one='"+mdt.getLl_one()+"', mdt_rl_one='"+mdt.getRl_one()+
				"', mdt_abnormal='"+mdt.getAbnormal()+"' ";

		if(user.getAccess() == 0) {
			query += " WHERE id='"+mdt.getId()+"'";
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
		String query = "SELECT * FROM MDTtest WHERE pictureName='"+picName+"'";
		Vector<MDTtest> mdt = SQLCommands.queryMDTtest(query);

		if(mdt.size() > 1) {
			//get the ones that don't need adjudication
			query = "SELECT * FROM MDTtest GROUP BY pictureName, "+
				"mdt_late, mdt_fp, mdt_lens, mdt_lens_y, mdt_dur, mdt_ptd, "+
				"mdt_lu_one, mdt_ru_one, mdt_ll_one, mdt_rl_one "+
				"HAVING COUNT(*)=2";
			Vector<MDTtest> set = SQLCommands.queryMDTtest(query);
			//get the ones that need adjudication
			query = "SELECT * FROM MDTtest GROUP BY pictureName, "+
				"mdt_late, mdt_fp, mdt_lens, mdt_lens_y, mdt_dur, mdt_ptd, "+
				"mdt_lu_one, mdt_ru_one, mdt_ll_one, mdt_rl_one "+
				"HAVING COUNT(*)=1";
			Vector<MDTtest> notSet = SQLCommands.queryMDTtest(query);

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
			query = "UPDATE MDTtest SET confirmed=2";
			query += " WHERE pictureName='"+picName+"'";
			if(set.size() > 0) {
				SQLCommands.update(query);
			}

			//update the ones that need confirming
			query = "UPDATE MDTtest SET confirmed=1 WHERE pictureName='"+picName+"'";
			if(notSet.size() > 0) {
				SQLCommands.update(query);
			}
		}
	}

	public static Vector<String> getUngradedNames() {
		Vector<String> result = new Vector<String>();
		String query = "SELECT * FROM picture WHERE name NOT IN (SELECT pictureName FROM MDTtest) AND type='MDT'";
		Vector<Picture> pictures = SQLCommands.queryPictures(query);

		for(int i=0; i<pictures.size(); i++) {
			result.add("MDT - " + pictures.get(i).getName());
		}

		return result;
	}

	public static Vector<String> getGradedOnceNames() {
		Vector<String> result = new Vector<String>();
		String query = "SELECT * FROM MDTtest GROUP BY pictureName HAVING COUNT(*)=1";
		Vector<MDTtest> mdt = SQLCommands.queryMDTtest(query);

		for(int i=0; i<mdt.size(); i++) {
			result.add("MDT - " + mdt.get(i).getPictureName());
		}

		return result;
	}
	
	public static Vector<String> getNeedsAdjudication() {
		Vector<String> result = new Vector<String>();
		String query = "SELECT DISTINCT pictureName FROM MDTtest WHERE confirmed='1'";
		Vector<String> mdt = SQLCommands.queryNames(query);

		for(int i=0; i<mdt.size(); i++) {
			result.add("MDT - " + mdt.get(i));
		}

		return result;
	}

	public static Vector<String> getAdjudicated() {
		Vector<String> result = new Vector<String>();
		String query = "SELECT DISTINCT pictureName FROM MDTtest WHERE confirmed='2'";
		Vector<String> mdt = SQLCommands.queryNames(query);

		for(int i=0; i<mdt.size(); i++) {
			result.add("MDT - " + mdt.get(i));
		}

		return result;
	}
	public static Vector<BaseTest> getBaseTest(int id) {
		String query = "SELECT * FROM MDTtest WHERE (userID="+id+" OR adjudicatorID="+id+")";
		return SQLCommands.queryBaseTest(query,  BaseTest.MDT);
	}

	public static Vector<String> getCSVLines() {
		Vector<String> result = new Vector<String>();
		String query = "SELECT * FROM MDTtest ORDER BY pictureName";
		Vector<MDTtest> mdtsAll = SQLCommands.queryMDTtest(query);
		Vector<MDTtest> mdts = new Vector<MDTtest>();

		for(int i=0; i<mdtsAll.size(); i++) {
			if(i<(mdtsAll.size()-1) && (mdtsAll.get(i).getConfirmed() == 2)) {
				i++;
			}
			mdts.add(mdtsAll.get(i));
		}
		
		String currLine = "confirmed, picture, userID, adjudicatorID, mdt_late, mdt_fp, mdt_lens, "+
				  "mdt_lens_y, mdt_dur, mdt_ptd, mdt_lu_one, mdt_ru_one, mdt_ll_one, "+
				  "mdt_rl_one, mdt_abnormal";
		result.add(currLine);

		for(MDTtest mdt : mdts) {
			currLine = mdt.getConfirmed()+", "+mdt.getPictureName()+", "+mdt.getUserID()+", "+mdt.getAdjudicatorID()+", "+
				   mdt.getLate()+", "+mdt.getFp()+", "+mdt.getLens()+", "+mdt.getLens_y()+", "+mdt.getDur()+", "+
				   mdt.getPtd()+", "+mdt.getLu_one()+", "+mdt.getRu_one()+", "+mdt.getLl_one()+", "+mdt.getRl_one()+", "+
				   mdt.getAbnormal();
			result.add(currLine);
		}

		return result;
	}

	public static Vector<String> getCSVFinishedLines() {
		Vector<String> result = new Vector<String>();
		String query = "SELECT * FROM MDTtest WHERE confirmed=2 GROUP BY pictureName";
		Vector<MDTtest> mdts = SQLCommands.queryMDTtest(query);
		
		String currLine = "picture, adjudicatorID, mdt_late, mdt_fp, mdt_lens, "+
				  "mdt_lens_y, mdt_dur, mdt_ptd, mdt_lu_one, mdt_ru_one, mdt_ll_one, "+
				  "mdt_rl_one, mdt_abnormal";
		result.add(currLine);

		for(MDTtest mdt : mdts) {
			currLine = mdt.getPictureName()+", "+mdt.getAdjudicatorID()+", "+
				   mdt.getLate()+", "+mdt.getFp()+", "+mdt.getLens()+", "+mdt.getLens_y()+", "+mdt.getDur()+", "+
				   mdt.getPtd()+", "+mdt.getLu_one()+", "+mdt.getRu_one()+", "+mdt.getLl_one()+", "+mdt.getRl_one()+", "+
				   mdt.getAbnormal();
			result.add(currLine);
		}

		return result;
	}

	public static void readCSV(String fileName) {
		String query = "SELECT * FROM MDTtest";

		Vector<MDTtest> alreadyHere = SQLCommands.queryMDTtest(query);
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
				MDTtest oldTest = null;
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
					int confirmed = Integer.parseInt(processedLine.get(Tools.CSVCONFIRMED).replace("'",""));
					if(confirmed > oldTest.getConfirmed()) {
						updateLines.add(line);
						toBeReplaced.add(oldTest.getPictureName());
					}
				}
				line = fileReader.readLine();
			}

			//add new records
			query = "INSERT INTO MDTtest (confirmed, pictureName, userID, adjudicatorID, "+
				"mdt_late, mdt_fp, mdt_lens, "+
				"mdt_lens_y, mdt_dur, mdt_ptd, mdt_lu_one, mdt_ru_one, mdt_ll_one, "+
				"mdt_rl_one, mdt_abnormal) VALUES ";
			for(int i=0; i<newLines.size(); i++) {
				if(i > 0) { query += ", "; }
				query += "("+newLines.get(i)+")";
			}
			if(newLines.size() > 0) {
				SQLCommands.update(query);
			}

			//delete recors that will be replaced
			query = "DELETE FROM MDTtest WHERE ";
			for(int i=0; i<updateLines.size(); i++) {
				if(i>0) {query+=" OR ";}
				query += "pictureName'"+toBeReplaced.get(i)+"'";
			}
			if(updateLines.size() > 0) {
				SQLCommands.update(query);
			}

			//insert records to replace the deleted ones
			query = "INSERT INTO MDTtest (confirmed, pictureName, userID, adjudicatorID, "+
				"mdt_late, mdt_fp, mdt_lens, "+
				"mdt_lens_y, mdt_dur, mdt_ptd, mdt_lu_one, mdt_ru_one, mdt_ll_one, "+
				"mdt_rl_one, mdt_abnormal) VALUES ";
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
		String query = "SELECT * FROM MDTtest WHERE confirmed=2";
		Vector<MDTtest> mdts = SQLCommands.queryMDTtest(query);
		Vector<MDTtest> needDuplicate = new Vector<MDTtest>();

		outerLoop:
		for(MDTtest mdt : mdts) {
			for(MDTtest check : mdts) {
				if(check.getId() == mdt.getId()) {
					continue;
				}
				if(check.getPictureName().equals(mdt.getPictureName())) {
					continue outerLoop;
				}
			}
			needDuplicate.add(mdt);
		}

		if(needDuplicate.size() == 0) return;
		
		query = "INSERT INTO MDTtest (confirmed, pictureName, userID, adjudicatorID, "+
				"mdt_late, mdt_fp, mdt_lens, "+
				"mdt_lens_y, mdt_dur, mdt_ptd, mdt_lu_one, mdt_ru_one, mdt_ll_one, "+
				"mdt_rl_one, mdt_abnormal) VALUES ";
		for(int i=0; i<needDuplicate.size(); i++) {
			if(i>0) {query += ", ";}
			MDTtest mdt = needDuplicate.get(i);
			query += "('"+
				mdt.getConfirmed()+"', '"+mdt.getPictureName()+"', '"+mdt.getUserID()+"', '"+mdt.getAdjudicatorID()+"', '"+
				mdt.getLate()+"', '"+mdt.getFp()+"', '"+mdt.getLens()+"', '"+mdt.getLens_y()+"', '"+mdt.getDur()+"', '"+
				mdt.getPtd()+"', '"+mdt.getLu_one()+"', '"+mdt.getRu_one()+"', '"+mdt.getLl_one()+"', '"+
				mdt.getRl_one()+"', '"+mdt.getAbnormal()+
				"')";
		}

		SQLCommands.update(query);
	}

	public static void remove(Vector<String> records) {
		Picture.remove(records, BaseTest.MDT);

		String query = "DELETE FROM MDTtest WHERE ";
		for(int i=0; i<records.size(); i++) {
			if (i > 0) { query += " OR "; }
			query += "PictureName='"+records.get(i)+"'";
		}

		if(records.size() > 0) {
			SQLCommands.update(query);
		}
	}

	public int getBaseType() {
		return BaseTest.MDT;
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
