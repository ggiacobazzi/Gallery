package functionalities;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import basecomponents.CategoryProtected;
import layout.CategoryImagePanel;

/**
 * Class that opens a Window when trying to use a protected category. It needs an input password in order to
 * check if it is the correct one
 */

public class PasswordCheck extends JFrame{

	private final String iconPath = "defaults" + File.separator + "lock-icon.png";
	private JTextField passField;
	private JButton cancelBt, confirmBt;
	private CategoryProtected catRef;
	private JFrame windowRef;
	private CategoryImagePanel catPanRef;
	private RemoveCategory remCatRef;
	private EditCategory editCatRef;
	private MergeCategories mergeCatRef;
	private int index;
	private int secIndex;
	private static final long serialVersionUID = 1L;
	
	//Base Constructor
	/**
	 * Initialize a Password Check window to access a protected category
	 * @param category Reference to the protected category
	 * @param caseCat String used to determine what to do
	 */
	public PasswordCheck(CategoryProtected category, String caseCat) {
		super("Input password");
		setCatRef(category);
		setWindowRef(this);
		JPanel contPan = new JPanel();
		createWindow(contPan, caseCat);
		add(contPan);
		setLocationRelativeTo(null);
		setVisible(true);
		pack();

	}
	
	//Constructor for accessCat
	/**
	 * Initialize a Password Check when the user wants to access a protected category
	 * @param category Reference to the protected category
	 * @param imagePanel Reference to the Category Image Panel of the category to access
	 * @param caseCat String used to determine what to do
	 */
	public PasswordCheck(CategoryProtected category, CategoryImagePanel imagePanel, String caseCat){
		this(category, caseCat);
		setCatPanRef(imagePanel);
	}
	
	//Constructor for removeCat
	/**
	 * Initialize a Password Check when the user wants to remove a protected category
	 * @param category Reference to the protected category
	 * @param caseCat String used to determine what to do
	 * @param remRef Reference to the Remove Category Window
	 * @param index Index of the Category to remove
	 */
	public PasswordCheck(CategoryProtected category, String caseCat, RemoveCategory remRef, int index) {
		this(category, caseCat);
		setRemCatRef(remRef);
		setIndex(index);
	}
	
	//Constructor for editCat
	/**
	 * Initialize a Password Check when the user wants to edit a protected category
	 * @param category Reference to the protected category
	 * @param caseCat String used to determine what to do
	 * @param editCatRef Reference to the Edit Category Window
	 * @param index Index of the Category to edit
	 */
	public PasswordCheck(CategoryProtected category, String caseCat, EditCategory editCatRef, int index) {
		this(category, caseCat);
		setEditCatRef(editCatRef);
		setIndex(index);
	}
	
