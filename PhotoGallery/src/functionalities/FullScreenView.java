package functionalities;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import basecomponents.Category;
import layout.Button;

/**
 * Class used to create a new window that displays the images of a category with their original size
 * It has two buttons to explore the category and one button to create a slideshow of it
 * @author gabri
 *
 */
public class FullScreenView extends JFrame {

	private JLabel imgPan;
	private Category catRef;
	private int currentIndex;
	private static final long serialVersionUID = 1L;
	
	public FullScreenView(Category catRef, int index) {
		super(catRef.getName());
		setCatRef(catRef);
		createFrame(index);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		pack();
	}
	
	/**
	 * Method used to create the Frame and lay out its components
	 * @param index
	 */
	public void createFrame(int index) {
		setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().height, Toolkit.getDefaultToolkit().getScreenSize().width));
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		JLabel imgLabel = new JLabel();
		imgLabel.setPreferredSize(new Dimension(1580, 1024));
		setImgPan(imgLabel);
		getImgPan().setBounds(40, 30, 1000, 700);
	
		//JScrollablePane
		JPanel jPan = new JPanel();
		//Initial image
		setImage(index);
		setCurrentIndex(index);
		getImgPan().setAlignmentX(Component.CENTER_ALIGNMENT);
		jPan.add(getImgPan());
		jPan.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JScrollPane scrollPane = new JScrollPane(jPan);
		add(scrollPane);
		
		//Buttons Panel
		JPanel buttPan = new JPanel();
		buttPan.setBounds(40, 30, 400, 300);
		
		//Last img button
		Button lastImg = new Button("previous", "defaults"+ File.separator + "previous-icon.png");
		lastImg.setPreferredSize(new Dimension(100, 100));
		lastImg.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setImage(getLastIndex(getCurrentIndex()));
			}
			
		});
		
		buttPan.add(lastImg);
		
		buttPan.add(Box.createRigidArea(new Dimension(50, 50)));
		
		//Create Slideshow button
		Button createSlideShow = new Button("slideshow", "defaults" + File.separator + "slideshow-icon.png");
		createSlideShow.setPreferredSize(new Dimension(100, 100));
		createSlideShow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new SlideShow(getCatRef());
			}
			
		});
		
		buttPan.add(createSlideShow);
		
		buttPan.add(Box.createRigidArea(new Dimension(50, 50)));
		
		//Next img button
		Button nextImg = new Button("next", "defaults" + File.separator + "next-icon.png");
		nextImg.setPreferredSize(new Dimension(100, 100));
		nextImg.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setImage(getNextIndex(getCurrentIndex()));
			}
			
		});
		
		buttPan.add(nextImg);
		
		add(buttPan);
	}

	/**
	 * Method to set a new image
	 */
	public void setImage(int index) {
		ImageIcon icon = new ImageIcon(getCatRef().getDefCat().get(index).getRawImage());
		Image img = icon.getImage();
		Image newImg = img.getScaledInstance(getCatRef().getDefCat().get(index).getRawImage().getWidth(), getCatRef().getDefCat().get(index).getRawImage().getHeight(), Image.SCALE_SMOOTH);
		ImageIcon newImc = new ImageIcon(newImg);
		getImgPan().setIcon(newImc);
	}
	
	/**
	 * Method used to get the next index of the image to display
	 * @param currentInd current image index
	 * @return updated index
	 */
	public int getNextIndex(int currentInd) {
		if(currentInd + 1 >= getCatRef().getDefCat().size()) {
			setCurrentIndex(0);
			return getCurrentIndex();
		}
		else {
			setCurrentIndex(getCurrentIndex()+1);
			return getCurrentIndex();
		}
	}
	
	/**
	 * Method used to get the previous index of the image to display
	 * @param currentInd current image index
	 * @return updated index
	 */
	public int getLastIndex(int currentInd) {
		if(currentInd <= 0) {
			setCurrentIndex(getCatRef().getDefCat().size()-1);
			return getCurrentIndex();
		}
		else {
			setCurrentIndex(getCurrentIndex()-1);
			return getCurrentIndex();
		}
	}
	
	/**
	 * Get Image Label
	 * @return label containing the image 
	 */
	public JLabel getImgPan() {
		return imgPan;
	}

	/**
	 * Set Image Label
	 * @param imgPan image label reference
	 */
	public void setImgPan(JLabel imgPan) {
		this.imgPan = imgPan;
	}

	/**
	 * Get Current Category Displayed
	 * @return current category 
	 */
	public Category getCatRef() {
		return catRef;
	}

	/**
	 * Set Current Category Displayed
	 * @param catRef current category
	 */
	public void setCatRef(Category catRef) {
		this.catRef = catRef;
	}

	/**
	 * Get Current Index used to display the current image
	 * @return current index
	 */
	public int getCurrentIndex() {
		return currentIndex;
	}

	/**
	 * Set Current Index
	 * @param currentIndex current index
	 */
	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}
	
	
	

}
