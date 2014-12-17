/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.*;
import utilities.*;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author aryner
 */
public class Photos {
	private int id;
	private int confirmed;
	private String pictureName;
	private int userID;
	private int type;
	private int qual;
	private String cdr;
	private int notch;
	private String notch_hrs_one;
	private String notch_hrs_two;
	private int erosion;
	private String eros_hrs_one;
	private String eros_hrs_two;
	private int disc;
	private String disc_hrs_one;
	private String disc_hrs_two;
	private int rnfl;
	private String rnfl_hrs_one;
	private String rnfl_hrs_two;

	private static final String slash = System.getProperty("file.separator");
	public static final int STEREO = 1;
	public static final int NETHRA = 2;

	public Photos(String npictureName) {
		pictureName = npictureName;
	}

	public Photos(String npictureName, int nuserID, int type) {
		String query = "INSERT INTO Photos (pictureName, userID, type) VALUES ('"+npictureName+"', '"+nuserID+"', '"+type+"')";
		SQLCommands.update(query);

		query = "SELECT * FROM Photos WHERE pictureName='"+npictureName+"' AND userID="+nuserID+" AND type="+type;
		Vector<Integer> data = SQLCommands.queryNewGrade(query);

		pictureName = npictureName;
		userID = data.get(0);
		id = data.get(1);	
	}

	public Photos(int id, int confirmed, String pictureName, int userID, int type, int qual,
		      String cdr, int notch, String notch_hrs_one, String notch_hrs_two, int erosion,
		      String eros_hrs_one, String eros_hrs_two, int disc, String disc_hrs_one,
		      String disc_hrs_two, int rnfl, String rnfl_hrs_one, String rnfl_hrs_two) 
	{
		this.id = id;
		this.confirmed = confirmed;
		this.pictureName = pictureName;
		this.userID = userID;
		this.type = type;
		this.qual = qual;
		this.cdr = cdr; 
		this.notch = notch;
		this.notch_hrs_one = notch_hrs_one;
		this.notch_hrs_two = notch_hrs_two;
		this.erosion = erosion;
		this.eros_hrs_one = eros_hrs_one;
		this.eros_hrs_two = eros_hrs_two;
		this.disc = disc;
		this.disc_hrs_one = disc_hrs_one;
		this.disc_hrs_two = disc_hrs_two;
		this.rnfl = rnfl;
		this.rnfl_hrs_one = rnfl_hrs_one;
		this.rnfl_hrs_two = rnfl_hrs_two;
	}

