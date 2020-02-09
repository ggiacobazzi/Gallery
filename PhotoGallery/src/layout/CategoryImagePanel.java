package layout;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import basecomponents.Category;

/**
 * This class defines the Image Panels used to display the Categories.
 * It has a reference to the right category in order to access it.
 * @author gabri
 */

public class CategoryImagePanel extends ImagePanel {

	private Category categoryRef;
	private CategoryImagePanel catPanRef;
	private static final long serialVersionUID = 1L;

	/**
	 * Initialize an Image Panel
	 * @param image Image that will be displayed
	 * @param name Name of the image that will be displayed beneath it
	 * @param catRef Reference to the category
	 */
	public CategoryImagePanel(BufferedImage image, String name, Category catRef, MiddlePanel middlePanRef) {
		super(image, name, middlePanRef);
		setCategoryRef(catRef);
		setCatPanRef(this);
		addMouseListener(this);
	}

	/**
	 * Method used to access the Category of the clicked Category Image Panel
	 */
	public void accessCategory() {
		getMiddlePanRef().accessCategory(getCategoryRef());
	}
	
	/**
	 * Get Category Reference
	 * @return category ref 
	 */
	public Category getCategoryRef() {
		return categoryRef;
	}

	/**
	 * Set Category Reference 
	 * @param categoryRef category ref
	 */
	public void setCategoryRef(Category categoryRef) {
		this.categoryRef = categoryRef;
	}

	/**
	 * Get CategoryImagePanel self-reference
	 * @return categoryimagepanel self-ref
	 */
	public CategoryImagePanel getCatPanRef() {
		return catPanRef;
	}

	/**
	 * Set CategoryImagePanel self-reference
	 * @param catPanRef categoryimagepanel self-ref
	 */
	public void setCatPanRef(CategoryImagePanel catPanRef) {
		this.catPanRef = catPanRef;
	}
	
	/**
	 * MouseListener used to access the category
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		getMiddlePanRef().getLeftPanRef().displayInfos(getCategoryRef());
		
		if(arg0.getClickCount() == 2) {
			getCategoryRef().accessCat(getCatPanRef());
		}
		
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
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
