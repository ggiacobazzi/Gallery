package functionalities;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class PhotoAlbum {
	@SuppressWarnings("unused")
	
	private int maxImages;
	private LocalDateTime dataOfCreation;
	private String name;
	private String description;
	private ArrayList<Image> defCat;
	
	/**
	 * Default constructor used when it's called without values
	 */
	
	public PhotoAlbum() {
		this.setDefCat(new ArrayList<Image>());
		setMaxImages(0);
		setDataOfCreation(java.time.LocalDateTime.now());
		setName("placeholder");
		setDescription("Just a normal category without a description, because the user was lazy :)");
	}
	
	/**
	 * Constructor that uses "name" and "desc" to create a "Category" class 
	 * @param name name of the instance of Category
	 * @param desc description of the instance of Category
	 */
	public PhotoAlbum(String name, String desc) {
		this.setDefCat(new ArrayList<Image>());
		//ArrayList<Image> cat = new ArrayList<Image>();
		setMaxImages(0);
		setDataOfCreation(java.time.LocalDateTime.now());
		setName(name);
		setDescription(desc);
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
}
