package functionalities;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import basecomponents.Category;
import basecomponents.CategoryProtected;
import basecomponents.Image;
import layout.CategoryImagePanel;
import layout.ImagePanel;
import layout.MiddlePanel;

/**
 * Window used to create a New Category (Protected or not)
 * @author gabri
 */
public class NewCategory extends JFrame{

	private final String[] labels = {"Name", "Description", "Password", "Cancel", "Confirm"};
	private JTextField nameBox, descBox, passwBox;
	private JCheckBox protectedStatus;
	private JButton cancelBt, confirmBt;
	private JFrame windowRef;
	private MiddlePanel parentPan;
	private final String defaultCatIcon = "defaults" + File.separator + "folder-blue-icon.png";
	private final String defaultProtCatIcon = "defaults" + File.separator + "folder-blue-locked-icon.png";
	private static final long serialVersionUID = 1L;

	/**
	 * Set the visibility of the new Category Window (Constructor used with Edit Category function)
	 */
	public NewCategory() {
		setVisible(false);
	}
	
	/**
	 * Initialize the New Category Window
	 * @param parentPan Reference to the parent panel
	 * @param value Boolean value used to set the visibility of the Window
	 */
	public NewCategory(MiddlePanel parentPan, boolean value) {
		super("Add Category");
		setParentPan(parentPan);
		setWindowRef(this);
		setSize(new Dimension(700, 300));
		setResizable(false);
		JPanel jp = new JPanel();
		createWindow(jp);
		add(jp);
		setLocationRelativeTo(null);
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		setVisible(value);
		pack();
	}

