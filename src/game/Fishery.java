package game;

import java.awt.*;
import javax.swing.*;

public class Fishery extends JLayeredPane{	
	public Fishery(int width, int height){
		this.setSize(width, height);
		
		fishControllerSetting();
		this.setVisible(true);
	}
	
	private void fishControllerSetting(){
		Thread fishController = new FishController(this);
		fishController.start();
	}
}
