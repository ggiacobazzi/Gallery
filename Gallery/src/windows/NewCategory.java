package windows;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import functionalities.Category;
import functionalities.CategoryProtected;
import graphics.LowerPanel;

public class NewCategory extends JFrame{

	/**
	 * 
	 */
	private String[] labels = {"Name", "Description", "Password", "Cancel", "Confirm"};
	private int boxnum = labels.length;
	private JTextField nameBox, descBox, passwBox;
	private JCheckBox protectedStatus;
	private JButton cancelBt, confirmBt;
	private JFrame windowRef;
	private LowerPanel parentPan;
	private static final long serialVersionUID = 1L;

	public NewCategory(LowerPanel ref) {
		setParentPan(ref);
		JFrame jf = new JFrame("Add Category");
		jf.setSize(new Dimension(700, 300));
		jf.setResizable(false);
		JPanel jp = new JPanel();
		createWindow(jp);
		jf.add(jp);
		jf.setLocationRelativeTo(null);
		jf.dispatchEvent(new WindowEvent(jf, WindowEvent.WINDOW_CLOSING));
		jf.setVisible(true);
		jf.pack();
		setWindowRef(jf);
	}

	/**
	 * Method used to create the main window
	 * @param jp panel used to store the various components(labels, textboxes, buttons)
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
		        if(e.getStateChange() == ItemEvent.SELECTED) {//checkbox has been selected
		            getPasswBox().setEnabled(true);
		        } else {//checkbox has been deselected
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
						System.out.println("Pass interno : " + pass);
						//TODO: non funziona il get path della categoria protetta :(
						CategoryProtected protcat = new CategoryProtected(name, desc, getParentPan().getP2(), pass, "");
						createCat(protcat);
						Status s = new Status(true, "Categoria protetta creata correttamente");
					} catch (Exception e1) {
						e1.printStackTrace();
						Status s = new Status(false, "Errore nel creare categoria protetta");
					}
				}
				//checkbox is not selected so a normal category will be created
				else {
					try {
						Category cat = new Category(name, desc, getParentPan().getP2(), "", "defaults" + File.separator + "folder-blue-icon.png");
						createCat(cat);
						Status s = new Status(true, "Categoria creata correttamente");
					} catch (Exception e1) {
						e1.printStackTrace();
						Status s = new Status(false, "Errore nel creare categoria");
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
	 * @param cat
	 */
	public void createCat(Category cat) {
		getParentPan().getImagePanel().add(cat.getIcon());
		SwingUtilities.updateComponentTreeUI(getParentPan().getImagePanel());
		getParentPan().getP2().getCurrentAlbum().addCategory(getParentPan().getP2().getCurrentAlbum(), cat);
	}
	/**
	 * Method used to close the frame
	 */
	public void closeJframe() {
		getWindowRef().dispose();
	}
	
	public JTextField getNameBox() {
		return nameBox;
	}

	public void setNameBox(JTextField nameBox) {
		this.nameBox = nameBox;
	}

	public JTextField getDescBox() {
		return descBox;
	}

	public void setDescBox(JTextField descBox) {
		this.descBox = descBox;
	}

	public JTextField getPasswBox() {
		return passwBox;
	}

	public void setPasswBox(JTextField passwBox) {
		this.passwBox = passwBox;
	}

	public JCheckBox getProtectedStatus() {
		return protectedStatus;
	}

	public void setProtectedStatus(JCheckBox protectedStatus) {
		this.protectedStatus = protectedStatus;
	}

	public JButton getCancelBt() {
		return cancelBt;
	}

	public void setCancelBt(JButton cancelBt) {
		this.cancelBt = cancelBt;
	}

	public JButton getConfirmBt() {
		return confirmBt;
	}

	public void setConfirmBt(JButton confirmBt) {
		this.confirmBt = confirmBt;
	}

	public JFrame getWindowRef() {
		return windowRef;
	}

	public void setWindowRef(JFrame windowRef) {
		this.windowRef = windowRef;
	}

	public LowerPanel getParentPan() {
		return parentPan;
	}

	public void setParentPan(LowerPanel parentPan) {
		this.parentPan = parentPan;
	}
}
