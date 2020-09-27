package ImageEditor;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;

public class colorConversion extends imageManipulation{
	
	private BufferedImage image;

	public colorConversion(BufferedImage BI) {
		super(BI);
		// TODO Auto-generated constructor stub
		
		image = BI;
		
	}
	
	public BufferedImage convertToBW() {
		
		ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
	    ColorConvertOp ccop = new ColorConvertOp(cs, null);
	 
	    return ccop.filter(image, null);
	}
	
}
