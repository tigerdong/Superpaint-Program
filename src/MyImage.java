import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class MyImage extends MyShape {
	private BufferedImage image;
	
	// constructor
	public MyImage( BufferedImage image ) {
		this.image = image;
	} // end constructor
	
	// actually draws the image
	public void draw( Graphics g ) {
		Graphics g2d = (Graphics2D) g;
		
		g2d.drawImage(image, 0, 0, null);
	} // end method draw
}
