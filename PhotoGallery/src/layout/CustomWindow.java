package layout;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import basecomponents.Category;
import basecomponents.PhotoAlbum;
import functionalities.Status;
import layout.LowerPanel;


/**
 * Class that creates a default window that will be used for various functions (e.g. Remove Im/Cat, Copy/Merge)
 * @author gabri
 *
 */
public class CustomWindow extends JFrame {

	private JPanel jpIm;
	private JPanel jpList;
	private JPanel jpBut;
	private JLabel image;
	private JFrame windowRef;
	private JPanel contPan;
	private JButton confirmBt;
	private JButton cancelBt;
	private String defaultIconPath;
	private JLabel defImg;
	private LowerPanel lowPanRef;
	private String typeWindow;
	private static final long serialVersionUID = 1L;

	/**
	 * Initialize a Custom Window that can be customized
	 * @param lowPanRef Reference to the Lower Panel
	 * @param title Title of the Window
	 * @param typeWindow String used to create Statuses
	 * @param iconPath Path of the default icons
	 */
	public CustomWindow(LowerPanel lowPanRef, String title, String typeWindow, String iconPath) {
		super(title);
		setLowPanRef(lowPanRef);
		setWindowRef(this);
		setTypeWindow(typeWindow);
		setDefaultIconPath(iconPath);
		contPan = new JPanel();
		setPreferredSize(new Dimension(700, 160));
		getContPan().setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
		setResizable(false);
		setLocationRelativeTo(null);
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		setVisible(false);
	}
	
	/**
	 * Methods used to create a JComboBox with a Category
	 * @param cat Reference to the category used to create the JComboBox
	 * @return the finished JComboBox
	 */
	
	public JComboBox<String> createJcomboBox(Category cat) {
		//JComboBox panel
		setJpList(new JPanel());
		JComboBox<String> jcb = createComboBox(cat);
		jpList.add(jcb);
		return jcb;
	}
	
	/**
	 * Method that creates a JComboBox used to select something. It displays an icon representing the object 
	 * we're dealing with
	 * @param cat reference to the category that will be used 
	 * @return the finished JComboBox
	 */
	public JComboBox<String> createComboBox(Category cat) {
		JComboBox<String> jcbListIm = new JComboBox<String>(createList(cat));
		jcbListIm.setPrototypeDisplayValue("XXXXXXXXXXXXXXXX");
		return jcbListIm;
	}

	/**
	 * Method that takes a category and creates an array that will be used to create the JComboBox
	 * @param cat Category used to display the name of the images in the JComboBox
	 * @return nameIm array of strings used to create the JComboBox
	 */
	public String[] createList(Category cat){
		String[] nameIm = new String[cat.getDefCat().size()+1];
		//first index not used
		nameIm[0] = "Select a " + getTypeWindow();
		for(int i = 1; i < nameIm.length; i++) {
			nameIm[i] = cat.getDefCat().get(i-1).getName();
		}

		return nameIm;

	}

	
	/**
	 * Methods used to create a JComboBox with a PhotoAlbum
	 * @param pa Reference to the Photo Album that will be used to create a JComboBox
	 * @return jcb The finished JComboBox
	 */ 
	
	//Normal JComboBox
	public JComboBox<String> createJcomboBox(PhotoAlbum pa) {
		//JComboBox panel
		JComboBox<String> jcb = createComboBox(pa);
		getJpList().add(jcb);
		return jcb;
	}
	
	//Create a JComboBox
	/**
	 * Method used to create a JComboBox
	 * @param pa Reference to the Photo Album used
	 * @return jcbListIm The List used in the JComboBox
	 */
	public JComboBox<String> createComboBox(PhotoAlbum pa) {
		JComboBox<String> jcbListIm = new JComboBox<String>(createList(pa));
		jcbListIm.setPrototypeDisplayValue("XXXXXXXXXXXXXXXX");
		return jcbListIm;
	}
	
	//Create a list for the JComboBox
	/**
	 * Method used to create the List that will be displayed in the JComboBox
	 * @param pa Reference to the Photo Album used
	 * @return nameAlb Array with the titles of the categories
	 */
	public String[] createList(PhotoAlbum pa){
		String[] nameAlb = new String[pa.getCategoryList().size()+1];
		//first index not used
		nameAlb[0] = "Select a category";
		for(int i = 1; i < nameAlb.length; i++) {
			nameAlb[i] = pa.getCategoryList().get(i-1).getName();
		}
		
		return nameAlb;
	}
	
	/**
	 * Method that uses the string passed to display the correct image
	 * @param image image used to update the label on the left
	 */
	public void updateLabel(JLabel image) {
		setImage(image);
		getJpIm().removeAll();
		getJpIm().add(getImage());

		SwingUtilities.updateComponentTreeUI(getJpIm());
	}

	/**
	 * Method used to create the Image Panel
	 */
	public void createImagePanel() {
		//Image Panel
		setJpIm(new JPanel());
		defImg = new JLabel(new ImageIcon(getDefaultIconPath()));
		defImg.setPreferredSize(new Dimension(160, 108));
		setImage(getDefImg());
		getJpIm().add(getImage());
	}
	
	/**
	 * Method used to create the Panel containing the buttons of the window
	 */
	public void createButtonsPanel() {
		//Buttons panel
		setJpBut(new JPanel());
		getJpBut().setLayout(new BoxLayout(getJpBut(), BoxLayout.Y_AXIS));
	}
	
