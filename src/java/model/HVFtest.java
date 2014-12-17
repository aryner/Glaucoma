/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.*; 
import java.io.*;
import utilities.SQLCommands;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author aryner
 */
public class HVFtest {
	private int id;
	private String opthName;
	private int adjudicatorID;
	private int confirmed;
	private int opthCheck;
	private String pictureName;
	private int userID;
	private int notes;
	private String notes_other;
	private int hvf_glau;
	private String vf_loss_oth;
	private String vf_defect_oth;
	private int mon;
	private String mon_oth2_c74; 
	private int tar;
	private String tar_oth;
	private int lossnum;
	private int lossden;
	private int fp;
	private int fn;
	private String dur;
	private int fov;
	private int stimintens;
	private int stimcol;
	private String stimcol_oth;
	private String back;
	private int strategy;
	private String strategy_oth;
	private String pup;
	private int vanum;
	private int vaden;
	private int sph_sign;
	private String sph_num;
	private int cyl_sign;
	private String cyl_num;
 	private int axis;
	private int ght;
	private String vfi;
	private int mdsign;
	private String mddb;
	private int mdp;
	private int psdsign;
	private String psddb;
	private int psdp;
	private int central_15;
	private int central_0;
	private int sup_hem;
	private int inf_hem;
	private int sup_hem2;
	private int inf_hem2;
	private int pts_five;
	private int pts_contig;
	private int pts_one;
	private int cluster;
	private int severe;
	private int reliable_review;
	private String vf_loss;
	private String vf_defect;
	private static final String slash = System.getProperty("file.separator");

	public HVFtest(String npictureName) {
		pictureName = npictureName;
	}

	public HVFtest(String npictureName, int nuserID) {
		String query = "INSERT INTO HVFtest (pictureName, userID) VALUES ('"+npictureName+"', '"+nuserID+"')";
		SQLCommands.update(query);

		query = "SELECT * FROM HVFtest WHERE pictureName='"+npictureName+"' AND userID="+nuserID;
		Vector<Integer> data = SQLCommands.queryNewGrade(query);

		pictureName = npictureName;
		userID = data.get(0);
		id = data.get(1);	
	}

	public HVFtest(int nid, String nopthName, int nadjudicatorID, int nconfirmed, int nopthCheck, 
		String npictureName, int nuserID, int nnotes, String nnotes_other, String nvf_loss,
		String nvf_defect, int nglau, String nvf_loss_oth, String nvf_defect_oth, int nmon, String nmon_oth2_c47,
		int ntar, String ntar_oth, int nlossnum, int nlossden, int nfp, int nfn, String ndur, int nfov, 
		int nstimintens, int nstimcol, String nstimcol_oth, String nback, int nstrategy, String nstrategy_oth,
		String npup, int nvanum, int nvaden, int nsph_sign, String nsph_num, int ncyl_sign, String ncyl_num,
		int naxis, int nght, String nvfi, int nmdsign, String nmddb, int nmdp, int npsdsign, String npsddb,
		int npsdp, int ncentral_15, int ncentral_0, int nsup_hem, int ninf_hem, int nsup_hem2, 
		int ninf_hem2, int npts_five, int npts_contig, int npts_one, int ncluster, int nsevere,
		int nreliable_review
	){
		id = nid;
		opthName = nopthName;
		adjudicatorID = nadjudicatorID;
		confirmed = nconfirmed;
		opthCheck = nopthCheck;
		pictureName = npictureName;
		userID = nuserID;
		notes = nnotes;
		notes_other = nnotes_other;
		vf_loss = nvf_loss;
		vf_defect = nvf_defect;
		hvf_glau = nglau;
		vf_loss_oth = nvf_loss_oth;
		vf_defect_oth = nvf_defect_oth;
		mon = nmon;
		mon_oth2_c74 = nmon_oth2_c47;
		tar = ntar;
		tar_oth = ntar_oth;
		lossnum = nlossnum;
		lossden = nlossden;
		fp = nfp;
		fn = nfn;
		dur = ndur;
		fov = nfov;
		stimintens = nstimintens;
		stimcol = nstimcol;
		stimcol_oth = nstimcol_oth;
		back = nback;
		strategy = nstrategy;
		strategy_oth = nstrategy_oth;
		pup = npup;
		vanum = nvanum;
		vaden = nvaden;
		sph_sign = nsph_sign;
		sph_num = nsph_num;
		cyl_sign = ncyl_sign;
		cyl_num = ncyl_num;
		axis = naxis;
		ght = nght;
		vfi = nvfi;
		mdsign = nmdsign;
		mddb = nmddb;
		mdp = nmdp;
		psdsign = npsdsign;
		psddb = npsddb;
		psdp = npsdp;
		central_15 = ncentral_15;
		central_0 = ncentral_0;
		sup_hem = nsup_hem;
		inf_hem = ninf_hem;
		sup_hem2 = nsup_hem2;
		inf_hem2 = ninf_hem2;
		pts_five = npts_five;
		pts_contig = npts_contig;
		pts_one = npts_one;
		cluster = ncluster;
		severe = nsevere;
		reliable_review = nreliable_review;
	}

