package game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Game extends JLayeredPane{
	private int gameFrameWidth;
	
	Game(JFrame gameFrame){
		initialize(gameFrame);
	}
	
	private void initialize(JFrame gameFrame){
		initializeExternalAttribute(gameFrame);
		initializeSelf(gameFrame);
		initializeInternalAttribute();
		
		initializePanes();
	}
	
	private void initializeExternalAttribute(JFrame gameFrame){
		this.gameFrameWidth = gameFrame.getSize().width;
	}
	
	private void initializeSelf(JFrame gameFrame){
		this.setLayout(null);
		this.setSize(gameFrame.getSize());
		this.setLocation(0, 0);
		this.setVisible(true);
	}
	
	private void initializeInternalAttribute(){
		calcLayout();
		
		initalizeBackground();
	}
	
	public class yLocaBox{
		public final int fisheryLocaY;
		public final int groundLocaY;
		public final int bottompaneLocaY;
		
		public yLocaBox(int fisheryLocaY, int groundLocaY, int bottompaneLocaY){
			this.fisheryLocaY = fisheryLocaY;
			this.groundLocaY = groundLocaY;
			this.bottompaneLocaY = bottompaneLocaY;
		}
		
		public int getFisheryPaneHeight(){
			return groundLocaY - fisheryLocaY;
		}
		
		public int getGroundPaneHeight(){
			return bottompaneLocaY - groundLocaY;
		}
	}
	
	private yLocaBox yBlueprint;
	
	private void calcLayout(){
		int fisheryLocaY = 40;
		int groundLocaY = (this.getSize().height - fisheryLocaY) / 2;
		int bottompaneLocaY = this.getSize().height - 100;
		
		yBlueprint = new yLocaBox(fisheryLocaY, groundLocaY, bottompaneLocaY);
	}
	
	static Image backgroundImg = null;
	
	private void initalizeBackground(){
		loadBackgroundImage();
		
		JLabel background = new JLabel(new ImageIcon(backgroundImg));
		background.setSize(backgroundImg.getWidth(null), backgroundImg.getHeight(null));
		background.setLocation(0, yBlueprint.fisheryLocaY);
		this.add(background);
		this.setLayer(background, JLayeredPane.DEFAULT_LAYER);
	}
	
	private void loadBackgroundImage(){
		final String backgroundImgPath = "GameBackground.png";
		BufferedImage backgroundBufferedImg = null;
		
		try {
			backgroundBufferedImg = ImageIO.read(new File(backgroundImgPath));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Failed to open the img : " + backgroundImgPath, "Loading the img is Failed", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
			e.printStackTrace();
		}
		
		backgroundImg = Toolkit.getDefaultToolkit().createImage(backgroundBufferedImg.getSource());
	}
	
	
	private void initializePanes(){
		initializeFishery();
		initializeGround();
	}
	
	private void initializeFishery(){
		int fisheryHeight = yBlueprint.getFisheryPaneHeight();
		Fishery fishery = new Fishery(gameFrameWidth, fisheryHeight);
		fishery.setLocation(0, yBlueprint.fisheryLocaY);
		this.add(fishery);
		this.setLayer(fishery, JLayeredPane.MODAL_LAYER);
	}
	
	private void initializeGround(){
		int groundHeight = yBlueprint.getGroundPaneHeight();
		Ground ground = new Ground(gameFrameWidth, groundHeight);
		ground.setLocation(0, yBlueprint.groundLocaY);
		this.add(ground);
		this.setLayer(ground, JLayeredPane.MODAL_LAYER);
	}
}