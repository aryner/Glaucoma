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
		try {
			String slash = System.getProperty("file.separator");
			FileOutputStream fileOut = new FileOutputStream(System.getProperty("user.home")+slash+"Desktop"+slash+fileName);

			for(int i=0; i<lines.size(); i++) {
				fileOut.write((lines.get(i)+"\n").getBytes());
			}

			fileOut.close();
		}
		catch(Exception e) { e.printStackTrace(); }
	}
}
