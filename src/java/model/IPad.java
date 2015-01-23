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
public class IPad implements BaseTest{
	private int id;
	private int confirmed;
	private String pictureName;
	private int userID;
	private int adjudicatorID;
	private int fp;
	private int fn;
	private String sup_hem;
	private String inf_hem;

	private int baseType;
	private static final String slash = System.getProperty("file.separator");

	public IPad(String npictureName) {
		pictureName = npictureName;
	}

	public IPad(String npictureName, int nuserID) {
		String query = "INSERT INTO iPad (pictureName, userID) VALUES ('"+npictureName+"', '"+nuserID+"')";
		SQLCommands.update(query);

		query = "SELECT * FROM iPad WHERE pictureName='"+npictureName+"' AND userID="+nuserID;
		Vector<Integer> data = SQLCommands.queryNewGrade(query);

		pictureName = npictureName;
		userID = data.get(0);
		id = data.get(1);	
	}

	public IPad(int id, int confirmed, String pictureName, int userID, int adjudicatorID, 
		    int fp, int fn, String sup_hem, String inf_hem) {
		this.id = id;
		this.confirmed = confirmed;
		this.pictureName = pictureName;
		this.userID = userID;
		this.adjudicatorID = adjudicatorID;
		this.fp = fp;
		this.fn = fn;
		this.sup_hem = sup_hem;
		this.inf_hem = inf_hem;
	}

	public static IPad getSingle(String name, int id, int access) {
		String query;
		if(access == 0) {
			query = "SELECT * FROM iPad WHERE pictureName='"+name+"' AND userID="+id;
		}
		else {
			query = "SELECT * FROM iPad WHERE pictureName='"+name+"' AND adjudicatorID="+id;
		}
		return SQLCommands.queryIPad(query).get(0);
	}

