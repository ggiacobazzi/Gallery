package graphics;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import functionalities.Category;
import functionalities.ImageFunctions;

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
			
		}
		else if(str.equals("newcat")) {
			Category cat = new Category();
			parentpanel.getImagePanel().add(cat.getIcon());
			System.out.println("cat added");
			SwingUtilities.updateComponentTreeUI(parentpanel.getImagePanel());
			parentpanel.getP2().getCurrentAlbum().addCategory(parentpanel.getP2().getCurrentAlbum(), cat);
			System.out.println("Categorie totali: " + parentpanel.getP2().getCurrentAlbum().getCategoryList().size());
		}
		else if(str.equals("copyto")) {
			
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
