package listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import functionalities.Category;
import functionalities.Image;

public class MouseListeners implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() instanceof Category) {
			//sendInfos();
			//Category.getRef().getLp().DisplayInfos(Category.getSelf(), null, true);
			if(arg0.getClickCount() == 2) {
				
			}
		}
		else if(arg0.getSource() instanceof Image) {
			
			
		}
		
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
