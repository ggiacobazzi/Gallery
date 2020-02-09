package basecomponents;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Class used to create a photoalbum containing categories which have images
 * It has a list of categories, a name and has a date of creation
 * @author gabri
 */

public class PhotoAlbum implements Serializable{
	
	private ArrayList<Category> categoryList;
	private String name;
	private String dateOfCreation;
	private static final long serialVersionUID = 1L;
	
	/**
	 * Basic constructor used to create an instance of album
	 */
	public PhotoAlbum() {
		this("");
	}
	
	/**
	 * Constructor that initializes a Photo Album
	 * @param name Name used for the album
	 */
	public PhotoAlbum(String name) {
		setDateOfCreation();
		setName(name);
		setCategoryList();
	}

	/**
	 * Method used to add a category
	 * @param alb Reference to the album 
	 * @param cat Category that will be added
	 */
	public void addCategory(PhotoAlbum alb, Category cat) {
		alb.getCategoryList().add(cat);
	}

	/**
	 * Method to remove a single category  TODO: use category instead of index
	 * @param alb Reference to the album
	 * @param index index of the category because PhotoAlbum uses a list
	 */
	public void removeCategory(PhotoAlbum alb, int index) {
		alb.categoryList.remove(index);
	}

	/**
	 * Method used to remove all images and all categories
	 * @param alb Album that will be resetted
	 */
	public void removeAll(PhotoAlbum alb) {
		alb.getCategoryList().removeAll(getCategoryList());
	}

	/**
	 * Set Category list of the Photo Album
	 */
	public void setCategoryList() {
		this.categoryList = new ArrayList<Category>();
	}

	/**
	 * Get Category list of the Photo Album
	 * @return list
	 */
	public ArrayList<Category> getCategoryList() {
		return this.categoryList;
	}

	/**
	 * Set Photo Album name
	 * @param name name of the album
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Get Photo Album name
	 * @return album name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Set Photo Album date of creation
	 */
	public void setDateOfCreation() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		this.dateOfCreation = formatter.format(date);
	}
	
	/**
	 * Get Photo Album date of creation
	 * @return
	 */
	public String getDateOfCreation() {
		return this.dateOfCreation;
	}
}
