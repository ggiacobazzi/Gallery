package basecomponents;

import java.io.Serializable;

import functionalities.EditCategory;
import functionalities.MergeCategories;
import functionalities.PasswordCheck;
import functionalities.RemoveCategory;
import layout.CategoryImagePanel;

/**
 * Class used to create a Protected Category
 * It needs a password to be accessed or to do actions regarding it
 * It has various elements of Polymorphism
 * @author gabri
 */
public class CategoryProtected extends Category implements Serializable{
	
	private String password;
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor that calls parent class and initializes a Protected Category
	 * @param name Name of the category
	 * @param desc Description of the category
	 * @param pass Password of the category
	 */
	public CategoryProtected(String name, String desc, String pass) {
		super(name, desc);
		setPassword(pass);
	}
	
	/**
	 * Method to access a protected category
	 * @param catPanRef Reference to the CategoryImagePanel
	 */
	@Override
	public void accessCat(CategoryImagePanel catPanRef) {
		new PasswordCheck(this, catPanRef, "access");
	}
	
	/**
	 * Method to remove a protected category
	 * @param remCatRef Reference to the Remove Category Window
	 * @param index index of the category to remove
	 */
	@Override
	public void remCat(RemoveCategory remCatRef, int index) {
		new PasswordCheck(this, "remove", remCatRef, index);
	}
	
	/**
	 * Method to edit a protected category
	 * @param editCatRef Reference to the Edit Category Window
	 * @param index index of the category to edit
	 */
	@Override
	public void editCat(EditCategory editCatRef, int index) {
		new PasswordCheck(this, "edit", editCatRef, index);
	}
	
	/**
	 * Method to merge a protected category to another category
	 * @param mergeCatRef Reference to the Merge Category Window
	 * @param source index of the source category to merge
	 * @param destination index of the destination category
	 */
	@Override
	public void mergeCat(MergeCategories mergeCatRef, int source, int destination) {
		new PasswordCheck(this, "merge", mergeCatRef, source, destination);
	}

	/**
	 * Get Password of the protected category
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Set Password of the protected category
	 * @param password password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
