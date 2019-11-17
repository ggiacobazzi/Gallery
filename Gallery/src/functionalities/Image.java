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
	private static BufferedImage rawimage;
	private static String path;   
	private static BasicFileAttributes attr;
	private static String name;
	private static String extension;
	private static long dimension;
	private static FileTime creation;
	private static FileTime lastaccess;
	private static FileTime lastmodification;
	
   //TODO: fix absolutepath@images_added_from_web;
	public Image(BufferedImage img, File rawfile) {
		try {
			Image.setRawimage(img);
			Image.setPath(rawfile.getAbsolutePath());
			Image.setAttr(Files.readAttributes(Paths.get(Image.getPath()), BasicFileAttributes.class));
			Image.setName(getName(rawfile));
			Image.setExtension(getExtension(rawfile));
			Image.setDimension(Image.getAttr().size());
			Image.setCreation(Image.getAttr().creationTime());
			Image.setLastaccess(Image.getAttr().lastAccessTime());
			Image.setLastmodification(Image.getAttr().lastModifiedTime());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	/**
	 * Set/Getters for the class
	 * @return
	 */
	
	public static BufferedImage getRawimage() {
		return rawimage;
	}

	public static void setRawimage(BufferedImage rawimage) {
		Image.rawimage = rawimage;
	}

	public static String getPath() {
		return path;
	}

	public static void setPath(String path) {
		Image.path = path;
	}
	
	public static BasicFileAttributes getAttr() {

		return attr;
	}
	
	public static void setAttr(BasicFileAttributes attr) {
		Image.attr = attr;
	}
	
	public static String getName(File rawfile) {
		return name;
	}
	public static void setName(String name) {
		Image.name = name;
	}
	
	public static String getExtension(File img) {
		 if (img == null) {
		        return "";
		    }
		    String name = img.getName();
		    int i = name.lastIndexOf('.');
		    extension = i > 0 ? name.substring(i + 1) : "";
		    return extension;
	}
	
	public static void setExtension(String ext) {
		Image.extension = ext;
	}

	public static long getDimension() {
		return dimension;
	}

	public static void setDimension(long dimension) {
		Image.dimension = dimension;
	}

	public static FileTime getCreation() {
		return creation;
	}

	public static void setCreation(FileTime creation) {
		Image.creation = creation;
	}

	public static FileTime getLastaccess() {
		return lastaccess;
	}

	public static void setLastaccess(FileTime lastaccess) {
		Image.lastaccess = lastaccess;
	}

	public static FileTime getLastmodification() {
		return lastmodification;
	}

	public static void setLastmodification(FileTime lastmodification) {
		Image.lastmodification = lastmodification;
	}

	
	
}

