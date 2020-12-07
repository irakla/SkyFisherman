package game;

import java.awt.Image;
import java.awt.Point;

import exception.ExceptionalCaseException;
import exception.ValueDomainException;
import game.Fish.Direct;

public class FishPath {
	public FishPath(Fishery fisheryPane, Fish.Fishes whatIsFishes){
		initialize(fisheryPane, whatIsFishes);
		setPath();
	}

	private Fishery fisheryPane;
	private Fish.Fishes whatIsFishes;
	
	private void initialize(Fishery fisheryPane, Fish.Fishes whatIsFishes){
		this.whatIsFishes = whatIsFishes;
		this.fisheryPane = fisheryPane;
	}
	
	private void setPath(){
		setStartingDirect();
		
		try{
			setStartingPoint();
			setArrivingPoint();
		}catch(ExceptionalCaseException e){
			e.occurExceptionalCaseError(e);
		}
	}
	
	private Fish.Direct startingDirect;
	
	private void setStartingDirect(){
		try {
			startingDirect = getRandomDirect();
		} catch (ValueDomainException e) {
			e.occurValueDomainError(e);
		}
	}
	
	private Direct getRandomDirect() throws ValueDomainException{
		Direct[] directList = Direct.values();
		int direct = (int)(Math.random() * directList.length);
		boolean ifNotExistTheDirect = direct >= directList.length || direct < 0;
		
		if(ifNotExistTheDirect){
			throw new ValueDomainException("getRandomDirect", Integer.toString(direct), 
					"must be >= 0 or < " + directList.length);
		}
		
		return directList[direct];
	}
	
	
	private Point startingPoint;
	
	private void setStartingPoint() throws ExceptionalCaseException{
		startingPoint = designRandomLocaInDirectSide(startingDirect);
		relocateForImage(startingPoint, startingDirect, 
				FishImage.fishImages.getFishImg(whatIsFishes));
		
	}
	
	private Fish.Direct arrivingPointDirect;
	private Point arrivingPoint;
	
	private void setArrivingPoint() throws ExceptionalCaseException{
		setArrivingPointDirect();
		arrivingPoint = designRandomLocaInDirectSide(arrivingPointDirect);
		relocateForImage(arrivingPoint, arrivingPointDirect,
				FishImage.fishImages.getFishImg(whatIsFishes));
	}
	
	private void setArrivingPointDirect() throws ExceptionalCaseException{
		switch(startingDirect){
		case Left:
			arrivingPointDirect = getRandomDirect(Direct.Upward, Direct.Right);
			break;
		case Upward:
			arrivingPointDirect = getRandomDirect(Direct.Left, Direct.Right);
			break;
		case Right:
			arrivingPointDirect = getRandomDirect(Direct.Left, Direct.Upward);
			break;
		default:
			
			throw new ExceptionalCaseException("setGoalDirect", startingDirect.toString());
		}
	}
	
	private Direct getRandomDirect(Direct direct1, Direct direct2){
		Direct targetDirects[] = new Direct[2];
		targetDirects[0] = direct1;
		targetDirects[1] = direct2;
		
		int selectedDirect = (int)(Math.random() * 2);
		
		return targetDirects[selectedDirect];
	}
	
	private Point designRandomLocaInDirectSide(Direct direct) throws ExceptionalCaseException{
		switch(direct){
		case Left:
			return getLeftLocation();
		case Upward:
			return getUpwardLocation();
		case Right:
			return getRightLocation();
		
		default:	
			throw new ExceptionalCaseException("designRandomLocaInDirectSide", direct.toString());
		}
	}
	
	private Point getLeftLocation(){
		int x = 0;
		int y = (int)(Math.random() * (fisheryPane.getHeight() - 1)) + 1;
		return new Point(x, y);
	}
	
	private Point getUpwardLocation(){
		int x = (int)(Math.random() * (fisheryPane.getWidth() - 2)) + 1;
		int y = 0;
		return new Point(x, y);
	}
	
	private Point getRightLocation(){
		int x = fisheryPane.getWidth();
		int y = (int)(Math.random() * fisheryPane.getHeight() - 1) + 1;
		return new Point(x, y);
	}
	
	private void relocateForImage(Point rawloca, Direct sideDirect, Image img) throws ExceptionalCaseException{
		Point calcingPoint = rawloca;
		
		switch(sideDirect){
		case Left:
			calcingPoint.setLocation(
					calcingPoint.getLocation().x - img.getWidth(null),
					calcingPoint.getLocation().y - img.getHeight(null));
			break;
		case Upward:
			calcingPoint.setLocation(
					calcingPoint.getLocation().x,
					calcingPoint.getLocation().y - img.getHeight(null));
			break;
		case Right:
			calcingPoint.setLocation(
					calcingPoint.getLocation().x + img.getWidth(null), 
					calcingPoint.getLocation().y - img.getHeight(null));
			break;
		
		default:
			throw new ExceptionalCaseException("relocateForSize", startingDirect.toString());
		}
	}
	
	
	public Point getStartingPoint(){
		return startingPoint;
	}
	
	public Point getArrivingPoint(){
		return arrivingPoint;
	}
	
	public Fish.Direct getArrivingPointDirect(){
		return arrivingPointDirect;
	}
}