	public static int assignHVF(HttpServletRequest request, User user) {
		int result = 0;
		boolean glaucoma = false;

		int early = 0;
		int moderate = 0;
		int advanced = 0;
		int severe = 0;
		int md = 0;

		int uID = user.getID();
		String picName = request.getParameter("pictureName");
		HVFtest hvf = null;
		if(user.getAccess() == 0) {
			hvf = new HVFtest(picName, uID);
		}
		else if (user.getAccess() == 1) {
			hvf = new HVFtest(picName);
		}

		//Set attributes
		String attr = request.getParameter("mon");
		hvf.setMon(Integer.parseInt(attr));
		attr = request.getParameter("mon_oth2_c74");
		hvf.setMon_oth2_c74(attr);
		attr = request.getParameter("tar");
		hvf.setTar(Integer.parseInt(attr));
		attr = request.getParameter("tar_oth");
		hvf.setTar_oth(attr);
		attr = request.getParameter("lossnum");
		hvf.setLossnum(Integer.parseInt(attr));
		attr = request.getParameter("lossden");
		hvf.setLossden(Integer.parseInt(attr));
		attr = request.getParameter("fp");
		hvf.setFp(Integer.parseInt(attr));
		attr = request.getParameter("fn");
		hvf.setFn(Integer.parseInt(attr));
		attr = request.getParameter("dur");
		hvf.setDur(attr);
		attr = request.getParameter("fov");
		hvf.setFov(Integer.parseInt(attr));
		attr = request.getParameter("stimintens");
		hvf.setStimintens(Integer.parseInt(attr));
		attr = request.getParameter("stimcol");
		hvf.setStimcol(Integer.parseInt(attr));
		attr = request.getParameter("stimcol_oth");
		hvf.setStimcol_oth(attr);
		attr = request.getParameter("back");
		hvf.setBack(attr);
		attr = request.getParameter("strategy");
		hvf.setStrategy(Integer.parseInt(attr));
		attr = request.getParameter("strategy_oth");
		hvf.setStrategy_oth(attr);
		attr = request.getParameter("pup");
		hvf.setPup(attr);
		attr = request.getParameter("vanum");
		hvf.setVanum(Integer.parseInt(attr));
		attr = request.getParameter("vaden");
		hvf.setVaden(Integer.parseInt(attr));
		attr = request.getParameter("sph_sign");
		hvf.setSph_sign(Integer.parseInt(attr));
		attr = request.getParameter("sph_num");
		hvf.setSph_num(attr);
		attr = request.getParameter("cyl_sign");
		hvf.setCyl_sign(Integer.parseInt(attr));
		attr = request.getParameter("cyl_num");
		hvf.setCyl_num(attr);
		attr = request.getParameter("axis");
		hvf.setAxis(Integer.parseInt(attr));
		attr = request.getParameter("ght");
		hvf.setGht(Integer.parseInt(attr));
		if(attr.equals("3")) {
			glaucoma = true;
		}
		attr = request.getParameter("vfi");
		hvf.setVfi(attr);
		attr = request.getParameter("mdsign");
		hvf.setMdsign(Integer.parseInt(attr));
		attr = request.getParameter("mddb");
		hvf.setMddb(attr);
		attr = request.getParameter("mdp");
		hvf.setMdp(Integer.parseInt(attr));
		attr = request.getParameter("psdsign");
		hvf.setPsdsign(Integer.parseInt(attr));
		attr = request.getParameter("psddb");
		hvf.setPsddb(attr);
		attr = request.getParameter("psdp");
		hvf.setPsdp(Integer.parseInt(attr));
		if(hvf.getPsdp() <= 4) {
			glaucoma = true;
		}
		attr = request.getParameter("central_15");
		hvf.setCentral_15(Integer.parseInt(attr));
		attr = request.getParameter("central_0");
		hvf.setCentral_0(Integer.parseInt(attr));
		attr = request.getParameter("sup_hem");
		hvf.setSup_hem(Integer.parseInt(attr));
		attr = request.getParameter("inf_hem");
		hvf.setInf_hem(Integer.parseInt(attr));
		attr = request.getParameter("pts_five");
		hvf.setPts_five(Integer.parseInt(attr));
		attr = request.getParameter("pts_one");
		hvf.setPts_one(Integer.parseInt(attr));
		attr = request.getParameter("cluster");
		hvf.setCluster(Integer.parseInt(attr));
		if(hvf.getCluster() == 1) {
			glaucoma = true;
		}
		attr = request.getParameter("notes");
		hvf.setNotes(Integer.parseInt(attr));
		attr = request.getParameter("notes_other");
		hvf.setNotes_other(attr);
		
		//check for severity if glaucoma
		if(glaucoma) {
			int mdSign = hvf.getMdsign();
			float mddb = Float.parseFloat(hvf.getMddb());
			if(mdSign == 2 || mddb < 0.001) {
				md = 0;
			}
			else if(mddb <= 6.) {
				md = 1;
			}
			else if(mddb <= 12.) {
				md = 2;
			}
			else if(mddb <= 20.) {
				md = 3;
			}
			else if (mddb < 999) {
				md = 4;
			}

			int ptsFive = hvf.getPts_five();
			int ptsOne = hvf.getPts_one();
			if((ptsFive >= 19 && ptsFive <=36) && (ptsOne >= 12 && ptsOne <= 18)) {
				moderate++;
			}
			else if((ptsFive >= 37 && ptsFive <= 55) && (ptsOne >= 19 && ptsOne <= 36)) {
				advanced++;
			}
			else if((ptsFive >= 56 && ptsFive < 999) || (ptsOne >= 37 && ptsOne < 999)) {
				severe++;
			}
			else if(((ptsFive >= 19 && ptsFive <=36) && (ptsOne >=19)) || (ptsFive >= 37 && (ptsOne >= 12 && ptsOne <= 18))){
				;//do nothing; it makes no contribution to classifications
			}
			else if (ptsFive < 999 && ptsOne < 999) {
				early++;
			}

			int central15 = hvf.getCentral_15();
			int central0 = hvf.getCentral_0();
			if (central15 >= 1 && central0 == 0 && central15 < 999) {
				moderate++;
			}
			else if(central0 == 1) {
				advanced++;
			}
			else if(central0 > 1 && central0 < 999) {
				severe++;
			}
			else if(central0 < 999) {
				early++;
			}

			int sup = hvf.getSup_hem();
			int inf = hvf.getInf_hem();
			if((sup == 0 && inf > 0 && inf < 999) || (sup > 0 && inf == 0 && sup < 999)) {
				moderate++;
			}
			else if(sup >= 2 && inf >= 2 && (sup < 999 && inf < 999)) {
				severe++;
			}
			else if((sup == 1 || sup == 2) && (inf == 1 || inf == 2)) {
				advanced++;
			}
			else {
				early++;
			}
		}

		String query = "UPDATE HVFtest SET hvf_mon='"+hvf.getMon()+"', hvf_mon_oth2_c47='"+hvf.getMon_oth2_c74()+"', "+
			"hvf_tar='"+hvf.getTar()+"', hvf_tar_oth='"+hvf.getTar_oth()+"', hvf_lossnum='"+hvf.getLossnum()+"', "+
			"hvf_lossden='"+hvf.getLossden()+"', hvf_fp='"+hvf.getFp()+"', hvf_fn='"+hvf.getFn()+"', "+
			"hvf_dur='"+hvf.getDur()+"', hvf_fov='"+hvf.getFov()+"', hvf_stimintens='"+hvf.getStimintens()+"', "+
			"hvf_stimcol='"+hvf.getStimcol()+"', hvf_stimcol_oth='"+hvf.getStimcol_oth()+"', hvf_back='"+hvf.getBack()+"', "+
			"hvf_strategy='"+hvf.getStrategy()+"', hvf_strategy_oth='"+hvf.getStrategy_oth()+"', hvf_pup='"+hvf.getPup()+"', "+
			"hvf_vanum='"+hvf.getVanum()+"', hvf_vaden='"+hvf.getVaden()+"', hvf_sph_sign='"+hvf.getSph_sign()+"', "+
			"hvf_sph_num='"+hvf.getSph_num()+"', hvf_cyl_sign='"+hvf.getCyl_sign()+"', hvf_cyl_num='"+hvf.getCyl_num()+"', "+
			"hvf_axis='"+hvf.getAxis()+"', hvf_ght='"+hvf.getGht()+"', hvf_vfi='"+hvf.getVfi()+"', "+
			"hvf_mdsign='"+hvf.getMdsign()+"', hvf_mddb='"+hvf.getMddb()+"', hvf_mdp='"+hvf.getMdp()+"', "+
			"hvf_psdsign='"+hvf.getPsdsign()+"', hvf_psddb='"+hvf.getPsddb()+"', hvf_psdp='"+hvf.getPsdp()+"', "+
			"hvf_central_15='"+hvf.getCentral_15()+"', hvf_central_0='"+hvf.getCentral_0()+"', hvf_sup_hem='"+hvf.getSup_hem()+"', "+
			"hvf_inf_hem='"+hvf.getInf_hem()+"', hvf_sup_hem2='"+hvf.getSup_hem2()+"', hvf_inf_hem2='"+hvf.getInf_hem2()+"', "+
			"hvf_pts_five='"+hvf.getPts_five()+"', "+ "hvf_pts_contig='"+hvf.getPts_contig()+"', hvf_pts_one='"+hvf.getPts_one()+"', "+
			"hvf_cluster='"+hvf.getCluster()+"', hvf_notes='"+hvf.getNotes()+"', hvf_notes_other='"+hvf.getNotes_other()+"' ";

		if(glaucoma) {
			query += ", hvf_glau='1'";

			if(md == 0) {
				query += ", hvf_severe='1'";
			}
			else if (md == 1) {
				if((moderate+advanced+severe) >= 1) {
					query += ", hvf_severe='2'";
				}
				else {
					query += ", hvf_severe='1'";
				}
			}
			else if (md == 2){
				if(early == 3 || (advanced + severe + moderate) == 0) {
					query += ", hvf_severe='1'";
				}
				else if((advanced + severe) >= 1 && early == 0) {
					query += ", hvf_severe='3'";
				}
				else {
					query += ", hvf_severe='2'";
				}
			}
			else if (md == 3) {
				if((early + moderate) == 3 || (advanced + severe) == 0) {
					query += ", hvf_severe='2'";
				}
				else if(severe >= 1 && (early + moderate) == 0) {
					query += ", hvf_severe='4'";
				}
				else {
					query += ", hvf_severe='3'";
				}
			}
			else if (md == 4){
				if ((early + moderate + advanced) == 3 || severe == 0) {
					query += ", hvf_severe='3'";
				}
				else {
					query += ", hvf_severe='4'";
				}
			}
		} else {
			query += ", hvf_glau='2', hvf_severe='0'";
			Random rand = new Random(System.currentTimeMillis());
			if (rand.nextDouble() < 0.1 && user.getAccess() == 1) {
				query += ", opthCheck=0";
			}
			else if(user.getAccess() == 1) {
				query += ", opthCheck=-1";
			}
		}

		if(user.getAccess() == 0) {
			query += " WHERE id='"+hvf.getId()+"'";
		} else if(user.getAccess() ==1) { 
			query += ", confirmed=2, adjudicatorID="+user.getID()+" WHERE pictureName='"+request.getParameter("pictureName")+"'";
			if(request.getParameter("alreadyConfirmed").equals("true")) {
				result = 2;
			}
		}
		SQLCommands.update(query);
		if(user.getAccess() == 0) {
			setForAdjudication(picName);
		}

		return result;
	}

