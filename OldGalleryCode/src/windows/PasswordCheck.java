package windows;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import functionalities.CategoryProtected;
import functionalities.ImageFunctions;

public class PasswordCheck extends JFrame{

	/**
	 * Window opened when trying to access a protected category. It needs an input password in order to
	 * check if it is the correct one
	 */
	private String iconPath = "defaults" + File.separator + "lock-icon.png";
	private JTextField passField;
	private JButton cancelBt, confirmBt;
	private static final long serialVersionUID = 1L;
	public PasswordCheck(CategoryProtected cat) {
		super("Input password");
		this.setSize(new Dimension(700, 300));
		this.setResizable(false);
		this.setLayout(new FlowLayout());
		JPanel jp = new JPanel();
		jp.setPreferredSize(this.getPreferredSize());
		this.add(jp);
		createWindow(jp);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}

	public void createWindow(JPanel jp) {
		System.out.println(getIconPath());
		jp.add(ImageFunctions.chooseImageForIcon(getIconPath()));
		SwingUtilities.updateComponentTreeUI(jp);
		System.out.println("oof");
		passField = new JTextField(25);
		System.out.println(getPassField());
		jp.add(getPassField());
		
	}
	
	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	public JTextField getPassField() {
		return passField;
	}

	public void setPassField(JTextField passField) {
		this.passField = passField;
	}

	public JButton getCancelBt() {
		return cancelBt;
	}

	public void setCancelBt(JButton cancelBt) {
		this.cancelBt = cancelBt;
	}

	public JButton getConfirmBt() {
		return confirmBt;
	}

	public void setConfirmBt(JButton confirmBt) {
		this.confirmBt = confirmBt;
	}
}
