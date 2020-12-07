package game;

import java.awt.*;
import javax.swing.*;

import exception.ValueDomainException;

import java.util.LinkedList;
import java.util.Iterator;

public class FishController extends Thread{
	private LinkedList<Fish> fishList = new LinkedList<Fish>();
	private Fishery fisheryPane;
	
	public FishController(Fishery fisheryPane){
		this.fisheryPane = fisheryPane;
	}
	
	@Override
	public void run(){
		long prevframe = System.currentTimeMillis();
		long frameperiod = 30;
		//millisecond
		
		while(!Thread.currentThread().isInterrupted()){
			if(System.currentTimeMillis() <= prevframe + frameperiod)
				continue;
			
			checkCreatingFish();
			moveFish();
			removingFish();
			prevframe = System.currentTimeMillis();
		}
	}
	
	private void checkCreatingFish(){
		int randomTo99 = (int)(Math.random() * 100);			//0 ~ 99
		final boolean TwentyPercent = randomTo99 < 20;
		
		if(TwentyPercent)
			createFish();
	}
	
	
	private void createFish(){
		Fish testFish = new Fish(fisheryPane, this);
		fisheryPane.add(testFish);
		fisheryPane.setLayer(testFish, JLayeredPane.MODAL_LAYER);
		fishList.add(testFish);
	}

	private void moveFish() {
		Iterator<Fish> fishIter = fishList.iterator();
		while(fishIter.hasNext()){
			Fish notmovedFish = fishIter.next();
			
			notmovedFish.move();
		}
	}
	
	private LinkedList<Fish> removeFishList = new LinkedList<Fish>();
	
	private void removingFish(){
		Iterator<Fish> removeFishIter = removeFishList.iterator();
		while(removeFishIter.hasNext()){
			Fish removingFish = removeFishIter.next();
			
			fishList.remove(removingFish);
		}
	}
	
	public void removeTheFish(Fish removingFish){
		removingFish.setVisible(false);
		removeFishList.add(removingFish);
	}
}
