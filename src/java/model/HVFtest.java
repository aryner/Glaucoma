/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.*; 
/*
import java.io.FileOutputStream;
import org.apache.poi.hwpf.model.PicturesTable;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.apache.poi.xwpf.usermodel.*;
import javax.imageio.ImageIO;
import java.awt.image.*;

import javax.imageio.stream.*;
*/
import java.io.*;
import javax.imageio.*;
/*
import javax.media.jai.*;

import java.awt.image.renderable.ParameterBlock; 
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;

import com.sun.media.jai.codec.*;
import com.sun.media.jai.codec.TIFFDecodeParam;
import org.ghost4j.document.PDFDocument;
import org.ghost4j.renderer.SimpleRenderer;
*/
import utilities.SQLCommands;
import java.awt.Graphics2D;
import java.awt.Image;
import java.nio.channels.FileChannel;
import java.nio.ByteBuffer; 
import com.sun.pdfview.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author aryner
 */
public class HVFtest {
	private int id;
	private int confirmed;
	private int opth_check;
	private int pictureID;
	private int userID;
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
	private int pts2;
	private int sup_hem;
	private int inf_hem;
	private int sup_hem2;
	private int inf_hem2;
	private int pts_five;
	private int pts_contig;
	private int pts_one;
	private int cluster;
	private int flau;
	private int severe;
	private static final String slash = System.getProperty("file.separator");

	public HVFtest(int npictureID) {
		pictureID = npictureID;
	}

	public HVFtest(int npictureID, int nuserID) {
		String query = "INSERT INTO HVFtest (pictureID, userID) VALUES ('"+npictureID+"', '"+nuserID+"')";
		SQLCommands.update(query);

		query = "SELECT * FROM HVFtest WHERE pictureID="+npictureID+" AND userID="+nuserID;
		Vector<Integer> data = SQLCommands.queryNewGrade(query);

		pictureID = data.get(0);
		userID = data.get(1);
		id = data.get(2);	
	}

