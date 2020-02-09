package basecomponents;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import functionalities.EditCategory;
import functionalities.MergeCategories;
import functionalities.RemoveCategory;
import layout.CategoryImagePanel;

/**
 * Class used to create a category inside a PhotoAlbum
 * It can be accessed freely
 * @author gabri
 *
 */
public class Category implements Serializable {

	private String dateOfCreation;
	private String name;
	private String description;
	private ArrayList<Image> defCat;
	private static int num = 0;
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor that uses "name" and "desc" to create a "Category" class 
	 * If no name or description are given it initializes the category using default values
	 * @method used to initialize the list containing the images
	 * @param name Name of the instance of Category
	 * @param desc Description of the instance of Category
	 */
	public Category(String name, String desc) {
		setDateOfCreation();
		setNameDesc(name, desc);
		setDefCat(new ArrayList<Image>());
	}
	
	/**
	 * Method that sets default values if the given ones are empty
	 * @param name Name value
	 * @param desc Description value
	 */
	public void setNameDesc(String name, String desc) {
		setName(name);
		setDescription(desc);
		if(name.isEmpty()) {
			setName("UnnamedCategory" + "(" + Category.getNum() + ")");
			Category.setNum(Category.getNum() + 1);
		}
		if(desc.isEmpty())
			setDescription("NoDescriptionGiven");
	}
	
	/**
	 * Method to access a normal category
	 * @param catPanRef reference to the category that will be opened
	 */
	public void accessCat(CategoryImagePanel catPanRef) {
		catPanRef.accessCategory();
	}
	
	/**
	 * Method to remove a normal category
	 * @param remRef reference to the Remove Category window
	 * @param index index of the category that will be removed
	 */
	public void remCat(RemoveCategory remRef, int index) {
		remRef.removeCat(index);
	}
	
	/**
	 * Method to edit a normal category
	 * @param editCat reference to the Edit Category window
	 * @param index index of the category that will be edited
	 */
	public void editCat(EditCategory editCat, int index) {
		editCat.editCategory(index);
	}
	
	/**
	 * Method to merge a normal category to another category
	 * @param mergeCat reference to the Merge Categories window
	 * @param source index of the first category
	 * @param destination index of the second category
	 */
	public void mergeCat(MergeCategories mergeCat, int source, int destination) {
		mergeCat.mergeCategories(source, destination);
	}
	
	/**
	 * Get Category Data of Creation
	 * @return data of creation
	 */
	public String getDateOfCreation() {
		return dateOfCreation;
	}
	
	/**
	 * Set Category Data of Creation
	 */
	public void setDateOfCreation() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		this.dateOfCreation = formatter.format(date);
	}
	
	/**
	 * Get Category Name
	 * @return category name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set Category Name
	 * @param name category name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Get Category Description
	 * @return category description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Set Category Description
	 * @param description category description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Get Category list of images
	 * @return list of images
	 */
	public ArrayList<Image> getDefCat() {
		return defCat;
	}

	/**
	 * Set Category list of images
	 * @param defCat list of images
	 */
	public void setDefCat(ArrayList<Image> defCat) {
		this.defCat = defCat;
	}

	/**
	 * Get number used to label Default Categories
	 * @return number
	 */
	public static int getNum() {
		return num;
	}

	/**
	 * Set number used to label Default Categories
	 * @param num number
	 */
	public static void setNum(int num) {
		Category.num = num;
	}

	
	
}
