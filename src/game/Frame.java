package game;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class Frame extends JFrame{
	public static void main(String[] args) {
		Frame game = new Frame();
	}
	
	Frame(){
		initializeFrame();
		initializeEvent();
		
		startGame();
	}
	
	private final String GameTitle = "SkyFisherman!";
	private final String IconPath = "Icon.jpg";
	private final Dimension EarlyFrameSize = new Dimension(400, 700);
	private final Point CenterLocation = new Point(
			Toolkit.getDefaultToolkit().getScreenSize().width / 2 - EarlyFrameSize.width / 2,
			Toolkit.getDefaultToolkit().getScreenSize().height / 2 - EarlyFrameSize.height / 2);
	
	private void initializeFrame(){
		this.setLayout(null);
		this.setTitle(GameTitle);
		this.setSize(EarlyFrameSize);
		this.setLocation(CenterLocation);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(IconPath));
		this.setResizable(false);
	}
	
	private void initializeEvent(){
		this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}
	
	private void startGame(){
		this.add(new Game(this));
		this.setVisible(true);
	}
}