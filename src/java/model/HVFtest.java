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

/**
 *
 * @author aryner
 */
public class HVFtest {
	private int id;
	private int confirmed;
	private int pictureID;
	private int userID;
	private int mon;
	private String mon_oth2_c74; 
	private int tar;
	private int tar_oth;
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
	private int pts_five;
	private int ptx_contig;
	private int pts_one;
	private int cluster;
	private int flau;
	private int severe;
	private static final String slash = System.getProperty("file.separator");

	public HVFtest(int nid, int npictureID) {
		id = nid;
		pictureID = npictureID;
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
	public int getTar_oth() {
		return tar_oth;
	}

	/**
	 * @param tar_oth the tar_oth to set
	 */
	public void setTar_oth(int tar_oth) {
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
	public int getPtx_contig() {
		return ptx_contig;
	}

	/**
	 * @param ptx_contig the ptx_contig to set
	 */
	public void setPtx_contig(int ptx_contig) {
		this.ptx_contig = ptx_contig;
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

}