package functionalities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

public class Image {

	/**
	 * Metadata of the image
	 */
	private BufferedImage rawimage;
	private String path;   
	private BasicFileAttributes attr;
	private String name;
	private String extension;
	private long dimension;
	private FileTime creation;
	private FileTime lastaccess;
	private FileTime lastmodification;
	
   //TODO: fix absolutepath@images_added_from_web;
	public Image(BufferedImage img, File rawfile) {
		try {
			this.setRawimage(img);
			this.setPath(rawfile.getAbsolutePath());
			this.setAttr(Files.readAttributes(Paths.get(this.getPath()), BasicFileAttributes.class));
			this.setDimension(this.getAttr().size());
			this.setCreation(this.getAttr().creationTime());
			this.setLastaccess(this.getAttr().lastAccessTime());
			this.setLastmodification(this.getAttr().lastModifiedTime());
			this.setName(rawfile.getName());
			this.setExtension(getExtension(rawfile));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	public Image(BufferedImage img, String name) {
		this.setRawimage(img);
		this.setName(name);
	}
	/**
	 * Set/Getters for the class
	 * @return
	 */
	
	public BufferedImage getRawimage() {
		return this.rawimage;
	}

	public void setRawimage(BufferedImage rawimage) {
		this.rawimage = rawimage;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public BasicFileAttributes getAttr() {
		return attr;
	}
	
	public void setAttr(BasicFileAttributes attr) {
		this.attr = attr;
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getExtension(File img) {
		 if (img == null) {
		        return "";
		    }
		    String name = img.getName();
		    int i = name.lastIndexOf('.');
		    this.extension = i > 0 ? name.substring(i + 1) : "";
		    return this.extension;
	}
	
	public void setExtension(String ext) {
		this.extension = ext;
	}

	public long getDimension() {
		return this.dimension;
	}

	public void setDimension(long dimension) {
		this.dimension = dimension;
	}

	public FileTime getCreation() {
		return this.creation;
	}

	public void setCreation(FileTime creation) {
		this.creation = creation;
	}

	public FileTime getLastaccess() {
		return this.lastaccess;
	}

	public void setLastaccess(FileTime lastaccess) {
		this.lastaccess = lastaccess;
	}

	public FileTime getLastmodification() {
		return this.lastmodification;
	}

	public void setLastmodification(FileTime lastmodification) {
		this.lastmodification = lastmodification;
	}

	
	
}

