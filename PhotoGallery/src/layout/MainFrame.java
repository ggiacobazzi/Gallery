package layout;

import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import basecomponents.PhotoAlbum;


/** This class defines the main window of the application: 
 * It has a container that contains the two "cards" that will be swapped while using the application
 * It is defined by a determined Photo Album
 * 
 * @author gabri
 */
public class MainFrame extends JFrame{

	private JPanel containerPan;
	private CardPanel catPan;
	private CardPanel albPan;
	private PhotoAlbum currentAlbum;
	private CardLayout cardLayout = new CardLayout();
	private static final long serialVersionUID = 1L;
	
	/**
	 * Initialize the MainFrame and its container
	 * @param title string used for the MainFrame title
	 * @param photoAlbumRef Reference to the Photo Album
	 */
	public MainFrame(String title, PhotoAlbum photoAlbumRef) {
		super(title);
		createFrame(photoAlbumRef);
		setCurrentAlbum(getAlbPan().getMiddlePanRef().getCurrentAlbum());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		getAlbPan().getMiddlePanRef().displayContents(false);
	}
	
	/**
	 * Method to create the Panel that will contain the two main "cards" of 
	 * the application (Album view / Category view)
	 */
	public void createCardPanel() {
		containerPan = new JPanel();
		containerPan.setLayout(getCardLayout());
		add(getContainerPan());
	}
	
	/**
	 * Method to create the entire Frame with the Album chosen (new or existing one)
	 * @param photoAlbumRef Reference to the photo album
	 */
	public void createFrame(PhotoAlbum photoAlbumRef) {
		setSize(1200, 650);
		setMinimumSize(new Dimension(1100, 500));
		
		//Card Main Panel
		createCardPanel();
		
		//Card Panels
		albPan = new CardPanel(true, this, photoAlbumRef);
		catPan = new CardPanel(false, this, photoAlbumRef);
		
		//Add Cards
		getContainerPan().add(getAlbPan(), getAlbPan().getNamePan());
		getContainerPan().add(getCatPan(), getCatPan().getNamePan());
		
		//Show right Card
		getCardLayout().show(getContainerPan(), getAlbPan().getNamePan());	
	}
	/**
	 * Method used to swap to the panel with the category and its images
	 */
	public void showCatPan() {
		//Swaps to Cat Pan
		getCardLayout().show(getContainerPan(), getCatPan().getNamePan());	
	}
	
	/**
	 * Method used to swap to the panel with all the categories of the album
	 */
	public void showAlbPan() {
		//Swaps to Alb Pan
		getAlbPan().getMiddlePanRef().displayContents(false);
		getCardLayout().show(getContainerPan(), getAlbPan().getNamePan());
		
	}
	
	/**
	 * Get Container Panel
	 * @return container pan
	 */
	public JPanel getContainerPan() {
		return containerPan;
	}
	
	/**
	 * Set Container Panel
	 * @param containerPan container pan
	 */
	public void setContainerPan(JPanel containerPan) {
		this.containerPan = containerPan;
	}
	
	/**
	 * Get CardPanel used to display the contents of a category
	 * @return category card panel
	 */
	public CardPanel getCatPan() {
		return catPan;
	}
	
	/**
	 * Set CardPanel used to display the contents of a category
	 * @param catPan category card panel
	 */
	public void setCatPan(CardPanel catPan) {
		this.catPan = catPan;
	}

	/**
	 * Get CardPanel used to display the contents of a photo album
	 * @return album card panel
	 */
	public CardPanel getAlbPan() {
		return albPan;
	}

	/**
	 * Set CardPanel used to display the contents of a photo album
	 * @param albPan album card panel
	 */
	public void setAlbPan(CardPanel albPan) {
		this.albPan = albPan;
	}

	/**
	 * Get Photo Album
	 * @return photo album
	 */
	public PhotoAlbum getCurrentAlbum() {
		return currentAlbum;
	}

	/**
	 * Set Photo Album
	 * @param currentAlbum photo album
	 */
	public void setCurrentAlbum(PhotoAlbum currentAlbum) {
		this.currentAlbum = currentAlbum;
	}
	
	/**
	 * Get Layout used to lay the CardPanels
	 * @return card layout
	 */
	public CardLayout getCardLayout() {
		return cardLayout;
	}

	/**
	 * Set Layout used to lay the CardPanels
	 * @param cardLayout card layout
	 */
	public void setCardLayout(CardLayout cardLayout) {
		this.cardLayout = cardLayout;
	}
}
