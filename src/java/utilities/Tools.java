/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utilities;

import model.*;
import java.util.*;
import java.io.*;
import java.io.FileOutputStream;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem; 
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload; 
import utilities.SQLCommands;

/**
 *
 * @author aryner
 */
public class Tools {
	private static final String slash = System.getProperty("file.separator");

	public static void createCSV(Vector<String> lines, String fileName) {
		Date date = new Date(System.currentTimeMillis());
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		int day = cal.get(Calendar.DAY_OF_MONTH);
		if(day < 10) {
			fileName += "0";
		}
		fileName += day;

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

	public static Vector<String> readCSVs(HttpServletRequest request) {
		Vector<String> errors = new Vector<String>();
		Vector<String> names = new Vector<String>();
		String path = "";

		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(100 * 1024);
		new File(".."+slash+"temp").mkdirs();
		factory.setRepository(new File(".."+slash+"temp"));

		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(4000000 * 1024);

		try {
			List fileItems = upload.parseRequest(request);
			Iterator i = fileItems.iterator();
			String extension;
			String type;
			int index;

			while (i.hasNext()) {
				FileItem item = (FileItem)i.next();
				index = -1;

				if(!item.isFormField()) {
					String fileName = item.getName();
					extension = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
					for(int j=0; j<fileName.length() && index < 0; j++) {
						if(Character.isDigit(fileName.charAt(j))) {
							index = j;
						}
					}
					if(index < 0) {
						index = fileName.length()-extension.length()-2;
					}

					if(extension.equals("csv") || extension.equals("CSV")) {
						type = fileName.substring(0,index);

						if(type.equals("hvf_grades")) {
							path = ".."+slash+"webapps"+slash+"HVF"+slash+"temp"+slash;
							new File(path).mkdirs();
							File file = new File(path+fileName);
							item.write(file);

							HVFtest.readCSV(fileName);
							file.delete();
						}
					}
					else {
						errors.add(fileName);
					}
				}
			}
		} 
		catch (Exception e) { e.printStackTrace(); }
	
		return errors;
	}
}
