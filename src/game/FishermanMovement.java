package game;

import java.awt.*;

import javax.swing.*;

public class FishermanMovement implements Runnable{
	private Point pathLeftLimit, pathRightLimit;
	
	public FishermanMovement(){
		pathLeftLimit = new Point(0, Game.backgroundImg.getHeight(null) / 5 * 4);
	}
	
	private void limitGroundPath(){
		final double pathLeftLimitWidthRateFromMap = 0;
		final double pathLeftLimitHeightRateFromMap = 1.0 / 5.0 * 4.0;
		
		pathLeftLimit = new Point((int)Math.round(pathLeftLimitWidthRateFromMap),
				(int)Math.round(Game.backgroundImg.getHeight(null)));
		
		final double pathRightLimitWidthRateFromMap = 0;
		final double pathRightLimitHeightRateFromMap = 0;
		
		pathRightLimit = new Point(0, 0);
	}
	
	@Override
	public void run() {
		
	}
}
