package functionalities;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class PhotoAlbum {
	@SuppressWarnings("unused")
	
	private int maxImages;
	private LocalDateTime dataOfCreation;
	private String name;
	private String description;
	
	/**
	 * Default constructor used when it's called without values
	 */
	
	public PhotoAlbum() {
		ArrayList<Image> defCat = new ArrayList<Image>();
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
		ArrayList<Image> cat = new ArrayList<Image>();
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
}
