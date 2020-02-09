package layout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import functionalities.AddImage;
import functionalities.EditCategory;
import functionalities.LoadSave;
import functionalities.MergeCategories;
import functionalities.MoveImageTo;
import functionalities.NewCategory;
import functionalities.RemoveCategory;
import functionalities.RemoveImage;

/**
 * Panel that contains the main buttons for the application
 * @author gabri
 */
public class LowerPanel extends JPanel {

	private JButton firstBt, secondBt, thirdBt, fourthBt, fifthBt;
	private MiddlePanel middlePanRef;
	private LowerPanel lowPanRef;
	private static final long serialVersionUID = 1L;
	
	/**
	 * Initialize the Lower Panel with its buttons
	 * @param middlePanRef Middle Panel Reference
	 * @param bool Boolean value used to create the right buttons (Album Buttons or Category Buttons)
	 */
	public LowerPanel(MiddlePanel middlePanRef, Boolean bool) {
		createPanel();
		if(bool) {
			createPanelAlbum();
		}
		else {
			createPanelCat();
		}
		setMiddlePanRef(middlePanRef);
	}
	
	/**
	 * Create the panel with default values
	 */
	public void createPanel() {
		setBackground(Color.LIGHT_GRAY);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		setMinimumSize(new Dimension(1000,100));
		setPreferredSize(new Dimension(800,100));	
	}
	
	/**
	 * Method used to create the panel with the buttons relative to the Album
	 */
	private void createPanelAlbum() {
		
		//New Category Button
		String newcategory = "defaults" + File.separator + "plus-icon.png";
		firstBt = new Button("New Category", newcategory);
		getFirstBt().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new NewCategory(getMiddlePanRef(), true);
			}
			
		});
		add(getFirstBt());
		
		//Remove Category Button
		String removecategory = "defaults" + File.separator + "minus-icon.png";
		secondBt = new Button("Remove Category", removecategory);
		getSecondBt().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new RemoveCategory(getMiddlePanRef().getCurrentAlbum(), getLowPanRef());
				
			}
			
		});
		add(getSecondBt());
		
		//Merge Categories Button
		String mergecategories = "defaults" + File.separator + "merge-folders.png";
		thirdBt = new Button("Merge Categories", mergecategories);
		getThirdBt().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new MergeCategories(getLowPanRef());
			}
			
		});
		add(getThirdBt());

		//Edit Category Button
		String editcategory = "defaults" + File.separator + "edit-icon.png";
		fourthBt= new Button("Edit Category" , editcategory);
		getFourthBt().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new EditCategory(getLowPanRef());
			}
			
		});
		add(getFourthBt());
		
		//Save Album Button
		String savealbum = "defaults" + File.separator + "save-icon.png";
		fifthBt = new Button("Save Album", savealbum);
		getFifthBt().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new LoadSave(getLowPanRef().getMiddlePanRef().getCurrentAlbum());
			}
			
		});
		add(getFifthBt());
		setLowPanRef(this);
	}
	
	/**
	 * Method used to create the panel with the buttons relative to the Category
	 */
	private void createPanelCat() {
		
		//Add Image From Web URL
		String addwebicon = "defaults" + File.separator + "globe-icon.png";
		firstBt = new Button("Add from url", addwebicon);
		getFirstBt().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddImage addIm = new AddImage();
				addIm.loadImage(true, getLowPanRef());
			}
			
		});
		add(getFirstBt());
		
		//Add Image
		String addicon = "defaults" + File.separator + "plus-icon.png";
		secondBt = new Button("Add", addicon);
		getSecondBt().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				AddImage addIm = new AddImage();
				addIm.loadImage(false, getLowPanRef());
			}
			
		});
		add(getSecondBt());
		
		//Remove Image
		String removeicon = "defaults" + File.separator + "minus-icon.png";
		thirdBt = new Button("Remove", removeicon);
		getThirdBt().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new RemoveImage(getLowPanRef());
			}
			
		});
		add(getThirdBt());
		
		//Move Image to
		String moveto = "defaults" + File.separator + "move-to-cat.png";
		fourthBt = new Button("Move to", moveto);
		getFourthBt().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new MoveImageTo(getLowPanRef());
				
			}
			
		});
		add(getFourthBt());
		
		//Return to Home Screen
		String home = "defaults" + File.separator + "home-icon.png";
		fifthBt = new Button("Home", home);
		getFifthBt().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				getMiddlePanRef().getParent().getAlbPan().getLeftPanRef().removeInfos();
				getMiddlePanRef().getParent().showAlbPan();
			}
			
		});
		add(getFifthBt());
		setLowPanRef(this);
	}
	
	/**
	 * Get first button
	 * @return button
	 */
	public JButton getFirstBt() {
		return firstBt;
	}
	
	/**
	 * Set first button
	 * @param firstBt first button
	 */
	public void setFirstBt(JButton firstBt) {
		this.firstBt = firstBt;
	}
	
	/**
	 * Get second button
	 * @return button
	 */
	public JButton getSecondBt() {
		return secondBt;
	}
	
	/**
	 * Set second button
	 * @param secondBt button
	 */
	public void setSecondBt(JButton secondBt) {
		this.secondBt = secondBt;
	}
	
	/**
	 * Get third button
	 * @return button
	 */
	public JButton getThirdBt() {
		return thirdBt;
	}
	
	/**
	 * Set third button
	 * @param thirdBt button
	 */
	public void setThirdBt(JButton thirdBt) {
		this.thirdBt = thirdBt;
	}
	
	/**
	 * Get fourth button
	 * @return button
	 */
	public JButton getFourthBt() {
		return fourthBt;
	}
	
	/**
	 * Set fourth button
	 * @param fourthBt button
	 */
	public void setFourthBt(JButton fourthBt) {
		this.fourthBt = fourthBt;
	}
	
	/**
	 * Get fifth button
	 * @return button
	 */
	public JButton getFifthBt() {
		return fifthBt;
	}
	
	/**
	 * Set fifth button
	 * @param fifthBt button
	 */
	public void setFifthBt(JButton fifthBt) {
		this.fifthBt = fifthBt;
	}
	
	/**
	 * Get Middle Panel Ref
	 * @return middle panel
	 */
	public MiddlePanel getMiddlePanRef() {
		return middlePanRef;
	}
	
	/**
	 * Set Middle Panel Ref
	 * @param middlePanRef middle panel
	 */
	public void setMiddlePanRef(MiddlePanel middlePanRef) {
		this.middlePanRef = middlePanRef;
	}

	/**
	 * Get Lower Panel Ref
	 * @return lower panel
	 */
	public LowerPanel getLowPanRef() {
		return lowPanRef;
	}

	/**
	 * Set Lower Panel Ref
	 * @param lowPanRef lower panel
	 */
	public void setLowPanRef(LowerPanel lowPanRef) {
		this.lowPanRef = lowPanRef;
	}
	
}
