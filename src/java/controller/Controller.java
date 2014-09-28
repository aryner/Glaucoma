/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import model.*;
import java.io.*; 
import java.util.*; 
import java.awt.image.*;
import java.awt.Image; 
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.servlet.ServletOutputStream; 
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession; 
import java.util.logging.Level;
import java.util.logging.Logger; 
import javax.imageio.ImageIO;
import org.apache.commons.fileupload.FileItem; 
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload; 

/**
 *
 * @author aryner
 */
@WebServlet(name = "Controller", urlPatterns = {"/Controller","/login","/home","/logout","/register","/createUser","/FDTtest","/HVFtest","/MDTtest","/OCTtest","/nethra","/stereo", "/upload", "/uploadPictures", "/img", "/pdf"})
public class Controller extends HttpServlet {
	private final String slash = System.getProperty("file.separator");
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
//		PrintWriter out = response.getWriter();
		String userPath = request.getServletPath(); 
		HttpSession session = request.getSession(); 

		//The user is not logged in so is redirected to the index/login page
		if(session.getAttribute("user") == null && !userPath.equals("/register")) {
			response.sendRedirect("/Glaucoma/index.jsp");
			return;
		}

		else if(userPath.equals("/HVFtest")) {
			Picture picture = HVFtest.getNext(((User)session.getAttribute("user")));

			request.setAttribute("slash",slash);
			request.setAttribute("picture",picture);
		}

		else if (userPath.equals("/pdf")) {
			String name = request.getParameter("name");
			String type = request.getParameter("type");
			response.setContentType("application/pdf");
			ServletOutputStream outPut = response.getOutputStream(); 
			FileInputStream imgStream = new FileInputStream(".."+slash+"webapps"+slash+"Glaucoma"+slash+type+slash+name);

			BufferedInputStream bufferedIn = new BufferedInputStream(imgStream);
			BufferedOutputStream bufferedOut = new BufferedOutputStream(outPut);

			int nextByte = 0; 
			while((nextByte = bufferedIn.read()) != -1) {
				bufferedOut.write(nextByte);
			}

			bufferedIn.close();
			imgStream.close();
			bufferedOut.close();
			outPut.close();
			return;
		
		}
		
		else if (userPath.equals("/img")) {
			String name = request.getParameter("name");
			String type = request.getParameter("type");
			response.setContentType("image/jpeg");
			ServletOutputStream outPut = response.getOutputStream(); 
			FileInputStream imgStream = new FileInputStream(".."+slash+"webapps"+slash+"Glaucoma"+slash+type+slash+name);

			BufferedInputStream bufferedIn = new BufferedInputStream(imgStream);
			BufferedOutputStream bufferedOut = new BufferedOutputStream(outPut);

			int nextByte = 0; 
			while((nextByte = bufferedIn.read()) != -1) {
				bufferedOut.write(nextByte);
			}

			bufferedIn.close();
			imgStream.close();
			bufferedOut.close();
			outPut.close();
			return;
		}

		else  if(userPath.equals("/home")) {

		}

		String url = "/WEB-INF/view" + userPath + ".jsp";

		try {
			request.getRequestDispatcher(url).forward(request, response);
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String userPath = request.getServletPath(); 
		HttpSession session = request.getSession(); 

		if(userPath.equals("/login")) {
			String name = request.getParameter("userName");
			String password = request.getParameter("password");
			User user = new User(name, password);

			if(user.getUserName() != null) {
				session.setAttribute("user", user); 
				response.sendRedirect("/Glaucoma/home"); 
				return;
			}
			else { 
				session.setAttribute("error", "Incorrect password or username");
				response.sendRedirect("/Glaucoma/index.jsp"); 
				return;
			} 
		}

		else if(userPath.equals("/uploadPictures")) {
			Picture.uploadDocs(request);

			response.sendRedirect("/Glaucoma/index.jsp"); 
			return;
		}

		else if(userPath.equals("/logout")) { 
			session.removeAttribute("user");
			session.removeAttribute("categoryID");
			response.sendRedirect("/Glaucoma/index.jsp"); 
			return;
		}

		else if(userPath.equals("/createUser")) {
			String name = request.getParameter("userName");
			String password = request.getParameter("password");
			String rePassword = request.getParameter("rePassword");

			if(password.equals(rePassword)){
				User user = User.createUser(name, password);

				if(user == null) {
					session.setAttribute("error", "That user name has been taken");
					response.sendRedirect("/Glaucoma/register");
					return;
				}
				else { 
					session.setAttribute("user", user); 
					response.sendRedirect("/Glaucoma/home"); 
					return;
				} 
			}
			else {
				session.setAttribute("error", "Passwords do not match");
				response.sendRedirect("/Glaucoma/register"); 
				return;
			}
		}

		else {
			String url = "/WEB-INF/view" + userPath + ".jsp";

			try {
				request.getRequestDispatcher(url).forward(request, response);
			} catch (Exception ex){
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
