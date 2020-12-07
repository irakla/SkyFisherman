package game;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import exception.ExceptionalCaseException;

public class FishImage {	
	static FishImages fishImages = new FishImages();
	Fish.Fishes fish;
	
	static class FishImages{
		private HashMap<Fish.Fishes, Image> fishImageMap = new HashMap<Fish.Fishes, Image>();
		
		public FishImages(){
			setFishFiles();
		}
		
		private void setFishFiles(){
			for(int fishes = 0; fishes < Fish.Fishes.values().length; fishes++){
				Image fishImage = null;
				Fish.Fishes whatIsFishes = Fish.Fishes.values()[fishes];
				
				fishImage = readFishFile(whatIsFishes);
				
				fishImageMap.put(whatIsFishes, fishImage);
			}
		}
		
		private Image readFishFile(Fish.Fishes whatIsFishes){
			final String filePrefix = "fish_";
			final String fileExtension = ".png";
			
			Image readingImage = null;
			
			try {
				readingImage = ImageIO.read(new File(filePrefix + whatIsFishes.toString() + fileExtension));
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,
						"Failed to open the img : " + filePrefix + whatIsFishes.toString() + fileExtension, "Loading the img is Failed", JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			}
			
			return readingImage;
		}
		
		
		public Image getFishImg(Fish.Fishes whatIsFishes){
			return fishImageMap.get(whatIsFishes);
		}
	}
	
	public FishImage(Fish.Fishes whatIsFish, Point startingPoint, Point arrivingPoint){
		this.fish = whatIsFish;
		initialize(startingPoint, arrivingPoint);
	}
	
	private void initialize(Point startingPoint, Point arrivingPoint){
		initializeOrgImage();
		decideImgDirect(startingPoint, arrivingPoint);
	}
	
	private void initializeOrgImage(){
		try {
			setImage();
		} catch (ExceptionalCaseException e) {
			e.occurExceptionalCaseError(e);
		}
	}
	
	private Image fishImg;
	
	private void setImage() throws ExceptionalCaseException{
		fishImg = fishImages.getFishImg(fish);
	}
	
	
	private void decideImgDirect(Point startingPoint, Point arrivingPoint){
		if(startingPoint.x < arrivingPoint.x)
			flipHorizontal();
	}
	
	private void flipHorizontal(){
		BufferedImage flippingImage = convertToBufferedImg();
		
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-fishImg.getWidth(null), 0);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		fishImg = op.filter(flippingImage, null);
	}
	
	private BufferedImage convertToBufferedImg(){
		BufferedImage convertingImage = 
				new BufferedImage(fishImg.getWidth(null), fishImg.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		
		convertingImage.getGraphics().drawImage(fishImg, 0, 0, null);
		return convertingImage;
	}
	
	
	public Image getDisplayingFishImage(){
		return fishImg;
	}
}
