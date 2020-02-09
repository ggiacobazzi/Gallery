package layout;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import basecomponents.Category;
import basecomponents.CategoryProtected;
import basecomponents.PhotoAlbum;
import functionalities.NewCategory;

/**
 * Main panel that contains the categories and can be used to view the images of those as well
 * @author gabri
 */
public class MiddlePanel extends JPanel implements MouseListener {

	private PhotoAlbum currentAlbum;
	private Category currentCategory;
	private ArrayList<ImagePanel> panelList;
	private LeftPanel leftPanRef;
	private MainFrame parent;
	private LowerPanel lowPanRef;
	private static final long serialVersionUID = 1L;
	
	/**
	 * Initialize a Middle Panel
	 * @param mf Reference to the Main Frame
	 * @param leftPanRef Reference to the Left Panel
	 * @param lowPanRef Reference to the Lower Panel
	 * @param photoAlbumRef Reference to the Photo Album
	 */
	public MiddlePanel(MainFrame mf, LeftPanel leftPanRef, LowerPanel lowPanRef, PhotoAlbum photoAlbumRef) {
		setCurrentAlbum(photoAlbumRef);
		setParent(mf);
		setLowPanRef(lowPanRef);
		setLeftPanRef(leftPanRef);
		createPanel();
		ArrayList<ImagePanel> panelList = new ArrayList<ImagePanel>();
		setPanelList(panelList);
	}
	
	/**
	 * Method used to create the panel
	 */
	public void createPanel() {
		setBackground(Color.PINK);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		setLayout(new WrapLayout(WrapLayout.LEFT, 20,20));
		setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	}
	
	/**
	 * Method used to display a single ImagePanel
	 * @param imPan ImagePanel that will be displayed
	 */
	public void displayImagePanel(ImagePanel imPan) {
		getPanelList().add(imPan);
		add(imPan);
		SwingUtilities.updateComponentTreeUI(this);	
	}
	
	/**
	 * Method used to display all the contents. If a category has more than one image it will have a custom icon.
	 * If the category is protected it will have a default icon instead.
	 */
	public void displayContents(boolean value) {
		//Clear Panel
		getLowPanRef().getMiddlePanRef().removeAll();
		getLowPanRef().getMiddlePanRef().revalidate();
		getLowPanRef().getMiddlePanRef().repaint();
		
		//Display images
		if(value) {
			//Clear old panel
			getParent().getCatPan().getMiddlePanRef().getPanelList().clear();
			for(int i = 0; i < getCurrentCategory().getDefCat().size(); i++) {
				ImagePanel newImage = new ImagePanel(getCurrentCategory().getDefCat().get(i).getRawImage(), getCurrentCategory().getDefCat().get(i).getName(), this);
				newImage.setMiddlePanRef(this);
				getParent().getCatPan().getMiddlePanRef().displayImagePanel(newImage);
			}
		}
		//Display categories
		else {
			//Clear old panel
			getParent().getAlbPan().getMiddlePanRef().getPanelList().clear();
			for(int i = 0; i < getCurrentAlbum().getCategoryList().size(); i++) {
				//Protected Category
				if(getCurrentAlbum().getCategoryList().get(i) instanceof CategoryProtected) {
					NewCategory fxCat = new NewCategory(this, false);
					ImagePanel newImage = new CategoryImagePanel(fxCat.createIcon(fxCat.getDefaultProtCatIcon()), getCurrentAlbum().getCategoryList().get(i).getName(),
							getCurrentAlbum().getCategoryList().get(i), this);
					newImage.setMiddlePanRef(this);
					getParent().getAlbPan().getMiddlePanRef().displayImagePanel(newImage);
				}
				//Non protected Category
				else {
					//Size > 0 then choose a random icon
					if(getCurrentAlbum().getCategoryList().get(i).getDefCat().size() != 0) {
						int rand = chooseNewIcon(getCurrentAlbum().getCategoryList().get(i));
						ImagePanel newImage = new CategoryImagePanel(getCurrentAlbum().getCategoryList().get(i).getDefCat().get(rand).getRawImage(), getCurrentAlbum().getCategoryList().get(i).getName(), 
								getCurrentAlbum().getCategoryList().get(i), this);
						newImage.setMiddlePanRef(this);
						getParent().getAlbPan().getMiddlePanRef().displayImagePanel(newImage);			
					}
					//Size == 0 then choose a default icon
					else {
						NewCategory fxCat = new NewCategory(this, false);
						ImagePanel newImage = new CategoryImagePanel(fxCat.createIcon(fxCat.getDefaultCatIcon()), getCurrentAlbum().getCategoryList().get(i).getName(),
								getCurrentAlbum().getCategoryList().get(i), this);
						newImage.setMiddlePanRef(this);
						getParent().getAlbPan().getMiddlePanRef().displayImagePanel(newImage);
					}
				}
				SwingUtilities.updateComponentTreeUI(this);
			}
		}
	}
	
