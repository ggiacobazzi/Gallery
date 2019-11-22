package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class LeftPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LeftPanel(Color c) {
		this.CreatePanel(c);
	}
	public void CreatePanel(Color c) {
		this.setBackground(c);
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new Dimension(200,400));
		this.setMinimumSize(new Dimension(200,400));
	}
	
	
}
