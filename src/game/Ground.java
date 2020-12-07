package game;

import java.awt.*;
import javax.swing.*;

public class Ground extends JLayeredPane{
	public Ground(int width, int height){
		this.setSize(width, height);
		
		initializeFisherman();
		this.setVisible(true);
	}
	
	private void initializeFisherman(){
		final Point fishermanStartLoca = new Point(this.getSize().width / 2, this.getSize().height / 4 * 3);
		
		Fisherman fisherman = new Fisherman(fishermanStartLoca);
		this.add(fisherman);
		this.setLayer(fisherman, JLayeredPane.MODAL_LAYER);
	}
}
