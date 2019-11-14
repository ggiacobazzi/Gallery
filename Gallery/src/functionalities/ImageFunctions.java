package functionalities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

import windows.AddWeb;

public class ImageFunctions implements ActionListener{

	
	public static JLabel loadImage(Boolean fromweb) {
		BufferedImage immy = null;
		JLabel picLabel = new JLabel();
		picLabel.setSize(160, 108);  //240, 160
		Boolean loaded = false;
		
		//If the image is taken from an url
			if(fromweb) {
				AddWeb window = new AddWeb();
				immy = window.getPic();
				loaded = true;
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
//						//Path of the File to recover the attributes of it
//						path = file.getAbsolutePath();
//						Path fpath = Paths.get(path);
//						BasicFileAttributes attr = Files.readAttributes(fpath, BasicFileAttributes.class);
//						name = getName(file);
//						extension = getExtension(file);
//						dimension = attr.size();
//						creation = attr.creationTime();
//						lastaccess = attr.lastAccessTime();
//						lastmodification = attr.lastModifiedTime();
//						System.out.println("File Path: " + path);
//						System.out.println("File Name : " + name);
//						System.out.println("File Extension: " + extension);
//						System.out.println("Dimension: " + dimension + " bytes");
//						System.out.println("Creation Time: " + creation);
//						System.out.println("Last Access Time: " + lastaccess);
//						System.out.println("Last Modification Time: " + lastmodification);
						loaded = true;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("Immagine non caricata correttamente");
					}
				}
				else
					System.out.println("Immagine non selezionata");
			}
		if(loaded) {
			ImageIcon img = new ImageIcon(immy.getScaledInstance(picLabel.getWidth(), picLabel.getHeight(), java.awt.Image.SCALE_SMOOTH));
			picLabel.setIcon(img);
			picLabel.setVisible(true);
			return picLabel;
		}
		else
			return null;
	}	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
