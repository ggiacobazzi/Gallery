package layout;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * This class defines the buttons used in the program
 * Each button has its own icon and its own name
 * @author gabri
 */
public class Button extends JButton {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Create the button
	 * @param name Name of the button
	 * @param iconPath Path of the icon 
	 */
	public Button(String name, String iconPath) {
		ImageIcon icon = new ImageIcon(iconPath);
		setToolTipText(name);
		setText(name);
		setIcon(icon);
		setBorderPainted(false);
		setFocusPainted(false);
		setContentAreaFilled(false);
		setPreferredSize(new Dimension(100, 100));
	}

}