	public static void createPictures(Vector<String> fileNames) {
		String query = "INSERT INTO picture (name, type) VALUES ";
		ArrayList<String> firstPics = new ArrayList<String>();
		ArrayList<String> secondPics = new ArrayList<String>();

		for(int i=0; i<fileNames.size(); i++) {
			try {

				//convert pdf to img
				String name = fileNames.get(i).substring(0,fileNames.get(i).indexOf("."));
				File file = new File(".."+slash+"webapps"+slash+"Glaucoma"+slash + "HVF" + slash+fileNames.get(i));
				RandomAccessFile raf = new RandomAccessFile(file, "r");
				FileChannel channel = raf.getChannel();
				ByteBuffer buff = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
				PDFFile pdf = new PDFFile(buff);
				PDFPage page = pdf.getPage(0);

				Rectangle rect = new Rectangle(0, 0, (int)page.getWidth(),
								(int)page.getHeight());
				BufferedImage bufferedImage = new BufferedImage(rect.width,rect.height, BufferedImage.TYPE_INT_RGB);
				Image image = page.getImage(rect.width, rect.height, rect, null, true, true);
				Graphics2D bufImageGraphics = bufferedImage.createGraphics();
				bufImageGraphics.drawImage(image, 0,0, null);
				ImageIO.write(bufferedImage, "png", new File(".."+slash+"webapps"+slash+"Glaucoma"+slash + "HVF" + slash+name+".png"));
				if (i>0) { query += ", "; }
				query += "('"+name+".png', 'HVF')";
				
//				file.delete(); 
/*
				PDFDocument document = new PDFDocument();
				document.load(file);

				SimpleRenderer render = new SimpleRenderer();
				render.setResolution(300);

				List<Image> images = render.render(document);

				ImageIO.write((RenderedImage)images.get(0), "png", new File(".."+slash+"webapps"+slash+"Glaucoma"+slash + "HVF" + slash+name+".png"));
*/
/*
				XWPFDocument doc = new XWPFDocument(file);
				List picList = doc.getAllPictures();

				ByteArraySeekableStream stream = new ByteArraySeekableStream(((XWPFPictureData)picList.get(0)).getData());
				TIFFDecodeParam decodeParam = new TIFFDecodeParam();
//				decodeParam.setDecodePaletteAsShorts(true);
				RenderedOp image1 = JAI.create("tiff", stream);
				BufferedImage img = image1.getAsBufferedImage();
*/
/*
				XWPFDocument doc = new XWPFDocument(file); 
				XHTMLOptions options = XHTMLOptions.create();
				
				FileOutputStream fileOut = new FileOutputStream(".."+slash+"webapps"+slash+"Glaucoma"+slash + "HVF" + slash+name+".html");
				XHTMLConverter.getInstance().convert(doc, fileOut, options);
*/
//				file.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		SQLCommands.update(query);
	}

	public static void assignHVF(HttpServletRequest request, int uID) {
		String picID = request.getParameter("pictureID");
		HVFtest hvf = new HVFtest(Integer.parseInt(picID), uID);

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
		attr = request.getParameter("pts2");
		hvf.setPts2(Integer.parseInt(attr));
		attr = request.getParameter("sup_hem");
		hvf.setSup_hem(Integer.parseInt(attr));
		attr = request.getParameter("inf_hem");
		hvf.setInf_hem(Integer.parseInt(attr));
		attr = request.getParameter("sup_hem2");
		hvf.setSup_hem2(Integer.parseInt(attr));
		attr = request.getParameter("inf_hem2");
		hvf.setInf_hem2(Integer.parseInt(attr));
		attr = request.getParameter("pts_five");
		hvf.setPts_five(Integer.parseInt(attr));
		attr = request.getParameter("pts_contig");
		hvf.setPts_contig(Integer.parseInt(attr));
		attr = request.getParameter("pts_one");
		hvf.setPts_one(Integer.parseInt(attr));
		attr = request.getParameter("cluster");
		hvf.setCluster(Integer.parseInt(attr));

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
			"hvf_pts2='"+hvf.getPts2()+"', hvf_sup_hem='"+hvf.getSup_hem()+"', hvf_inf_hem='"+hvf.getInf_hem()+"', "+
			"hvf_sup_hem2='"+hvf.getSup_hem2()+"', hvf_inf_hem2='"+hvf.getInf_hem2()+"', hvf_pts_five='"+hvf.getPts_five()+"', "+
			"hvf_pts_contig='"+hvf.getPts_contig()+"', hvf_pts_one='"+hvf.getPts_one()+"', hvf_cluster='"+hvf.getCluster()+"' " +
			"WHERE id="+hvf.getId();
		SQLCommands.update(query);
	}

	public static Picture getNext(User user) {
		Picture result = null;
		String query = "";
		if (user.getAccess() == 0) {
			query = "SELECT * FROM picture WHERE id NOT IN (SELECT "+
				" pictureID FROM HVFtest WHERE userID="+user.getID()+")";
		}

		Vector<Picture> pictures = SQLCommands.queryPictures(query);
		
		if(pictures.size() > 0) {
			Random rand = new Random(System.currentTimeMillis());
			result = pictures.get(rand.nextInt(pictures.size()));
		}

		return result;
	}

	public static int getNeedToPairCount() {
		String query = "SELECT * FROM HVFtest GROUP BY pictureID HAVING COUNT(*)=1";
		return SQLCommands.getCount(query);
	}

	public static void setForAdjudication() {
		//get the ones that don't need adjudication
		String query = "SELECT * FROM HVFtest GROUP BY pictureID, "+
			"hvf_mon, hvf_mon_oth2_c47, hvf_tar, hvf_tar_oth, hvf_lossnum, hvf_lossden, "+
			"hvf_fp, hvf_fn, hvf_dur, hvf_fov, hvf_stimintens, hvf_stimcol, hvf_stimcol_oth, "+
			"hvf_back, hvf_strategy, hvf_strategy_oth, hvf_pup, hvf_vanum, hvf_vaden, hvf_sph_sign, "+
			"hvf_sph_num, hvf_cyl_sign, hvf_cyl_num, hvf_axis, hvf_ght, hvf_vfi, hvf_mdsign, hvf_mddb, "+
			"hvf_mdp, hvf_psdsign, hvf_psddb, hvf_psdp, hvf_pts2, hvf_sup_hem, hvf_inf_hem, hvf_sup_hem2, "+
			"hvf_inf_hem2, hvf_pts_five, hvf_pts_contig, hvf_pts_one, hvf_cluster "+
			"HAVING COUNT(*)=2";
		Vector<HVFtest> set = SQLCommands.queryHVFtest(query);
		//get the ones that need adjudication
		query = "SELECT * FROM HVFtest GROUP BY pictureID, "+
			"hvf_mon, hvf_mon_oth2_c47, hvf_tar, hvf_tar_oth, hvf_lossnum, hvf_lossden, "+
			"hvf_fp, hvf_fn, hvf_dur, hvf_fov, hvf_stimintens, hvf_stimcol, hvf_stimcol_oth, "+
			"hvf_back, hvf_strategy, hvf_strategy_oth, hvf_pup, hvf_vanum, hvf_vaden, hvf_sph_sign, "+
			"hvf_sph_num, hvf_cyl_sign, hvf_cyl_num, hvf_axis, hvf_ght, hvf_vfi, hvf_mdsign, hvf_mddb, "+
			"hvf_mdp, hvf_psdsign, hvf_psddb, hvf_psdp, hvf_pts2, hvf_sup_hem, hvf_inf_hem, hvf_sup_hem2, "+
			"hvf_inf_hem2, hvf_pts_five, hvf_pts_contig, hvf_pts_one, hvf_cluster "+
			"HAVING COUNT(*)=1";
		Vector<HVFtest> notSet = SQLCommands.queryHVFtest(query);
		
		//update the confirmed ones
		query = "UPDATE HVFtest SET confirmed=2 WHERE ";
		for(int i=0; i<set.size(); i++) {
			if(i>0) { query += " OR "; }
			query += "pictureID="+set.get(i).getPictureID();
		}
		if(set.size() > 0) {
			SQLCommands.update(query);
		}

		//update the ones that need confirming
		query = "UPDATE HVFtest SET confirmed=1 WHERE ";
		for(int i=0; i<notSet.size(); i++) {
			if(i>0) { query += " OR "; }
			query += "pictureID="+notSet.get(i).getPictureID();
		}
		if(notSet.size() > 0) {
			SQLCommands.update(query);
		}
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
	 * @return the pictureID
	 */
	public int getPictureID() {
		return pictureID;
	}

	/**
	 * @param pictureID the pictureID to set
	 */
	public void setPictureID(int pictureID) {
		this.pictureID = pictureID;
	}

	/**
	 * @return the userID
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
	 * @return the pts2
	 */
	public int getPts2() {
		return pts2;
	}

	/**
	 * @param pts2 the pts2 to set
	 */
	public void setPts2(int pts2) {
		this.pts2 = pts2;
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
	 * @return the flau
	 */
	public int getFlau() {
		return flau;
	}

	/**
	 * @param flau the flau to set
	 */
	public void setFlau(int flau) {
		this.flau = flau;
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
		return opth_check;
	}

	/**
	 * @param opth_check the opth_check to set
	 */
	public void setOpth_check(int opth_check) {
		this.opth_check = opth_check;
	}

}