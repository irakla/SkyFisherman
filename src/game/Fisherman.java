package game;

import java.awt.*;
import javax.swing.*;

public class Fisherman extends JLabel{
	private FishermanImage fishermanImage = new FishermanImage();
	
	public Fisherman(Point startLoca){
		this.setIcon(new ImageIcon(fishermanImage.getDisplayingFishermanImage()));
		this.setLocation(startLoca);
		this.setSize(fishermanImage.getDisplayingFishermanImage().getWidth(null),
				fishermanImage.getDisplayingFishermanImage().getHeight(null));
	}
	
	public void Fisherman(){
		
	}
}
