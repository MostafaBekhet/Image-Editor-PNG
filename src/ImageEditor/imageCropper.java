package ImageEditor;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;

public class imageCropper extends imageManipulation{
	
	static Rectangle clip;

	public imageCropper(BufferedImage BI) {
		super(BI);
		// TODO Auto-generated constructor stub
	}
	
	public BufferedImage crop(BufferedImage im , int w , int h , int x , int y) throws Exception {
		
		BufferedImage cImage = null;
		Dimension size = new Dimension(w, h);
		createClip(im, size, x, y);
		
		try {
			int newW = clip.width;
			int newH = clip.height;
			 
			cImage = im.getSubimage(clip.x, clip.y, newW, newH);

			} catch (RasterFormatException rfe) {
				return null;
			}
		
		return cImage;
	}
	
	private static void createClip(BufferedImage img, Dimension size,
			int clipX, int clipY) throws Exception {
			
			boolean isClipAreaAdjusted = false;
			 
			/**Checking for negative X Co-ordinate**/
			if (clipX < 0) {
			clipX = 0;
			isClipAreaAdjusted = true;
			}
			/**Checking for negative Y Co-ordinate**/
			if (clipY < 0) {
			clipY = 0;
			isClipAreaAdjusted = true;
			}
			 
			/**Checking if the clip area lies outside the rectangle**/
			if ((size.width + clipX) <= img.getWidth()
			&& (size.height + clipY) <= img.getHeight()) {
			 
			/**
			* Setting up a clip rectangle when clip area
			* lies within the image.
			*/
			 
			clip = new Rectangle(size);
			clip.x = clipX;
			clip.y = clipY;
			} else {
			 
			/**
			* Checking if the width of the clip area lies outside the image.
			* If so, making the image width boundary as the clip width.
			*/
			if ((size.width + clipX) > img.getWidth())
			size.width = img.getWidth() - clipX;
			 
			/**
			* Checking if the height of the clip area lies outside the image.
			* If so, making the image height boundary as the clip height.
			*/
			if ((size.height + clipY) > img.getHeight())
			size.height = img.getHeight() - clipY;
			 
			/**Setting up the clip are based on our clip area size adjustment**/
			clip = new Rectangle(size);
			clip.x = clipX;
			clip.y = clipY;
			 
			isClipAreaAdjusted = true;
			 
			}
		}
	
	
}
