package ImageEditor;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class smoothFilter implements imageFilteration{

	@Override
	public BufferedImage applyFilter(BufferedImage im) {
		// TODO Auto-generated method stub
		
		BufferedImage image = im;
		
		Kernel kernel = new Kernel(3, 3,
				 
			new float[] {
				 
					1f/9f, 1f/9f, 1f/9f,
					 
					1f/9f, 1f/9f, 1f/9f,
					 
					1f/9f, 1f/9f, 1f/9f});				 
			 
		BufferedImageOp op = new ConvolveOp(kernel);
				 
			  image = op.filter(image, null);
		
		return image;
	}

}
