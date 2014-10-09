/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import model.*;
import utilities.*;
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
@WebServlet(name = "Controller", urlPatterns = {"/Controller","/login","/home","/logout","/register","/createUser","/FDTtest","/HVFtest","/MDTtest","/OCTtest","/nethra","/stereo", "/upload", "/uploadPictures", "/img", "/pdf", "/assignHVF", "/OpHVFtest", "/OpReviewHVF", "/printCSV", "/printCSVs"})
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
			response.sendRedirect("/HVF/index.jsp");
			return;
		}

		else if(userPath.equals("/home")) {
			User user = (User)session.getAttribute("user");
			request.setAttribute("access",user.getAccess());
			if(user.getAccess() == 1) {
				request.setAttribute("HVFACount", HVFtest.needAdjudicationCount());
			}
			else if(user.getAccess() == 2) {
				request.setAttribute("HVFACount", HVFtest.getOpCheckCount());
			}
		}

		else if(userPath.equals("/HVFtest")) {
			User user = (User)session.getAttribute("user");
			Picture picture = HVFtest.getNext(user);
			if(picture == null) {
				if(user.getAccess() == 0) {
					Integer needToPairCount = HVFtest.getNeedToPairCount(); 
					request.setAttribute("needToPairCount", needToPairCount);
					if(needToPairCount == 0) {
						//saftey measure to ensure nothing was missed but shouldn't be needed
						HVFtest.setAllForAdjudication();
					}
				}
				if(user.getAccess() == 1 && HVFtest.needInitialCount() == 0) {
					//add 10% of no glau to be checked by opth
					HVFtest.addNegatives(0.1);
				}
			}
			if(user.getAccess() == 1 && picture != null) {
				Vector<HVFtest> pair = HVFtest.getPair(picture.getName());
				request.setAttribute("pair",pair);
			}

			request.setAttribute("access", user.getAccess());
			request.setAttribute("slash",slash);
			request.setAttribute("picture",picture);
		}

		else if (userPath.equals("/OpHVFtest")) {
			User user = (User)session.getAttribute("user");
			Picture picture = HVFtest.getNext(user);

			if(picture != null) {
				request.setAttribute("hvf",HVFtest.getOpHVF(picture.getName()));
			}
			request.setAttribute("slash",slash);
			request.setAttribute("picture",picture);
		}

		else if (userPath.equals("/printCSV")){
			request.setAttribute("pics", Picture.getCSVLines());
			request.setAttribute("hvf", HVFtest.getCSVLines());
		}

		else if (userPath.equals("/pdf")) {
			String name = request.getParameter("name");
			String type = request.getParameter("type");
			response.setContentType("application/pdf");
			ServletOutputStream outPut = response.getOutputStream(); 
			FileInputStream imgStream = new FileInputStream(".."+slash+"webapps"+slash+"HVF"+slash+type+slash+name);

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
			FileInputStream imgStream = new FileInputStream(".."+slash+"webapps"+slash+"HVF"+slash+type+slash+name);

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
				response.sendRedirect("/HVF/home"); 
				return;
			}
			else { 
				session.setAttribute("error", "Incorrect password or username");
				response.sendRedirect("/HVF/index.jsp"); 
				return;
			} 
		}

		else if(userPath.equals("/uploadPictures")) {
			Vector<String> errors = Picture.uploadDocs(request);

			if(errors.size()>0) {
				session.setAttribute("errors", errors);
			}
			response.sendRedirect("/HVF/home"); 
			return;
		}

		else if(userPath.equals("/assignHVF")) {
			User user = (User)session.getAttribute("user");
			HVFtest.assignHVF(request, user);
		
			response.sendRedirect("/HVF/HVFtest"); 
			return;
		}

		else if(userPath.equals("/OpReviewHVF")) {
			User user = (User)session.getAttribute("user");
			HVFtest.opthAssignHVF(request, user);

			response.sendRedirect("/HVF/OpHVFtest"); 
			return;
		}

		else if(userPath.equals("/printCSVs")) {
			Tools.createCSV(Picture.getCSVLines(),"hvf_pictures"+System.currentTimeMillis()+".csv");
			Tools.createCSV(HVFtest.getCSVLines(),"hvf_grades"+System.currentTimeMillis()+".csv");
			response.sendRedirect("/HVF/home"); 
			return;
		}

		else if(userPath.equals("/logout")) { 
			session.removeAttribute("user");
			session.removeAttribute("categoryID");
			response.sendRedirect("/HVF/index.jsp"); 
			return;
		}

		else if(userPath.equals("/createUser")) {
			String name = request.getParameter("userName");
			String password = request.getParameter("password");
			String rePassword = request.getParameter("rePassword");
			String type = request.getParameter("graderType");

			if(password.equals(rePassword)){
				User user = User.createUser(name, password, type);

				if(user == null) {
					session.setAttribute("error", "That user name has been taken");
					response.sendRedirect("/HVF/register");
					return;
				}
				else { 
					session.setAttribute("user", user); 
					response.sendRedirect("/HVF/home"); 
					return;
				} 
			}
			else {
				session.setAttribute("error", "Passwords do not match");
				response.sendRedirect("/HVF/register"); 
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
