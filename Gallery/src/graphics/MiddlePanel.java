package graphics;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import functionalities.Category;
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
	private Category currentCategory;
	private PhotoAlbum currentAlbum;
	public MiddlePanel(Color c) {
		//default category without name and description
		this.setCurrentAlbum(new PhotoAlbum());
		this.CreatePanel(c);
	}
	
	public void CreatePanel(Color c) {
		this.setBackground(c);
		//this.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 20));
		this.setLayout(new WrapLayout(WrapLayout.LEFT, 0,0));
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
	
}
