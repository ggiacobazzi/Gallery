package functionalities;

import functionalities.Utils;
import graphics.MiddlePanel;

/**
 * Class that creates a new layout that will be displayed in the main frame.
 * At first it disables the old panel, then it creates a new one that will be put on top of it
 * @author gabri
 *
 */
public class BuildNewLayout {

	/**
	 * 
	 * @param ref Parent panel that will be used to build the new one
	 * @return new panel
	 */
	public MiddlePanel Enter(MiddlePanel ref) {
		//Disable Panel at first
		DisablePanel(ref, false);
		//Create new MiddlePanel 
		MiddlePanel newWindow = new MiddlePanel(java.awt.Color.PINK, ref.getParent(), ref.getParent().getMiddlePanel().getLp(), null);
		//Enable new Panel
		DisablePanel(newWindow, true);
		return newWindow;
	}
	
	/**
	 * Method used to disable/enable the panel that is passed using a boolean value
	 * @param ref panel that will be enabled/disables
	 * @param value boolean value that is used to activate/deactivate
	 */
	public void DisablePanel(MiddlePanel ref, Boolean value) {
		ref.setVisible(value);
		Utils ut = new Utils();
		ut.setPanelEnabled(ref, value);
	}
}