	public static boolean opthAssignHVF(HttpServletRequest request, User user) {
		boolean firstReview = true;
		String picName = request.getParameter("pictureName");
		HVFtest hvf = new HVFtest(picName);

		String attr = request.getParameter("fp");
		hvf.setFp(Integer.parseInt(attr));
		attr = request.getParameter("fn");
		hvf.setFn(Integer.parseInt(attr));
		attr = request.getParameter("ght");
		hvf.setGht(Integer.parseInt(attr));
		attr = request.getParameter("psdp");
		hvf.setPsdp(Integer.parseInt(attr));
		attr = request.getParameter("cluster");
		hvf.setCluster(Integer.parseInt(attr));
		attr = request.getParameter("glau");
		hvf.setHvf_glau(Integer.parseInt(attr));
		attr = request.getParameter("mdsign");
		hvf.setMdsign(Integer.parseInt(attr));
		attr = request.getParameter("mddb");
		hvf.setMddb(attr);
//		attr = request.getParameter("mdp");
//		hvf.setMdp(Integer.parseInt(attr));
		attr = request.getParameter("central_15");
		hvf.setCentral_15(Integer.parseInt(attr));
		attr = request.getParameter("central_0");
		hvf.setCentral_0(Integer.parseInt(attr));
		attr = request.getParameter("sup_hem");
		hvf.setSup_hem(Integer.parseInt(attr));
		attr = request.getParameter("inf_hem");
		hvf.setInf_hem(Integer.parseInt(attr));
//		attr = request.getParameter("sup_hem2");
//		hvf.setSup_hem2(Integer.parseInt(attr));
//		attr = request.getParameter("inf_hem2");
//		hvf.setInf_hem2(Integer.parseInt(attr));
		attr = request.getParameter("pts_five");
		hvf.setPts_five(Integer.parseInt(attr));
//		attr = request.getParameter("pts_contig");
//		hvf.setPts_contig(Integer.parseInt(attr));
		attr = request.getParameter("pts_one");
		hvf.setPts_one(Integer.parseInt(attr));
		attr = request.getParameter("severe");
		hvf.setSevere(Integer.parseInt(attr));
		attr = request.getParameter("reliable_review");
		hvf.setReliable_review(Integer.parseInt(attr));
		attr = request.getParameter("vf_loss");
		hvf.setVf_loss(attr);
		attr = request.getParameter("vf_loss_oth");
		hvf.setVf_loss_oth(attr);
//		attr = request.getParameter("vf_defect");
//		hvf.setVf_defect(Integer.parseInt(attr));
		attr = request.getParameter("vf_defect_oth");
		hvf.setVf_defect_oth(attr);
		attr = request.getParameter("notes");
		hvf.setNotes(Integer.parseInt(attr));
		attr = request.getParameter("notes_other");
		hvf.setNotes_other(attr);

		String defect = "";
		for(int i=1; i<9; i++) {
			if(request.getParameter("vf_defect"+i) != null) {
				defect += "2";
			}
			else {
				defect += "1";
			}
		}
		hvf.setVf_defect(defect);

		String query = "UPDATE HVFtest SET hvf_fp='"+hvf.getFp()+"', hvf_fn='"+hvf.getFn()+"', hvf_ght='"+hvf.getGht()+"', "+
				"hvf_psdp='"+hvf.getPsdp()+"', hvf_cluster='"+hvf.getCluster()+"', hvf_glau='"+hvf.getHvf_glau()+"', "+
				"hvf_mdsign='"+hvf.getMdsign()+"', hvf_mddb='"+hvf.getMddb()+"', "+
				"hvf_central_15='"+hvf.getCentral_15()+"', hvf_central_0='"+hvf.getCentral_0()+"', hvf_sup_hem='"+hvf.getSup_hem()+"', "+
				"hvf_inf_hem='"+hvf.getInf_hem()+"', "+
				"hvf_pts_five='"+hvf.getPts_five()+"', hvf_pts_one='"+hvf.getPts_one()+"', "+
				"hvf_severe='"+hvf.getSevere()+"', hvf_reliable_review='"+hvf.getReliable_review()+"', hvf_vf_loss='"+hvf.getVf_loss()+"', "+
				"hvf_vf_loss_oth='"+hvf.getVf_loss_oth()+"', hvf_vf_defect='"+hvf.getVf_defect()+"', hvf_vf_defect_oth='"+hvf.getVf_defect_oth()+
				"', opthCheck='"+user.getID()+"', opthName='"+user.getUserName()+"', hvf_notes='"+hvf.getNotes()+"', hvf_notes_other='"+
				hvf.getNotes_other()+"' WHERE pictureName='"+picName+"'";
		SQLCommands.update(query);

		if(request.getParameter("reviewedAgain").equals("true")) {
			firstReview = false;
		}
		return firstReview;
	}

	public static void addNegatives(double percent) {
		String query = "SELECT * FROM HVFtest WHERE confirmed=3";

		if(SQLCommands.getCount(query) == 0) {
			query = "SELECT * FROM HVFtest WHERE opthCheck=-1 && confirmed=2 GROUP BY pictureName HAVING COUNT(*)=2";
			//just get picName
			Vector<HVFtest> noGlau = SQLCommands.queryHVFtest(query);
			Vector<HVFtest> toChange = new Vector<HVFtest>();
			Random rand = new Random(System.currentTimeMillis());
			int amountToChange = (int)((double)noGlau.size() * percent);

			for(int i=0; i<amountToChange; i++) {
				toChange.add(noGlau.remove(rand.nextInt(noGlau.size())));
			}

			query = "UPDATE HVFtest SET opthCheck=0, confirmed=3 WHERE ";
			for(int i=0; i<toChange.size(); i++) {
				if(i > 0) { query += " OR "; }
				query += " pictureName ='"+toChange.get(i).getPictureName()+"' ";
			}

			if(toChange.size() > 0) {
				SQLCommands.update(query);
			}
		}
	}

	public static HVFtest getOpHVF(String picName) {
		HVFtest result = null;
		String query = "SELECT * FROM HVFtest WHERE pictureName='"+picName+"'";

		Vector<HVFtest> hvf = SQLCommands.queryHVFtestMaster(query);
		if(hvf.size() > 0) {
			result = hvf.get(0);
		}

		return result;
	}

	public static int needInitialCount() {
		String query = "SELECT * FROM picture WHERE type='HVF'";
		int picCount = SQLCommands.getCount(query);
		query = "SELECT * FROM HVFtest WHERE confirmed=2 GROUP BY pictureName HAVING COUNT(*)=2";
		int confirmedCount = SQLCommands.getCount(query);

		return picCount - confirmedCount;
	}

	public static Vector<HVFtest> getPair(String picName) {
		String query = "SELECT * FROM HVFtest WHERE pictureName='"+picName+"'";	
		return SQLCommands.queryHVFtestMaster(query);
	}

