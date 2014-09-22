/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.*;
import java.io.*;
import java.awt.image.*;
import java.awt.Image; 
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import javax.servlet.ServletOutputStream; 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.logging.Level;
import java.util.logging.Logger; 
import org.apache.commons.fileupload.FileItem; 
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload; 

/**
 *
 * @author aryner
 */
public class Picture {
	private final int id;
	private final String name;
	private final String type;

	public Picture(int nid, String nname, String ntype) {
		id = nid;
		name = nname;
		type = ntype;
	}

	public static void createPictures(HttpServletRequest request) {
		Vector<String> names = new Vector<String>();
		String path = null;

		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(100 * 1024);
		factory.setRepository(new File("../temp;"));

		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(4000000 * 1024);

		try {
			List fileItems = upload.parseRequest(request);
			Iterator i = fileItems.iterator();
			String extension;

			while(i.hasNext()) {
				FileItem fileItem = (FileItem)i.next();

				if(!fileItem.isFormField()) {
					String fieldName = fileItem.getFieldName();
					String fileName = fileItem.getName();
					extension = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());

					names.add(fileName);
					String filePath = "../webapps/Glaucoma/" + path + "/";
					new File(filePath).mkdirs();
					File file = new File(filePath + fileName);
					fileItem.write(file);
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
		
		} catch (Exception ex) {
			Logger.getLogger(Picture.class.getName()).log(Level.SEVERE,null,ex);
		}


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
