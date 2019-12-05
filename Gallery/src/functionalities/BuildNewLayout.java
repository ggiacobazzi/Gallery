package functionalities;

import functionalities.Utils;
import graphics.MiddlePanel;

public class BuildNewLayout {

	public MiddlePanel Enter(MiddlePanel ref) {
		//Disable Panel at first
		DisablePanel(ref);
		//Create new MiddlePanel 
		MiddlePanel newWindow = new MiddlePanel(java.awt.Color.PINK, ref.getParent());
		newWindow.setVisible(true);
		return newWindow;
	}
	
	public void DisablePanel(MiddlePanel ref) {
		ref.setVisible(false);
		Utils ut = new Utils();
		ut.setPanelEnabled(ref, false);
	}
}
