package graphics;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Button extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Button(String name, String iconPath) {
		ImageIcon icon = new ImageIcon(iconPath);
		this.setText(name);
		this.setIcon(icon);
		this.setBorderPainted(false);
		this.setFocusPainted(false);
		this.setContentAreaFilled(false);
		this.setSize(new Dimension(100, 100));
		this.setPreferredSize(new Dimension(100, 100));
	}
}
