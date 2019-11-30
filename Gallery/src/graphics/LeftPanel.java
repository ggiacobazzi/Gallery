package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.nio.file.attribute.FileTime;

import javax.swing.JPanel;

public class LeftPanel extends JPanel{

	/**
	 * 
	 */
	private String name;
	private String desc;
	private String date;
	private String extension;
	private String path;   
	private FileTime creation;
	private FileTime lastaccess;
	private FileTime lastmodification;
	
	private static final long serialVersionUID = 1L;

	public LeftPanel(Color c) {
		this.CreatePanel(c);
	}
	public void CreatePanel(Color c) {
		this.setBackground(c);
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new Dimension(200,400));
		this.setMinimumSize(new Dimension(300,400));
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public FileTime getCreation() {
		return creation;
	}
	public void setCreation(FileTime creation) {
		this.creation = creation;
	}
	public FileTime getLastaccess() {
		return lastaccess;
	}
	public void setLastaccess(FileTime lastaccess) {
		this.lastaccess = lastaccess;
	}
	public FileTime getLastmodification() {
		return lastmodification;
	}
	public void setLastmodification(FileTime lastmodification) {
		this.lastmodification = lastmodification;
	}
	
	
}