	/**
	 * Method used to access a category
	 * @param catRef Category that will be accessed
	 */
	public void accessCategory(Category catRef) {
		//Clear Panel
		getParent().getCatPan().getMiddlePanRef().removeAll();
	
		//Copy references to the new panels
		getParent().getAlbPan().getMiddlePanRef().setCurrentCategory(catRef);
		getParent().getCatPan().getMiddlePanRef().setCurrentCategory(catRef);
		
		//Display category contents
		displayContents(true);
		
		getParent().showCatPan();
	}
	
	/**
	 * Method used to choose randomly an image
	 * @param catRef Category reference used to have a range of numbers
	 * @return randnum The generated random number
	 */
	public int chooseNewIcon(Category catRef) {
		Random random = new Random();
		int	randnum = random.nextInt(catRef.getDefCat().size());
		return randnum;
	}
	
	/**
	 * Get Current Photo Album
	 * @return photo album
	 */
	public PhotoAlbum getCurrentAlbum() {
		return currentAlbum;
	}

	/**
	 * Set Current Photo Album
	 * @param currentAlbum photo album
	 */
	public void setCurrentAlbum(PhotoAlbum currentAlbum) {
		this.currentAlbum = currentAlbum;
	}

	/**
	 * Get Current Category
	 * @return category
	 */
	public Category getCurrentCategory() {
		return currentCategory;
	}

	/**
	 * Set Current Category
	 * @param currentCategory category
	 */
	public void setCurrentCategory(Category currentCategory) {
		this.currentCategory = currentCategory;
	}

	/**
	 * Get Panel List of Image Panels displayed in the Panel
	 * @return list
	 */
	public ArrayList<ImagePanel> getPanelList() {
		return panelList;
	}

	/**
	 * Set Panel List of Image Panels displayed in the Panel
	 * @param panelList list
	 */
	public void setPanelList(ArrayList<ImagePanel> panelList) {
		this.panelList = panelList;
	}

	/**
	 * Get Left Panel Ref
	 * @return left pan
	 */
	public LeftPanel getLeftPanRef() {
		return leftPanRef;
	}

	/**
	 * Set Left Panel Ref
	 * @param leftPanRef left pan
	 */
	public void setLeftPanRef(LeftPanel leftPanRef) {
		this.leftPanRef = leftPanRef;
	}

	/**
	 * Get Parent ref
	 * @return main frame
	 */
	public MainFrame getParent() {
		return parent;
	}

	/**
	 * Set Parent ref
	 * @param parent main frame
	 */
	public void setParent(MainFrame parent) {
		this.parent = parent;
	}

	/**
	 * Get Lower Panel Ref
	 * @return lower panel
	 */
	public LowerPanel getLowPanRef() {
		return lowPanRef;
	}

	/**
	 * Set Lower Panel ref
	 * @param lowPanRef lower panel
	 */
	public void setLowPanRef(LowerPanel lowPanRef) {
		this.lowPanRef = lowPanRef;
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