	public static Picture getNext(User user) {
		Picture result = null;
		String query = "";
		if (user.getAccess() == 0) {
			query = "SELECT * FROM picture WHERE name NOT IN (SELECT "+
				" pictureName FROM iPad WHERE userID="+user.getID()+") AND type='iPad'"+
				" AND name NOT IN (SELECT pictureName FROM iPad GROUP BY pictureName HAVING COUNT(*)>=2)";
		}
		else if (user.getAccess() == 1) {
			query = "SELECT * FROM picture WHERE name IN (SELECT pictureName FROM "+
				"iPad WHERE CONFIRMED=1) AND type='iPad'";
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
		String query = "SELECT * FROM iPad GROUP BY pictureName HAVING COUNT(*)=1";
		return SQLCommands.getCount(query);
	}

	public static Vector<IPad> getPair(String picName) {
		String query = "SELECT * FROM iPad WHERE pictureName='"+picName+"'";	
		return SQLCommands.queryIPad(query);
	}

	public static int assignIPad(HttpServletRequest request, User user) {
		int result = 0;

		int uID = user.getID();
		String picName = request.getParameter("pictureName");
		IPad iPad = null;
		if(user.getAccess() == 0) {
			iPad = new IPad(picName, uID);
		}
		else if (user.getAccess() == 1) {
			iPad = new IPad(picName);
		}

		String attr = request.getParameter("fp");
		iPad.setFp(Integer.parseInt(attr));
		attr = request.getParameter("fn");
		iPad.setFn(Integer.parseInt(attr));
		attr = request.getParameter("sup_hem");
		iPad.setSup_hem(attr);
		attr = request.getParameter("inf_hem");
		iPad.setInf_hem(attr);


		String query = "UPDATE iPad SET ipad_fp='"+iPad.getFp()+"', ipad_fn='"+iPad.getFn()+"', "+
				"ipad_sup_hem='"+iPad.getSup_hem()+"', ipad_inf_hem='"+iPad.getInf_hem()+"' ";

		if(user.getAccess() == 0) {
			query += " WHERE id='"+iPad.getId()+"'";
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
		String query = "SELECT * FROM iPad WHERE pictureName='"+picName+"'";
		Vector<IPad> iPad = SQLCommands.queryIPad(query);

		if(iPad.size() > 1) {
			//get the ones that don't need adjudication
			query = "SELECT * FROM iPad GROUP BY pictureName, "+
				"ipad_fp, ipad_fn, ipad_sup_hem, ipad_inf_hem "+
				"HAVING COUNT(*)=2";
			Vector<IPad> set = SQLCommands.queryIPad(query);
			//get the ones that need adjudication
			query = "SELECT * FROM iPad GROUP BY pictureName, "+
				"ipad_fp, ipad_fn, ipad_sup_hem, ipad_inf_hem "+
				"HAVING COUNT(*)=1";
			Vector<IPad> notSet = SQLCommands.queryIPad(query);

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
			query = "UPDATE iPad SET confirmed=2";
			query += " WHERE pictureName='"+picName+"'";
			if(set.size() > 0) {
				SQLCommands.update(query);
			}

			//update the ones that need confirming
			query = "UPDATE iPad SET confirmed=1 WHERE pictureName='"+picName+"'";
			if(notSet.size() > 0) {
				SQLCommands.update(query);
			}
		}
	}

	public static int updateIPad(HttpServletRequest request, User user) {
		int result = 0;

		int uID = user.getID();
		String picName = request.getParameter("pictureName");
		IPad iPad = null;
		iPad = new IPad(picName);

		String attr = request.getParameter("fp");
		iPad.setFp(Integer.parseInt(attr));
		attr = request.getParameter("fn");
		iPad.setFn(Integer.parseInt(attr));
		attr = request.getParameter("sup_hem");
		iPad.setSup_hem(attr);
		attr = request.getParameter("inf_hem");
		iPad.setInf_hem(attr);

		String query = "UPDATE iPad SET ipad_fp='"+iPad.getFp()+"', ipad_fn='"+iPad.getFn()+"', "+
				"ipad_sup_hem='"+iPad.getSup_hem()+"', ipad_inf_hem='"+iPad.getInf_hem()+"' ";

		if(user.getAccess() == 0) {
			query += " WHERE pictureName='"+iPad.getPictureName()+"' AND userID='"+user.getID()+"'";
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

	public static Vector<String> getUngradedNames() {
		Vector<String> result = new Vector<String>();
		String query = "SELECT * FROM picture WHERE name NOT IN (SELECT pictureName FROM iPad) AND type='iPad'";
		Vector<Picture> pictures = SQLCommands.queryPictures(query);

		for(int i=0; i<pictures.size(); i++) {
			result.add("iPad - " + pictures.get(i).getName());
		}

		return result;
	}

	public static Vector<String> getGradedOnceNames() {
		Vector<String> result = new Vector<String>();
		String query = "SELECT * FROM iPad GROUP BY pictureName HAVING COUNT(*)=1";
		Vector<IPad> iPad = SQLCommands.queryIPad(query);

		for(int i=0; i<iPad.size(); i++) {
			result.add("iPad - " + iPad.get(i).getPictureName());
		}

		return result;
	}
	
	public static Vector<String> getNeedsAdjudication() {
		Vector<String> result = new Vector<String>();
		String query = "SELECT DISTINCT pictureName FROM iPad WHERE confirmed='1'";
		Vector<String> iPad = SQLCommands.queryNames(query);

		for(int i=0; i<iPad.size(); i++) {
			result.add("iPad - " + iPad.get(i));
		}

		return result;
	}

	public static Vector<String> getAdjudicated() {
		Vector<String> result = new Vector<String>();
		String query = "SELECT DISTINCT pictureName FROM iPad WHERE confirmed='2'";
		Vector<String> iPad = SQLCommands.queryNames(query);

		for(int i=0; i<iPad.size(); i++) {
			result.add("iPad - " + iPad.get(i));
		}

		return result;
	}
	public static Vector<BaseTest> getBaseTest(int id) {
		String query = "SELECT * FROM iPad WHERE (userID="+id+" OR adjudicatorID="+id+")";
		return SQLCommands.queryBaseTest(query,  BaseTest.IPAD);
	}

	public static Vector<String> getCSVLines() {
		Vector<String> result = new Vector<String>();
		String query = "SELECT * FROM iPad ORDER BY pictureName";
		Vector<IPad> iPadsAll = SQLCommands.queryIPad(query);
		Vector<IPad> iPads = new Vector<IPad>();

		for(int i=0; i<iPadsAll.size(); i++) {
			if(i<(iPadsAll.size()-1) && (iPadsAll.get(i).getConfirmed() == 2)) {
				i++;
			}
			iPads.add(iPadsAll.get(i));
		}
		
		String currLine = "confirmed, picture, userID, adjudicatorID, ipad_fp, ipad_fn, ipad_sup_hem, ipad_inf_hem";
		result.add(currLine);

		for(IPad iPad : iPads) {
			currLine = iPad.getConfirmed()+", "+iPad.getPictureName()+", "+iPad.getUserID()+", "+iPad.getAdjudicatorID()+", "+
					iPad.getFp()+", "+iPad.getFn()+", "+iPad.getSup_hem()+", "+iPad.getInf_hem();
			result.add(currLine);
		}

		return result;
	}

	public static void readCSV(String fileName) {
		String query = "SELECT * FROM iPad";

		Vector<IPad> alreadyHere = SQLCommands.queryIPad(query);
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
				IPad oldTest = null;
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
			query = "INSERT INTO iPad (confirmed, pictureName, userID, adjudicatorID, "+
				"ipad_fp, ipad_fn, ipad_sup_hem, ipad_inf_hem"+
				") VALUES ";
			for(int i=0; i<newLines.size(); i++) {
				if(i > 0) { query += ", "; }
				query += "("+newLines.get(i)+")";
			}
			if(newLines.size() > 0) {
				SQLCommands.update(query);
			}

			//delete recors that will be replaced
			query = "DELETE FROM iPad WHERE ";
			for(int i=0; i<updateLines.size(); i++) {
				if(i>0) {query+=" OR ";}
				query += "pictureName'"+toBeReplaced.get(i)+"'";
			}
			if(updateLines.size() > 0) {
				SQLCommands.update(query);
			}

			//insert records to replace the deleted ones
			query = "INSERT INTO iPad (confirmed, pictureName, userID, adjudicatorID, "+
				"ipad_fp, ipad_fn, ipad_sup_hem, ipad_inf_hem"+
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
		String query = "SELECT * FROM iPad WHERE confirmed=2";
		Vector<IPad> iPads = SQLCommands.queryIPad(query);
		Vector<IPad> needDuplicate = new Vector<IPad>();

		outerLoop:
		for(IPad iPad: iPads) {
			for(IPad check : iPads) {
				if(check.getId() == iPad.getId()) {
					continue;
				}
				if(check.getPictureName().equals(iPad.getPictureName())) {
					continue outerLoop;
				}
			}
			needDuplicate.add(iPad);
		}

		if(needDuplicate.size() == 0) return;
		
		query = "INSERT INTO iPad (confirmed, pictureName, userID, adjudicatorID, "+
				"ipad_fp, ipad_fn, ipad_sup_hem, ipad_inf_hem"+
				") VALUES ";
		for(int i=0; i<needDuplicate.size(); i++) {
			if(i>0) {query += ", ";}
			IPad iPad = needDuplicate.get(i);
			query += "('"+
				iPad.getConfirmed()+"', '"+iPad.getPictureName()+"', '"+iPad.getUserID()+"', '"+iPad.getAdjudicatorID()+"', '"+
				iPad.getFp()+"', '"+iPad.getFn()+"', '"+iPad.getSup_hem()+"', '"+iPad.getInf_hem()+
				"')";
		}

		SQLCommands.update(query);
	}

	public static ArrayList<String> needPictures(){
		ArrayList<String> needPics = new ArrayList<String>();
		String query = "SELECT * FROM iPad WHERE pictureName NOT IN (SELECT name FROM picture WHERE type='iPad')";
		Vector<IPad> iPad = SQLCommands.queryIPad(query);

		for(int i=0; i<iPad.size(); i++) {
			if(!needPics.contains(iPad.get(i).getPictureName())) {
				needPics.add(iPad.get(i).getPictureName());
			}
		}

		String ext;
		for(int i=0; i<needPics.size(); i++) {
			ext = needPics.get(i).substring(needPics.get(i).indexOf(".")+1, needPics.get(i).length());
			if(ext.equals("pdf")) {
				needPics.set(i, "iPad: "+needPics.get(i).substring(0,needPics.get(i).indexOf("."))+".jpg");
			}
		}

		return needPics;
	}

	public static void remove(Vector<String> records) {
		Picture.remove(records, BaseTest.IPAD);

		String query = "DELETE FROM iPad WHERE ";
		for(int i=0; i<records.size(); i++) {
			if (i > 0) { query += " OR "; }
			query += "PictureName='"+records.get(i)+"'";
		}

		if(records.size() > 0) {
			SQLCommands.update(query);
		}
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
	 * @return the fp
	 */
	public int getFp() {
		return fp;
	}

	/**
	 * @param fp the fp to set
	 */
	public void setFp(int fp) {
		this.fp = fp;
	}

	/**
	 * @return the fn
	 */
	public int getFn() {
		return fn;
	}

	/**
	 * @param fn the fn to set
	 */
	public void setFn(int fn) {
		this.fn = fn;
	}

	/**
	 * @return the sup_hem
	 */
	public String getSup_hem() {
		return sup_hem;
	}

	/**
	 * @param sup_hem the sup_hem to set
	 */
	public void setSup_hem(String sup_hem) {
		this.sup_hem = sup_hem;
	}

	/**
	 * @return the inf_hem
	 */
	public String getInf_hem() {
		return inf_hem;
	}

	/**
	 * @param inf_hem the inf_hem to set
	 */
	public void setInf_hem(String inf_hem) {
		this.inf_hem = inf_hem;
	}

	/**
	 * @return the baseType
	 */
	public int getBaseType() {
		return baseType;
	}

	/**
	 * @param baseType the baseType to set
	 */
	public void setBaseType(int baseType) {
		this.baseType = baseType;
	}
	
}
