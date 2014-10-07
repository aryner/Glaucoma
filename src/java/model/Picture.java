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

	public static Vector<String> uploadDocs(HttpServletRequest request) {
		Vector<String> names = new Vector<String>();
		Vector<String> errors = new Vector<String>();
		Vector<FileItem> files= new Vector<FileItem>();
		String path = null;

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

			for(int j=0; j< names.size(); j++) {
				FileItem fileItem = files.get(j);
				String fileName = names.get(j);

				String filePath = ".."+slash+"webapps"+slash+"Glaucoma"+slash + path + slash;
				new File(filePath).mkdirs();
				File file = new File(filePath + fileName);
				fileItem.write(file); 
			}
		
		} catch (Exception ex) {
			Logger.getLogger(Picture.class.getName()).log(Level.SEVERE,null,ex);
		}

		String query = "INSERT INTO picture (name, type) VALUES ";
		for(int i=0; i<names.size(); i++) {
			if (i>0) { query += ", "; }
			query += "('"+names.get(i)+"', '"+path+"')";
		}
		if(names.size() > 0) {
			SQLCommands.update(query); 
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
