package windows;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import functionalities.Image;
import functionalities.ImageFunctions;
import graphics.LowerPanel;

public class AddWeb extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String urlstring;
	private JTextField box;
	private BufferedImage pic;
	private LowerPanel lp;
	private File downloadedfile;
	
	/**
	 * Class used to create a window when the user tries to add an image from an url
	 * @param pic
	 */
	public AddWeb(LowerPanel lowerPanel) {
		super("Add Image from web");
		this.setSize(new Dimension(500, 100));
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
		this.setResizable(false);
		JPanel jp = new JPanel();
		this.add(jp);
		JLabel jtf = new JLabel("Insert URL: ");
		jp.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		jp.add(jtf);
		this.box = new JTextField();
		box.setPreferredSize(new Dimension(200, 25));
		jp.add(box);
		JButton send = new JButton("Enter");
		jp.add(send);
		send.addActionListener(this);
		this.setLocationRelativeTo(null);
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		//jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.pack();
		this.lp = lowerPanel;
	}
	/**
	 * ActionListener 
	 */
	@SuppressWarnings("unused")
	public void actionPerformed(ActionEvent e){
		String choice = e.getActionCommand();
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		if(choice == "Enter") {
			this.urlstring = box.getText();
			try {
				URL url = new URL(urlstring);
				String filename = Paths.get(new URI(urlstring).getPath()).getFileName().toString();
				Image newimage = new Image(ImageIO.read(url), filename);
				lp.getP2().getCurrentAlbum().getList().add(newimage);
				System.out.println("Elementi nella lista: " + lp.getP2().getCurrentAlbum().getList().size());
				System.out.println("Nome: " + newimage.getName());
				this.displayImageFromWeb(newimage.getRawimage(), lp);
			} catch (MalformedURLException e1) {
				Status s = new Status(false, "URL non valido");
				e1.printStackTrace();
				return;
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Status s = new Status(true, "Immagine caricata correttamente da URL");
		}
	}
	/**
	 * method used to display an Image loaded from an url.
	 * @param img image that will be added to the panel
	 * @param lp reference to the panel where the image will be loaded
	 */
	public void displayImageFromWeb(BufferedImage img, LowerPanel lp) {
		lp.getImagePanel().add(ImageFunctions.displayImage(img));
		SwingUtilities.updateComponentTreeUI(lp.getImagePanel());
	}
	
	public BufferedImage getPic() {
		return pic;
	}
	public void setPic(BufferedImage pic) {
		this.pic = pic;
	}
	public void setFile(String str) {
		this.downloadedfile = new File(str);
	}
	public File getFile(){
		return this.downloadedfile;
	}
	
	
	

}
