package layout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import basecomponents.Image;
import functionalities.FullScreenView;

/**
 * Class that creates an Image Panel
 * @author gabri
 *
 */
public class ImagePanel extends JPanel implements MouseListener{

	private JLabel imageIcon;
	private JLabel nameLabel;
	private MiddlePanel middlePanRef;
	private static final long serialVersionUID = 1L;

	/**
	 * Initialize an Image Panel
	 * @param image Image of the Panel
	 * @param name Name of the Image
	 * @param middlePanRef Reference to the Panel where the ImagePanel is stored
	 */
	public ImagePanel(BufferedImage image, String name, MiddlePanel middlePanRef) {
		setPreferredSize(new Dimension(200, 200));
		setBackground(Color.PINK);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setMiddlePanRef(middlePanRef);
		createPanel(image, name);
		addMouseListener(this);
	}

	/**
	 * Method used to create the Panel with the correct data
	 * @param image Image that will be displayed
	 * @param name Name of the image that will be displayed
	 */
	public void createPanel(BufferedImage image, String name) {
		//Icon
		setImageIcon(displayImage(image));
		getImageIcon().setAlignmentX(Component.CENTER_ALIGNMENT);
		add(getImageIcon());
		
		//Name
		JLabel label = new JLabel(name, JLabel.CENTER);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		setNameLabel(label);
		add(getNameLabel());
	}
	
	/**
	 * Method used to display the image
	 * @param image Image that will be displayed
	 * @return Label with the loaded image
	 */
	public static JLabel displayImage(BufferedImage image) {
		JLabel picLabel = new JLabel();
		picLabel.setSize(160, 108);
		ImageIcon img = new ImageIcon(image.getScaledInstance(160, 108, java.awt.Image.SCALE_SMOOTH));
		picLabel.setIcon(img);
		picLabel.setVisible(true);
		return picLabel;
	}
	
	/**
	 * Method used to retrieve the Index of the Image opened
	 * @return index
	 */
	public int retrieveIndex() {
		int pos = 0;
		for(Image img: getMiddlePanRef().getCurrentCategory().getDefCat()) {
			if(this.getNameLabel().getText().equals(img.getName())) {
				return pos;
			}
			pos++;
		}
		return -1;
	}
	/**
	 * Get Image Label of the Panel
	 * @return image label
	 */
	public JLabel getImageIcon() {
		return imageIcon;
	}

	/**
	 * Set Image Label of the Panel
	 * @param imageIcon image label
	 */
	public void setImageIcon(JLabel imageIcon) {
		this.imageIcon = imageIcon;
	}

	/**
	 * Get Name Label of the Panel
	 * @return name label
	 */
	public JLabel getNameLabel() {
		return nameLabel;
	}

	/**
	 * Set Name Label of the Panel
	 * @param nameLabel name label
	 */
	public void setNameLabel(JLabel nameLabel) {
		this.nameLabel = nameLabel;
	}

	/**
	 * Get Middle Panel Ref
	 * @return middle panel
	 */
	public MiddlePanel getMiddlePanRef() {
		return middlePanRef;
	}

	/**
	 * Set Middle Panel Ref
	 * @param middlePanRef middle panel
	 */
	public void setMiddlePanRef(MiddlePanel middlePanRef) {
		this.middlePanRef = middlePanRef;
	}
	
	/**
	 * MouseListener that opens the image clicked in a new Window
	 */
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getClickCount() == 2) {
			new FullScreenView(getMiddlePanRef().getCurrentCategory(), retrieveIndex());
		}
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
