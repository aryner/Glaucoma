/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.*;
import java.io.*;
import javax.servlet.http.HttpServletRequest;
import java.util.logging.Level;
import java.util.logging.Logger; 
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Image;
import org.apache.commons.fileupload.FileItem; 
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload; 
import utilities.SQLCommands;

/**
 *
 * @author aryner
 */
public class Picture {
	private final int id;
	private final String name;
	private final String type;
	private static final String slash = System.getProperty("file.separator");

	public Picture(int nid, String nname, String ntype) {
		id = nid;
		name = nname;
		type = ntype;
	}

	public static Picture getPictureByName(String picName) {
		String query = "SELECT * FROM picture WHERE name='"+picName+"'";
		Vector<Picture> pics = SQLCommands.queryPictures(query);

		if(pics == null || pics.size() == 0 ) {
			return null;
		}
		return pics.get(0);
	}

	public static Vector<String> uploadDocs(HttpServletRequest request) {
		Vector<String> names = new Vector<String>();
		Vector<String> imgNames = new Vector<String>();
		Vector<String> errors = new Vector<String>();
		Vector<FileItem> files= new Vector<FileItem>();
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

			while(i.hasNext()) {
				FileItem fileItem = (FileItem)i.next();

				if(!fileItem.isFormField()) { 
					String fileName = fileItem.getName();
					extension = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());

					if(extension.equals("pdf") || extension.equals("PDF")) {
						names.add(fileName);
						files.add(fileItem);
					}
					else if(extension.equals("jpg") || extension.equals("JPG") ||
						extension.equals("gif") || extension.equals("GIF") ||
						extension.equals("png") || extension.equals("PNG")) {

						Document document = new Document();
						String tempPath = ".."+slash+"webapps"+slash+"HVF"+slash+"tempImg"+slash;
						String finalPath = ".."+slash+"webapps"+slash+"HVF"+slash + path + slash;
						new File(finalPath).mkdirs();
						String temp = fileName.substring(0,fileName.indexOf("."));
						finalPath += temp + ".pdf";
						new File(tempPath).mkdirs();
						File tempFile = new File(tempPath+fileName);
						fileItem.write(tempFile);

						try {
							FileOutputStream outStream = new FileOutputStream(finalPath);
							PdfWriter writer = PdfWriter.getInstance(document, outStream);
							writer.open();
							document.open();
							Image img = Image.getInstance(tempPath+fileName);
							img.scalePercent(50);
							document.add(img);
							document.close();
							writer.close();
							imgNames.add(temp+".pdf");
							tempFile.delete();
						}
						catch(Exception e) {
							e.printStackTrace();
						}
					}
					else {
						errors.add(fileName);
					}
				}
				else {
					String field = fileItem.getString();
					if(field != null && !field.equals("")) {
						if(fileItem.getFieldName().equals("type")) {
							path = field;
						}
					}
				}
			}

			if(path.equals("HVF")) {
				for(int j=0; j< names.size(); j++) {
					FileItem fileItem = files.get(j);
					String fileName = names.get(j);

					String filePath = ".."+slash+"webapps"+slash+"HVF"+slash + path + slash;
					new File(filePath).mkdirs();
					File file = new File(filePath + fileName);
					fileItem.write(file); 
				}
			}
			else if (path.equals("grading")) {
				for(int j=0; j< names.size(); j++) {
					FileItem fileItem = files.get(j);
					String fileName = names.get(j);

					String filePath = ".."+slash+"webapps"+slash+"HVF"+slash + path + slash;
					new File(filePath).mkdirs();
					File file = new File(filePath + fileName);
					fileItem.write(file); 
				}
			}
			else if (path.equals("severity")) {
				for(int j=0; j< names.size(); j++) {
					FileItem fileItem = files.get(j);
					String fileName = names.get(j);

					String filePath = ".."+slash+"webapps"+slash+"HVF"+slash + path + slash;
					new File(filePath).mkdirs();
					File file = new File(filePath + fileName);
					fileItem.write(file); 
				}
			}
		
		} catch (Exception ex) {
			Logger.getLogger(Picture.class.getName()).log(Level.SEVERE,null,ex);
		}

		names.addAll(imgNames);
		String query = "";
		if(!path.equals("grading") && !path.equals("severity")){
			query = "SELECT * FROM picture WHERE type='"+path+"'";
			Vector<Picture> oldPics = SQLCommands.queryPictures(query);

			for(int i=0; i<oldPics.size(); i++) {
				for(int j=0; j<names.size(); j++) {
					if(oldPics.get(i).getName().equals(names.get(j))) {
						errors.add(names.remove(j));
					}
				}
			}
		}

		query = "INSERT INTO picture (name, type) VALUES ";
		for(int i=0; i<names.size(); i++) {
			if (i>0) { query += ", "; }
			query += "('"+names.get(i)+"', '"+path+"')";
		}
		if(names.size() > 0) {
			SQLCommands.update(query); 
		}
		String ext;
		for(int i=0; i<errors.size(); i++) {
			ext = errors.get(i).substring(errors.get(i).indexOf(".")+1, errors.get(i).length());
			if(ext.equals("pdf")) {
				errors.set(i, errors.get(i).substring(0,errors.get(i).indexOf("."))+".jpg");
			}
		}
		return errors;
	}

	public static Vector<String> getCSVLines() {
		Vector<String> result = new Vector<String>();
		String query = "SELECT * FROM picture";
		Vector<Picture> pics = SQLCommands.queryPictures(query);

		result.add("id, name, type");
		for(int i=0; i<pics.size(); i++) {
			result.add(pics.get(i).getId()+", "+pics.get(i).getName()+", "+pics.get(i).getType());
		}

		return result;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
}
