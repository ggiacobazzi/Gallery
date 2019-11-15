package functionalities;

import java.io.File;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

public class Image {

	/**
	 * Metadata of the image
	 */
	private static BasicFileAttributes attr;
	private static String name;
	private static String extension;
	private static long dimension;
	private static FileTime creation;
	private static FileTime lastaccess;
	private static FileTime lastmodification;
	private static String path;   

	
	public BasicFileAttributes getAttr() {
		return attr;
	}
	public void setAttr(BasicFileAttributes attr) {
		Image.attr = attr;
	}
	public static String getName(File img) {
		return img.getName();
	}
	public void setName(String name) {
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
	
}

