package layout;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import basecomponents.Category;
import basecomponents.CategoryProtected;

/**
 * Class that describes the left panel of the album
 * It displays the metadata of the Category/Image clicked
 * @author gabri
 *
 */
public class LeftPanel extends JPanel{

	private JPanel contPan;
	private static final long serialVersionUID = 1L;

	/**
	 * Initialize a Left Panel
	 */
	public LeftPanel() {
		createPanel();
	}
	
	/**
	 * Method used to create the Panel and lay out its components
	 */
	public void createPanel() {
		setBackground(Color.LIGHT_GRAY);
		setPreferredSize(new Dimension(400,400));
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		setMinimumSize(new Dimension(400,400));
	}
	
	/**
	 * Method used to display metadata of the category
	 * @param cat Reference to the category clicked
	 */
	public void displayInfos(Category cat) {
		if(contPan != null)
			removeInfos();
		contPan = new JPanel();
		contPan.setBackground(Color.LIGHT_GRAY);
		contPan.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		contPan.setLayout(new BoxLayout(contPan, BoxLayout.Y_AXIS));
		
		JLabel jlName = new JLabel("Name: " + cat.getName() + "\n");
		jlName.setBackground(Color.LIGHT_GRAY);
		contPan.add(jlName);
		JLabel jlDesc = new JLabel("Description: " + cat.getDescription() + "\n");
		jlDesc.setBackground(Color.LIGHT_GRAY);
		contPan.add(jlDesc);
		JLabel jlDate = new JLabel("Date of creation: " + cat.getDateOfCreation() + "\n");
		jlDate.setBackground(Color.LIGHT_GRAY);
		contPan.add(jlDate);
		JLabel jlDim = new JLabel("Total Photos: " + cat.getDefCat().size() + "\n");
		jlDim.setBackground(Color.LIGHT_GRAY);
		contPan.add(jlDim);
		JLabel jlProtStat = new JLabel("Protected: " + (cat instanceof CategoryProtected) + "\n");
		jlProtStat.setBackground(Color.LIGHT_GRAY);
		contPan.add(jlProtStat);
		
		add(contPan);
		SwingUtilities.updateComponentTreeUI(this);
		
	}
	
	/**
	 * Method to remove old informations from the panel
	 */
	public void removeInfos() {
		remove(getJp());
	}
	
	/**
	 * Set Panel that will contain category info
	 * @param jP panel
	 */
	
	public void setJp(JPanel jP) {
		this.contPan = jP;
	}
	
	/**
	 * Get Panel that will contain category info
	 * @return panel
	 */
	public JPanel getJp() {
		return contPan;
	}
}