	//Constructor for mergeCat
	/**
	 * Initialize a Password Check when the user wants to merge two categories (the source category is protected)
	 * @param category Reference to the protected category
	 * @param caseCat String used to determine what to do
	 * @param mergeCatRef Reference to the Merge Categories Window
	 * @param source index of the source category
	 * @param destination index of the destination category
	 */
	public PasswordCheck(CategoryProtected category, String caseCat, MergeCategories mergeCatRef, int source, int destination) {
		this(category, caseCat);
		setMergeCatRef(mergeCatRef);
		setIndex(source);
		setSecIndex(destination);
	}
	
	
	/**
	 * Method used to create the window and lay out its components
	 * @param jPan Reference to the Panel that will be added
	 * @param caseCat String used to determine which action it needs to do
	 */
	@SuppressWarnings("unused")
	public void createWindow(JPanel jPan, String caseCat) {
		setPreferredSize(new Dimension(700, 160));
		setResizable(false);
		jPan.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
		jPan.add(chooseImageForIcon(getIconPath()));
		SwingUtilities.updateComponentTreeUI(jPan);
		passField = new JTextField(25);
		getPassField().setPreferredSize(new Dimension(200, 25));
		jPan.add(getPassField());
		
		//Button Panel
		JPanel buttPan = new JPanel();
		buttPan.setLayout(new BoxLayout(buttPan, BoxLayout.Y_AXIS));
		
		confirmBt = new JButton("Enter");
		buttPan.add(getConfirmBt());
		
		getConfirmBt().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Check Password
				if(getPassField().getText().equals(getCatRef().getPassword())){
					Status s = new Status(true, "Password corretta!");
					determineCase(caseCat);
				}
				else {
					Status s = new Status(false, "Password errata!");
				}
				
				closeJframe();
			}
			
		});
		
		buttPan.add(Box.createRigidArea(new Dimension(25, 10)));
		
		cancelBt = new JButton("Cancel");
		buttPan.add(getCancelBt());
		getCancelBt().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Status s = new Status(false, "Password non immessa!");
				closeJframe();
			}
			
		});
		
		jPan.add(buttPan);
		pack();
	}
	
	/**
	 * Method used to create a Label that contains the image
	 * @param image Image that will be displayed
	 * @return picLabel JLabel with the image
	 */
	public static JLabel displayImage(BufferedImage image) {
		JLabel picLabel = new JLabel();
		picLabel.setSize(240, 160);  //240, 160  160 108
		ImageIcon img = new ImageIcon(image.getScaledInstance(160, 108, java.awt.Image.SCALE_SMOOTH));
		picLabel.setIcon(img);
		picLabel.setVisible(true);
		return picLabel;
	}
	
	/**
	 * Method used to display an image using an icon Path.
	 * @param iconPath
	 * @return it calls another method specialized in creating a JLabel that will be returned here
	 * 		   null if it fails to read the image (it should not happen at all)
	 */
	public JLabel chooseImageForIcon(String iconPath) {
		BufferedImage ex;
		try {
			ex = ImageIO.read(new File(iconPath));
			return displayImage(ex);
		} catch (IOException e) {
			new Status(false, "Icon not loaded correctly!");
		}
		
		System.out.println("Caricamento fallito");
		return null;
	}
	
	/**
	 * Method used to determine which action to do after the user inputs a correct password
	 * @param catCase
	 */
	public void determineCase(String catCase){
		String str = catCase;
		
		switch(str) {
			case "access":{
				createPassCatAcc();
				break;
			}
			case "remove":{
				createPassCatRem();
				break;
			}
			case "edit":{
				createPassCatEdit();
				break;
			}
			case "merge":{
				createPassCatMerge();
				break;
			}
		}
	}
	
	/**
	 * Method used to access a category after the right password has been input
	 */
	public void createPassCatAcc() {
		getCatPanRef().accessCategory();
	}
	
	/**
	 * Method used to remove a category after the right password has been input
	 */
	public void createPassCatRem() {
		getRemCatRef().removeCat(getIndex());
	}
	
	/**
	 * Method used to edit a category after the right password has been input
	 */
	public void createPassCatEdit() {
		getEditCatRef().editCategory(getIndex());
	}
	
	/**
	 * Method used to merge two categories after the right password has been input
	 */
	public void createPassCatMerge() {
		getMergeCatRef().mergeCategories(getIndex(), getSecIndex());
	}
	
	/**
	 * Method used to close the frame
	 */
	public void closeJframe() {
		getWindowRef().dispose();
	}
	
	/**
	 * Get path of the passwordcheck icon
	 * @return path
	 */
	public String getIconPath() {
		return iconPath;
	}

	/**
	 * Get JTextField containing the input password
	 * @return jtextfield
	 */
	public JTextField getPassField() {
		return passField;
	}

	/**
	 * Set JTextField containing the input password
	 * @param passField jtextfield
	 */
	public void setPassField(JTextField passField) {
		this.passField = passField;
	}

	/**
	 * Get JButton of Cancel
	 * @return jbutton
	 */
	public JButton getCancelBt() {
		return cancelBt;
	}

	/**
	 * Set JButton of Cancel
	 * @param cancelBt jbutton
	 */
	public void setCancelBt(JButton cancelBt) {
		this.cancelBt = cancelBt;
	}

	/**
	 * Get JButton of Confirm
	 * @return jbutton
	 */
	public JButton getConfirmBt() {
		return confirmBt;
	}

	/**
	 * Set JButton of Confirm
	 * @param confirmBt jbutton
	 */
	public void setConfirmBt(JButton confirmBt) {
		this.confirmBt = confirmBt;
	}

	/**
	 * Get Protected Category ref
	 * @return protected category
	 */
	public CategoryProtected getCatRef() {
		return catRef;
	}

	/**
	 * Set Protected Category ref
	 * @param catRef protected category
	 */
	public void setCatRef(CategoryProtected catRef) {
		this.catRef = catRef;
	}

	/**
	 * Get PasswordCheck Window ref
	 * @return passwordcheck window
	 */
	public JFrame getWindowRef() {
		return windowRef;
	}

	/**
	 * Set PasswordCheck Window ref
	 * @param windowRef passwordcheck window
	 */
	public void setWindowRef(JFrame windowRef) {
		this.windowRef = windowRef;
	}

	/**
	 * Set CategoryImagePanel ref used to access a protected category
	 * @return categoryimagepanel
	 */
	public CategoryImagePanel getCatPanRef() {
		return catPanRef;
	}

	/**
	 * Set CategoryImagePanel ref used to access a protected category
	 * @param catPanRef categoryimagepanel
	 */
	public void setCatPanRef(CategoryImagePanel catPanRef) {
		this.catPanRef = catPanRef;
	}

	/**
	 * Get RemoveCategory Window ref used to remove a protected category
	 * @return removecategory window
	 */
	public RemoveCategory getRemCatRef() {
		return remCatRef;
	}

	/**
	 * Set RemoveCategory Window ref used to remove a protected category
	 * @param remCatRef removecategory window
	 */
	public void setRemCatRef(RemoveCategory remCatRef) {
		this.remCatRef = remCatRef;
	}

	/**
	 * Get EditCategory Window ref used to edit a protected category
	 * @return editcategory window
	 */
	public EditCategory getEditCatRef() {
		return editCatRef;
	}

	/**
	 * Set EditCategory Window ref used to edit a protected category
	 * @param editCatRef editcategory window
	 */
	public void setEditCatRef(EditCategory editCatRef) {
		this.editCatRef = editCatRef;
	}

	/**
	 * Get MergeCategories Window ref used to merge a protected category
	 * @return mergecategories window
	 */
	public MergeCategories getMergeCatRef() {
		return mergeCatRef;
	}

	/**
	 * Set MergeCategories Window ref used to merge a protected category
	 * @param mergeCatRef mergecategories window
	 */
	public void setMergeCatRef(MergeCategories mergeCatRef) {
		this.mergeCatRef = mergeCatRef;
	}

	/**
	 * Get index of the first category used
	 * @return index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Set index of the first category used
	 * @param index index
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * Get index of the second category used
	 * @return index
	 */
	public int getSecIndex() {
		return secIndex;
	}

	/**
	 * Set index of the second category used
	 * @param secIndex index 
	 */
	public void setSecIndex(int secIndex) {
		this.secIndex = secIndex;
	}
}
