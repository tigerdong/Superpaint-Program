import java.awt.Color;
import java.awt.Graphics;

abstract class MyBoundedShape extends MyShape
{
    private boolean fillColor; // whether the shape is filled
    
    // constructor without input values
    public MyBoundedShape()
    {
        super(); // calls parent class constructor
        fillColor = false; // set whether the shape is filled
    } // end MyBoundedShape constructor
    
    // constructor with input values
    public MyBoundedShape( int x1, int y1, int x2, int y2, Color color1, Color color2, boolean isDashed, boolean hasGradient, boolean fillShape, int strokeWidth, int dashLength )
    {
        super( x1, y1, x2, y2, color1, color2, isDashed, hasGradient, strokeWidth, dashLength ); // calls parent class constructor
        fillColor = fillShape; // set whether the shape is filled
    } // end MyBoundedShape constructor
    
    // accessor method that gets the upper left x coordinate
    protected int getUpperLeftX()
    {
        if (getX1() < getX2())
            return getX1();
        else
            return getX2();
    } // end getUpperLeftX
    
    // accessor method that gets the upper left y coordinate
    protected int getUpperLeftY()
    {
        if (getY1() < getY2())
            return getY1();
        else
            return getY2();
    } // end getUpperLeftY
    
    // accessor method that gets width
    protected int getWidth()
    {
        return Math.abs(getX2() - getX1());
    } // end getWidth
    
    // accessor method that gets height
    protected int getHeight()
    {
        return Math.abs(getY2() - getY1());
    } // end getHeight
    
    // accessor method that gets whether the shape is filled
    protected boolean getFillColor()
    {
        return fillColor;
    } // end getFillColor
    
    // mutator method that sets whether the shape is filled
    protected void setFillColor( boolean fillShape )
    {
        fillColor = fillShape;
    } // end setFillColor
    
    // abstract draw method that draws the shape
    public abstract void draw( Graphics g );
    
} // end class MyBoundedShape