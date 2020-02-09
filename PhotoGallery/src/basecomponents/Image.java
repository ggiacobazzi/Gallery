package basecomponents;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

/**
 * Class that describes an Image
 * An Image is described by the image itself and by a name
 * @author gabri
 */

public class Image implements Serializable{

	private BufferedImage rawImage;
	private String name;
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor that initializes an Image
	 * @param img Image 
	 * @param name Name of the image
	 */
	public Image(BufferedImage img, String name) {
		setRawImage(img);
		setName(name);
	}
	
	/**
	 * Method used to serialize the object
	 * @param out Output file
	 * @throws IOException
	 */
	private void writeObject(java.io.ObjectOutputStream out)throws IOException{
		//Name
		out.writeObject(getName());
		
		//Image
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		ImageIO.write(getRawImage(), "jpg", buffer);
		
		out.writeInt(buffer.size());
		buffer.writeTo(out);
	}
	
	/**
	 * Method used to serialize the object
	 * @param in Input file
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void readObject(java.io.ObjectInputStream in)throws IOException, ClassNotFoundException{
		//Name
		setName((String)in.readObject());
		
		//Image
		int size = in.readInt();
		byte[] buffer = new byte[size];
		in.readFully(buffer);
		setRawImage(ImageIO.read(new ByteArrayInputStream(buffer)));
	}
	
	/**
	 * Get Image
	 * @return image 
	 */
	public BufferedImage getRawImage() {
		return rawImage;
	}

	/**
	 * Set Image
	 * @param rawImage Image
	 */
	public void setRawImage(BufferedImage rawImage) {
		this.rawImage = rawImage;
	}

	/**
	 * Get Image name
	 * @return image name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set Image name
	 * @param name image name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}