	public static Picture getNext(User user) {
		Picture result = null;
		String query = "";
		if (user.getAccess() == 0) {
			query = "SELECT * FROM picture WHERE name NOT IN (SELECT "+
				" pictureName FROM HVFtest WHERE userID="+user.getID()+") AND type='HVF'"+
				" AND name NOT IN (SELECT pictureName FROM HVFtest GROUP BY pictureName HAVING COUNT(*)>=2)";
		}
		else if (user.getAccess() == 1) {
			query = "SELECT * FROM picture WHERE name IN (SELECT pictureName FROM "+
				"HVFtest WHERE CONFIRMED=1) AND type='HVF'";
		}
		else if (user.getAccess() == 2) {
			query = "SELECT * FROM picture WHERE name IN (SELECT pictureName FROM HVFtest WHERE "+
				"confirmed>=2 && opthCheck=0) AND type='HVF'";
		}

		Vector<Picture> pictures = SQLCommands.queryPictures(query); 
		if(pictures.size() > 0) {
			Random rand = new Random(System.currentTimeMillis());
			result = pictures.get(rand.nextInt(pictures.size()));

			if(user.getAccess() == 0) {
				result = pictures.get(0);
			}
		}

		return result;
	}

	public static int getNeedToPairCount() {
		String query = "SELECT * FROM HVFtest GROUP BY pictureName HAVING COUNT(*)=1";
		return SQLCommands.getCount(query);
	}

	public static int getOpCheckCount() {
		String query = "SELECT * FROM HVFtest WHERE confirmed=2 && opthCheck=0 && (hvf_severe=1 "+
			"OR hvf_severe=2 OR hvf_severe=3 OR hvf_severe=4 OR hvf_glau=1)";
		int count = (SQLCommands.getCount(query) / 2);
		return count;
	}

	public static void setForAdjudication(String picName) {
		String query = "SELECT * FROM HVFtest WHERE pictureName='"+picName+"'";
		Vector<HVFtest> hvf = SQLCommands.queryHVFtestMaster(query);

		if(hvf.size() > 1) {
			//get the ones that don't need adjudication
			query = "SELECT * FROM HVFtest GROUP BY pictureName, "+
				"hvf_mon, hvf_mon_oth2_c47, hvf_tar, hvf_tar_oth, hvf_lossnum, hvf_lossden, "+
				"hvf_fp, hvf_fn, hvf_dur, hvf_fov, hvf_stimintens, hvf_stimcol, hvf_stimcol_oth, "+
				"hvf_back, hvf_strategy, hvf_strategy_oth, hvf_pup, hvf_vanum, hvf_vaden, hvf_sph_sign, "+
				"hvf_sph_num, hvf_cyl_sign, hvf_cyl_num, hvf_axis, hvf_ght, hvf_vfi, hvf_mdsign, hvf_mddb, "+
				"hvf_mdp, hvf_psdsign, hvf_psddb, hvf_psdp, hvf_sup_hem, hvf_inf_hem, "+
				"hvf_pts_five, hvf_pts_one, hvf_cluster, hvf_notes, hvf_notes_other "+
				"HAVING COUNT(*)=2";
			Vector<HVFtest> set = SQLCommands.queryHVFtestMaster(query);
			//get the ones that need adjudication
			query = "SELECT * FROM HVFtest GROUP BY pictureName, "+
				"hvf_mon, hvf_mon_oth2_c47, hvf_tar, hvf_tar_oth, hvf_lossnum, hvf_lossden, "+
				"hvf_fp, hvf_fn, hvf_dur, hvf_fov, hvf_stimintens, hvf_stimcol, hvf_stimcol_oth, "+
				"hvf_back, hvf_strategy, hvf_strategy_oth, hvf_pup, hvf_vanum, hvf_vaden, hvf_sph_sign, "+
				"hvf_sph_num, hvf_cyl_sign, hvf_cyl_num, hvf_axis, hvf_ght, hvf_vfi, hvf_mdsign, hvf_mddb, "+
				"hvf_mdp, hvf_psdsign, hvf_psddb, hvf_psdp, hvf_sup_hem, hvf_inf_hem, "+
				"hvf_pts_five, hvf_pts_one, hvf_cluster, hvf_notes, hvf_notes_other "+
				"HAVING COUNT(*)=1";
			Vector<HVFtest> notSet = SQLCommands.queryHVFtest(query);

			for(int i=set.size()-1; i>=0; i--) {
				if (!set.get(i).getPictureName().equals(picName)) {
					set.remove(i);
				}
			}
			for(int i=notSet.size()-1; i>=0; i--) {
				if (!notSet.get(i).getPictureName().equals(picName)) {
					notSet.remove(i);
				}
			}
			
			//update the confirmed ones
			query = "UPDATE HVFtest SET confirmed=2";
			Random rand = new Random(System.currentTimeMillis());
			if(set.size() > 0 && (set.get(0).getHvf_glau() == 2 && rand.nextDouble() > 0.1)) {
				query += ", opthCheck=-1";
			}
			query += " WHERE pictureName='"+picName+"'";
			if(set.size() > 0) {
				SQLCommands.update(query);
			}

			//update the ones that need confirming
			query = "UPDATE HVFtest SET confirmed=1 WHERE pictureName='"+picName+"'";
			if(notSet.size() > 0) {
				SQLCommands.update(query);
			}
		}
	}

	public static void setAllForAdjudication() {
		//get the ones that don't need adjudication
		String query = "SELECT * FROM HVFtest GROUP BY pictureName, "+
			"hvf_mon, hvf_mon_oth2_c47, hvf_tar, hvf_tar_oth, hvf_lossnum, hvf_lossden, "+
			"hvf_fp, hvf_fn, hvf_dur, hvf_fov, hvf_stimintens, hvf_stimcol, hvf_stimcol_oth, "+
			"hvf_back, hvf_strategy, hvf_strategy_oth, hvf_pup, hvf_vanum, hvf_vaden, hvf_sph_sign, "+
			"hvf_sph_num, hvf_cyl_sign, hvf_cyl_num, hvf_axis, hvf_ght, hvf_vfi, hvf_mdsign, hvf_mddb, "+
			"hvf_mdp, hvf_psdsign, hvf_psddb, hvf_psdp, hvf_sup_hem, hvf_inf_hem, hvf_sup_hem2, "+
			"hvf_inf_hem2, hvf_pts_five, hvf_pts_contig, hvf_pts_one, hvf_cluster, hvf_notes, hvf_notes_other "+
			"HAVING COUNT(*)=2";
		Vector<HVFtest> set = SQLCommands.queryHVFtest(query);
		//get the ones that need adjudication
		query = "SELECT * FROM HVFtest GROUP BY pictureName, "+
			"hvf_mon, hvf_mon_oth2_c47, hvf_tar, hvf_tar_oth, hvf_lossnum, hvf_lossden, "+
			"hvf_fp, hvf_fn, hvf_dur, hvf_fov, hvf_stimintens, hvf_stimcol, hvf_stimcol_oth, "+
			"hvf_back, hvf_strategy, hvf_strategy_oth, hvf_pup, hvf_vanum, hvf_vaden, hvf_sph_sign, "+
			"hvf_sph_num, hvf_cyl_sign, hvf_cyl_num, hvf_axis, hvf_ght, hvf_vfi, hvf_mdsign, hvf_mddb, "+
			"hvf_mdp, hvf_psdsign, hvf_psddb, hvf_psdp, hvf_sup_hem, hvf_inf_hem, hvf_sup_hem2, "+
			"hvf_inf_hem2, hvf_pts_five, hvf_pts_contig, hvf_pts_one, hvf_cluster, hvf_notes, hvf_notes_other "+
			"HAVING COUNT(*)=1";
		Vector<HVFtest> notSet = SQLCommands.queryHVFtest(query);
		
		//update the confirmed ones
		query = "UPDATE HVFtest SET confirmed=2 WHERE ";
		for(int i=0; i<set.size(); i++) {
			if(i>0) { query += " OR "; }
			query += "pictureName='"+set.get(i).getPictureName()+"'";
		}
		if(set.size() > 0) {
			SQLCommands.update(query);
		}

		//update the ones that need confirming
		query = "UPDATE HVFtest SET confirmed=1 WHERE ";
		for(int i=0; i<notSet.size(); i++) {
			if(i>0) { query += " OR "; }
			query += "pictureName='"+notSet.get(i).getPictureName()+"'";
		}
		if(notSet.size() > 0) {
			SQLCommands.update(query);
		}
	}

	public static int needAdjudicationCount() {
		String query = "SELECT * FROM HVFtest WHERE confirmed=1";
		return SQLCommands.getCount(query);
	}

