/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utilities;

import java.util.*;
import java.io.FileOutputStream;

/**
 *
 * @author aryner
 */
public class Tools {

	public static void createCSV(Vector<String> lines, String fileName) {
		Date date = new Date(System.currentTimeMillis());
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		fileName += cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH);

		switch (month) {
			case 0:
				fileName += "jan";
				break;
			case 1:
				fileName += "feb";
				break;
			case 2:
				fileName += "mar";
				break;
			case 3:
				fileName += "apr";
				break;
			case 4:
				fileName += "may";
				break;
			case 5:
				fileName += "jun";
				break;
			case 6:
				fileName += "jul";
				break;
			case 7:
				fileName += "aug";
				break;
			case 8:
				fileName += "sep";
				break;
			case 9:
				fileName += "oct";
				break;
			case 10:
				fileName += "nov";
				break;
			case 11:
				fileName += "dec";
				break;
		}

		fileName += cal.get(Calendar.YEAR);

		try {
			String slash = System.getProperty("file.separator");
			FileOutputStream fileOut = new FileOutputStream(System.getProperty("user.home")+slash+"Desktop"+slash+fileName+".csv");

			for(int i=0; i<lines.size(); i++) {
				fileOut.write((lines.get(i)+"\n").getBytes());
			}

			fileOut.close();
		}
		catch(Exception e) { e.printStackTrace(); }
	}
}
