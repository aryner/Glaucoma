/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author aryner
 */
public interface BaseTest {
	public static int FDT = 1;
	public static int MDT = 2;
	public static int OCT = 3;
	public static int STEREO = 4;
	public static int NETHRA = 5;

	public String getPictureName();
	public void setPictureName(String pictureName);
	public int getUserID();
	public void setUserID(int userID);
	public int getAdjudicatorID();
	public void setAdjudicatorID(int adjudicatorID);
	public int getConfirmed();
	public void setConfirmed(int confimred);
	public int getId();
	public void setId(int id);
	public int getBaseType();
	public void setBaseType(int type);
}