	/**
	 * Method used to create the "Confirm" button
	 */
	public void createConfirmButton() {
		//Confirm button
		JButton confBt = new JButton("Confirm");
		setConfirmBt(confBt);
		getJpBut().add(confBt);
	}
	
	/**
	 * Method used to create the "Cancel" button
	 */
	public void createCancelButton() {
		//Cancel button
		JButton cancBt = new JButton("Cancel");
		
		/**
		 * ActionListener that fires if the user presses the "Cancel" button. It closes the selection
		 * window and displays an error message
		 */
		cancBt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Status(false, getTypeWindow() + " not selected!");
				closeJframe();
			}
		});
		
		setCancelBt(cancBt);
		getJpBut().add(cancBt);
	}
	
	/**
	 * Method used to close the frame
	 */
	public void closeJframe() {
		getWindowRef().dispose();
	}

	/**
	 * Method used to repaint
	 * @param value Value used for distinguish categories from images
	 */
	public void repaintPan(boolean value) {
		getLowPanRef().getMiddlePanRef().displayContents(value);
	}
	
	/**
	 * Method used to add all the panels
	 */
	public void addPanels() {
		getContPan().add(getJpIm());
		getContPan().add(getJpList());
		getContPan().add(Box.createRigidArea(new Dimension(15, 0)));
		getContPan().add(getJpBut());
	}
	
	/**
	 * Get Panel used to display the image
	 * @return panel
	 */
	public JPanel getJpIm() {
		return jpIm;
	}

	/**
	 * Set Panel used to display the image
	 * @param jpIm panel
	 */
	public void setJpIm(JPanel jpIm) {
		this.jpIm = jpIm;
	}

	/**
	 * Get Panel used to display the JComboBox
	 * @return panel
	 */
	public JPanel getJpList() {
		return jpList;
	}

	/**
	 * Set Panel used to display the JComboBox
	 * @param jpList panel
	 */
	public void setJpList(JPanel jpList) {
		this.jpList = jpList;
	}

	/**
	 * Get Panel used to display the Buttons
	 * @return panel
	 */
	public JPanel getJpBut() {
		return jpBut;
	}

	/**
	 * Set Panel used to display the Buttons
	 * @param jpBut panel
	 */
	public void setJpBut(JPanel jpBut) {
		this.jpBut = jpBut;
	}

	/**
	 * Get the Image Label
	 * @return image label
	 */
	public JLabel getImage() {
		return image;
	}

	/**
	 * Set the Image label
	 * @param image image label
	 */
	public void setImage(JLabel image) {
		this.image = image;
	}

	/**
	 * Get the CustomWindow Ref used to close it eventually
	 * @return customwindow self-ref
	 */
	public JFrame getWindowRef() {
		return windowRef;
	}

	/**
	 * Set the CustomWindow Ref used to close it eventually
	 * @param ref customwindow self-ref
	 */
	public void setWindowRef(JFrame ref) {
		this.windowRef = ref;
	}

	/**
	 * Get the Container Panel
	 * @return container panel
	 */
	public JPanel getContPan() {
		return contPan;
	}

	/**
	 * Set the Container Panel
	 * @param contPan container panel
	 */
	public void setContPan(JPanel contPan) {
		this.contPan = contPan;
	}

	/**
	 * Get the Confirm Button
	 * @return button
	 */
	public JButton getConfirmBt() {
		return confirmBt;
	}

	/**
	 * Set the Confirm Button
	 * @param confirmBt button
	 */
	public void setConfirmBt(JButton confirmBt) {
		this.confirmBt = confirmBt;
	}

	/**
	 * Get the Cancel Button
	 * @return button
	 */
	public JButton getCancelBt() {
		return cancelBt;
	}

	/**
	 * Set the Cancel Button
	 * @param cancelBt button
	 */
	public void setCancelBt(JButton cancelBt) {
		this.cancelBt = cancelBt;
	}

	/**
	 * Get the path used to create the default icon
	 * @return path
	 */
	public String getDefaultIconPath() {
		return defaultIconPath;
	}

	/**
	 * Set the path used to create the default icon
	 * @param defaultIconPath path
	 */
	public void setDefaultIconPath(String defaultIconPath) {
		this.defaultIconPath = defaultIconPath;
	}

	/**
	 * Get the Image Label used to display the icon
	 * @return image label
	 */
	public JLabel getDefImg() {
		return defImg;
	}

	/**
	 * Set the Image Label used to display the icon
	 * @param defImg image label
	 */
	public void setDefImg(JLabel defImg) {
		this.defImg = defImg;
	}

	/**
	 * Get the Lower Panel Ref
	 * @return lower panel
	 */
	public LowerPanel getLowPanRef() {
		return lowPanRef;
	}

	/**
	 * Set the Lower Panel Ref
	 * @param lowPanRef lower panel
	 */
	public void setLowPanRef(LowerPanel lowPanRef) {
		this.lowPanRef = lowPanRef;
	}

	/**
	 * Get the TypeWindow String used to customize the statuses
	 * @return typewindow string
	 */
	public String getTypeWindow() {
		return typeWindow;
	}

	/**
	 * Set the TypeWindow String used to customize the statuses
	 * @param typeWindow typewindow string
	 */
	public void setTypeWindow(String typeWindow) {
		this.typeWindow = typeWindow;
	}
}

