package functionalities;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import basecomponents.Category;

/**
 * Class used to create a Slideshow using the current category
 * @author gabri
 */

public class SlideShow extends JFrame{
	
	private JLabel picLabel;
    private Timer timer;
    private Category catRef;
    private int x = 0;
    private static final long serialVersionUID = 1L;
    
    /**
     * Initialize the SlideShow Window
     * @param catRef Reference of the category that will be viewed
     */
    public SlideShow(Category catRef){
        super("Category SlideShow");
        picLabel = new JLabel("", SwingConstants.CENTER);
        setPicLabel(picLabel);
        getPicLabel().setBounds(40, 40, 1000, 600);

        setCatRef(catRef);
        
        //Call The Function SetImageSize
        SetImageSize(0);
        
        //Set a timer
        timer = new Timer(1500, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                SetImageSize(x);
                x += 1;
                if(x >= getCatRef().getDefCat().size())
                    x = 0; 
            }
        });
        getContentPane().add(picLabel, SwingConstants.CENTER);
        timer.start();
        setLayout(new GridBagLayout());
        setSize(1200, 800);
        getContentPane().setBackground(Color.decode("#bdb67b"));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    
    /**
     * Method to resize the image
     * @param index index of the image
     */
    public void SetImageSize(int index){
        ImageIcon icon = new ImageIcon(getCatRef().getDefCat().get(index).getRawImage());
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(picLabel.getWidth(), picLabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newImc = new ImageIcon(newImg);
        picLabel.setIcon(newImc);
    }
    
    /**
     * Get Label containing the image
     * @return label
     */
	public JLabel getPicLabel() {
		return picLabel;
	}
	
	/**
	 * Set Label containing the image
	 * @param picLabel label
	 */
	public void setPicLabel(JLabel picLabel) {
		this.picLabel = picLabel;
	}

	/**
	 * Get Current Category that will be used in the slideshow
	 * @return current category
	 */
	public Category getCatRef() {
		return catRef;
	}

	/**
	 * Set Current Category that will be used in the slideshow
	 * @param catRef current category
	 */
	public void setCatRef(Category catRef) {
		this.catRef = catRef;
	}
}