package functionalities;

import java.io.File;
import javax.swing.filechooser.*;

/**
 * Class used to filter the files in order to get only the images
 * @author gabri
 *
 */

public class ImageFilter extends FileFilter{
	    public boolean accept(File f) {
	        if (f.isDirectory()) {
	            return true;
	        }
	 
	        String extension = Utils.getExtension(f);
	        if (extension != null) {
	            if (extension.equals(Utils.gif) ||
	                extension.equals(Utils.jpeg) ||
	                extension.equals(Utils.jpg) ||
	                extension.equals(Utils.png)) {
	                    return true;
	            } else {
	                return false;
	            }
	        }
	 
	        return false;
	    }
	 
	    public String getDescription() {
	        return "Immagini (.gif/.jpeg/.jpg/.png)";
	    }
	}
