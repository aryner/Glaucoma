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
import javax.servlet.ServletOutputStream; 
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession; 

/**
 *
 * @author aryner
 */
@WebServlet(name = "Controller", urlPatterns = {"/Controller","/login","/home","/logout","/register","/createUser","/FDTtest","/HVFtest","/MDTtest","/OCTtest","/nethra","/stereo", "/upload", "/uploadPictures", "/img", "/pdf", "/assignHVF", "/OpHVFtest", "/OpReviewHVF", "/printCSV", "/printCSVs", "/uploadData", "/dataUpload", "/assignFDT", "/assignMDT", "/assignOCT", "/assign3Nethra", "/assignStereo"})
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

		else if(userPath.equals("/home")) {
			User user = (User)session.getAttribute("user");
			request.setAttribute("access",user.getAccess());

			if(user.getAccess() == 1) {
				request.setAttribute("HVFACount", HVFtest.needAdjudicationCount());
			}
			else if(user.getAccess() == 2) {
				request.setAttribute("HVFACount", HVFtest.getOpCheckCount());
			}

			Vector<String> ungraded = Tools.getUngradedNames();
			Vector<String> onceGraded = Tools.getGradedOnceNames();
			Vector<String> needsAdj = Tools.getNeedsAdjudication();
			Vector<String> needsReview = HVFtest.getNeedsReview();
			Vector<String> reviewed = HVFtest.getReviewed();

			if(user.getAccess() == 2) {
				request.setAttribute("reviewedBy", HVFtest.getReviewedBy(user.getUserName()));
			}
			if(user.getAccess() == 1) {
				request.setAttribute("adjudicatedBy", HVFtest.getAdjudicatedBy(user.getID()));
			}

			request.setAttribute("ungraded",ungraded);
			request.setAttribute("gradedOnce",onceGraded);
			request.setAttribute("needsAdj",needsAdj);
			request.setAttribute("needsReview",needsReview);
			request.setAttribute("reviewed",reviewed);
			request.setAttribute("hvfNeedPictures", HVFtest.needPictures());
		}

		else if (userPath.equals("/upload")) {
			request.setAttribute("hvfNeedPictures", HVFtest.needPictures());
		}

		else if(userPath.equals("/FDTtest")) {
			String pictureName = request.getParameter("pictureName");
			User user = (User)session.getAttribute("user");
			Picture picture;

			if(pictureName != null && pictureName.length() > 0) {
				picture = Picture.getPictureByName(pictureName);
				request.setAttribute("confirmed", "true");
			}
			else {
				picture = FDTtest.getNext(user);
				if(picture == null) {
					if(user.getAccess() == 0) {
						Integer needToPairCount = FDTtest.getNeedToPairCount();
						request.setAttribute("needToPairCount", needToPairCount);
					}
				}
			}

			if(user.getAccess() == 1 && picture != null) {
				Vector<FDTtest> pair = FDTtest.getPair(picture.getName());
				request.setAttribute("pair",pair);
			}

			request.setAttribute("access", user.getAccess());
			request.setAttribute("slash",slash);
			request.setAttribute("picture",picture);
			request.setAttribute("missingPics", Tools.needPictures());
		}

		else if(userPath.equals("/MDTtest")) {
			String pictureName = request.getParameter("pictureName");
			User user = (User)session.getAttribute("user");
			Picture picture;

			if(pictureName != null && pictureName.length() > 0) {
				picture = Picture.getPictureByName(pictureName);
				request.setAttribute("confirmed", "true");
			}
			else {
				picture = MDTtest.getNext(user);
				if(picture == null) {
					if(user.getAccess() == 0) {
						Integer needToPairCount = MDTtest.getNeedToPairCount();
						request.setAttribute("needToPairCount", needToPairCount);
					}
				}
			}

			if(user.getAccess() == 1 && picture != null) {
				Vector<MDTtest> pair = MDTtest.getPair(picture.getName());
				request.setAttribute("pair",pair);
			}

			request.setAttribute("access", user.getAccess());
			request.setAttribute("slash",slash);
			request.setAttribute("picture",picture);
			request.setAttribute("missingPics", Tools.needPictures());
		}

		else if(userPath.equals("/OCTtest")) {
			String pictureName = request.getParameter("pictureName");
			User user = (User)session.getAttribute("user");
			Picture picture;

			if(pictureName != null && pictureName.length() > 0) {
				picture = Picture.getPictureByName(pictureName);
				request.setAttribute("confirmed", "true");
			}
			else {
				picture = OCTtest.getNext(user);
				if(picture == null) {
					if(user.getAccess() == 0) {
						Integer needToPairCount = OCTtest.getNeedToPairCount();
						request.setAttribute("needToPairCount", needToPairCount);
					}
				}
			}

			if(user.getAccess() == 1 && picture != null) {
				Vector<OCTtest> pair = OCTtest.getPair(picture.getName());
				request.setAttribute("pair",pair);
			}

			request.setAttribute("access", user.getAccess());
			request.setAttribute("slash",slash);
			request.setAttribute("picture",picture);
			request.setAttribute("missingPics", Tools.needPictures());
		}

		else if(userPath.equals("/stereo")) {
			String pictureName = request.getParameter("pictureName");
			User user = (User)session.getAttribute("user");
			Picture picture;

			if(pictureName != null && pictureName.length() > 0) {
				picture = Picture.getPictureByName(pictureName);
				request.setAttribute("confirmed", "true");
			}
			else {
				picture = Photos.getNextStereo(user);
				if(picture == null) {
					if(user.getAccess() == 0) {
						Integer needToPairCount = Photos.getNeedToPairCountStereo();
						request.setAttribute("needToPairCount", needToPairCount);
					}
				}
			}

			if(user.getAccess() == 1 && picture != null) {
				Vector<Photos> pair = Photos.getPairStereo(picture.getName());
				request.setAttribute("pair",pair);
			}

			request.setAttribute("access", user.getAccess());
			request.setAttribute("slash",slash);
			request.setAttribute("picture",picture);
			request.setAttribute("missingPics", Tools.needPictures());
		}
		else if(userPath.equals("/nethra")) {
			String pictureName = request.getParameter("pictureName");
			User user = (User)session.getAttribute("user");
			Picture picture;

			if(pictureName != null && pictureName.length() > 0) {
				picture = Picture.getPictureByName(pictureName);
				request.setAttribute("confirmed", "true");
			}
			else {
				picture = Photos.getNextNethra(user);
				if(picture == null) {
					if(user.getAccess() == 0) {
						Integer needToPairCount = Photos.getNeedToPairCountNethra();
						request.setAttribute("needToPairCount", needToPairCount);
					}
				}
			}

			if(user.getAccess() == 1 && picture != null) {
				Vector<Photos> pair = Photos.getPairNethra(picture.getName());
				request.setAttribute("pair",pair);
			}

			request.setAttribute("access", user.getAccess());
			request.setAttribute("slash",slash);
			request.setAttribute("picture",picture);
			request.setAttribute("missingPics", Tools.needPictures());
		}

		else if(userPath.equals("/HVFtest")) {
			String pictureName = request.getParameter("pictureName");
			User user = (User)session.getAttribute("user");
			Picture picture = null;

			if(pictureName != null && pictureName.length() > 0) {
				picture = Picture.getPictureByName(pictureName);
				request.setAttribute("confirmed","true");
			}
			else {
				picture = HVFtest.getNext(user);
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
						//HVFtest.addNegatives(0.1);
					}
				}
			}

			if(user.getAccess() == 1 && picture != null) {
				Vector<HVFtest> pair = HVFtest.getPair(picture.getName());
				request.setAttribute("pair",pair);
			}

			request.setAttribute("gradingChart", SQLCommands.queryGradingChartName());
			request.setAttribute("access", user.getAccess());
			request.setAttribute("slash",slash);
			request.setAttribute("picture",picture);
			request.setAttribute("missingPics", HVFtest.needPictures());
		}

		else if (userPath.equals("/OpHVFtest")) {
			Picture picture = null;
			String pictureName = request.getParameter("pictureName");

			if (pictureName != null && pictureName.length() > 0) {
				picture = Picture.getPictureByName(pictureName);
			} 
			else {
				User user = (User)session.getAttribute("user");
				picture = HVFtest.getNext(user);
			}

			if(picture != null) {
				request.setAttribute("hvf",HVFtest.getOpHVF(picture.getName()));
			}
			request.setAttribute("chart",SQLCommands.querySeverityChartName());
			request.setAttribute("slash",slash);
			request.setAttribute("picture",picture);
			request.setAttribute("missingPics", HVFtest.needPictures());
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
			Vector<String> errors = Picture.uploadDocs(request);

			if(errors.size()>0) {
				session.setAttribute("errors", errors);
			}
			response.sendRedirect("/Glaucoma/home"); 
			return;
		}

		else if(userPath.equals("/dataUpload")) {
			Vector<String> errors = Tools.readCSVs(request);

			response.sendRedirect("/Glaucoma/home"); 
			return;
		}

		else if(userPath.equals("/assignHVF")) {
			User user = (User)session.getAttribute("user");
			int returnType = HVFtest.assignHVF(request, user);

			if(returnType == 2) {
				response.sendRedirect("/Glaucoma/home"); 
				return;
			}
		
			response.sendRedirect("/Glaucoma/HVFtest"); 
			return;
		}

		else if(userPath.equals("/assignFDT")) {
			User user = (User)session.getAttribute("user");
			int returnType = FDTtest.assignFDT(request, user);

			if(returnType == 2) {
				response.sendRedirect("/Glaucoma/home"); 
				return;
			}
		
			response.sendRedirect("/Glaucoma/FDTtest"); 
			return;
		}

		else if(userPath.equals("/assignMDT")) {
			User user = (User)session.getAttribute("user");
			int returnType = MDTtest.assignMDT(request, user);

			if(returnType == 2) {
				response.sendRedirect("/Glaucoma/home"); 
				return;
			}
		
			response.sendRedirect("/Glaucoma/MDTtest"); 
			return;
		}

		else if(userPath.equals("/assignOCT")) {
			User user = (User)session.getAttribute("user");
			int returnType = OCTtest.assignOCT(request, user);

			if(returnType == 2) {
				response.sendRedirect("/Glaucoma/home"); 
				return;
			}
		
			response.sendRedirect("/Glaucoma/OCTtest"); 
			return;
		}

		else if(userPath.equals("/assignStereo")) {
			User user = (User)session.getAttribute("user");
			int returnType = Photos.assignPhoto(request, user, Photos.STEREO);

			if(returnType == 2) {
				response.sendRedirect("/Glaucoma/home"); 
				return;
			}
		
			response.sendRedirect("/Glaucoma/stereo"); 
			return;
		}

		else if(userPath.equals("/assign3Nethra")) {
			User user = (User)session.getAttribute("user");
			int returnType = Photos.assignPhoto(request, user, Photos.NETHRA);

			if(returnType == 2) {
				response.sendRedirect("/Glaucoma/home"); 
				return;
			}
		
			response.sendRedirect("/Glaucoma/nethra"); 
			return;
		}
		else if(userPath.equals("/OpReviewHVF")) {
			User user = (User)session.getAttribute("user");
			boolean firstReview = HVFtest.opthAssignHVF(request, user);

			if(!firstReview) {
				response.sendRedirect("/Glaucoma/home"); 
				return;
			}

			response.sendRedirect("/Glaucoma/OpHVFtest"); 
			return;
		}

		else if(userPath.equals("/printCSVs")) {

//			Tools.createCSV(Picture.getCSVLines(),"hvf_pictures");
			Tools.createCSV(HVFtest.getCSVLines(),"hvf_grades");
			response.sendRedirect("/Glaucoma/home"); 
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
			String type = request.getParameter("graderType");

			if(password.equals(rePassword)){
				User user = User.createUser(name, password, type);

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
