package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Panel that contains the main buttons for the application
 * @author gabbb
 */
public class LowerPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	/**
	 * Buttons of the application
	 * @param next go to next image, if able
	 * @param last go to previous image, if able
	 * @param add add an image
	 * @param remove remove a category
	 * @param addweb add an image from a URL
	 */
	private JButton addwebbt, addbt, removebt, categorybt, copytobt, mergebt, prevbt, nextbt;
	private MiddlePanel p2;
	private ImagePanel ip;
	
	public LowerPanel(Color c, LeftPanel p1, MiddlePanel p2, ImagePanel ip, JFrame jf) {
		this.setP2(p2);
		setBorder(BorderFactory.createLineBorder(Color.GREEN, 5));
		this.setImagePanel(ip);
		this.CreatePanel(c);
	}
	/**
	 * Method used to create the panel with buttons and such
	 * @param c Color of the background of the panel
	 */
	private void CreatePanel(Color c) {
		this.setBackground(c);
		this.setMinimumSize(new Dimension(1000,100));
		this.setPreferredSize(new Dimension(800,100));
		this.setLayout(new FlowLayout());
		
		String addwebicon = "defaults" + File.separator + "globe-icon.png";
		this.addwebbt = new Button("Add from url", addwebicon, "addweb", this);
		this.add(this.addwebbt);
		this.addwebbt.addActionListener((ActionListener) this.addwebbt);
		
		String addicon = "defaults" + File.separator + "plus-icon.png";
		this.addbt = new Button("Add", addicon, "add", this);
		this.add(this.addbt);
		this.addbt.addActionListener((ActionListener) this.addbt);
		
		String removeicon = "defaults" + File.separator + "minus-icon.png";
		this.removebt = new Button("Remove", removeicon, "remove", this);
		this.add(this.removebt);
		this.removebt.addActionListener((ActionListener) this.removebt);
		
		String newcategory = "defaults" + File.separator + "category-icon.png";
		this.categorybt = new Button("New Category", newcategory, "newcat", this);
		this.add(this.categorybt);
		this.categorybt.addActionListener((ActionListener) this.categorybt);
		
		String copyto = "defaults" + File.separator + "copy-to-cat.png";
		this.copytobt = new Button("Copy to", copyto, "copyto", this);
		this.add(this.copytobt);
		this.copytobt.addActionListener((ActionListener) this.copytobt);
		
		String mergecategories = "defaults" + File.separator + "merge-folders.png";
		this.mergebt = new Button("Merge Categories", mergecategories, "mergeto", this);
		this.add(this.mergebt);
		this.mergebt.addActionListener((ActionListener) this.mergebt);

		String previous = "defaults" + File.separator + "previous-icon.png";
		this.prevbt = new Button("Previous", previous, "prev", this);
		this.add(this.prevbt);
		this.prevbt.addActionListener((ActionListener) this.prevbt);
		
		String next = "defaults" + File.separator + "next-icon.png";
		this.nextbt = new Button("Next", next, "next", this);
		this.add(this.nextbt);
		this.mergebt.addActionListener((ActionListener) this.nextbt);
	}
	
	public MiddlePanel getP2() {
		return p2;
	}

	public void setP2(MiddlePanel p2) {
		this.p2 = p2;
	}

	public ImagePanel getImagePanel() {
		return this.ip;
	}
	
	public void setImagePanel(ImagePanel ip) {
		this.ip = ip;
	}
	
}
