package functionalities;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import basecomponents.Category;
import basecomponents.CategoryProtected;
import basecomponents.PhotoAlbum;
import layout.CustomWindow;
import layout.LowerPanel;

/**
 * Class used to edit a category (Name, Description, Protection status)
 * @author gabri
 */
public class EditCategory extends CustomWindow {

	private EditCategory editCatRef;
	private NewCategory newCatMethodsRef;
	private static final long serialVersionUID = 1L;

	public EditCategory(LowerPanel lowPanRef) {
		super(lowPanRef, "Edit Category", "Category", null);
		
		if(getLowPanRef().getMiddlePanRef().getCurrentAlbum().getCategoryList().size() != 0) {
			NewCategory newCat = new NewCategory();
			setNewCatMethodsRef(newCat);
			setPreferredSize(new Dimension(400, 300));
			setVisible(true);
			createWindow(getLowPanRef().getMiddlePanRef().getCurrentAlbum());
			setEditCatRef(this);
			add(getContPan());
			pack();
		}
		else {
			new Status(false, "There are no " + getTypeWindow().replace("y", "ies") + " to edit!");
			closeJframe();
		}
	}
	
	/**
	 * Method used to create the window and lay out its components
	 * @param photoAlbumRef Reference to the Photo Album
	 */
	public void createWindow(PhotoAlbum photoAlbumRef) {
		getContPan().setLayout(new BoxLayout(getContPan(), BoxLayout.Y_AXIS));
		getContPan().setAlignmentY(LEFT_ALIGNMENT);
		
		//JComboBox
		setJpList(new JPanel());
		JComboBox<String> jcb = createJcomboBox(photoAlbumRef);
		getContPan().add(getJpList());
		
		//Name
		getContPan().add(getNewCatMethodsRef().createLabel(0));
		getNewCatMethodsRef().setNameBox(getNewCatMethodsRef().createTbox());
		getContPan().add(getNewCatMethodsRef().getNameBox());
		
		//Description
		getContPan().add(getNewCatMethodsRef().createLabel(1));
		getNewCatMethodsRef().setDescBox(getNewCatMethodsRef().createTbox());
		getContPan().add(getNewCatMethodsRef().getDescBox());
		
		//ProtectedCheckBox
		JCheckBox isProtected = new JCheckBox("Protetta");
		isProtected.addItemListener(new ItemListener() {
			@Override
		    public void itemStateChanged(ItemEvent e) {
		        if(e.getStateChange() == ItemEvent.SELECTED) {//checkbox has been selected
		            getNewCatMethodsRef().getPasswBox().setEnabled(true);
		        } else {//checkbox has been deselected
		           	getNewCatMethodsRef().getPasswBox().setEnabled(false);
		        };
		    }
		});
		getNewCatMethodsRef().setProtectedStatus(isProtected);
		getContPan().add(getNewCatMethodsRef().getProtectedStatus());
		
		//Password
		getContPan().add(getNewCatMethodsRef().createLabel(2));
		getNewCatMethodsRef().setPasswBox(getNewCatMethodsRef().createTbox());
		getNewCatMethodsRef().getPasswBox().setEnabled(false);
		getContPan().add(getNewCatMethodsRef().getPasswBox());
		
		//Buttons
		JPanel panBut = new JPanel();
		getNewCatMethodsRef().setCancelBt(getNewCatMethodsRef().createButton(3));
		getNewCatMethodsRef().getCancelBt().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Status(false, getTypeWindow() + " not modified!");
				closeJframe();
			}
			
		});
		
		getNewCatMethodsRef().setConfirmBt(getNewCatMethodsRef().createButton(4));
		getNewCatMethodsRef().getConfirmBt().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(jcb.getSelectedIndex() == 0) {
						new Status(false, getTypeWindow() + " not selected!");
					}
					else {
						getLowPanRef().getMiddlePanRef().getCurrentAlbum().getCategoryList().get(jcb.getSelectedIndex()-1).editCat(getEditCatRef(),
								jcb.getSelectedIndex()-1);
					}
					
				}
				catch (Exception e) {
					new Status(false, getTypeWindow() + " not modified!");
				}
				
			}
			
		});
		
		panBut.add(getNewCatMethodsRef().getCancelBt());
		panBut.add(getNewCatMethodsRef().getConfirmBt());
		getContPan().add(panBut);
	}

	/**
	 * Method used to edit a category
	 * @param index
	 */
	public void editCategory(int index) {
		String newName = getNewCatMethodsRef().getNameBox().getText();
		String newDesc = getNewCatMethodsRef().getDescBox().getText();
		
		//Edit to protected
		if(getNewCatMethodsRef().getProtectedStatus().isSelected()){
			String newPass = getNewCatMethodsRef().getPasswBox().getText();
			//Edit 
			if(getLowPanRef().getMiddlePanRef().getCurrentAlbum().getCategoryList().get(index) instanceof CategoryProtected) {
				editMetaData(index, newName, newDesc);
				((CategoryProtected)getLowPanRef().getMiddlePanRef().getCurrentAlbum().getCategoryList().get(index)).setPassword(newPass);
			}
			else {
				getLowPanRef().getMiddlePanRef().getCurrentAlbum().getCategoryList().set(index, 
						changeCategory(getLowPanRef().getMiddlePanRef().getCurrentAlbum().getCategoryList().get(index), 
								true, newName, newDesc, newPass));
			}
				
		}
		//Edit to normal
		else {
			if(getLowPanRef().getMiddlePanRef().getCurrentAlbum().getCategoryList().get(index) instanceof CategoryProtected) {
				getLowPanRef().getMiddlePanRef().getCurrentAlbum().getCategoryList().set(index, 
						changeCategory(getLowPanRef().getMiddlePanRef().getCurrentAlbum().getCategoryList().get(index),
								false, newName, newDesc, ""));
			}
			else {
				editMetaData(index, newName, newDesc);
			}
			
		}
		
		repaintPan(false);
		new Status(true, getTypeWindow() + " edited with success!");
		closeJframe();
	}
	
	/**
	 * Method used to change the status of the category. A protected Category will be transformed
	 * into a normal one if needed and viceversa
	 * @param catRef Reference to the category
	 * @param value Boolean value used to determine if the category needs to be transformed or not
	 * @param newName New Name that will be used
	 * @param newDesc New Description that will be used
	 * @param newPass New Password that will be used
	 * @return return the Category edited
	 */
	public Category changeCategory(Category catRef, boolean value, String newName, String newDesc, String newPass) {
		if(value) {
			CategoryProtected newCat = new CategoryProtected(newName, newDesc, newPass);
			for(int i = 0; i < catRef.getDefCat().size(); i++){
				newCat.getDefCat().add(catRef.getDefCat().get(i));
			}
			
			return newCat;
		}
		else {
			Category newCat = new Category(newName, newDesc);
			for(int i = 0; i < catRef.getDefCat().size(); i++) {
				newCat.getDefCat().add(catRef.getDefCat().get(i));
			}
			
			return newCat;
		}
	}
	
	/**
	 * Method to edit the metadata of the category
	 * @param index Index of the category to edit
	 * @param newName New Name that will be used
	 * @param newDesc New Description that will be used
	 */
	public void editMetaData(int index, String newName, String newDesc) {
		getLowPanRef().getMiddlePanRef().getCurrentAlbum().getCategoryList().get(index).setName(newName);
		getLowPanRef().getMiddlePanRef().getCurrentAlbum().getCategoryList().get(index).setDescription(newDesc);	
	}
	
	/**
	 * Get EditCategory Window Ref
	 * @return editcat reference
	 */
	public EditCategory getEditCatRef() {
		return editCatRef;
	}

	/**
	 * Set EditCategory Window Ref
	 * @param editCatRef editcat reference
	 */
	public void setEditCatRef(EditCategory editCatRef) {
		this.editCatRef = editCatRef;
	}

	/**
	 * Get NewCategory Class Reference (used to use its methods)
	 * @return newcategory reference
	 */
	public NewCategory getNewCatMethodsRef() {
		return newCatMethodsRef;
	}

	/**
	 * Set NewCategory Class Reference
	 * @param newCatMethodsRef newcategory reference
	 */
	public void setNewCatMethodsRef(NewCategory newCatMethodsRef) {
		this.newCatMethodsRef = newCatMethodsRef;
	}

}
