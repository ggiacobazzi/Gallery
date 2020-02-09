package functionalities;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import basecomponents.PhotoAlbum;
import layout.CustomWindow;
import layout.LowerPanel;

/**
 * Class that merges two categories
 * @author gabri
 */
public class MergeCategories extends CustomWindow {

	private MergeCategories mergeCatRef;
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor that creates the Merge Categories Window
	 * @param lowPanRef Reference to the Panel with the References to the categories
	 */
	public MergeCategories(LowerPanel lowPanRef) {
		super(lowPanRef, "Merge categories", "Category", "defaults" + File.separator + "category-icon.png");
		
		if(getLowPanRef().getMiddlePanRef().getCurrentAlbum().getCategoryList().size() != 0) {
			setVisible(true);
			createWindow(getLowPanRef().getMiddlePanRef().getParent().getCurrentAlbum());
			setMergeCatRef(this);
			add(getContPan());
			pack();
		}
		else {
			new Status(false, "There are no " + getTypeWindow().replace("y", "ies") + " to merge!");
			closeJframe();
		}
	}
	
	/**
	 * Method to create the window and lay out its components
	 * @param photoAlbumRef reference to the Photo Album
	 */
	public void createWindow(PhotoAlbum photoAlbumRef) {
		//Image Panels
		createImagePanel();
		
		//JComboBoxes
		setJpList(new JPanel());
		getJpList().setLayout(new BoxLayout(getJpList(), BoxLayout.Y_AXIS));
		JComboBox<String> jcb = createJcomboBox(photoAlbumRef);
		
		jcb.addActionListener(new ActionListener() {

			/**
			 * ActionListener used to update the label using the item selected in the JComboBox (Icon of the current Category)
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
	
		getJpList().add(Box.createRigidArea(new Dimension(0, 15)));
		
		JComboBox<String> jcbSec = createJcomboBox(photoAlbumRef);
		
		//Buttons Panel
		createButtonsPanel();
		
		//Confirm Button
		createConfirmButton();
		
		/**
		 * ActionListener that fires if the user selects a category from the list and a category from the other list and then presses
		 * the "Confirm" button. It moves the contents of the first category the user chose to the other chosen category and updates the panel.
		 * The first Category ceases to exist after the operation.
		 * It displays status messages based on the result of the operation.
		 */
		getConfirmBt().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(jcb.getSelectedIndex() == 0 || jcbSec.getSelectedIndex() == 0) {
						new Status(false, getTypeWindow() + " not selected");
					}
					else{
						//Same Category selected. Nothing is done
						if((photoAlbumRef.getCategoryList().get(jcbSec.getSelectedIndex()-1).getName().equals(photoAlbumRef.getCategoryList().get(jcb.getSelectedIndex()-1).getName()))) {
							new Status(false, "Same category selected! The Category won't be merged!");
							closeJframe();
						}
						else {
							photoAlbumRef.getCategoryList().get(jcb.getSelectedIndex()-1).mergeCat(getMergeCatRef(), jcb.getSelectedIndex()-1, jcbSec.getSelectedIndex()-1);
						}		
					}
				} catch (Exception e) {
					new Status(false, getTypeWindow() + " not merged!");
				}
			}
			
		});
		
		getJpBut().add(Box.createRigidArea(new Dimension(0, 15)));
		
		//Cancel Button
		createCancelButton();
		
		addPanels();
	}

	/**
	 * Method used to merge two categories
	 * @param index Index of the first category
	 * @param secIndex Index of the second category
	 */
	public void mergeCategories(int index, int secIndex) {
		for(int i = 0; i < getLowPanRef().getMiddlePanRef().getCurrentAlbum().getCategoryList().get(index).getDefCat().size(); i++) {
			getLowPanRef().getMiddlePanRef().getCurrentAlbum().getCategoryList().get(secIndex).getDefCat().add(getLowPanRef().getMiddlePanRef().getCurrentAlbum().getCategoryList().get(index).getDefCat().get(i));
		}
		getLowPanRef().getMiddlePanRef().getCurrentAlbum().removeCategory(getLowPanRef().getMiddlePanRef().getCurrentAlbum(), index);
		getLowPanRef().getMiddlePanRef().getPanelList().remove(index);
		repaintPan(false);
		new Status(true, "Merged " + getTypeWindow().replace("y", "ies") + " with success!");
		closeJframe();
	}

	/**
	 * Get MergeCategories Window Ref
	 * @return mergecategories window ref
	 */
	public MergeCategories getMergeCatRef() {
		return mergeCatRef;
	}

	/**
	 * Set MergeCategories Window Ref
	 * @param mergeCatRef mergecategories window ref
	 */
	public void setMergeCatRef(MergeCategories mergeCatRef) {
		this.mergeCatRef = mergeCatRef;
	}
}
