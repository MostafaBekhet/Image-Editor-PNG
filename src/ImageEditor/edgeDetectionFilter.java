package ImageEditor;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class edgeDetectionFilter implements imageFilteration{

	@Override
	public BufferedImage applyFilter(BufferedImage im) {

		BufferedImage image = im;
		
		Kernel kernel = new Kernel(3, 3,
				 
			new float[] {
				 
			0.0f, -1.0f, 0.0f,
			 
			-1.0f, 4.0f, -1.0f,
				 
			0.0f, -1.0f, 0.0f});				 
			 
		BufferedImageOp op = new ConvolveOp(kernel);
				 
			  image = op.filter(image, null);
		
		return image;
	}

}
