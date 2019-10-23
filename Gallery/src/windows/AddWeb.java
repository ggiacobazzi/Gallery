package windows;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddWeb implements ActionListener {

	private String url;
	private JPanel p;
	public AddWeb() {
		JFrame jf = new JFrame("Add Image from web");
		jf.setSize(new Dimension(500, 100));
		jf.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
		jf.setResizable(false);
		this.p = new JPanel();
		JPanel jp = new JPanel();
		jf.add(jp);
		JLabel jtf = new JLabel("Insert URL: ");
		jp.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		jp.add(jtf);
		JTextField box = new JTextField();
		box.setPreferredSize(new Dimension(200, 25));
		jp.add(box);
		JButton send = new JButton("Enter");
		send.addActionListener(this);
		jp.add(send);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setVisible(true);
		jf.pack();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//this.url = p.
		//System.out.println("Ciao");
		
	}

}
