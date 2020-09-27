package ImageEditor;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class sharpnessFilter implements imageFilteration{

	public BufferedImage applyFilter(BufferedImage im) {
		// TODO Auto-generated method stub
		
		BufferedImage image = im;
		
		Kernel kernel = new Kernel(3, 3,
                new float[]{
                    -1, -1, -1,
                    -1, 9, -1,
                    -1, -1, -1});
 
        BufferedImageOp op = new ConvolveOp(kernel);
 
        image = op.filter(image, null);
		
		return image;
	}

}
