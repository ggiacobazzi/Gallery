package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.nio.file.attribute.FileTime;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import functionalities.Category;
import functionalities.CategoryProtected;
import functionalities.Image;

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
	private JPanel jp;
	
	private static final long serialVersionUID = 1L;

	public LeftPanel(Color c) {
		this.CreatePanel(c);
	}
	public void CreatePanel(Color c) {
		this.setBackground(c);
		this.setPreferredSize(new Dimension(400,400));
		this.setMinimumSize(new Dimension(400,400));
	}
	
	/**
	 * method used to display metadata of the object (category or image)
	 * @param cat reference to the category clicked
	 * @param img reference to the image clicked
	 * @param catOrimage boolean used in order to distinguish what to do
	 */
	public void DisplayInfos(Category cat, Image img, Boolean catOrimage) {
		if(jp != null)
			this.remove(jp);
		jp = new JPanel();
		jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
		
		if(catOrimage) {
			JLabel jlName = new JLabel("Name: " + cat.getName());
			jp.add(jlName);
			JLabel jlDesc = new JLabel("Description: " + cat.getDescription());
			jp.add(jlDesc);
			JLabel jlDate = new JLabel("Date of creation: " + cat.getDataOfCreation());
			jp.add(jlDate);
			JLabel jlDim = new JLabel("Total Photos: " + cat.getDefCat().size());
			jp.add(jlDim);
			JLabel jlProtStat = new JLabel("Protected: " + (cat instanceof CategoryProtected));
			jp.add(jlProtStat);
		}
		else {
			JLabel jlName = new JLabel("Name: " + img.getName());
			jp.add(jlName);
			JLabel jlExt = new JLabel("Extension: " + img.getExtension());
			jp.add(jlExt);
			JLabel jlDim = new JLabel("Dimension: " + img.getDimension());
			jp.add(jlDim);
		}
		
		this.add(jp);
		SwingUtilities.updateComponentTreeUI(this);
		
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
