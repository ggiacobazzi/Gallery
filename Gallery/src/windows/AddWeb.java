package windows;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddWeb extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String urlstring;
	private JTextField box;
	private BufferedImage pic;
	/**
	 * Class used to create a window when the user tries to add an image from an url
	 * @param pic
	 */
	public AddWeb() {
		JFrame jf = new JFrame("Add Image from web");
		jf.setSize(new Dimension(500, 100));
		jf.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
		jf.setResizable(false);
		JPanel jp = new JPanel();
		jf.add(jp);
		JLabel jtf = new JLabel("Insert URL: ");
		jp.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		jp.add(jtf);
		this.box = new JTextField();
		box.setPreferredSize(new Dimension(200, 25));
		jp.add(box);
		JButton send = new JButton("Enter");
		jp.add(send);
		send.addActionListener(this);
		jf.setLocationRelativeTo(null);
		jf.dispatchEvent(new WindowEvent(jf, WindowEvent.WINDOW_CLOSING));
		//jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setVisible(true);
		jf.pack();
	}
	/**
	 * ActionListener 
	 */
	public void actionPerformed(ActionEvent e){
		String choice = e.getActionCommand();
		if(choice == "Enter") {
			this.urlstring = box.getText();
			try {
				URL url = new URL(urlstring);
				this.setPic(ImageIO.read(url));
				System.out.println("Letto URL!");
			} catch (MalformedURLException e1) {
				LoadingStatus ls = new LoadingStatus(false);
				e1.printStackTrace();
				return;
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			LoadingStatus ls = new LoadingStatus(true);
		}
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
	public BufferedImage getPic() {
		return pic;
	}
	public void setPic(BufferedImage pic) {
		this.pic = pic;
	}
	
	

}
