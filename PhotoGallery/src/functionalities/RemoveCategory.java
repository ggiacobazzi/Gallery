package functionalities;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import basecomponents.PhotoAlbum;
import layout.CustomWindow;
import layout.LowerPanel;

/**
 * Class that creates the Remove Category Window when the user presses the remove button
 * It has a list of the categories that can be removed from the selected album and the category icon is displayed on the left
 * @author gabri
 */ 
public class RemoveCategory extends CustomWindow{

	private RemoveCategory remCatRef;
	private PhotoAlbum photoAlbumRef;
	private static final long serialVersionUID = 1L;
	
	/**
	 * Initialize the Remove Category Window
	 * @param photoAlbumRef Reference to the Photo Album
	 * @param lowPanRef Reference to the Panel containing the categories
	 */
	public RemoveCategory(PhotoAlbum photoAlbumRef, LowerPanel lowPanRef) {
		super(lowPanRef, "Remove a category", "Category", "defaults" + File.separator + "category-icon.png");
		
		if(getLowPanRef().getMiddlePanRef().getCurrentAlbum().getCategoryList().size() != 0) {
			setVisible(true);
			createWindow(getLowPanRef().getMiddlePanRef().getCurrentAlbum());
			setPhotoAlbumRef(photoAlbumRef);
			setRemCatRef(this);
			add(getContPan());
			pack();
		}
		else {
			new Status(false, "There are no " + getTypeWindow().replace("y", "ies") + "!");
		}
	}
	
	/**
	 * Method used to create the window and lay out its components
	 * @param pa Reference to the Photo Album
	 */
	public void createWindow(PhotoAlbum pa) {
		//Image Panel
		createImagePanel();
		
		//JComboBox Panel
		setJpList(new JPanel());
		JComboBox<String> jcb = createJcomboBox(pa);
		jcb.addActionListener(new ActionListener() {

			/**
			 * ActionListener used to update the label using the item selected in the JComboBox
			 */
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//First index isn't used so it needs to be reduced by 1

				//Selected first index in the list
				if(jcb.getSelectedIndex()==0) {
					updateLabel(getDefImg());
				}
				else {
					updateLabel(getLowPanRef().getMiddlePanRef().getParent().getAlbPan().getMiddlePanRef().getPanelList().get(jcb.getSelectedIndex()-1).getImageIcon());
				}
			}
		});
			

		
		//Buttons Panel
		createButtonsPanel();
	
		//Confirm Button
		createConfirmButton();
		getConfirmBt().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(jcb.getSelectedIndex() == 0) {
						new Status(false, getTypeWindow() + " not selected!");
					}
					else {
						pa.getCategoryList().get(jcb.getSelectedIndex()-1).remCat(getRemCatRef(), jcb.getSelectedIndex()-1);
					}
				} catch (Exception e) {
					new Status(false, getTypeWindow() + " not removed!");
				}
			}
		});
		
		getJpBut().add(Box.createRigidArea(new Dimension(0, 15)));
		
		//Cancel Button
		createCancelButton();
	
		addPanels();
	}

	/**
	 * Method used to remove a Category from the album using an index
	 * @param index Index of the Category
	 */
	public void removeCat(int index) {
		getPhotoAlbumRef().removeCategory(getPhotoAlbumRef(), index);
		getLowPanRef().getMiddlePanRef().getPanelList().remove(index);
		repaintPan(false);
		new Status(true, "Removed " + getTypeWindow() + " with success!");
		closeJframe();
	}
	
	/**
	 * Get RemoveCategory Window Ref
	 * @return removecategory window ref
	 */
	public RemoveCategory getRemCatRef() {
		return remCatRef;
	}

	/**
	 * Set RemoveCategory Window Ref
	 * @param remCatRef removecategory window ref
	 */
	public void setRemCatRef(RemoveCategory remCatRef) {
		this.remCatRef = remCatRef;
	}

	/**
	 * Get PhotoAlbum Reference
	 * @return photoalbum reference
	 */
	public PhotoAlbum getPhotoAlbumRef() {
		return photoAlbumRef;
	}

	/**
	 * Set PhotoAlbum Reference
	 * @param photoAlbumRef photoalbum ref 
	 */
	public void setPhotoAlbumRef(PhotoAlbum photoAlbumRef) {
		this.photoAlbumRef = photoAlbumRef;
	}
}
