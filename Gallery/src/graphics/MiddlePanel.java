package graphics;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import functionalities.Category;
import functionalities.Image;
import functionalities.PhotoAlbum;

/**
 * Main panel that contains the categories and can be used to access the images
 * @author gabbb
 *
 */
public class MiddlePanel extends JPanel implements MouseListener {

	private int imgcurr = 0;
	private int imgmax = 4;      //Max Images in a row
	private static final long serialVersionUID = 1L;
	private PhotoAlbum currentAlbum;
	private Category currentCategory;
	private Image currentImage;
	private LeftPanel lp;
	private MainFrame parent;
	private ImagePanel ip;
	public MiddlePanel(Color c, MainFrame mf) {
		//default category without name and description
		setCurrentAlbum(new PhotoAlbum());
		setParent(mf);
		CreatePanel(c);
	}
	
	public void CreatePanel(Color c) {
		setBackground(c);
		setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
		//this.setLayout(new WrapLayout(WrapLayout.LEFT, 0,0));
		setMinimumSize(new Dimension(400,400));
		setPreferredSize(new Dimension(1100, 750));
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	}

	public int getImgcurr() {
		return imgcurr;
	}

	public void setImgcurr(int imgcurr) {
		this.imgcurr = imgcurr;
	}

	public int getImgmax() {
		return imgmax;
	}

	public void setImgmax(int imgmax) {
		this.imgmax = imgmax;
	}

	public PhotoAlbum getCurrentAlbum() {
		return currentAlbum;
	}

	public void setCurrentAlbum(PhotoAlbum currentAlbum) {
		this.currentAlbum = currentAlbum;
	}

	public Category getCurrentCategory() {
		return currentCategory;
	}

	public void setCurrentCategory(Category currentCategory) {
		this.currentCategory = currentCategory;
	}

	public Image getCurrentImage() {
		return currentImage;
	}

	public void setCurrentImage(Image currentImage) {
		this.currentImage = currentImage;
	}
	
	
	public LeftPanel getLp() {
		return lp;
	}

	public void setLp(LeftPanel lp) {
		this.lp = lp;
	}

	public MainFrame getParent() {
		return parent;
	}

	public void setParent(MainFrame parent) {
		this.parent = parent;
	}

	public ImagePanel getIp() {
		return ip;
	}

	public void setIp(ImagePanel ip) {
		this.ip = ip;
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
