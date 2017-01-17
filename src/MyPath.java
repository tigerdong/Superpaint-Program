import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

public class MyPath extends MyShape {
	private Path2D path;

	// constructor without input values
	public MyPath() {
		super();
	} // end constructor
	
	// constructor with input values
	public MyPath( int x1, int y1, int x2, int y2, Color color1, Color color2, boolean isDashed, boolean hasGradient, int strokeWidth, int dashLength ) {
		super( x1, y1, x2, y2, color1, color2, isDashed, hasGradient, strokeWidth, dashLength ); // calls parent class constructor
		path = new Path2D.Double();
	} // end constructor
	
	// adds new points on to the path
    public void extend()
    {
        path.moveTo( getX1(), getY1() );
        path.lineTo( getX2(), getY2() );
        setX1( getX2() );
        setY1( getY2() );
    } // end method extend

    // closes off the path
    public void close()
    {
        try {
            path.closePath();
        } catch(Exception e) {} // ignore the exception
    } //end method close
    
	// actually draws the path
	public void draw( Graphics g ) {
    	Graphics2D g2d = (Graphics2D) g;
    	
		if ( getHasGradient() )
			g2d.setPaint( new GradientPaint( getX1(), getY1(), getColor1(), getX2(), getY2(), getColor2() ) );
		else
            g2d.setPaint( getColor1() );
		
		if ( getIsDashed() ) {
            float dashes[] = { getDashLength(), getDashLength() }; // specify dash pattern
            g2d.setStroke( new BasicStroke( getStrokeWidth(), BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dashes, 0 ) ); 
        } else
            g2d.setStroke( new BasicStroke( getStrokeWidth() ) );
		
        // call these functions to create a smooth stroke path
        g2d.fill( path);
        g2d.draw( path );
	} // end method draw
} // end class MyPath
