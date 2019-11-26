package functionalities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JLabel;

/**
 * Class used to create a category inside a PhotoAlbum
 * It can be accessed freely
 * @author gabri
 *
 */
public class Category {
	@SuppressWarnings("unused")
	
	private int maxImages;
	private LocalDateTime dataOfCreation;
	private String name;
	private String description;
	private ArrayList<Image> defCat;
	private JLabel caticon;
	private String path = "defaults" + File.separator + "folder-blue-icon.png/";
	
	/**
	 * Default constructor used when it's called without values
	 */
	
	public Category() {
		this("placeholder", "Just a normal category without a description, because the user was lazy :)");
	}
	
	/**
	 * Constructor that uses "name" and "desc" to create a "Category" class 
	 * @method used to initialize the list containing the images
	 * @param name name of the instance of Category
	 * @param desc description of the instance of Category
	 */
	public Category(String name, String desc) {
		newCategorySetUp();
		setName(name);
		setDescription(desc);
		System.out.println("cat created");
	}
	
	//to override in protected
	public void newCategorySetUp() {
		setUpIcon();
		this.setDefCat(new ArrayList<Image>());
		this.setMaxImages(0);
		this.setDataOfCreation(java.time.LocalDateTime.now());
	}
	
	public void setUpIcon() {
		this.setIcon(chooseImageForIcon());
	}
	
	public JLabel chooseImageForIcon() {
		BufferedImage ex;
		try {
			ex = ImageIO.read(new File(this.getPath()));
			return ImageFunctions.displayImage(ex);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Caricamento fallito");
		return null;
	}
	
	public int getMaxImages() {
		return maxImages;
	}
	
	public void setMaxImages(int maxImages) {
		this.maxImages = maxImages;
	}
	
	public LocalDateTime getDataOfCreation() {
		return dataOfCreation;
	}
	
	public void setDataOfCreation(LocalDateTime localDateTime) {
		this.dataOfCreation = localDateTime;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<Image> getDefCat() {
		return defCat;
	}

	public void setDefCat(ArrayList<Image> defCat) {
		this.defCat = defCat;
	}

	public JLabel getIcon() {
		return caticon;
	}

	public void setIcon(JLabel icon) {
		this.caticon = icon;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
