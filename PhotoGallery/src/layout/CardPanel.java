package layout;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import basecomponents.PhotoAlbum;

/**
 * This class initializes a Panel that will be used as a card for the Main Frame
 * @author gabri
 */
public class CardPanel extends JPanel{

	private String namePan;
	private MainFrame parentFrame;
	private LeftPanel leftPanRef;
	private MiddlePanel middlePanRef;
	private LowerPanel lowPanRef;
	private JScrollPane scrollablePan;
	private GridBagConstraints leftPanConst;
	private GridBagConstraints middlePanConst;
	private GridBagConstraints lowPanConst;
	private static final long serialVersionUID = 1L;

	/**
	 * Initialize a Card Panel
	 * @param value Boolean value used to create the right Lower Panel (Album/Category Mode)
	 * @param parentFrame Reference to the Parent
	 * @param photoAlbumRef Reference to the Photo Album
	 */
	public CardPanel(Boolean value, MainFrame parentFrame, PhotoAlbum photoAlbumRef) {
		setParentFrame(parentFrame);
		createPan(value, photoAlbumRef);
	}
	
	/**
	 * Method used to create the Panel and lay out its components
	 * @param value Boolean value used to create the right Lower Panel (Album buttons/Category Buttons)
	 * @param photoAlbumRef Reference to the Photo Album
	 */
	public void createPan(Boolean value, PhotoAlbum photoAlbumRef) {
		setSize(1200, 650);
		setMinimumSize(new Dimension(1100, 500));
		setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();

		//LeftPanel
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridheight = 2;
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 0;
		setLeftPanConst(gbc);
		createLeftPan(getLeftPanConst());

		//MiddlePanel
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.FIRST_LINE_END;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		setMiddlePanConst(gbc);
		createMiddlePan(getMiddlePanConst(), photoAlbumRef);

		//LowerPanel
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.LAST_LINE_END;
		gbc.weightx = 0.2;
		gbc.weighty = 0.2;
		gbc.gridx = 1;
		gbc.gridy = 1;
		setLowPanConst(gbc);

		if(value)
			createLowPan(getLowPanConst(), value);
		else
			createLowPan(getLowPanConst(), value);

		getMiddlePanRef().setLowPanRef(getLowPanRef());
		getMiddlePanRef().setLeftPanRef(getLeftPanRef());

		if(value) 
			setNamePan("ALBPAN");
		else 
			setNamePan("CATPAN");
	}
	
	/**
	 * Method used to create the Left Panel with a set of constraints
	 * @param gbc Constraints
	 */
	public void createLeftPan(GridBagConstraints gbc){
		leftPanRef = new LeftPanel();
		leftPanRef.setVisible(true);
		add(getLeftPanRef(), gbc);
	}
	
	/**
	 * Method used to create the right Lower Panel with a set of constraints 
	 * @param gbc Constraints
	 * @param value Boolean value used to create the right Lower Panel (Album mode/Category Mode)
	 */
	public void createLowPan(GridBagConstraints gbc, Boolean value){
		lowPanRef = new LowerPanel(getMiddlePanRef(), value);
		lowPanRef.setVisible(true);
		add(getLowPanRef(), gbc);
	}

	/**
	 * Method used to create the Middle Panel with a set of constraints
	 * @param gbc Constraints
	 * @param photoAlbumRef Reference to the Photo Album
	 */
	public void createMiddlePan(GridBagConstraints gbc, PhotoAlbum photoAlbumRef) {
		//Middle Panel 
		middlePanRef = new MiddlePanel(getParentFrame(), getLeftPanRef(), getLowPanRef(), photoAlbumRef);
		setMiddlePanRef(middlePanRef);
		
		//Panel used as a container for the MiddlePanel
		JPanel jPan = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));
		jPan.add(getMiddlePanRef());
		jPan.setPreferredSize(getMiddlePanRef().getPreferredSize());
		scrollablePan = new JScrollPane(
				jPan, 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				 JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollablePan.setBorder(null);
		setScrollablePan(scrollablePan);
		add(getScrollablePan(), gbc);
	}
	
	/**
	 * Get Name of the Card Panel
	 * @return name
	 */
	public String getNamePan() {
		return namePan;
	}

	/**
	 * Set Name of the Card Panel
	 * @param namePan name
	 */
	public void setNamePan(String namePan) {
		this.namePan = namePan;
	}

	/**
	 * Get Parent Main Frame
	 * @return main frame
	 */
	public MainFrame getParentFrame() {
		return parentFrame;
	}

	/**
	 * Set Parent Main Frame
	 * @param parentFrame main frame
	 */
	public void setParentFrame(MainFrame parentFrame) {
		this.parentFrame = parentFrame;
	}

	/**
	 * Get Left Panel reference
	 * @return left panel
	 */
	public LeftPanel getLeftPanRef() {
		return leftPanRef;
	}
	
	/**
	 * Set Left Panel reference
	 * @param leftPanRef left panel
	 */
	public void setLeftPanRef(LeftPanel leftPanRef) {
		this.leftPanRef = leftPanRef;
	}
	
	/**
	 * Get Middle Panel reference
	 * @return middle panel
	 */
	public MiddlePanel getMiddlePanRef() {
		return middlePanRef;
	}
	
	/**
	 * Set Middle Panel reference
	 * @param middlePanRef middle panel
	 */
	public void setMiddlePanRef(MiddlePanel middlePanRef) {
		this.middlePanRef = middlePanRef;
	}

	/**
	 * Get Lower Panel reference
	 * @return lower panel
	 */
	public LowerPanel getLowPanRef() {
		return this.lowPanRef;
	}
	
	/**
	 * Set Lower Panel reference
	 * @param lowPanRef lower panel
	 */
	public void setLowPanRef(LowerPanel lowPanRef) {
		this.lowPanRef = lowPanRef;
	}
	
	/**
	 * Get JScrollPane
	 * @return jscrollpane
	 */
	public JScrollPane getScrollablePan() {
		return scrollablePan;
	}
	
	/**
	 * Set JScrollPane
	 * @param scrollablePan jscrollpane
	 */
	public void setScrollablePan(JScrollPane scrollablePan) {
		this.scrollablePan = scrollablePan;
	}
	
	/**
	 * Get Left Panel Constraints used to lay it out correctly
	 * @return constraints
	 */
	public GridBagConstraints getLeftPanConst() {
		return leftPanConst;
	}

	/**
	 * Set Left Panel Constraints used to lay it out correctly
	 * @param leftPanConst constraints
	 */
	public void setLeftPanConst(GridBagConstraints leftPanConst) {
		this.leftPanConst = leftPanConst;
	}

	/**
	 * Get Middle Panel Constraints used to lay it out correctly
	 * @return constraints
	 */
	public GridBagConstraints getMiddlePanConst() {
		return middlePanConst;
	}

	/**
	 * Set Middle Panel Constraints used to lay it out correctly
	 * @param middlePanConst constraints
	 */
	public void setMiddlePanConst(GridBagConstraints middlePanConst) {
		this.middlePanConst = middlePanConst;
	}

	/**
	 * Get Lower Panel Constraints used to lay it out correctly
	 * @return constraints
	 */
	public GridBagConstraints getLowPanConst() {
		return lowPanConst;
	}

	/**
	 * Set Lower Panel Constraints used to lay it out correctly
	 * @param lowPanConst constraints
	 */
	public void setLowPanConst(GridBagConstraints lowPanConst) {
		this.lowPanConst = lowPanConst;
	}
}
