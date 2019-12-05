package graphics;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import functionalities.Category;
import functionalities.ImageFunctions;
import windows.NewCategory;
import windows.Remove;

public class Button extends JButton implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LowerPanel parentpanel;
	public Button(String name, String iconPath, String actionCommand, LowerPanel parentpanel) {
		ImageIcon icon = new ImageIcon(iconPath);
		this.setActionCommand(actionCommand);
		this.setText(name);
		this.setIcon(icon);
		this.setBorderPainted(false);
		this.setFocusPainted(false);
		this.setContentAreaFilled(false);
		this.setSize(new Dimension(100, 100));
		this.setPreferredSize(new Dimension(100, 100));
		this.setParentpanel(parentpanel);
	}
	
public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if(str.equals("add")){
			ImageFunctions.loadImage(false, this.getParentpanel());
		}
		else if(str.equals("addweb")) {
			ImageFunctions.loadImage(true, this.getParentpanel());
		}
		else if(str.equals("remove")) {
			Remove rem = new Remove(getParentpanel().getP2().getCurrentCategory());
		}
		else if(str.equals("removecat")) {
			
		}
		else if(str.equals("copyto")) {
			
		}
		else if(str.equals("mergeto")){
			
		}
		else if(str.equals("newcat")) {
			NewCategory newcat = new NewCategory(parentpanel);
		}
		else if (str.equals("prev")){
			
		}
		else if(str.equals("next")) {
			
		}
	}

public LowerPanel getParentpanel() {
	return parentpanel;
}

public void setParentpanel(LowerPanel parentpanel) {
	this.parentpanel = parentpanel;
}
}
