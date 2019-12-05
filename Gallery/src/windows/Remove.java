package windows;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import functionalities.Category;
import functionalities.ImageFunctions;

/**
 * Class that creates the Remove Window when the user presses the remove button
 * It has a list of images that can be removed from the selected category and the image is displayed
 * on the left
 * @author gabri
 *
 */

public class Remove extends JFrame{

	/**
	 * 
	 */
	private JPanel jpIm;
	private JPanel jpList;
	private JPanel jpBut;
	private JLabel image;
	private JFrame windowRef;
	private static final long serialVersionUID = 1L;

	public Remove(Category cat) {
		super("Remove an image: ");
		setPreferredSize(new Dimension(700, 300));
		setResizable(false);
		createWindow(cat);
		setWindowRef(this);
	}
	
	public void createWindow(Category cat) {
		//Image Panel
		setJpIm(new JPanel());
		setImage(new JLabel());
		getImage().setPreferredSize(new Dimension(160, 108));
		jpIm.add(getImage());
		
		//JComboBox panel
		setJpList(new JPanel());
		JComboBox<String> jcb = createComboBox(cat);
		jpList.add(jcb);
		
		//Buttons panel
		setJpBut(new JPanel());
		getJpBut().setLayout(new BoxLayout(getJpBut(), BoxLayout.Y_AXIS));
		//Confirm button
		JButton confBt = new JButton("Confirm");
		getJpBut().add(confBt);
		confBt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			
			
		});
		//Cancel button
		JButton cancBt = new JButton("Cancel");
		getJpBut().add(cancBt);
		cancBt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				closeJframe();
			}
		});
		
	}
	
	/**
	 * Method that creates a JComboBox used to select the images to remove. The selected image will be
	 * displayed in the left-side of the window.
	 * @param cat reference to the category that will be used 
	 * @return the finished JComboBox
	 */
	public JComboBox<String> createComboBox(Category cat) {
		JComboBox<String> jcbListIm = new JComboBox<String>(createList(cat));
		jcbListIm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				updateLabel(cat.getDefCat().get(jcbListIm.getSelectedIndex()).getRawimage());
			}
		});
		
		return jcbListIm;
	}
	
	/**
	 * Method that takes a category and creates an array that will be used to create the JComboBox
	 * @param cat Category used to display the name of the images in the JComboBox
	 * @return nameIm array of strings used to create the JComboBox
	 */
	public String[] createList(Category cat){
		String[] nameIm = new String[cat.getDefCat().size()+1];
		//first index not used
		nameIm[0] = "Select an image";
		for(int i = 1; i < cat.getDefCat().size()+1; i++) {
			nameIm[i] = cat.getDefCat().get(i).getName();
		}
		
		return nameIm;
		
	}

	/**
	 * Method that uses the string passed to display the correct image
	 * @param nameIm name of the image in the category
	 */
	public void updateLabel(BufferedImage image) {
		setImage(ImageFunctions.displayImage(image));
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	/**
	 * Method used to close the frame
	 */
	public void closeJframe() {
		getWindowRef().dispose();
	}
	
	/**
	 * get/setters
	 * @return
	 */
	public JPanel getJpIm() {
		return jpIm;
	}

	public void setJpIm(JPanel jpIm) {
		this.jpIm = jpIm;
	}
	
	public JPanel getJpList() {
		return jpList;
	}
	
	public void setJpList(JPanel jpList) {
		this.jpList = jpList;
	}

	public JPanel getJpBut() {
		return jpBut;
	}

	public void setJpBut(JPanel jpBut) {
		this.jpBut = jpBut;
	}

	public JLabel getImage() {
		return image;
	}

	public void setImage(JLabel image) {
		this.image = image;
	}

	public JFrame getWindowRef() {
		return windowRef;
	}

	public void setWindowRef(JFrame ref) {
		this.windowRef = ref;
	}
}