	public static Vector<String> getUngradedNames() {
		Vector<String> result = new Vector<String>();
		String query = "SELECT * FROM picture WHERE name NOT IN (SELECT pictureName FROM HVFtest) AND type='HVF'";
		Vector<Picture> pictures = SQLCommands.queryPictures(query);

		for(int i=0; i<pictures.size(); i++) {
			result.add(pictures.get(i).getName());
		}

		return result;
	}

	public static Vector<String> getGradedOnceNames() {
		Vector<String> result = new Vector<String>();
		String query = "SELECT * FROM HVFtest GROUP BY pictureName HAVING COUNT(*)=1";
		Vector<HVFtest> hvf = SQLCommands.queryHVFtest(query);

		for(int i=0; i<hvf.size(); i++) {
			result.add(hvf.get(i).getPictureName());
		}

		return result;
	}
	
	public static Vector<String> getNeedsAdjudication() {
		Vector<String> result = new Vector<String>();
		String query = "SELECT DISTINCT pictureName FROM HVFtest WHERE confirmed='1'";
		Vector<HVFtest> hvf = SQLCommands.queryHVFtest(query);

		for(int i=0; i<hvf.size(); i++) {
			result.add(hvf.get(i).getPictureName());
		}

		return result;
	}

	public static Vector<String> getNeedsReview() {
		Vector<String> result = new Vector<String>();
		String query = "SELECT DISTINCT pictureName FROM HVFtest WHERE confirmed >= 2 AND opthCheck='0'";
		Vector<HVFtest> hvf = SQLCommands.queryHVFtest(query);

		for(int i=0; i<hvf.size(); i++) {
			result.add(hvf.get(i).getPictureName());
		}

		return result;
	}
	
	public static Vector<String> getReviewed() {
		Vector<String> result = new Vector<String>();
		String query = "SELECT DISTINCT pictureName FROM HVFtest WHERE confirmed >=2 AND opthCheck != 0";
		Vector<HVFtest> hvf = SQLCommands.queryHVFtest(query);

		for(int i=0; i<hvf.size(); i++) {
			result.add(hvf.get(i).getPictureName());
		}

		return result;
	}

	public static Vector<HVFtest> getReviewedBy(String userName) {
		Vector<HVFtest> result = null;
		String query = "SELECT * FROM HVFtest WHERE opthName='"+userName+"'";
		result = SQLCommands.queryHVFtestMaster(query);

		return result;
	}
	public static Vector<HVFtest> getAdjudicatedBy(int adID) {
		Vector<HVFtest> result = null;
		String query = "SELECT * FROM HVFtest WHERE adjudicatorID='"+adID+"' AND opthCheck <= 0";
		result = SQLCommands.queryHVFtestMaster(query);

		return result;
	}

	public static Vector<String> getCSVLines() {
		Vector<String> result = new Vector<String>();
		String query = "SELECT * FROM HVFtest";
		Vector<HVFtest> hvf = SQLCommands.queryHVFtestMaster(query);

		String currLine = "id, confirmed, opthCheck, pictureName, userID, vf_loss, vf_defect, glau, vf_loss_oth, vf_defect_oth, "+
			"mon, mon_oth2_c47, tar, tar_oth, lossnum, lossden, fp, fn, dur, fov, stimintens, stimcol, stimcol_oth, back, "+
			"strategy, strategy_oth, pup, vanum, vaden, sph_sign, sph_num, cyl_sign, cyl_num, axis, ght, vfi, mdsign, mddb, "+
			"mdp, psdsign, psddb, psdp, central_15, central_0, sup_hem, inf_hem, sup_hem2, inf_hem2, pts_five, pts_contig, "+
			"pts_one, cluster, severe, reliable_review, notes, notes_other, opthName, adjudicatorID";
		result.add(currLine);
		for(int i=0; i<hvf.size(); i++) {
			currLine = 
				hvf.get(i).getId()+", "+hvf.get(i).getConfirmed()+", "+hvf.get(i).getOpthCheck()+", "+
				hvf.get(i).getPictureName()+", "+hvf.get(i).getUserID()+", "+hvf.get(i).getVf_loss()+", "+
				hvf.get(i).getVf_defect()+", "+hvf.get(i).getHvf_glau()+", "+hvf.get(i).getVf_loss_oth()+", "+
				hvf.get(i).getVf_defect_oth()+", "+hvf.get(i).getMon()+", "+hvf.get(i).getMon_oth2_c74()+", "+
				hvf.get(i).getTar()+", "+hvf.get(i).getTar_oth()+", "+hvf.get(i).getLossnum()+", "+
				hvf.get(i).getLossden()+", "+hvf.get(i).getFp()+", "+hvf.get(i).getFn()+", "+
				hvf.get(i).getDur()+", "+hvf.get(i).getFov()+", "+hvf.get(i).getStimintens()+", "+
				hvf.get(i).getStimcol()+", "+hvf.get(i).getStimcol_oth()+", "+hvf.get(i).getBack()+", "+
				hvf.get(i).getStrategy()+", "+hvf.get(i).getStrategy_oth()+", "+hvf.get(i).getPup()+", "+
				hvf.get(i).getVanum()+", "+hvf.get(i).getVaden()+", "+hvf.get(i).getSph_sign()+", "+
				hvf.get(i).getSph_num()+", "+hvf.get(i).getCyl_sign()+", "+hvf.get(i).getCyl_num()+", "+
				hvf.get(i).getAxis()+", "+hvf.get(i).getGht()+", "+hvf.get(i).getVfi()+", "+
				hvf.get(i).getMdsign()+", "+hvf.get(i).getMddb()+", "+hvf.get(i).getMdp()+", "+
				hvf.get(i).getPsdsign()+", "+hvf.get(i).getPsddb()+", "+hvf.get(i).getPsdp()+", "+
				hvf.get(i).getCentral_15()+", "+hvf.get(i).getCentral_0()+", "+hvf.get(i).getSup_hem()+", "+
				hvf.get(i).getInf_hem()+", "+hvf.get(i).getSup_hem2()+", "+hvf.get(i).getInf_hem2()+", "+
				hvf.get(i).getPts_five()+", "+hvf.get(i).getPts_contig()+", "+hvf.get(i).getPts_one()+", "+
				hvf.get(i).getCluster()+", "+hvf.get(i).getSevere()+", "+hvf.get(i).getReliable_review()+", "+
				hvf.get(i).getNotes()+", "+hvf.get(i).getNotes_other()+", "+
				hvf.get(i).getOpthName()+", "+hvf.get(i).getAdjudicatorID();
			result.add(currLine);
		}

		return result;
	}

