package ImageEditor;

import java.awt.image.BufferedImage;

public class imageResizer extends imageManipulation{

	public imageResizer(BufferedImage BI) {
		super(BI);
		// TODO Auto-generated constructor stub
	}
	
	public BufferedImage resize(BufferedImage src , int w , int h) {
		BufferedImage img = 
	            new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	    int x, y;
	    int ww = src.getWidth();
	    int hh = src.getHeight();
	    int[] ys = new int[h];
	    for (y = 0; y < h; y++)
	        ys[y] = y * hh / h;
	    for (x = 0; x < w; x++) {
	        int newX = x * ww / w;
	        for (y = 0; y < h; y++) {
	            int col = src.getRGB(newX, ys[y]);
	            img.setRGB(x, y, col);
	        }
	    }
	    return img;
		
	}

}
