package functionalities;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import basecomponents.PhotoAlbum;
import layout.MainFrame;

/**
 * Class used to Load and Save a PhotoAlbum
 * @author gabri
 */
public class LoadSave {

	private JFrame loadSaveWinRef;
	
	//Constructor for load
	/**
	 * Constructor used to Load the Album
	 */
	public LoadSave() {
		JFrame loadWindow = new JFrame("Initialize Photo Album");
		loadWindow.setPreferredSize(new Dimension(300, 110));
		loadWindow.setLocationRelativeTo(null);
		setLoadSaveWinRef(loadWindow);
		loadWindow.getContentPane().setLayout(new BoxLayout(loadWindow.getContentPane(), BoxLayout.Y_AXIS));
		JPanel contPan = new JPanel();
		createWindow(contPan);
		loadWindow.add(contPan);
		loadWindow.setVisible(true);
		loadWindow.pack();
	}
	
	//Constructor for save
	/**
	 * Constructor used to Save the Album
	 * @param photoAlbumRef Reference to the Photo Album to save
	 */
	public LoadSave(PhotoAlbum photoAlbumRef) {
		saveAlbum(photoAlbumRef);
	}
	
	/**
	 * Method used to create the Window and lay out its components
	 * @param contPan Reference to the Panel that will be added
	 */
	public void createWindow(JPanel contPan) {
		//Text
		JLabel textLab = new JLabel("Do you want to load an existing album?");
		
		//Buttons
		JPanel buttonsPan = new JPanel();
		JButton confirmBt = new JButton("Yes");
		confirmBt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = createFileChooser();
				
				int retrieval = chooser.showSaveDialog(null);

				if (retrieval == JFileChooser.APPROVE_OPTION) {
					String path = chooser.getSelectedFile().getAbsolutePath();
					try {
						FileInputStream fileIn = new FileInputStream(path);
						ObjectInputStream in = new ObjectInputStream(fileIn);
						PhotoAlbum inputAlbum = (PhotoAlbum) in.readObject();
						in.close();
						fileIn.close();
						new MainFrame(chooser.getSelectedFile().getName(), inputAlbum);
						closeJframe();
					} catch (Exception ex) {
						new Status(false, "Album not loaded correctly!");
					}
			}	
		}});
				
		
		buttonsPan.add(confirmBt);
		
		buttonsPan.add(Box.createRigidArea(new Dimension(50, 50)));
		
		JButton cancelBt = new JButton("No");
		cancelBt.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				 new MainFrame("NewPhotoAlbum", new PhotoAlbum());
				 closeJframe();
			}
		});
		
		buttonsPan.add(cancelBt);
		
		contPan.add(textLab);
		contPan.add(buttonsPan);
	}
	/**
	 * Method to save a PhotoAlbum as a .album file
	 * @param photoAlbumRef
	 */
	public void saveAlbum(PhotoAlbum photoAlbumRef) {
		JFileChooser chooser = createFileChooser();			
		int retrieval = chooser.showSaveDialog(null);

		if (retrieval == JFileChooser.APPROVE_OPTION) {
			try {
					FileOutputStream fOut = new FileOutputStream(chooser.getSelectedFile()+ ".album");
					ObjectOutputStream objOut = new ObjectOutputStream(fOut);
					objOut.writeObject(photoAlbumRef);
					objOut.close();
					new Status(true, "PhotoAlbum " + chooser.getSelectedFile().getName() + " has been saved!");
		        } catch (Exception ex) {
		        	new Status(false, "Something went wrong!");
		        }
		    }
	}
	
	/**
	 * Method to create a JFileChooser with a default filter
	 * @return The created JFileChooser
	 */
	public JFileChooser createFileChooser() {
		JFileChooser chooser = new JFileChooser(); 
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"PhotoAlbum", "album");
		chooser.setFileFilter(filter);
		chooser.setAcceptAllFileFilterUsed(false);
		
		return chooser;
	}

	/**
	 * Method to close the JFrame
	 */
	public void closeJframe() {
		getLoadSaveWinRef().dispose();
	}
	
	/**
	 * Get LoadSave Window Ref
	 * @return loadsave window
	 */
	public JFrame getLoadSaveWinRef() {
		return loadSaveWinRef;
	}

	/**
	 * Set LoadSave Window Ref
	 * @param loadSaveWinRef loadsave window ref
	 */
	public void setLoadSaveWinRef(JFrame loadSaveWinRef) {
		this.loadSaveWinRef = loadSaveWinRef;
	}
}
