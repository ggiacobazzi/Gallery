package functionalities;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class Image implements ActionListener{

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
		    String ext = i > 0 ? name.substring(i + 1) : "";
		    return ext;
	}
	
	public static JLabel loadImage(Boolean fromweb) {
		BufferedImage immy = null;
		JLabel picLabel = new JLabel();
		picLabel.setSize(160, 108);  //240, 160
		
		//If the image is taken from an url
		if(fromweb) {
			JFrame jf = new JFrame("Add Image from web");
			jf.setSize(new Dimension(500, 100));
			jf.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
			jf.setResizable(false);
			JPanel jp = new JPanel();
			jf.add(jp);
			JLabel jtf = new JLabel("Insert URL: ");
			jp.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			jp.add(jtf);
			JTextField box = new JTextField();
			box.setPreferredSize(new Dimension(200, 25));
			jp.add(box);
			JButton send = new JButton("Enter");
			jp.add(send);
			jf.setLocationRelativeTo(null);
			jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			jf.setVisible(true);
			jf.pack();
			
		}
		else {
			final JFileChooser fc = new JFileChooser();
			fc.addChoosableFileFilter(new ImageFilter());
			fc.setAcceptAllFileFilterUsed(false);
			int returnVal = fc.showOpenDialog(null);
			
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				
				try {
					immy = ImageIO.read(file);
					//Path of the File to recover the attributes of it
					path = file.getAbsolutePath();
					Path fpath = Paths.get(path);
					BasicFileAttributes attr = Files.readAttributes(fpath, BasicFileAttributes.class);
					name = getName(file);
					extension = getExtension(file);
					dimension = attr.size();
					creation = attr.creationTime();
					lastaccess = attr.lastAccessTime();
					lastmodification = attr.lastModifiedTime();
					System.out.println("File Path: " + path);
					System.out.println("File Name : " + name);
					System.out.println("File Extension: " + extension);
					System.out.println("Dimension: " + dimension + " bytes");
					System.out.println("Creation Time: " + creation);
					System.out.println("Last Access Time: " + lastaccess);
					System.out.println("Last Modification Time: " + lastmodification);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					System.out.println("Immagine non caricata correttamente");
				}
				
				ImageIcon img = new ImageIcon(immy.getScaledInstance(picLabel.getWidth(), picLabel.getHeight(), java.awt.Image.SCALE_SMOOTH));
				picLabel.setIcon(img);
				picLabel.setVisible(true);
			}
			else
				System.out.println("Immagine non selezionata");
		}
		return picLabel;
	}
//	public void actionPerformed(JPanel jp, ActionEvent e) {
//		// TODO Auto-generated method stub
//		if(e.getSource() == jp.get) {
//			
//		}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
		
	}

