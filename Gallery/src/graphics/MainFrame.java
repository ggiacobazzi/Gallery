package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import functionalities.PhotoAlbum;


/** This class defines the main window of the application: 
 * It has a container containing the 3 main panels
 * LeftPanel, MiddlePanel, LowerPanel
 * 
 * @author gabbb
 */
public class MainFrame extends JFrame implements MouseListener{

	private static final long serialVersionUID = 1L;
	private JPanel container;
	private LeftPanel p1;
	private MiddlePanel p2;
	private LowerPanel p3;
	private ImagePanel ip;
	private PhotoAlbum album;
	/**
	 * Initialize the MainFrame without a title
	 */
	public MainFrame() {
		this("Placeholder title");
	}

	/**
	 * Initialize the MainFrame and its container
	 * @param title string used for the MainFrame title
	 */
	public MainFrame(String title) {
		// TODO Auto-generated constructor stub
		super(title);
		this.setSize(1200, 650);
		this.setMinimumSize(new Dimension(600, 500));
		this.container = new JPanel();
		this.container.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		this.album = new PhotoAlbum();
		
		/**
		 * Used for resizing purposes (doesn't work)
		 */
		//Component Listener
		this.addComponentListener(new java.awt.event.ComponentAdapter() {
	        public void componentResized(ComponentEvent e) {
	                    p2.revalidate();
	            }
	    });
		
		//Mouse Listener
		container.addMouseListener(this);
		addMouseListener(this);
		
		//Left Panel
		this.p1 = new LeftPanel(java.awt.Color.CYAN);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridheight = 2;
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.container.add(p1, gbc);
		
		//Middle Panel 
		this.p2 = new MiddlePanel(java.awt.Color.PINK);
		this.ip = new ImagePanel(p2);
		JScrollPane jsp = new JScrollPane(ip, 
			ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setMinimumSize(new Dimension(p2.getMinimumSize()));
		jsp.setMaximumSize(new Dimension(p2.getMaximumSize()));
		jsp.setPreferredSize(new Dimension(p2.getPreferredSize()));
		jsp.setVisible(true);
		this.p2.add(jsp);
		SwingUtilities.updateComponentTreeUI(this);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.FIRST_LINE_END;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		this.container.add(p2, gbc);
		
		//Lower Panel
		this.p3 = new LowerPanel(new Color(201, 221, 155), p1, p2, ip, this);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.LAST_LINE_END;
		gbc.weightx = 0.2;
		gbc.weighty = 0.2;
		gbc.gridx = 1;
		gbc.gridy = 1;
		this.container.add(p3, gbc);
		this.add(container);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
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
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
