package ImageEditor;

import java.awt.image.BufferedImage;

public class imageManipulation {
	
	private imageFilteration fBI;
	
	private BufferedImage image;
	private int choice;
	
	public imageManipulation(BufferedImage BI , int ch) {
		image = BI;
		choice = ch;
		if(choice == 1) {
			fBI = new sharpnessFilter();
		}else if(choice == 2) {
			fBI = new smoothFilter();
		}else if(choice == 3) {
			fBI = new edgeDetectionFilter();
		}
	}
	
	public imageManipulation(BufferedImage BI) {
		image = BI;
	}
	
	public BufferedImage filter() {
		return fBI.applyFilter(image);
	}
	
	public BufferedImage convertToBW() {
		return null;
	}
	
	public BufferedImage crop(BufferedImage im , int w , int h , int x , int y) throws Exception {
		return null;
	}
	
	public BufferedImage resize(BufferedImage im , int w , int h) {
		return null;
	}
	
}
