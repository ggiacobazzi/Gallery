package functionalities;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import graphics.MiddlePanel;

/**
 * Class used to create a category inside a PhotoAlbum
 * It can be accessed freely
 * @author gabri
 *
 */
public class Category implements MouseListener {
	@SuppressWarnings("unused")
	
	private int maxImages;
	private LocalDateTime dataOfCreation;
	private String name;
	private String description;
	private ArrayList<Image> defCat;
	private JLabel caticon;
	private String path = "defaults" + File.separator + "folder-blue-icon.png/";
	private static MiddlePanel ref;
	
	/**
	 * Default constructor used when it's called without values
	 */
	
//	public Category() {
//		this("placeholder", "Just a normal category without a description, because the user was lazy :)");
//	}
	
	/**
	 * Constructor that uses "name" and "desc" to create a "Category" class 
	 * @method used to initialize the list containing the images
	 * @param name name of the instance of Category
	 * @param desc description of the instance of Category
	 */
	public Category(String name, String desc, MiddlePanel reference) {
		Category.setRef(reference);
		newCategorySetUp();
		setName(name);
		setDescription(desc);
		this.caticon.addMouseListener(this);
		System.out.println("cat created");
	}
	
	//to override in protected
	public void newCategorySetUp() {
		this.setDefCat(new ArrayList<Image>());
		this.setMaxImages(0);
		this.setDataOfCreation(java.time.LocalDateTime.now());
		setUpIcon();
	}
	
	public void setUpIcon() {
		this.setIcon(chooseImageForIcon());
	}
	
	public JLabel chooseImageForIcon() {
		
		//default icon
		System.out.println("dim: " + this.getDefCat().size());
		if(this.defCat.size() == 0) {
			BufferedImage ex;
			try {
				ex = ImageIO.read(new File(this.getPath()));
				return ImageFunctions.displayImage(ex);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//random icon taken from the images in the category
		else {
			Random random = new Random();
			int randnum = random.nextInt(this.getDefCat().size());
			return ImageFunctions.displayImage(this.getDefCat().get(randnum).getRawimage());
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

	public static MiddlePanel getRef() {
		return ref;
	}

	public static void setRef(MiddlePanel ref) {
		Category.ref = ref;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getClickCount() == 2) {
			
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
