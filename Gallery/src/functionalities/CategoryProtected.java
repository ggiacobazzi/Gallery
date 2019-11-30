package functionalities;

import java.io.File;

import graphics.MiddlePanel;

/**
 * Class used to create a protected category inside a PhotoAlbum
 * It needs a correct password in order to be accessed
 * @author gabri
 *
 */
public class CategoryProtected extends Category {

	private String path = "defaults" + File.separator + "folder-blue-locked-icon.png";
	private String password;
	public CategoryProtected(String name, String desc, MiddlePanel reference, String pass) {
		super(name, desc, reference);
		setPassword(pass);
		System.out.println("Pass: " + getPassword());
	}
	
	
	@Override
	public void setUpIcon() {
		System.out.println("Path prot: " + getPath());
		System.out.println("Pass: " + getPassword());
		super.setIcon(super.chooseImageForIcon(getPath()));
	}
	
	@Override
	public String getPath() {
		return path;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
