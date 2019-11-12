package windows;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 * Class used to display the status of the loading phase 
 * @author gabri
 *
 */
public class LoadingStatus {
	
	private JFrame window;
	
	public LoadingStatus(Boolean status) {
		if(status) {
//			//JDialog success = new JDialog(window, "Success", true);
			this.window = new JFrame("Load an image");
			window.setLayout(new GridBagLayout());
			window.setSize(new Dimension(500, 100));
			window.setVisible(true);
			window.setLocationRelativeTo(null);
			//window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
			//SwingUtilities.getWindowAncestor(super.getClass()).dispose();
			window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			JLabel txt = new JLabel("Immagine caricata correttamente da URL");
			window.add(txt);
		}
		else {
			JFrame window = new JFrame("Error");
			window.setLayout(new GridBagLayout());
			window.setSize(new Dimension(500, 100));
			window.setVisible(true);
			window.setLocationRelativeTo(null);
			//window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
			window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			//SwingUtilities.getWindowAncestor(window).dispose();
			JLabel txt = new JLabel("URL non valido");
			window.add(txt);
			System.out.println("URL non valido");
		}
	}
	
	public void closeJFrame() {
	}
}
