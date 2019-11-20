package graphics;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import functionalities.PhotoAlbum;

/**
 * Main panel that contains the categories and can be used to access the images
 * @author gabbb
 *
 */
public class MiddlePanel extends JPanel {

	private int imgcurr = 0;
	private int imgmax = 4;      //Max Images in a row
	private static final long serialVersionUID = 1L;
	private PhotoAlbum currentCategory;
	public MiddlePanel(Color c) {
		this.CreatePanel(c);
	}
	
	public void CreatePanel(Color c) {
		this.setBackground(c);
		this.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 20));
		//this.setLayout(new WrapLayout(WrapLayout.LEFT, 20,20));
		this.setMinimumSize(new Dimension(400,400));
		this.setPreferredSize(new Dimension(1100, 750));
		this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
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

	public PhotoAlbum getCurrentCategory() {
		return currentCategory;
	}

	public void setCurrentCategory(PhotoAlbum currentCategory) {
		this.currentCategory = currentCategory;
	}
	
}
