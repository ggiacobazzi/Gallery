package windows;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Class used to display the status of the loading phase 
 * @author gabri
 *
 */
public class Status {
	
	private JFrame window;
	
	public Status(Boolean status, String str) {
		this.window = new JFrame();
		window.setLayout(new GridBagLayout());
		window.setSize(new Dimension(500, 100));
		window.setVisible(true);
		window.setLocationRelativeTo(null);
		//window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		if(status) {
//			//JDialog success = new JDialog(window, "Success", true);
			this.window.setTitle("Success");
			JLabel txt = new JLabel(str);
			window.add(txt);
			
		}
		else {
			this.window.setTitle("Error");
			//SwingUtilities.getWindowAncestor(window).dispose();
			JLabel txt = new JLabel(str);
			window.add(txt);
		}
	}
}
