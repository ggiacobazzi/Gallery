package windows;

import java.awt.Dimension;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import graphics.SpringUtilities;

public class NewCategory extends JFrame{

	/**
	 * 
	 */
	private String[] labels = {"Name", "Description", "Password", "Cancel", "Confirm"};
	private int boxnum = labels.length;
	private JTextField box;
	private static final long serialVersionUID = 1L;

	public NewCategory() {
		JFrame jf = new JFrame("Add Category");
		jf.setSize(new Dimension(700, 300));
		jf.setResizable(false);
		JPanel jp = new JPanel();
		jf.add(jp);
		//jp.setLayout(new SpringLayout());
		System.out.println("dim spring: " + boxnum);
		jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
		
		
		
		for (int i = 0; i < boxnum; i++) {
			
			if(labels[i].equals("Password")) {
				JLabel l = new JLabel("", JLabel.LEADING);
				jp.add(l);
				JCheckBox isProtected = new JCheckBox("Protetta");
				l.setLabelFor(isProtected);
				jp.add(isProtected);
			}
			
			if(labels[i].equals("Cancel")) {
				JLabel l = new JLabel("", JLabel.LEADING);
				jp.add(l);
				JButton jb = new JButton(labels[i]);
				System.out.println("qua");
				l.setLabelFor(jb);
				jp.add(jb);
				break;
			}
			
			if(labels[i].equals("Confirm")) {
				JLabel l1 = new JLabel("", JLabel.LEADING);
				jp.add(l1);
				JButton jb1 = new JButton(labels[i]);
				System.out.println("qui");
				l1.setLabelFor(jb1);
				jp.add(jb1);
				break;
			}
			
		    JLabel l = new JLabel(labels[i], JLabel.TRAILING);
		    jp.add(l);
		    JTextField textField = new JTextField(25);
		    l.setLabelFor(textField);
		    jp.add(textField);
		}
//		
//		//Lay out the panel.
//		SpringUtilities.makeCompactGrid(jp,
//		                                boxnum+1, 2, //rows, cols
//		                                6, 6,        //initX, initY
//		                                6, 6);       //xPad, yPad
		
		jf.setLocationRelativeTo(null);
		jf.dispatchEvent(new WindowEvent(jf, WindowEvent.WINDOW_CLOSING));
		//jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setVisible(true);
		jf.pack();
	}
}