	public static Vector<String> getFinishedCSVLines() {
		Vector<String> result = new Vector<String>();
		String query = "SELECT * FROM HVFtest WHERE opthCheck != 0 AND confirmed >=2 GROUP BY pictureName";
		Vector<HVFtest> hvf = SQLCommands.queryHVFtestMaster(query);

		String currLine = "opthID, adjudicatorID, picture, hvf_mon, hvf_mon_oth2_c74, hvf_tar, hvf_tar_oth, "+
				  "hvf_lossnum, hvf_lossden, hvf_fp, hvf_fn, hvf_dur, hvf_fov, hvf_stimintens, hvf_stimcol, "+
				  "hvf_stimcol_oth, hvf_back, hvf_strategy, hvf_strategy_oth, hvf_pup, hvf_vanum, hvf_vaden, "+
				  "hvf_sph_sign, hvf_sph_num, hvf_cyl_sign, hvf_cyl_num, hvf_axis, hvf_ght, hvf_vfi, hvf_mdsign, "+
				  "hvf_mddb, hvf_mdp, hvf_psdsign, hvf_psddb, hvf_psdp, hvf_central_15, hvf_central_0, hvf_sup_hem, "+
				  "hvf_inf_hem, hvf_sup_hem2, hvf_inf_hem2, hvf_pts_five, hvf_pts_contig, hvf_pts_one, hvf_cluster, "+
				  "hvf_glau, hvf_severe, hvf_reliable_review, hvf_vf_loss, hvf_vf_defect1, hvf_vf_defect2, hvf_vf_defect3, "+
				  "hvf_vf_defect4, hvf_vf_defect5, hvf_vf_defect6, hvf_vf_defect7, hvf_vf_defect_oth";
		result.add(currLine);
		String defects;

		for(int i=0; i<hvf.size(); i++) {
			defects = hvf.get(i).getVf_defect();
			int index = 1;
			while(index < defects.length()) {
				defects = defects.substring(0,index)+","+defects.substring(index,defects.length());
				index += 2;
			}
			defects.replace(",", ", ");
			currLine = 
				hvf.get(i).getOpthCheck()+", "+hvf.get(i).getAdjudicatorID()+", "+hvf.get(i).getPictureName()+", "+
				hvf.get(i).getMon()+", "+hvf.get(i).getMon_oth2_c74()+", "+hvf.get(i).getTar()+", "+
				hvf.get(i).getTar_oth()+", "+hvf.get(i).getLossnum()+", "+hvf.get(i).getLossden()+", "+
				hvf.get(i).getFp()+", "+hvf.get(i).getFn()+", "+hvf.get(i).getDur()+", "+hvf.get(i).getFov()+", "+
				hvf.get(i).getStimintens()+", "+hvf.get(i).getStimcol()+", "+hvf.get(i).getStimcol_oth()+", "+
				hvf.get(i).getBack()+", "+hvf.get(i).getStrategy()+", "+hvf.get(i).getStrategy_oth()+", "+
				hvf.get(i).getPup()+", "+hvf.get(i).getVanum()+", "+hvf.get(i).getVaden()+", "+hvf.get(i).getSph_sign()+", "+
				hvf.get(i).getSph_num()+", "+hvf.get(i).getCyl_sign()+", "+hvf.get(i).getCyl_num()+", "+
				hvf.get(i).getAxis()+", "+hvf.get(i).getGht()+", "+hvf.get(i).getVfi()+", "+hvf.get(i).getMdsign()+", "+
				hvf.get(i).getMddb()+", "+hvf.get(i).getMdp()+", "+hvf.get(i).getPsdsign()+", "+hvf.get(i).getPsddb()+", "+
				hvf.get(i).getPsdp()+", "+hvf.get(i).getCentral_15()+", "+hvf.get(i).getCentral_0()+", "+
				hvf.get(i).getSup_hem()+", "+hvf.get(i).getInf_hem()+", "+hvf.get(i).getSup_hem2()+", "+
				hvf.get(i).getInf_hem2()+", "+hvf.get(i).getPts_five()+", "+hvf.get(i).getPts_contig()+", "+
				hvf.get(i).getPts_one()+", "+hvf.get(i).getCluster()+", "+hvf.get(i).getHvf_glau()+", "+
				hvf.get(i).getSevere()+", "+hvf.get(i).getReliable_review()+", "+hvf.get(i).getVf_loss()+", "+
				defects+", "+hvf.get(i).getVf_defect_oth();
			result.add(currLine);
		}

		return result;
	}