	/**
	 * Method used to create the window and lay out its components
	 * @param jp Reference to the panel that will be added
	 */
	public void createWindow(JPanel jp) {
		jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
		jp.setAlignmentY(LEFT_ALIGNMENT);
		
		//Name
		jp.add(createLabel(0));
		setNameBox(createTbox());
		jp.add(getNameBox());
		
		//Desc
		jp.add(createLabel(1));
		setDescBox(createTbox());
		jp.add(getDescBox());
		
		//ProtectedCheckBox
		JCheckBox isProtected = new JCheckBox("Protetta");
		isProtected.addItemListener(new ItemListener() {
			@Override
		    public void itemStateChanged(ItemEvent e) {
				//checkbox has been selected
		        if(e.getStateChange() == ItemEvent.SELECTED) {
		            getPasswBox().setEnabled(true);
		        } 
		        //checkbox has been deselected
		        else {
		           	getPasswBox().setEnabled(false);
		        };
		    }
		});
		setProtectedStatus(isProtected);
		jp.add(getProtectedStatus());
		
		//Password
		jp.add(createLabel(2));
		setPasswBox(createTbox());
		getPasswBox().setEnabled(false);
		jp.add(getPasswBox());
		
		//Buttons
		JPanel p = new JPanel();
		setCancelBt(createButton(3));
		getCancelBt().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Status(false, "Categoria non aggiunta");
				closeJframe();
			}
		});

		setConfirmBt(createButton(4));
		getConfirmBt().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = getNameBox().getText();
				String desc = getDescBox().getText();
				//checkbox is selected so a protected category will be created. It retrieves the value of the @param passwBox too.
				if(getProtectedStatus().isSelected()) {
					try {
						String pass = getPasswBox().getText();
						CategoryProtected protcat = new CategoryProtected(name, desc, pass);
						createCat(protcat, false);
						new Status(true, "Categoria protetta creata correttamente");
					} catch (Exception e1) {
						new Status(false, "Errore nel creare categoria protetta");
					}
				}
				//checkbox is not selected so a normal category will be created
				else {
					try {
						Category cat = new Category(name, desc);
						createCat(cat, true);
						new Status(true, "Categoria creata correttamente");
					} catch (Exception e1) {
						new Status(false, "Errore nel creare categoria");
					}
					
				}
				closeJframe();	
			}
			
		});
		
		p.add(getCancelBt());
		p.add(getConfirmBt());
		jp.add(p);
	}
	
	/**
	 * Method used to create different labels with different names
	 * @param labelIndex used to get the string for the various labels from an array
	 * @return return the label that will be added to the panel
	 */
	public JLabel createLabel(int labelIndex) {
		JLabel l = new JLabel(labels[labelIndex]);
		l.setAlignmentY(LEFT_ALIGNMENT);
		return l;
	}
	
	/**
	 * Method used to create different TextBoxes
	 * @return return the textbox that will be added to the panel
	 */
	public JTextField createTbox() {
		JTextField textField = new JTextField(30);
		return textField;
	}
	
	/**
	 * Method used to create the buttons
	 * @param nameIndex used to get the string that will be the word displayed in the button
	 * @return return the button that will be added to the panel
	 */
	public JButton createButton(int nameIndex) {
		JButton jB = new JButton(labels[nameIndex]);
		return jB;
	}
	/**
	 * Method used to display the category in the panel and to add it to the Album
	 * @param cat New Protected/Non-Protected Category
	 * @param status Boolean used to determine if the category is protected or not
	 */
	public void createCat(Category cat, Boolean status) {
		ImagePanel catIcon;
		
		//Initialize array
		ArrayList<Image> newArr = new ArrayList<Image>();
		cat.setDefCat(newArr);
		
		if(status) {
			catIcon = new CategoryImagePanel(createIcon(getDefaultCatIcon()), cat.getName(), cat, getParentPan());
		}
		else {
			catIcon = new CategoryImagePanel(createIcon(getDefaultProtCatIcon()), cat.getName(), cat, getParentPan());
		}
		
		getParentPan().displayImagePanel(catIcon);
		
		getParentPan().getCurrentAlbum().addCategory(getParentPan().getCurrentAlbum(), cat);
	}
	
	/**
	 * Method used to create the icon using the path
	 * @param pathIcon Reference to the icon path
	 * @return return the icon updated
	 */
	public BufferedImage createIcon(String pathIcon) {
		BufferedImage icon;
		try {
			icon = ImageIO.read(new File(pathIcon));
			return icon;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Method used to close the frame
	 */
	public void closeJframe() {
		getWindowRef().dispose();
	}
	
	/**
	 * Get JTextField where the name is stored
	 * @return JTextField
	 */
	public JTextField getNameBox() {
		return nameBox;
	}

	/**
	 * Set JTextField where the name is stored
	 * @param nameBox jtextfield
	 */
	public void setNameBox(JTextField nameBox) {
		this.nameBox = nameBox;
	}

	/**
	 * Get JTextField where the description is stored
	 * @return JTextField
	 */
	public JTextField getDescBox() {
		return descBox;
	}

	/**
	 * Set JTextField where the description is stored
	 * @param descBox jtextfield
	 */
	public void setDescBox(JTextField descBox) {
		this.descBox = descBox;
	}

	/**
	 * Get JTextField where the password is stored
	 * @return JTextField
	 */
	public JTextField getPasswBox() {
		return passwBox;
	}

	/**
	 * Set JTextField where the password is stored
	 * @param passwBox jtextfield
	 */
	public void setPasswBox(JTextField passwBox) {
		this.passwBox = passwBox;
	}

	/**
	 * Get JCheckBox indicating the status of the category
	 * @return JCheckBoxStatus
	 */
	public JCheckBox getProtectedStatus() {
		return protectedStatus;
	}

	/**
	 * Set JCheckBox indicating the status of the category
	 * @param protectedStatus jcheckbox
	 */
	public void setProtectedStatus(JCheckBox protectedStatus) {
		this.protectedStatus = protectedStatus;
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
	 * Get NewCategory Window Ref
	 * @return newcategory window ref
	 */
	public JFrame getWindowRef() {
		return windowRef;
	}

	/**
	 * Set NewCategory Window Ref
	 * @param windowRef newcategory window ref
	 */
	public void setWindowRef(JFrame windowRef) {
		this.windowRef = windowRef;
	}

	/**
	 * Get Parent Pan Reference (Middle Panel)
	 * @return parent pan reference
	 */
	public MiddlePanel getParentPan() {
		return parentPan;
	}

	/**
	 * Set Parent Pan Reference
	 * @param parentPan2 parent pan reference
	 */
	public void setParentPan(MiddlePanel parentPan2) {
		this.parentPan = parentPan2;
	}

	/**
	 * Get Path of the category default icon
	 * @return path
	 */
	public String getDefaultCatIcon() {
		return defaultCatIcon;
	}

	/**
	 * Get Path of the protected category default icon
	 * @return path
	 */
	public String getDefaultProtCatIcon() {
		return defaultProtCatIcon;
	}

}
