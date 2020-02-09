package functionalities;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import basecomponents.Category;
import basecomponents.PhotoAlbum;
import layout.CustomWindow;
import layout.LowerPanel;

/**
 * Class that creates the MoveImageTo Window when the user presses the move button 
 * It has the list of the images of the category currently viewed and a list of the categories to which the image can be moved (the current category is not there obviously)
 * @author gabri
 *
 */
public class MoveImageTo extends CustomWindow{

	private static final long serialVersionUID = 1L;

	/**
	 * Initialize the Move Image Window
	 * @param lowPanRef Reference to the panel containing the references to the categories
	 */
	public MoveImageTo(LowerPanel lowPanRef){
		super(lowPanRef, "Move an image to", "Image", "defaults" + File.separator + "gallery-icon.png");
		
		if(getLowPanRef().getMiddlePanRef().getCurrentCategory().getDefCat().size() != 0) {
			setVisible(true);
			createWindow(getLowPanRef().getMiddlePanRef().getCurrentCategory(), getLowPanRef().getMiddlePanRef().getParent().getCurrentAlbum());
			add(getContPan());
			pack();
		}
		else
			new Status(false, "There are no " + getTypeWindow() + "s to move!");
	}

	/**
	 * Method used to create the window and lay out its components
	 * @param catRef reference to the category
	 * @param photoAlbRef reference to the Photo Album
	 */
	public void createWindow(Category catRef, PhotoAlbum photoAlbRef) {
		//Image Panels
		createImagePanel();
		
		//JComboBoxes
		setJpList(new JPanel());
		getJpList().setLayout(new BoxLayout(getJpList(), BoxLayout.Y_AXIS));
		JComboBox<String> jcb = createJcomboBox(catRef);
		jcb.addActionListener(new ActionListener() {

			/**
			 * ActionListener used to update the label using the item selected in the JComboBox (Images of the current category in this case)
			 */
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//First index isn't used so it needs to be reduced by 1

				//Selected first index in the list
				if(jcb.getSelectedIndex()==0) {
					updateLabel(getDefImg());
				}
				else {
					updateLabel(getLowPanRef().getMiddlePanRef().getParent().getCatPan().getMiddlePanRef().getPanelList().get(jcb.getSelectedIndex()-1).getImageIcon());
				}
			}
		});
	
		getJpList().add(Box.createRigidArea(new Dimension(0, 15)));
		
		JComboBox<String> jcbSec = createJcomboBox(photoAlbRef);
		
		//Buttons Panel
		createButtonsPanel();
		
		//Confirm Button
		createConfirmButton();
		
		/**
		 * ActionListener that fires if the user selects an image from the list and a category from the list and then presses
		 * the "Confirm" button. It moves the image the user chose to the chosen category and updates the panel.
		 * It displays status messages based on the result of the operation.
		 */
		getConfirmBt().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(jcb.getSelectedIndex() == 0) {
						new Status(false, getTypeWindow() + " not selected");
					}
					else{
						//Same Category selected. Nothing is done
						if((photoAlbRef.getCategoryList().get(jcbSec.getSelectedIndex()-1).getName().equals(catRef.getName()))) {
							new Status(false, "Same category selected! Image will not be moved");
							closeJframe();
						}
						else {
							//Move Image to the selected category
							photoAlbRef.getCategoryList().get(jcbSec.getSelectedIndex()-1).getDefCat().add(catRef.getDefCat().get(jcb.getSelectedIndex()-1));
							//Remove Image and the redraw the panel
							catRef.getDefCat().remove(jcb.getSelectedIndex()-1);
							getLowPanRef().getMiddlePanRef().getPanelList().remove(jcb.getSelectedIndex()-1);
							
							repaintPan(true);
							
							new Status(true, "Moved " + getTypeWindow() + " with success!");
							closeJframe();
						}		
					}
				} catch (Exception e) {
					new Status(false, getTypeWindow() + " not moved!");
				}
			}
			
		});
		
		getJpBut().add(Box.createRigidArea(new Dimension(0, 15)));
		
		//Cancel Button
		createCancelButton();
		
		addPanels();
	}
}
