package functionalities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import basecomponents.Image;
import layout.ImagePanel;
import layout.LowerPanel;

/**
 * Class used to add images to the album (From web or from local)
 * @author gabri
 */

public class AddImage{
	
	/**
	 * method used to load an image. 
	 * @param fromWeb boolean used to decide the way to load the image (from web or locally)
	 * @param parentPan reference to the panel that contains the panel storing the images
	 */
	public void loadImage(Boolean fromWeb, LowerPanel parentPan) {
		
			//If the image is taken from an url
			if(fromWeb) {
				new AddWeb(parentPan, this);
			}
			//If the image is taken locally
			else {
				BufferedImage image = null;
				final JFileChooser imageFileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"Image", "jpg", "jpeg", "png", "gif");
				imageFileChooser.addChoosableFileFilter(filter);
				imageFileChooser.setAcceptAllFileFilterUsed(false);
				int returnVal = imageFileChooser.showOpenDialog(null);
				
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					File file = imageFileChooser.getSelectedFile();
					
					try {
						//Load Image
						image = ImageIO.read(file);
						Image newImage = new Image(image, file.getName());
						
						addImagePanel(newImage, parentPan);
						
						new Status(true, "Immagine caricata correttamente");
					} catch (IOException e) {
						new Status(false, "Immagine non caricata correttamente");
					}
				}
				else {
					new Status(false, "Immagine non selezionata");
				}
			}
	}	
	
	/**
	 * Method used to add a new Image Panel to the Middle Panel
	 * @param newImage New Image to add to the category
	 * @param parentPan Parent Panel
	 */
	public void addImagePanel(Image newImage, LowerPanel parentPan) {
		//Create Image Panel
		ImagePanel newImageIcon = new ImagePanel(newImage.getRawImage(), newImage.getName(), parentPan.getMiddlePanRef());
		parentPan.getMiddlePanRef().getParent().getCatPan().getMiddlePanRef().displayImagePanel(newImageIcon);
		
		//Add Image Panel
		parentPan.getMiddlePanRef().getCurrentCategory().getDefCat().add(newImage);
	}
}
	