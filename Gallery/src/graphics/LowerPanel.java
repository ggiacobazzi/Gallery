package graphics;

import functionalities.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Panel that contains the main buttons for the application
 * @author gabbb
 */
public class LowerPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	/**
	 * Buttons of the application
	 * @param next go to next image, if able
	 * @param last go to previous image, if able
	 * @param add add a category
	 * @param remove remove a category
	 */
	private JButton next, addweb, add, remove;
	private MiddlePanel p2;
	private ImagePanel ip;
	public LowerPanel(Color c, LeftPanel p1, MiddlePanel p2, ImagePanel ip, JFrame jf) {
		this.setMinimumSize(new Dimension(600,100));
		this.p2 = p2;
		this.ip = ip;
		this.CreatePanel(c);
	}
	
	private void CreatePanel(Color c) {
		this.setBackground(c);
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(6, 6, 6, 6);
		
		this.add((addweb = new JButton("Add from url")), gbc);
		addweb.setSize(new Dimension(700, 400));
		addweb.setPreferredSize(new Dimension(700, 400));
		addweb.addActionListener(this);
		gbc.gridx++;
		this.add((add = new JButton("Add")), gbc);
		add.setPreferredSize(new Dimension(400, 400));
		add.addActionListener(this);
		gbc.gridx++;
		this.add((remove = new JButton("Remove")), gbc);
		remove.setPreferredSize(new Dimension(400, 400));
		gbc.gridx++;
		this.add((next = new JButton("Next")), gbc);
		next.setPreferredSize(new Dimension(400, 400));
	}
	
	/**
	 * 
	 * 
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == add) {
			ip.add(Image.loadImage(false));
			//ip.add(picLabel);
			SwingUtilities.updateComponentTreeUI(ip);
			SwingUtilities.updateComponentTreeUI(p2);
			System.out.println("ciao");
			//ip.revalidate();
		}
		else if(e.getSource() == addweb) {
			ip.add(Image.loadImage(true));
			SwingUtilities.updateComponentTreeUI(ip);
			ip.revalidate();
		}
		
		else if(e.getSource() == remove) {
			
		}
		else if(e.getSource() == next) {
			
		}
	}
	
}
