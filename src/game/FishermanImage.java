package game;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class FishermanImage {
	private LinkedList<CatchingFishFigure> catchingFishList = null;
	
	private class CatchingFishFigure{
		Fish.Fishes whatIsFishes;
		
		CatchingFishFigure(Fish.Fishes whatIsFishes){
			this.whatIsFishes = whatIsFishes;
		}
		
		public Fish.Fishes getWhatIsFishes(){
			return whatIsFishes;
		}
	}
	
	private FishermanImages fishermanImages = new FishermanImages();
	
	static class FishermanImages{
		private final String FishermanPath = "Fisherman.png";
		private Image fishermanImage;
		
		public FishermanImages(){
			getFishermanImageFromFile();
		}

		private void getFishermanImageFromFile() {
			try {
				fishermanImage = ImageIO.read(new File(FishermanPath));
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,
						"Failed to open the img : " + FishermanPath, "Loading the img is Failed", JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			}
		}
		
		public Image getFishermanImage(){
			return fishermanImage;
		}
	}
	
	public Image getDisplayingFishermanImage(){
		return paintFisherman();
	}
	
	private Image paintFisherman(){
		//TODO : Fisherman's image must be finished by here.
		BufferedImage paintBoard = new BufferedImage(fishermanImages.getFishermanImage().getWidth(null),
				fishermanImages.getFishermanImage().getHeight(null), BufferedImage.TYPE_INT_ARGB);
		
		paintBoard.getGraphics().drawImage(fishermanImages.getFishermanImage(), 0, 0, null);
		
		return Toolkit.getDefaultToolkit().createImage(paintBoard.getSource());
		//BufferedImage be Converted to Image.
	}
}
