package functionalities;

import java.io.File;

import graphics.MiddlePanel;
import windows.PasswordCheck;

/**
 * Class used to create a protected category inside a PhotoAlbum
 * It needs a correct password in order to be accessed
 * @author gabri
 *
 */
public class CategoryProtected extends Category {

	private String password;
	public CategoryProtected(String name, String desc, MiddlePanel reference, String pass, String path) {
		super(name, desc, reference, pass, "defaults" + File.separator + "folder-blue-locked-icon.png", false);
		System.out.println("Path figlio1: " + getPath());
		setPassword(pass);
		PasswordCheck pc = new PasswordCheck(this);
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		System.out.println("Password nel set: " + password);
		this.password = password;
	}

}