	public static void readCSV(String fileName) {
		String query = "SELECT * FROM HVFtest";

		Vector<HVFtest> alreadyHere = SQLCommands.queryHVFtestMaster(query);
		ArrayList<String> newLines = new ArrayList<String>();
		ArrayList<String> updateLines = new ArrayList<String>();
		ArrayList<String> toBeReplaced = new ArrayList<String>();
		String picName;
		int confirmed, opthCheck;
		File file = null;
		FileReader reader = null;
		BufferedReader fileReader = null;

		try {
			file = new File(".."+slash+"webapps"+slash+"Glaucoma"+slash+"temp"+slash+fileName);
			reader = new FileReader(file);
			fileReader = new BufferedReader(reader);

			String line = fileReader.readLine();
			int index;
			if(line != null && line.length() > 0) {
				line = fileReader.readLine();
			}
			while(line != null && line.length() > 0) {
				line = "'"+line+"'";
				line = line.replaceAll(", ", "', '");
				line.replaceAll("null", "");
				line.replaceAll("NULL", "");
				index = line.indexOf(",");
				confirmed = Integer.parseInt(line.substring(index+3,line.indexOf(",",index+1)-1));
				index = line.indexOf(",", index+1);
				opthCheck = Integer.parseInt(line.substring(index+3,line.indexOf(",",index+1)-1));
				index = line.indexOf(",", index+1);
				picName = line.substring(index+3,line.indexOf(",",index+1)-1);
				line = line.substring(line.indexOf(",")+2);

				boolean duplicate = false;
				HVFtest oldTest = null;
				for(int i=0; i<alreadyHere.size() && !duplicate; i++) {
					if(picName.equals(alreadyHere.get(i).getPictureName())){
						oldTest = alreadyHere.get(i);
						duplicate = true;
					}
				}

				if(!duplicate) {
					newLines.add(line);
				}
				else {
					if(confirmed > oldTest.getConfirmed()) {
						updateLines.add(line);
						toBeReplaced.add(oldTest.getPictureName());
					}
					else if(confirmed == oldTest.getConfirmed() && oldTest.getOpthCheck() == 0) {
						updateLines.add(line);
						toBeReplaced.add(oldTest.getPictureName());
					}
				}

				line = fileReader.readLine();
			}

			//Add new records
			query = "INSERT INTO HVFtest (confirmed, opthCheck, pictureName, userID, hvf_vf_loss, "+
				"hvf_vf_defect, hvf_glau, hvf_vf_loss_oth, hvf_vf_defect_oth, hvf_mon, hvf_mon_oth2_c47, hvf_tar, hvf_tar_oth, "+
				"hvf_lossnum, hvf_lossden, hvf_fp, hvf_fn, hvf_dur, hvf_fov, hvf_stimintens, hvf_stimcol, hvf_stimcol_oth, hvf_back, "+
				"hvf_strategy, hvf_strategy_oth, hvf_pup, hvf_vanum, hvf_vaden, hvf_sph_sign, hvf_sph_num, hvf_cyl_sign, hvf_cyl_num, "+
				"hvf_axis, hvf_ght, hvf_vfi, hvf_mdsign, hvf_mddb, hvf_mdp, hvf_psdsign, hvf_psddb, hvf_psdp, hvf_central_15, hvf_central_0, "+
				"hvf_sup_hem, hvf_inf_hem, hvf_sup_hem2, hvf_inf_hem2, hvf_pts_five, hvf_pts_contig, hvf_pts_one, hvf_cluster, "+
				"hvf_severe, hvf_reliable_review, hvf_notes, hvf_notes_other, opthName, adjudicatorID) VALUES ";
			for(int i=0; i<newLines.size(); i++) {
				if(i > 0) { query += ", "; }
				query += "("+newLines.get(i)+")";
			}
			if(newLines.size() > 0) {
				SQLCommands.update(query);
			}

			//delete records that will be replaced
			query = "DELETE FROM HVFtest WHERE ";
			for(int i=0; i<updateLines.size(); i++) {
				if(i>0) {query+=" OR ";}
				query += "pictureName='"+toBeReplaced.get(i)+"'";
			}
			if(updateLines.size() > 0) {
				SQLCommands.update(query);
			}

			//insert records to replace the deleted ones
			query = "INSERT INTO HVFtest (confirmed, opthCheck, pictureName, userID, hvf_vf_loss, "+
				"hvf_vf_defect, hvf_glau, hvf_vf_loss_oth, hvf_vf_defect_oth, hvf_mon, hvf_mon_oth2_c47, hvf_tar, hvf_tar_oth, "+
				"hvf_lossnum, hvf_lossden, hvf_fp, hvf_fn, hvf_dur, hvf_fov, hvf_stimintens, hvf_stimcol, hvf_stimcol_oth, hvf_back, "+
				"hvf_strategy, hvf_strategy_oth, hvf_pup, hvf_vanum, hvf_vaden, hvf_sph_sign, hvf_sph_num, hvf_cyl_sign, hvf_cyl_num, "+
				"hvf_axis, hvf_ght, hvf_vfi, hvf_mdsign, hvf_mddb, hvf_mdp, hvf_psdsign, hvf_psddb, hvf_psdp, hvf_central_15, hvf_central_0, "+
				"hvf_sup_hem, hvf_inf_hem, hvf_sup_hem2, hvf_inf_hem2, hvf_pts_five, hvf_pts_contig, hvf_pts_one, hvf_cluster, "+
				"hvf_severe, hvf_reliable_review, hvf_notes, hvf_notes_other, opthName, adjudicatorID) VALUES ";
			for(int i=0; i<updateLines.size(); i++) {
				if(i>0) { query += ", "; }
				query += "("+updateLines.get(i)+")";
			}
			if(updateLines.size() > 0) {
				SQLCommands.update(query);
			}
		}
		catch (Exception e) { 
			e.printStackTrace(); 
		}
		finally {
			try {
				reader.close();
				fileReader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static ArrayList<String> needPictures(){
		ArrayList<String> needPics = new ArrayList<String>();
		String query = "SELECT * FROM HVFtest WHERE pictureName NOT IN (SELECT name FROM picture)";
		Vector<HVFtest> hvf = SQLCommands.queryHVFtestMaster(query);

		for(int i=0; i<hvf.size(); i++) {
			if(!needPics.contains(hvf.get(i).getPictureName())) {
				needPics.add(hvf.get(i).getPictureName());
			}
		}

		String ext;
		for(int i=0; i<needPics.size(); i++) {
			ext = needPics.get(i).substring(needPics.get(i).indexOf(".")+1, needPics.get(i).length());
			if(ext.equals("pdf")) {
				needPics.set(i, needPics.get(i).substring(0,needPics.get(i).indexOf("."))+".jpg");
			}
		}

		return needPics;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the confirmed
	 */
	public int getConfirmed() {
		return confirmed;
	}

	/**
	 * @param confirmed the confirmed to set
	 */
	public void setConfirmed(int confirmed) {
		this.confirmed = confirmed;
	}

	/**
	 * @return the user/ID
	 */
	public int getUserID() {
		return userID;
	}

	/**
	 * @param userID the userID to set
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}

	/**
	 * @return the mon
	 */
	public int getMon() {
		return mon;
	}

	/**
	 * @param mon the mon to set
	 */
	public void setMon(int mon) {
		this.mon = mon;
	}

	/**
	 * @return the mon_oth2_c74
	 */
	public String getMon_oth2_c74() {
		return mon_oth2_c74;
	}

	/**
	 * @param mon_oth2_c74 the mon_oth2_c74 to set
	 */
	public void setMon_oth2_c74(String mon_oth2_c74) {
		this.mon_oth2_c74 = mon_oth2_c74;
	}

	/**
	 * @return the tar
	 */
	public int getTar() {
		return tar;
	}

	/**
	 * @param tar the tar to set
	 */
	public void setTar(int tar) {
		this.tar = tar;
	}

	/**
	 * @return the tar_oth
	 */
	public String getTar_oth() {
		return tar_oth;
	}

	/**
	 * @param tar_oth the tar_oth to set
	 */
	public void setTar_oth(String tar_oth) {
		this.tar_oth = tar_oth;
	}

	/**
	 * @return the lossnum
	 */
	public int getLossnum() {
		return lossnum;
	}

	/**
	 * @param lossnum the lossnum to set
	 */
	public void setLossnum(int lossnum) {
		this.lossnum = lossnum;
	}

	/**
	 * @return the lossden
	 */
	public int getLossden() {
		return lossden;
	}

	/**
	 * @param lossden the lossden to set
	 */
	public void setLossden(int lossden) {
		this.lossden = lossden;
	}

	/**
	 * @return the fp
	 */
	public int getFp() {
		return fp;
	}

	/**
	 * @param fp the fp to set
	 */
	public void setFp(int fp) {
		this.fp = fp;
	}

	/**
	 * @return the fn
	 */
	public int getFn() {
		return fn;
	}

	/**
	 * @param fn the fn to set
	 */
	public void setFn(int fn) {
		this.fn = fn;
	}

	/**
	 * @return the dur
	 */
	public String getDur() {
		return dur;
	}

	/**
	 * @param dur the dur to set
	 */
	public void setDur(String dur) {
		this.dur = dur;
	}

	/**
	 * @return the fov
	 */
	public int getFov() {
		return fov;
	}

	/**
	 * @param fov the fov to set
	 */
	public void setFov(int fov) {
		this.fov = fov;
	}

	/**
	 * @return the stimintens
	 */
	public int getStimintens() {
		return stimintens;
	}

	/**
	 * @param stimintens the stimintens to set
	 */
	public void setStimintens(int stimintens) {
		this.stimintens = stimintens;
	}

	/**
	 * @return the stimcol
	 */
	public int getStimcol() {
		return stimcol;
	}

	/**
	 * @param stimcol the stimcol to set
	 */
	public void setStimcol(int stimcol) {
		this.stimcol = stimcol;
	}

	/**
	 * @return the stimcol_oth
	 */
	public String getStimcol_oth() {
		return stimcol_oth;
	}

	/**
	 * @param stimcol_oth the stimcol_oth to set
	 */
	public void setStimcol_oth(String stimcol_oth) {
		this.stimcol_oth = stimcol_oth;
	}

	/**
	 * @return the back
	 */
	public String getBack() {
		return back;
	}

	/**
	 * @param back the back to set
	 */
	public void setBack(String back) {
		this.back = back;
	}

	/**
	 * @return the strategy
	 */
	public int getStrategy() {
		return strategy;
	}

	/**
	 * @param strategy the strategy to set
	 */
	public void setStrategy(int strategy) {
		this.strategy = strategy;
	}

	/**
	 * @return the pup
	 */
	public String getPup() {
		return pup;
	}

	/**
	 * @param pup the pup to set
	 */
	public void setPup(String pup) {
		this.pup = pup;
	}

	/**
	 * @return the vanum
	 */
	public int getVanum() {
		return vanum;
	}

	/**
	 * @param vanum the vanum to set
	 */
	public void setVanum(int vanum) {
		this.vanum = vanum;
	}

	/**
	 * @return the vaden
	 */
	public int getVaden() {
		return vaden;
	}

	/**
	 * @param vaden the vaden to set
	 */
	public void setVaden(int vaden) {
		this.vaden = vaden;
	}

	/**
	 * @return the sph_sign
	 */
	public int getSph_sign() {
		return sph_sign;
	}

	/**
	 * @param sph_sign the sph_sign to set
	 */
	public void setSph_sign(int sph_sign) {
		this.sph_sign = sph_sign;
	}

	/**
	 * @return the sph_num
	 */
	public String getSph_num() {
		return sph_num;
	}

	/**
	 * @param sph_num the sph_num to set
	 */
	public void setSph_num(String sph_num) {
		this.sph_num = sph_num;
	}

	/**
	 * @return the cyl_sign
	 */
	public int getCyl_sign() {
		return cyl_sign;
	}

	/**
	 * @param cyl_sign the cyl_sign to set
	 */
	public void setCyl_sign(int cyl_sign) {
		this.cyl_sign = cyl_sign;
	}

	/**
	 * @return the cyl_num
	 */
	public String getCyl_num() {
		return cyl_num;
	}

	/**
	 * @param cyl_num the cyl_num to set
	 */
	public void setCyl_num(String cyl_num) {
		this.cyl_num = cyl_num;
	}

	/**
	 * @return the axis
	 */
	public int getAxis() {
		return axis;
	}

	/**
	 * @param axis the axis to set
	 */
	public void setAxis(int axis) {
		this.axis = axis;
	}

	/**
	 * @return the ght
	 */
	public int getGht() {
		return ght;
	}

	/**
	 * @param ght the ght to set
	 */
	public void setGht(int ght) {
		this.ght = ght;
	}

	/**
	 * @return the vfi
	 */
	public String getVfi() {
		return vfi;
	}

	/**
	 * @param vfi the vfi to set
	 */
	public void setVfi(String vfi) {
		this.vfi = vfi;
	}

	/**
	 * @return the mdsign
	 */
	public int getMdsign() {
		return mdsign;
	}

	/**
	 * @param mdsign the mdsign to set
	 */
	public void setMdsign(int mdsign) {
		this.mdsign = mdsign;
	}

	/**
	 * @return the mddb
	 */
	public String getMddb() {
		return mddb;
	}

	/**
	 * @param mddb the mddb to set
	 */
	public void setMddb(String mddb) {
		this.mddb = mddb;
	}

	/**
	 * @return the mdp
	 */
	public int getMdp() {
		return mdp;
	}

	/**
	 * @param mdp the mdp to set
	 */
	public void setMdp(int mdp) {
		this.mdp = mdp;
	}

	/**
	 * @return the psdsign
	 */
	public int getPsdsign() {
		return psdsign;
	}

	/**
	 * @param psdsign the psdsign to set
	 */
	public void setPsdsign(int psdsign) {
		this.psdsign = psdsign;
	}

	/**
	 * @return the psddb
	 */
	public String getPsddb() {
		return psddb;
	}

	/**
	 * @param psddb the psddb to set
	 */
	public void setPsddb(String psddb) {
		this.psddb = psddb;
	}

	/**
	 * @return the psdp
	 */
	public int getPsdp() {
		return psdp;
	}

	/**
	 * @param psdp the psdp to set
	 */
	public void setPsdp(int psdp) {
		this.psdp = psdp;
	}

	/**
	 * @return the sup_hem
	 */
	public int getSup_hem() {
		return sup_hem;
	}

	/**
	 * @param sup_hem the sup_hem to set
	 */
	public void setSup_hem(int sup_hem) {
		this.sup_hem = sup_hem;
	}

	/**
	 * @return the inf_hem
	 */
	public int getInf_hem() {
		return inf_hem;
	}

	/**
	 * @param inf_hem the inf_hem to set
	 */
	public void setInf_hem(int inf_hem) {
		this.inf_hem = inf_hem;
	}

	/**
	 * @return the pts_five
	 */
	public int getPts_five() {
		return pts_five;
	}

	/**
	 * @param pts_five the pts_five to set
	 */
	public void setPts_five(int pts_five) {
		this.pts_five = pts_five;
	}

	/**
	 * @return the ptx_contig
	 */
	public int getPts_contig() {
		return pts_contig;
	}

	/**
	 * @param ptx_contig the ptx_contig to set
	 */
	public void setPts_contig(int pts_contig) {
		this.pts_contig = pts_contig;
	}

	/**
	 * @return the pts_one
	 */
	public int getPts_one() {
		return pts_one;
	}

	/**
	 * @param pts_one the pts_one to set
	 */
	public void setPts_one(int pts_one) {
		this.pts_one = pts_one;
	}

	/**
	 * @return the cluster
	 */
	public int getCluster() {
		return cluster;
	}

	/**
	 * @param cluster the cluster to set
	 */
	public void setCluster(int cluster) {
		this.cluster = cluster;
	}

	/**
	 * @return the severe
	 */
	public int getSevere() {
		return severe;
	}

	/**
	 * @param severe the severe to set
	 */
	public void setSevere(int severe) {
		this.severe = severe;
	}

	/**
	 * @return the sup_hem2
	 */
	public int getSup_hem2() {
		return sup_hem2;
	}

	/**
	 * @param sup_hem2 the sup_hem2 to set
	 */
	public void setSup_hem2(int sup_hem2) {
		this.sup_hem2 = sup_hem2;
	}

	/**
	 * @return the inf_hem2
	 */
	public int getInf_hem2() {
		return inf_hem2;
	}

	/**
	 * @param inf_hem2 the inf_hem2 to set
	 */
	public void setInf_hem2(int inf_hem2) {
		this.inf_hem2 = inf_hem2;
	}

	/**
	 * @return the strategy_oth
	 */
	public String getStrategy_oth() {
		return strategy_oth;
	}

	/**
	 * @param strategy_oth the strategy_oth to set
	 */
	public void setStrategy_oth(String strategy_oth) {
		this.strategy_oth = strategy_oth;
	}

	/**
	 * @return the opth_check
	 */
	public int getOpth_check() {
		return getOpthCheck();
	}

	/**
	 * @param opth_check the opth_check to set
	 */
	public void setOpth_check(int opth_check) {
		this.setOpthCheck(opth_check);
	}

	/**
	 * @return the hvf_glau
	 */
	public int getHvf_glau() {
		return hvf_glau;
	}

	/**
	 * @param hvf_glau the hvf_glau to set
	 */
	public void setHvf_glau(int hvf_glau) {
		this.hvf_glau = hvf_glau;
	}

	/**
	 * @return the central_15
	 */
	public int getCentral_15() {
		return central_15;
	}

	/**
	 * @param central_15 the central_15 to set
	 */
	public void setCentral_15(int central_15) {
		this.central_15 = central_15;
	}

	/**
	 * @return the central_0
	 */
	public int getCentral_0() {
		return central_0;
	}

	/**
	 * @param central_0 the central_0 to set
	 */
	public void setCentral_0(int central_0) {
		this.central_0 = central_0;
	}

	/**
	 * @return the reliable_review
	 */
	public int getReliable_review() {
		return reliable_review;
	}

	/**
	 * @param reliable_review the reliable_review to set
	 */
	public void setReliable_review(int reliable_review) {
		this.reliable_review = reliable_review;
	}

	/**
	 * @return the vf_loss
	 */
	public String getVf_loss() {
		return vf_loss;
	}

	/**
	 * @param vf_loss the vf_loss to set
	 */
	public void setVf_loss(String vf_loss) {
		this.vf_loss = vf_loss;
	}

	/**
	 * @return the vf_defect
	 */
	public String getVf_defect() {
		return vf_defect;
	}

	/**
	 * @param vf_defect the vf_defect to set
	 */
	public void setVf_defect(String vf_defect) {
		this.vf_defect = vf_defect;
	}

	/**
	 * @return the vf_loss_oth
	 */
	public String getVf_loss_oth() {
		return vf_loss_oth;
	}

	/**
	 * @param vf_loss_oth the vf_loss_oth to set
	 */
	public void setVf_loss_oth(String vf_loss_oth) {
		this.vf_loss_oth = vf_loss_oth;
	}

	/**
	 * @return the vf_defect_oth
	 */
	public String getVf_defect_oth() {
		return vf_defect_oth;
	}

	/**
	 * @param vf_defect_oth the vf_defect_oth to set
	 */
	public void setVf_defect_oth(String vf_defect_oth) {
		this.vf_defect_oth = vf_defect_oth;
	}

	/**
	 * @return the opthCheck
	 */
	public int getOpthCheck() {
		return opthCheck;
	}

	/**
	 * @param opthCheck the opthCheck to set
	 */
	public void setOpthCheck(int opthCheck) {
		this.opthCheck = opthCheck;
	}

	/**
	 * @return the pictureName
	 */
	public String getPictureName() {
		return pictureName;
	}

	/**
	 * @param pictureName the pictureName to set
	 */
	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}

	/**
	 * @return the opthName
	 */
	public String getOpthName() {
		return opthName;
	}

	/**
	 * @param opthName the opthName to set
	 */
	public void setOpthName(String opthName) {
		this.opthName = opthName;
	}

	/**
	 * @return the adjudicatorID
	 */
	public int getAdjudicatorID() {
		return adjudicatorID;
	}

	/**
	 * @param adjudicatorID the adjudicatorID to set
	 */
	public void setAdjudicatorID(int adjudicatorID) {
		this.adjudicatorID = adjudicatorID;
	}

	/**
	 * @return the notes
	 */
	public int getNotes() {
		return notes;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(int notes) {
		this.notes = notes;
	}

	/**
	 * @return the notes_other
	 */
	public String getNotes_other() {
		return notes_other;
	}

	/**
	 * @param notes_other the notes_other to set
	 */
	public void setNotes_other(String notes_other) {
		this.notes_other = notes_other;
	}

}