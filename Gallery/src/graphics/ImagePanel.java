package graphics;

import java.awt.Color;
import java.awt.ComponentOrientation;

import javax.swing.JPanel;

/**
 * Panel used to store the images
 * 
 * @author gabri
 *
 */

public class ImagePanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ImagePanel(MiddlePanel p2) {
		this.setPreferredSize(p2.getPreferredSize());
		this.setMinimumSize(p2.getMinimumSize());
		this.setBackground(Color.BLACK);
		this.setLayout(new WrapLayout(WrapLayout.LEFT, 20,20));
		this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		this.setVisible(true);
	}
}
