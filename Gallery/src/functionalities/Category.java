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
	private String path;
	private MiddlePanel ref;
	
	/**
	 * Constructor that uses "name" and "desc" to create a "Category" class 
	 * @method used to initialize the list containing the images
	 * @param name name of the instance of Category
	 * @param desc description of the instance of Category
	 * @param status used to determine if the category is protected or not (in order to add the default icon)
	 */
	public Category(String name, String desc, MiddlePanel reference, String password, String path, Boolean status) {
		setRef(reference);
		setName(name);
		setDescription(desc);
		setPath(path);
		newCategorySetUp(path, status);
		getIcon().addMouseListener(this);
	}
	
	//to override in protected
	public void newCategorySetUp(String defaultPath, Boolean status) {
		setDefCat(new ArrayList<Image>());
		setMaxImages(0);
		setDataOfCreation(java.time.LocalDateTime.now());
		setUpIcon(defaultPath, status);
	}
	
	public void setUpIcon(String defaultPath, Boolean status) {
		setIcon(chooseImageForIcon(defaultPath, status));
	}
	
	public JLabel chooseImageForIcon(String defaultIcon, Boolean status) {
		
		//default icon if there are no images in the category
		if(getDefCat().size() == 0) {
			return ImageFunctions.chooseImageForIcon(defaultIcon);
//			BufferedImage ex;
//			try {
//				ex = ImageIO.read(new File(defaultIcon));
//				return ImageFunctions.displayImage(ex);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		}
		//random icon taken from the images in the category
		else if(status) {
			Random random = new Random();
			int randnum = random.nextInt(this.getDefCat().size());
			return ImageFunctions.displayImage(this.getDefCat().get(randnum).getRawimage());
		}
		//default icon if there are images in a protected category
		else {
			return ImageFunctions.chooseImageForIcon(defaultIcon);
		}
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

	public MiddlePanel getRef() {
		return this.ref;
	}

	public void setRef(MiddlePanel ref) {
		this.ref = ref;
	}

	public void sendInfos() {
		
		
	}
	
	public void displayContents() {
		//Display all the images contained in the category
		for(int i = 0; i < this.getDefCat().size(); i++) {
			getRef().getIp().add(ImageFunctions.displayImage(this.getDefCat().get(i).getRawimage()));
		}
		
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		ref.getLp().DisplayInfos(this, null, true);
		if(e.getClickCount() == 2) {
			setRef(ref.getParent().updateFrame());
			displayContents();
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
