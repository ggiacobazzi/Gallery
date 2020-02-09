package functionalities;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import basecomponents.Image;
import layout.LowerPanel;

/**
 * Class that creates a window where the user can input an url used to load an image
 * @author gabri
 */
public class AddWeb extends JFrame {
	
	
	private String urlString;
	private JTextField box;
	private LowerPanel lowPanRef;
	private JFrame windowRef;
	private AddImage functionRef;
	private static final long serialVersionUID = 1L;
	
	/**
	 * Class used to create a window when the user tries to add an image from an url
	 * @param lowerPanel Reference to the Lower Panel
	 * @param ref Reference to the Add Image Window
	 */
	public AddWeb(LowerPanel lowerPanel, AddImage ref) {
		super("Add Image from web");
		setLowPanRef(lowerPanel);
		setWindowRef(this);
		setFunctionRef(ref);
		JPanel jPan= new JPanel();
		createWindow(jPan);
		add(jPan);
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter() {
			@Override 
			public void windowClosing(WindowEvent event) {
				if(getBox().getText().isEmpty()) {
					new Status(false, "Immagine non caricata");
				}
			}
		});
		setVisible(true);
		pack();
		
	}
	
	/**
	 * Method used to create the window and to lay out its components
	 * @param jPan Panel that will be added to the Frame
	 */
	public void createWindow(JPanel jPan) {
		setPreferredSize(new Dimension(500, 100));
		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
		setResizable(false);
		JLabel jtf = new JLabel("Insert URL: ");
		jPan.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		jPan.add(jtf);
		box = new JTextField();
		getBox().setPreferredSize(new Dimension(200, 25));
		
		jPan.add(getBox());
		JButton send = new JButton("Enter");
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				String choice = e.getActionCommand();
				if(choice == "Enter") {
					setUrlString(getBox().getText());
					try {
						URL url = new URL(getUrlString());
						String fileName = Paths.get(new URI(getUrlString()).getPath()).getFileName().toString();
						Image newImage = new Image(ImageIO.read(url), fileName);
						
						getFunctionRef().addImagePanel(newImage, getLowPanRef());
					} catch (MalformedURLException e1) {
						new Status(false, "URL non valido");
						closeJframe();
						return;
					} catch (IOException e1) {
						new Status(false, "URL non valido");
						closeJframe();
						return;
					} catch (URISyntaxException e1) {
						new Status(false, "URL non valido");
						closeJframe();
						return;
					}
					new Status(true, "Immagine caricata correttamente da URL");
				}
				else {
					new Status(false, "Immagine non caricata");
				}
				
				closeJframe();
			}
		});
		jPan.add(send);
		pack();
	}

	/**
	 * Method used to close the frame
	 */
	public void closeJframe() {
		getWindowRef().dispose();
	}
	
	/**
	 * Get Url String
	 */
	public String getUrlString() {
		return urlString;
	}

	/**
	 * Set Url String
	 * @param urlString url
	 */
	public void setUrlString(String urlString) {
		this.urlString = urlString;
	}

	/**
	 * Get JTextField box
	 * @return box
	 */
	public JTextField getBox() {
		return box;
	}

	/**
	 * Set JTextField box
	 * @param box box
	 */
	public void setBox(JTextField box) {
		this.box = box;
	}
	
	/**
	 * Get Reference to Lower Panel
	 * @return lower panel
	 */
	public LowerPanel getLowPanRef() {
		return lowPanRef;
	}

	/**
	 * Set Reference to Lower Panel
	 * @param lowPanRef lower panel ref
	 */
	public void setLowPanRef(LowerPanel lowPanRef) {
		this.lowPanRef = lowPanRef;
	}

	/**
	 * Get AddWeb Window Ref
	 * @return addweb window ref
	 */
	public JFrame getWindowRef() {
		return windowRef;
	}

	/**
	 * Set AddWeb Window Ref
	 * @param windowRef addweb window ref 
	 */
	public void setWindowRef(JFrame windowRef) {
		this.windowRef = windowRef;
	}

	/**
	 * Get Reference to AddImage Class (used to use the methods)
	 * @return addimage reference
	 */
	public AddImage getFunctionRef() {
		return functionRef;
	}

	/**
	 * Set Reference to AddImage Class
	 * @param functionRef addimage reference
	 */
	public void setFunctionRef(AddImage functionRef) {
		this.functionRef = functionRef;
	}

}
