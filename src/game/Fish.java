package game;

import java.awt.*;
import java.lang.reflect.Method;
import java.awt.geom.*;
import java.awt.image.AffineTransformOp;

import javax.swing.*;


import exception.ExceptionalCaseException;
import exception.ValueDomainException;


public class Fish extends JLabel{
	public enum Fishes{
		BLUE, RED, YELLOWGREEN, PURPLE, GRAY;
	}
	
	private Fishes whatIsFishes = Fishes.values()[(int)(Math.random() * Fishes.values().length)];
	
	private double speed;
	
	private Fishery fisheryPane;
	private FishController fishController;
	
	public Fish(Fishery fisheryPane, FishController fishController){
		initialize(fisheryPane, fishController);
		
		calcSpeed();
		calcMovingDistance();
	}
	
	private int fishWidth;
	private int fishHeight;
	
	public int getFishWidth(){
		return fishWidth;
	}
	
	public int getFishHeight(){
		return fishHeight;
	}
	
	private void initialize(Fishery fisheryPane, FishController fishController){
		
		Image fishImgOrg = FishImage.fishImages.getFishImg(whatIsFishes);
		
		fishWidth = fishImgOrg.getWidth(null);
		fishHeight = fishImgOrg.getHeight(null);
		this.setSize(fishWidth, fishHeight);
		
		this.fisheryPane = fisheryPane;
		this.fishController = fishController;
		
		setPath();
		
		setFishImage();
		this.setIcon(new ImageIcon(fishFigure.getDisplayingFishImage()));
		
		initialMovingDirectX();
		initialMovingDirectY();
	}
	
	private FishPath path;
	
	private void setPath(){
		path = new FishPath(fisheryPane, whatIsFishes);
		
		setStartingPoint();
		setArrivingPoint();
	}
	
	private FishImage fishFigure;
	
	public void setFishImage(){
		fishFigure = new FishImage(whatIsFishes, path.getStartingPoint(), path.getArrivingPoint());
	}
	
	@Override
	public void setIcon(Icon img){
		super.setIcon(img);
	}
	
	enum Direct{
		Left, Upward, Right;
	}
	
	private double nowLocationX;
	private double nowLocationY;
	
	private void setStartingPoint(){
		this.setLocation(path.getStartingPoint());
		
		nowLocationX = this.getLocation().x;
		nowLocationY = this.getLocation().y;
	}
	
	private Point goal;
	private Direct goalDirect;
	
	private void setArrivingPoint(){
		goal = path.getArrivingPoint();
		goalDirect = path.getArrivingPointDirect();
	}
	
	private void initialMovingDirectX(){
		final byte Left = -1;
		final byte Right = 1;
		
		if(this.getLocation().x > goal.x)
			directX = Left;
		else
			directX =  Right;
	}
	
	private void initialMovingDirectY(){
		final byte Up = -1;
		final byte Down = 1;
		
		if(this.getLocation().y > goal.y)
			directY = Up;
		else
			directY = Down;
	}
	
	private void calcSpeed(){
		final double MaxSpeed = 3;
		final double MinSpeed = 1.5;
		
		speed = (MaxSpeed - MinSpeed) * Math.random() + MinSpeed;
	}

	private double movingDistanceX, movingDistanceY;
	private byte directX, directY;
	
	private void calcMovingDistance(){	
		double distance = Math.sqrt(Math.pow(Math.abs(this.getLocation().x - goal.x), 2) + Math.pow(Math.abs(this.getLocation().y - goal.y), 2));
		double movingcount = distance / speed;
		double perDistanceX = Math.abs(this.getLocation().x - goal.x) / movingcount;
		double perDistanceY = Math.abs(this.getLocation().y - goal.y) / movingcount;
		
		movingDistanceX = speed * perDistanceX * directX;
		movingDistanceY = speed * perDistanceY * directY;
	}
	
	
	public void move(){
		nowLocationX += movingDistanceX;
		nowLocationY += movingDistanceY;
		setLocation((int)nowLocationX, (int)nowLocationY);
		
		checkOutOfDisplay();
	}
	
	private void checkOutOfDisplay(){
		boolean isOutOfLeftBorder = nowLocationX < fishWidth * -1;
		boolean isOutOfUpwardBorder = nowLocationY < fishHeight * -1;
		boolean isOutOfRightBorder = nowLocationX > fishWidth + fisheryPane.getWidth();
		
		switch(goalDirect){
		case Left:
			if(isOutOfLeftBorder)
				fishController.removeTheFish(this);
			break;
		case Upward:
			if(isOutOfUpwardBorder)
				fishController.removeTheFish(this);
			break;
		case Right:
			if(isOutOfRightBorder)
				fishController.removeTheFish(this);
			break;
		}
	}
}
