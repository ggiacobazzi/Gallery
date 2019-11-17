package functionalities;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

import windows.*;

public class ImageFunctions{
	//@SuppressWarnings("unused")
	
	/**
	 * method used to load an image. 
	 * @param fromweb boolean used to decide the way to load the image (from web or locally)
	 * @return returns a JLabel that will be added to the panel
	 */
	public static JLabel loadImage(Boolean fromweb) {
		BufferedImage immy = null;
		JLabel picLabel = new JLabel();
		picLabel.setSize(160, 108);  //240, 160
		
		//If the image is taken from an url
			if(fromweb) {
				AddWeb window = new AddWeb();
				Image newimage = new Image(window.getPic(), window.getFile());
				//immy = window.getPic();
				if(window.getLoaded()) {
					System.out.println("leavanny");
					return displayImage(newimage.getRawimage(), picLabel);
				}
			}
			//If the image is taken locally
			else {
				final JFileChooser fc = new JFileChooser();
				fc.addChoosableFileFilter(new ImageFilter());
				fc.setAcceptAllFileFilterUsed(false);
				int returnVal = fc.showOpenDialog(null);
				
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					
					try {
						immy = ImageIO.read(file);
						Image newimage = new Image(immy, file);
						Status s = new Status(true, "Immagine caricata correttamente");
						return displayImage(newimage.getRawimage(), picLabel);
					} catch (IOException e) {
						e.printStackTrace();
						Status s = new Status(false, "Immagine non caricata correttamente");
					}
				}
				else {
					Status s = new Status(false, "Immagine non selezionata");
				}
			}
		return picLabel;
	}	
	/**
	 * method used to display the image loaded
	 * @param immy image used to create the label
	 * @param picLabel label that is added to the panel
	 * @return
	 */
	public static JLabel displayImage(BufferedImage immy, JLabel picLabel) {
		ImageIcon img = new ImageIcon(immy.getScaledInstance(picLabel.getWidth(), picLabel.getHeight(), java.awt.Image.SCALE_SMOOTH));
		picLabel.setIcon(img);
		picLabel.setVisible(true);
		return picLabel;
	}
	
	/**
	 * method used to save a file locally from a url
	 * @param url url of the image 
	 * @return
	 */
	public static String saveFromWeb(URL url) {
		String newstr = "new_file";
		InputStream in;
		try {
			in = new BufferedInputStream(url.openStream());
			OutputStream out = new BufferedOutputStream(new FileOutputStream(newstr));

			for ( int i; (i = in.read()) != -1; ) {
			    out.write(i);
			}
			in.close();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return newstr;
	}

}
