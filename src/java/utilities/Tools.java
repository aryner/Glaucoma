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

	public static void createCSVs(Vector<String> lines, String fileName) {
		fileName += getFormattedDate();
		String slash = System.getProperty("file.separator");
		new File(System.getProperty("user.home")+slash+"Desktop"+slash+fileName+".csv").delete();

		try {
			slash = System.getProperty("file.separator");
			FileOutputStream fileOut = new FileOutputStream(System.getProperty("user.home")+slash+"Desktop"+slash+fileName+".csv");

			for(int i=0; i<lines.size(); i++) {
				fileOut.write((lines.get(i)+"\n").getBytes());
			}

			fileOut.close();
		}
		catch(Exception e) { e.printStackTrace(); }
	}

	public static void createCSV(int type) {
		switch(type) {
			case BaseTest.HVF:
				createCSVs(HVFtest.getCSVLines(), "hvf_grades");
				break;
			case BaseTest.FDT:
				createCSVs(FDTtest.getCSVLines(), "fdt_grades");
				break;
			case BaseTest.MDT:
				createCSVs(MDTtest.getCSVLines(), "mdt_grades");
				break;
			case BaseTest.OCT:
				createCSVs(OCTtest.getCSVLines(), "oct_grades");
				break;
			case BaseTest.STEREO:
				createCSVs(Photos.getStereoCSVLines(), "stereo_grades");
				break;
			case BaseTest.NETHRA:
				createCSVs(Photos.getNethraCSVLines(), "3Nethra_grades");
				break;
			case BaseTest.IPAD:
				createCSVs(IPad.getCSVLines(), "iPad_grades");
				break;
		}
	}

	public static String getFormattedDate() {
		String result = "";
		Date date = new Date(System.currentTimeMillis());
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		int day = cal.get(Calendar.DAY_OF_MONTH);
		if(day < 10) {
			result += "0";
		}
		result += day;

		int month = cal.get(Calendar.MONTH);

		switch (month) {
			case 0:
				result += "jan";
				break;
			case 1:
				result += "feb";
				break;
			case 2:
				result += "mar";
				break;
			case 3:
				result += "apr";
				break;
			case 4:
				result += "may";
				break;
			case 5:
				result += "jun";
				break;
			case 6:
				result += "jul";
				break;
			case 7:
				result += "aug";
				break;
			case 8:
				result += "sep";
				break;
			case 9:
				result += "oct";
				break;
			case 10:
				result += "nov";
				break;
			case 11:
				result += "dec";
				break;
		}

		result += cal.get(Calendar.YEAR);

		return result;
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
						else if(type.equals("hvf_pictures")) {}
						else { errors.add(fileName); }
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

	public static ArrayList<String> needPictures(){
		ArrayList<String> result = FDTtest.needPictures();
		result.addAll(MDTtest.needPictures());
		result.addAll(OCTtest.needPictures());
		return result;
	}

	public static Vector<String> getUngradedNames() {
		Vector<String> result = new Vector<String>();
		result.addAll(HVFtest.getUngradedNames());
		result.addAll(FDTtest.getUngradedNames());
		result.addAll(MDTtest.getUngradedNames());
		result.addAll(OCTtest.getUngradedNames());
		result.addAll(Photos.getUngradedNames());
		result.addAll(IPad.getUngradedNames());

		return result;
	}

	public static Vector<String> getGradedOnceNames() {
		Vector<String> result = new Vector<String>();
		result.addAll(HVFtest.getGradedOnceNames());
		result.addAll(FDTtest.getGradedOnceNames());
		result.addAll(MDTtest.getGradedOnceNames());
		result.addAll(OCTtest.getGradedOnceNames());
		result.addAll(Photos.getGradedOnceNames());
		result.addAll(IPad.getGradedOnceNames());

		return result;
	}

	public static Vector<String> getNeedsAdjudication() {
		Vector<String> result = new Vector<String>();
		result.addAll(HVFtest.getNeedsAdjudication());
		result.addAll(FDTtest.getNeedsAdjudication());
		result.addAll(MDTtest.getNeedsAdjudication());
		result.addAll(OCTtest.getNeedsAdjudication());
		result.addAll(Photos.getNeedsAdjudication());
		result.addAll(IPad.getNeedsAdjudication());

		return result;
	}

	public static Vector<String> getAdjudicated() {
		Vector<String> result = new Vector<String>();
		result.addAll(FDTtest.getAdjudicated());
		result.addAll(MDTtest.getAdjudicated());
		result.addAll(OCTtest.getAdjudicated());
		result.addAll(Photos.getAdjudicated());
		result.addAll(IPad.getAdjudicated());

		return result;
	}

	public static Vector<BaseTest> getBaseTests(int id) {
		Vector<BaseTest> result = new Vector<BaseTest>();
		result.addAll(FDTtest.getBaseTest(id));
		result.addAll(MDTtest.getBaseTest(id));
		result.addAll(OCTtest.getBaseTest(id));
		result.addAll(Photos.getBaseTest(id));
		result.addAll(IPad.getBaseTest(id));

		return result;
	}

	public static void splitCounts(ArrayList<String> csvLines, int type) {
		switch (type) {
			case BaseTest.HVF :
				String [] fields = csvLines.get(0).split(", ");
				if(fields.length == 59) {
					return;
				}
				for(int i=0; i<csvLines.size(); i++) {
					fields = csvLines.get(i).split(", ");
					csvLines.set(i, HVFtest.splitCount(fields));
				}
				break;
		}
	}
}
