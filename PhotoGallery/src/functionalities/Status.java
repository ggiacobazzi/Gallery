package functionalities;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Class used to display the status of the app
 * @author gabri
 *
 */
public class Status extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Initialize a Status Window
	 * @param status Boolean value used to determine if the task has concluded with success or not
	 * @param str String that will be displayed
	 */
	public Status(Boolean status, String str) {
		super("");
		this.setLayout(new GridBagLayout());
		this.setSize(new Dimension(500, 100));
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		if(status) {
			this.setTitle("Success");
			JLabel txt = new JLabel(str);
			this.add(txt);
			
		}
		else {
			this.setTitle("Error");
			JLabel txt = new JLabel(str);
			this.add(txt);
		}
	}
}
