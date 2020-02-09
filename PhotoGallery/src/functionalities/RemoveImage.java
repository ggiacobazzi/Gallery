package functionalities;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import basecomponents.Category;
import layout.CustomWindow;
import layout.LowerPanel;

/**
 * Class that creates the Remove Window when the user presses the remove button
 * It has a list of images that can be removed from the selected category and the image is displayed
 * on the left
 * @author gabri
 */

public class RemoveImage extends CustomWindow{

	private static final long serialVersionUID = 1L;

	/**
	 * Initialize the Remove Image Window
	 * @param lowPanRef Reference to the panel containing the images
	 */
	public RemoveImage(LowerPanel lowPanRef) {
		super(lowPanRef, "Remove an image", "Image", "defaults" + File.separator + "gallery-icon.png");
		
		if(lowPanRef.getMiddlePanRef().getCurrentCategory().getDefCat().size() != 0) {
			setVisible(true);
			createWindow(lowPanRef.getMiddlePanRef().getCurrentCategory());
			add(getContPan());
			pack();
		}
		else {
			new Status(false, "There are no " + getTypeWindow() + "s!");
			closeJframe();
		}
		
	}
	
	/**
	 * Method used to create the Window and lay out its components
	 * @param cat Category used to create the lists
	 */
	public void createWindow(Category cat){
		//Image Panel
		createImagePanel();
		
		//JComboBox
		setJpList(new JPanel());
		JComboBox<String> jcb = createJcomboBox(cat);
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
						updateLabel(getLowPanRef().getMiddlePanRef().getParent().getCatPan().getMiddlePanRef().getPanelList().get(jcb.getSelectedIndex()-1).getImageIcon());
					}
				}
			});
		
		//Buttons Panel
		createButtonsPanel();
		
		//Confirm Button
		createConfirmButton();
		
		/**
		 * ActionListener that fires if the user selects an image from the list and then presses
		 * the "Confirm" button. It deletes the image the user chose and updates the panel.
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
						//Remove image and the redraw the panel
						cat.getDefCat().remove(jcb.getSelectedIndex()-1);
						getLowPanRef().getMiddlePanRef().getPanelList().remove(jcb.getSelectedIndex()-1);
						repaintPan(true);
						
						new Status(true, "Eliminated " + getTypeWindow() + " with success!");
						closeJframe();
					}
				} catch (Exception e) {
					new Status(false, getTypeWindow() + " not removed!");
				}
			}
			
		});
		
		getJpBut().add(Box.createRigidArea(new Dimension(0, 15)));
		
		//Cancel button
		createCancelButton();
		
		addPanels();
	}
	
}
