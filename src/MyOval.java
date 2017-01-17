import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class MyOval extends MyBoundedShape
{
    // constructor without input values
    public MyOval()
    {
        super(); // calls parent class constructor
    } // end MyOval constructor
    
    // constructor with input values
    public MyOval( int x1, int y1, int x2, int y2, Color color1, Color color2, boolean isDashed, boolean hasGradient, boolean fillShape, int strokeWidth, int dashLength )
    {
        super( x1, y1, x2, y2, color1, color2, isDashed, hasGradient, fillShape, strokeWidth, dashLength ); // calls parent class constructor
    } // end MyOval constructor
    
    // actually draws the oval
    public void draw( Graphics g )
    {
    	Graphics2D g2d = (Graphics2D) g;
    	
        if ( getHasGradient() )
            g2d.setPaint( new GradientPaint( getX1(), getY1(), getColor1(), getX2(), getY2(), getColor2() ) );  
        else
            g2d.setPaint( getColor1() );  
        
        if ( getIsDashed() )
        {
            float dashes[] = { getDashLength(), getDashLength() }; // specify dash pattern
            g2d.setStroke( new BasicStroke( getStrokeWidth(), BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dashes, 0 ) ); 
        }
        else
            g2d.setStroke( new BasicStroke( getStrokeWidth() ) );
        
        if ( getFillColor() )
            g2d.fill( new Ellipse2D.Double(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight()) );
        else
            g2d.draw( new Ellipse2D.Double(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight()) );
    } // end method draw
} // end class MyOval