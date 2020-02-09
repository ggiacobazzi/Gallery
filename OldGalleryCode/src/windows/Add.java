package windows;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import graphics.LowerPanel;

public class Add extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public Add(LowerPanel parentparent) {
		JFrame jf = new JFrame("Add");
		jf.setSize(600, 600);
		jf.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
		jf.setResizable(false);
		JPanel jp = new JPanel();
		jf.add(jp);
		
		
		
		
		
		
	}
}
