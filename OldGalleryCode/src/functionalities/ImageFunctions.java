package functionalities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import graphics.LowerPanel;
import graphics.MiddlePanel;
import windows.*;

public class ImageFunctions{
	//@SuppressWarnings("unused")
	
	/**
	 * method used to load an image. 
	 * @param fromweb boolean used to decide the way to load the image (from web or locally)
	 * @param lowerPanel reference to the panel that contains the panel storing the images
	 */
	public static void loadImage(Boolean fromweb, LowerPanel lowerPanel) {
		
		
			//If the image is taken from an url
			if(fromweb) {
				AddWeb window = new AddWeb(lowerPanel);
			}
			//If the image is taken locally
			else {
				BufferedImage immy = null;
				final JFileChooser fc = new JFileChooser();
				fc.addChoosableFileFilter(new ImageFilter());
				fc.setAcceptAllFileFilterUsed(false);
				int returnVal = fc.showOpenDialog(null);
				
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					
					try {
						immy = ImageIO.read(file);
						Image newimage = new Image(immy, file);
						lowerPanel.getP2().getCurrentCategory().getDefCat().add(newimage);
						lowerPanel.getP2().getCurrentAlbum().getList().add(newimage);
						System.out.println("Oggetti in lista: " + lowerPanel.getP2().getCurrentCategory().getDefCat().size());
						Status s = new Status(true, "Immagine caricata correttamente");
						lowerPanel.getImagePanel().add(displayImage(newimage.getRawimage()));
						SwingUtilities.updateComponentTreeUI(lowerPanel.getP2().getParent());
					} catch (IOException e) {
						e.printStackTrace();
						Status s = new Status(false, "Immagine non caricata correttamente");
					}
				}
				else {
					Status s = new Status(false, "Immagine non selezionata");
				}
			}
	}	
	
	/**
	 * method used to display the image loaded
	 * @param immy image used to create the label
	 * @param picLabel label that is added to the panel
	 * @param name is the name displayed under the Image/Category
	 * @return the label that will be added to the major panel
	 */
	public static JLabel displayImage(BufferedImage immy) {
		JLabel picLabel = new JLabel();
		picLabel.setSize(240, 160);  //240, 160  160 108
		ImageIcon img = new ImageIcon(immy.getScaledInstance(160, 108, java.awt.Image.SCALE_SMOOTH));
		picLabel.setIcon(img);
		picLabel.setVisible(true);
		return picLabel;
	}
	/**
	 * method used to display icons in various panel in the program	
	 * @param iconPath
	 * @return it calls another method specialized in creating a JLabel that will be returned here
	 * 		   null if it fails to read the image (it should not happen at all)
	 */
	public static JLabel chooseImageForIcon(String iconPath) {
		BufferedImage ex;
		try {
			ex = ImageIO.read(new File(iconPath));
			return ImageFunctions.displayImage(ex);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Caricamento fallito");
		return null;
	}
	
	//TODO remove image from a category-->method gets called in Remove.java
	public static void removeImage(Category cat, int index, MiddlePanel mp) {
		cat.getDefCat().remove(index);
		SwingUtilities.updateComponentTreeUI(mp.getIp());
		
	}
	
//	/**
//	 * method used to save a file locally from a url
//	 * @param url url of the image 
//	 * @return
//	 */
//	public static String saveFromWeb(URL url) {
//		String newstr = "new_file";
//		InputStream in;
//		try {
//			in = new BufferedInputStream(url.openStream());
//			OutputStream out = new BufferedOutputStream(new FileOutputStream(newstr));
//
//			for ( int i; (i = in.read()) != -1; ) {
//			    out.write(i);
//			}
//			in.close();
//			out.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return newstr;
//	}
//	
//	public static void downloadFromWeb(String search, String path) {
//		
//		// This will get input data from the server
//	    InputStream inputStream = null;
//
//	    // This will read the data from the server;
//	    OutputStream outputStream = null;
//
//	    try {
//	        // This will open a socket from client to server
//	        URL url = new URL(search);
//
//	       // This user agent is for if the server wants real humans to visit
//	        String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36";
//
//	       // This socket type will allow to set user_agent
//	        URLConnection con = url.openConnection();
//
//	        // Setting the user agent
//	        con.setRequestProperty("User-Agent", USER_AGENT);
//
//	        // Requesting input data from server
//	        inputStream = con.getInputStream();
//
//	        // Open local file writer
//	        outputStream = new FileOutputStream(path);
//
//	        // Limiting byte written to file per loop
//	        byte[] buffer = new byte[2048];
//
//	        // Increments file size
//	        int length;
//
//	        // Looping until server finishes
//	        while ((length = inputStream.read(buffer)) != -1) {
//	            // Writing data
//	            outputStream.write(buffer, 0, length);
//	        }
//	    } catch (Exception ex) {
//	        Logger.getLogger(WebCrawler.class.getName()).log(Level.SEVERE, null, ex);
//	     }
//
//	     // closing used resources
//	     // The computer will not be able to use the image
//	     // This is a must
//
//	     outputStream.close();
//	     inputStream.close();
//	}

}