	public static Picture getNextStereo(User user) {
		Picture result = null;
		String query = "";
		if (user.getAccess() == 0) {
			query = "SELECT * FROM picture WHERE name NOT IN (SELECT "+
				" pictureName FROM Photos WHERE userID="+user.getID()+" AND type="+STEREO+") AND type='stereo'"+
				" AND name NOT IN (SELECT pictureName FROM Photos WHERE type="+STEREO+" GROUP BY pictureName HAVING COUNT(*)>=2)";
		}
		else if (user.getAccess() == 1) {
			query = "SELECT * FROM picture WHERE name IN (SELECT pictureName FROM "+
				"Photos WHERE CONFIRMED=1 AND type="+STEREO+") AND type='stereo'";
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

	public static Picture getNextNethra(User user) {
		Picture result = null;
		String query = "";
		if (user.getAccess() == 0) {
			query = "SELECT * FROM picture WHERE name NOT IN (SELECT "+
				" pictureName FROM Photos WHERE userID="+user.getID()+" AND type="+NETHRA+") AND type='3Nethra'"+
				" AND name NOT IN (SELECT pictureName FROM Photos WHERE type="+NETHRA+" GROUP BY pictureName HAVING COUNT(*)>=2)";
		}
		else if (user.getAccess() == 1) {
			query = "SELECT * FROM picture WHERE name IN (SELECT pictureName FROM "+
				"Photos WHERE CONFIRMED=1 AND type="+NETHRA+") AND type='3Nethra'";
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

	public static int getNeedToPairCountStereo() {
		String query = "SELECT * FROM Photos WHERE type="+STEREO+" GROUP BY pictureName HAVING COUNT(*)=1";
		return SQLCommands.getCount(query);
	}
	public static int getNeedToPairCountNethra() {
		String query = "SELECT * FROM Photos WHERE type="+NETHRA+" GROUP BY pictureName HAVING COUNT(*)=1";
		return SQLCommands.getCount(query);
	}

	public static Vector<Photos> getPairStereo(String picName) {
		String query = "SELECT * FROM Photos WHERE pictureName='"+picName+"' AND type="+STEREO;	
		return SQLCommands.queryPhotos(query);
	}
	public static Vector<Photos> getPairNethra(String picName) {
		String query = "SELECT * FROM Photos WHERE pictureName='"+picName+"' AND type="+NETHRA;	
		return SQLCommands.queryPhotos(query);
	}

	public static ArrayList<String> needPictures(){
		ArrayList<String> needPics = new ArrayList<String>();
		String query = "SELECT * FROM Photos WHERE pictureName NOT IN (SELECT name FROM picture)";
		Vector<Photos> photos = SQLCommands.queryPhotos(query);

		for(int i=0; i<photos.size(); i++) {
			if(!needPics.contains(photos.get(i).getPictureName())) {
				needPics.add(photos.get(i).getPictureName());
			}
		}

		String ext;
		for(int i=0; i<needPics.size(); i++) {
			ext = needPics.get(i).substring(needPics.get(i).indexOf(".")+1, needPics.get(i).length());
			if(ext.equals("pdf")) {
				needPics.set(i, "Photos: "+needPics.get(i).substring(0,needPics.get(i).indexOf("."))+".jpg");
			}
		}

		return needPics;
	}

	public static int assignPhoto(HttpServletRequest request, User user, int type) {
		int result = 0;

		int uID = user.getID();
		String picName = request.getParameter("pictureName");
		Photos photo = null;
		if(user.getAccess() == 0) {
			photo = new Photos(picName, uID, type);
		}
		else if (user.getAccess() == 1) {
			photo = new Photos(picName);
		}

		String attr = request.getParameter("qual");
		photo.setQual(Integer.parseInt(attr));
		attr = request.getParameter("cdr");
		photo.setCdr(attr);
		attr = request.getParameter("notch");
		photo.setNotch(Integer.parseInt(attr));
		attr = request.getParameter("notch_hrs_one");
		photo.setNotch_hrs_one(attr);
		attr = request.getParameter("notch_hrs_two");
		photo.setNotch_hrs_two(attr);
		attr = request.getParameter("erosion");
		photo.setErosion(Integer.parseInt(attr));
		attr = request.getParameter("eros_hrs_one");
		photo.setEros_hrs_one(attr);
		attr = request.getParameter("eros_hrs_two");
		photo.setEros_hrs_two(attr);
		attr = request.getParameter("disc");
		photo.setDisc(Integer.parseInt(attr));
		attr = request.getParameter("disc_hrs_one");
		photo.setDisc_hrs_one(attr);
		attr = request.getParameter("disc_hrs_two");
		photo.setDisc_hrs_two(attr);
		attr = request.getParameter("rnfl");
		photo.setRnfl(Integer.parseInt(attr));
		attr = request.getParameter("rnfl_hrs_one");
		photo.setRnfl_hrs_one(attr);
		attr = request.getParameter("rnfl_hrs_two");
		photo.setRnfl_hrs_two(attr);

		String query = "UPDATE Photos SET photo_qual='"+photo.getQual()+"', photo_cdr='"+photo.getCdr()+"', "+
			 	"photo_notch='"+photo.getNotch()+"', notch_hrs_one='"+photo.getNotch_hrs_one()+"', "+
				"notch_hrs_two='"+photo.getNotch_hrs_two()+"', photo_erosion='"+photo.getErosion()+"', "+
				"eros_hrs_one='"+photo.getEros_hrs_one()+"', eros_hrs_two='"+photo.getEros_hrs_two()+"', "+
				"photo_disc='"+photo.getDisc()+"', disc_hrs_one='"+photo.getDisc_hrs_one()+"', "+
				"disc_hrs_two='"+photo.getDisc_hrs_two()+"', photo_rnfl='"+photo.getRnfl()+"', "+
				"rnfl_hrs_one='"+photo.getRnfl_hrs_one()+"', rnfl_hrs_two='"+photo.getRnfl_hrs_two()+"' ";

		if(user.getAccess() == 0) {
			query += " WHERE id='"+photo.getId()+"'";
		} else if(user.getAccess() ==1) { 
			query += ", confirmed=2, adjudicatorID="+user.getID()+" WHERE pictureName='"+request.getParameter("pictureName")+"'"+
				 " AND type='"+type+"' ";
			if(request.getParameter("alreadyConfirmed").equals("true")) {
				result = 2;
			}
		}
		SQLCommands.update(query);
		if(user.getAccess() == 0) {
			setForAdjudication(picName, type);
		}

		return result;
	}

	public static void setForAdjudication(String picName, int type) {
		String query = "SELECT * FROM Photos WHERE pictureName='"+picName+"' AND type='"+type+"'";
		Vector<Photos> photos = SQLCommands.queryPhotos(query);

		if(photos.size() > 1) {
			//get the ones that don't need adjudication
			query = "SELECT * FROM Photos WHERE type='"+type+"' GROUP BY pictureName, "+
				"photo_qual, photo_cdr, " +
				"photo_notch, notch_hrs_one, notch_hrs_two, photo_erosion, eros_hrs_one, "+
				"eros_hrs_two, photo_disc, disc_hrs_one, disc_hrs_two, photo_rnfl, "+
				"rnfl_hrs_one, rnfl_hrs_two " +
				"HAVING COUNT(*)=2";
			Vector<Photos> set = SQLCommands.queryPhotos(query);
			//get the ones that need adjudication
			query = "SELECT * FROM Photos WHERE type='"+type+"' GROUP BY pictureName, "+
				"photo_qual, photo_cdr, " +
				"photo_notch, notch_hrs_one, notch_hrs_two, photo_erosion, eros_hrs_one, "+
				"eros_hrs_two, photo_disc, disc_hrs_one, disc_hrs_two, photo_rnfl, "+
				"rnfl_hrs_one, rnfl_hrs_two " +
				"HAVING COUNT(*)=1";
			Vector<Photos> notSet = SQLCommands.queryPhotos(query);

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
			query = "UPDATE Photos SET confirmed=2";
			query += " WHERE pictureName='"+picName+"' AND type='"+type+"'";
			if(set.size() > 0) {
				SQLCommands.update(query);
			}

			//update the ones that need confirming
			query = "UPDATE Photos SET confirmed=1 WHERE pictureName='"+picName+"' AND type='"+type+"'";
			if(notSet.size() > 0) {
				SQLCommands.update(query);
			}
		}
	}

	public static Vector<String> getUngradedNames() {
		Vector<String> result = new Vector<String>();
		String query = "SELECT * FROM picture WHERE name NOT IN (SELECT pictureName FROM Photos WHERE type="+STEREO+") AND type='stereo'";
		Vector<Picture> pictures = SQLCommands.queryPictures(query);

		for(int i=0; i<pictures.size(); i++) {
			result.add("Stereo - " + pictures.get(i).getName());
		}

		query = "SELECT * FROM picture WHERE name NOT IN (SELECT pictureName FROM Photos WHERE type="+NETHRA+") AND type='3Nethra'";
		pictures = SQLCommands.queryPictures(query);

		for(int i=0; i<pictures.size(); i++) {
			result.add("3Nethra - " + pictures.get(i).getName());
		}

		return result;
	}

	public static Vector<String> getGradedOnceNames() {
		Vector<String> result = new Vector<String>();
		String query = "SELECT * FROM Photos WHERE type='"+STEREO+"' GROUP BY pictureName HAVING COUNT(*)=1";
		Vector<Photos> photos = SQLCommands.queryPhotos(query);

		for(int i=0; i<photos.size(); i++) {
			result.add("Stereo - " + photos.get(i).getPictureName());
		}

		query = "SELECT * FROM Photos WHERE type='"+NETHRA+"' GROUP BY pictureName HAVING COUNT(*)=1";
		photos = SQLCommands.queryPhotos(query);

		for(int i=0; i<photos.size(); i++) {
			result.add("3Nethra - " + photos.get(i).getPictureName());
		}

		return result;
	}
	
	public static Vector<String> getNeedsAdjudication() {
		Vector<String> result = new Vector<String>();
		String query = "SELECT DISTINCT pictureName FROM Photos WHERE confirmed='1' AND type='"+STEREO+"'";
		Vector<String> photo = SQLCommands.queryNames(query);

		for(int i=0; i<photo.size(); i++) {
			result.add("Stereo - " + photo.get(i));
		}

		query = "SELECT DISTINCT pictureName FROM Photos WHERE confirmed='1' AND type='"+NETHRA+"'";
		photo = SQLCommands.queryNames(query);

		for(int i=0; i<photo.size(); i++) {
			result.add("3Nethra - " + photo.get(i));
		}

		return result;
	}

	public static Vector<String> getAdjudicated() {
		Vector<String> result = new Vector<String>();
		String query = "SELECT DISTINCT pictureName FROM Photos WHERE confirmed='2' AND type='"+STEREO+"'";
		Vector<String> photos = SQLCommands.queryNames(query);

		for(int i=0; i<photos.size(); i++) {
			result.add("Stereo - " + photos.get(i));
		}

		query = "SELECT DISTINCT pictureName FROM Photos WHERE confirmed='2' AND type='"+NETHRA+"'";
		photos = SQLCommands.queryNames(query);

		for(int i=0; i<photos.size(); i++) {
			result.add("3Nethra - " + photos.get(i));
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
	 * @return the qual
	 */
	public int getQual() {
		return qual;
	}

	/**
	 * @param qual the qual to set
	 */
	public void setQual(int qual) {
		this.qual = qual;
	}

	/**
	 * @return the cdr
	 */
	public String getCdr() {
		return cdr;
	}

	/**
	 * @param cdr the cdr to set
	 */
	public void setCdr(String cdr) {
		this.cdr = cdr;
	}

	/**
	 * @return the notch
	 */
	public int getNotch() {
		return notch;
	}

	/**
	 * @param notch the notch to set
	 */
	public void setNotch(int notch) {
		this.notch = notch;
	}

	/**
	 * @return the hrs_one
	 */
	public String getNotch_hrs_one() {
		return notch_hrs_one;
	}

	/**
	 * @param hrs_one the hrs_one to set
	 */
	public void setNotch_hrs_one(String hrs_one) {
		this.notch_hrs_one = hrs_one;
	}

	/**
	 * @return the hrs_two
	 */
	public String getNotch_hrs_two() {
		return notch_hrs_two;
	}

	/**
	 * @param notch_hrs_two the hrs_two to set
	 */
	public void setNotch_hrs_two(String notch_hrs_two) {
		this.notch_hrs_two = notch_hrs_two;
	}

	/**
	 * @return the erosion
	 */
	public int getErosion() {
		return erosion;
	}

	/**
	 * @param erosion the erosion to set
	 */
	public void setErosion(int erosion) {
		this.erosion = erosion;
	}

	/**
	 * @return the eros_hrs_one
	 */
	public String getEros_hrs_one() {
		return eros_hrs_one;
	}

	/**
	 * @param eros_hrs_one the eros_hrs_one to set
	 */
	public void setEros_hrs_one(String eros_hrs_one) {
		this.eros_hrs_one = eros_hrs_one;
	}

	/**
	 * @return the eros_hrs_two
	 */
	public String getEros_hrs_two() {
		return eros_hrs_two;
	}

	/**
	 * @param eros_hrs_two the eros_hrs_two to set
	 */
	public void setEros_hrs_two(String eros_hrs_two) {
		this.eros_hrs_two = eros_hrs_two;
	}

	/**
	 * @return the disc
	 */
	public int getDisc() {
		return disc;
	}

	/**
	 * @param disc the disc to set
	 */
	public void setDisc(int disc) {
		this.disc = disc;
	}

	/**
	 * @return the disc_hrs_one
	 */
	public String getDisc_hrs_one() {
		return disc_hrs_one;
	}

	/**
	 * @param disc_hrs_one the disc_hrs_one to set
	 */
	public void setDisc_hrs_one(String disc_hrs_one) {
		this.disc_hrs_one = disc_hrs_one;
	}

	/**
	 * @return the disc_hrs_two
	 */
	public String getDisc_hrs_two() {
		return disc_hrs_two;
	}

	/**
	 * @param disc_hrs_two the disc_hrs_two to set
	 */
	public void setDisc_hrs_two(String disc_hrs_two) {
		this.disc_hrs_two = disc_hrs_two;
	}

	/**
	 * @return the rnfl
	 */
	public int getRnfl() {
		return rnfl;
	}

	/**
	 * @param rnfl the rnfl to set
	 */
	public void setRnfl(int rnfl) {
		this.rnfl = rnfl;
	}

	/**
	 * @return the rnfl_hrs_one
	 */
	public String getRnfl_hrs_one() {
		return rnfl_hrs_one;
	}

	/**
	 * @param rnfl_hrs_one the rnfl_hrs_one to set
	 */
	public void setRnfl_hrs_one(String rnfl_hrs_one) {
		this.rnfl_hrs_one = rnfl_hrs_one;
	}

	/**
	 * @return the rnfl_hrs_two
	 */
	public String getRnfl_hrs_two() {
		return rnfl_hrs_two;
	}

	/**
	 * @param rnfl_hrs_two the rnfl_hrs_two to set
	 */
	public void setRnfl_hrs_two(String rnfl_hrs_two) {
		this.rnfl_hrs_two = rnfl_hrs_two;
	}
	
}
