import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

public class MyLine extends MyShape
{
    // constructor without input values
    public MyLine()
    {
        super(); // calls parent class constructor
    } // end MyLine constructor
    
    // constructor with input values
    public MyLine( int x1, int y1, int x2, int y2, Color color1, Color color2, boolean isDashed, boolean hasGradient, int strokeWidth, int dashLength )
    {
        super( x1, y1, x2, y2, color1, color2, isDashed, hasGradient, strokeWidth, dashLength ); // calls parent class constructor
    } // end MyLine constructor
    
    // actually draws the line
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
        
        g2d.draw( new Line2D.Double(getX1(), getY1(), getX2(), getY2()) );
    } // end method draw
} // end class MyLine