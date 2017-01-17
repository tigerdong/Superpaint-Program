import java.awt.Color;
import java.awt.Graphics;

abstract class MyShape
{
    private int x1; // x coordinate of first endpoint
    private int y1; // y coordinate of first endpoint
    private int x2; // x coordinate of second endpoint
    private int y2; // y coordinate of second endpoint
    private Color myColor1; // first color of this shape
    private Color myColor2; // second color of this shape
    private boolean isDashed; // whether the shape is dashed
    private boolean hasGradient; // whether the shape has a gradient
    private int strokeWidth; // width of stroke
    private int dashLength; // length of dash
    
    // constructor without input values
    public MyShape()
    {
        x1 = 0; 
        y1 = 0; 
        x2 = 0; 
        y2 = 0; 
        myColor1 = Color.BLACK;
        myColor2 = Color.BLACK;
        isDashed = false;
        hasGradient = false;
        strokeWidth = 1;
        dashLength = 10;
    } // end MyShape constructor
    
    // constructor with input values
    public MyShape( int x1, int y1, int x2, int y2, Color color1, Color color2, boolean isDashed, boolean hasGradient, int strokeWidth, int dashLength )
    {
        this.x1 = x1; 
        this.y1 = y1; 
        this.x2 = x2; 
        this.y2 = y2;
        myColor1 = color1;
        myColor2 = color2;
        this.isDashed = isDashed;
        this.hasGradient = hasGradient;
        this.strokeWidth = strokeWidth;
        this.dashLength = dashLength;
    } // end MyShape constructor
    
    // mutator method that sets x coordinate of first endpoint
    protected void setX1( int x1 )
    {
        this.x1 = x1;
    } // end setX1
    
    // mutator method that sets y coordinate of first endpoint
    protected void setY1( int y1 )
    {
        this.y1 = y1;
    } // end setY1
    
    // mutator method that sets x coordinate of second endpoint
    protected void setX2( int x2 )
    {
        this.x2 = x2; 
    } // end setX2
    
    // mutator method that sets y coordinate of second endpoint
    protected void setY2( int y2 )
    {
        this.y2 = y2;
    } // end setY2
    
    // mutator method that sets the first color of the shape
    protected void setColor1 ( Color myColor1 )
    {
        this.myColor1 = myColor1;
    } // end setColor1
    
    // mutator method that sets the second color of the shape
    protected void setColor2 ( Color myColor2 )
    {
        this.myColor2 = myColor2;
    } // end setColor2
    
    // mutator method that sets whether the shape is dashed
    protected void setIsDashed ( boolean isDashed )
    {
        this.isDashed = isDashed;
    } // end setIsDashed
    
    // mutator method that sets whether the shape has a gradient
    protected void setHasGradient ( boolean hasGradient )
    {
        this.hasGradient = hasGradient;
    } // end setHasGradient
    
    // mutator method that sets the stroke width
    protected void setStrokeWidth ( int strokeWidth )
    {
        this.strokeWidth = strokeWidth;
    } // end setStrokeWidth
    
    
    // mutator method that sets the dash length
    protected void setDashLength ( int dashLength )
    {
        this.dashLength = dashLength;
    } // end setDashLength
    
    // accessor method that gets x coordinate of first endpoint
    protected int getX1()
    {
        return x1;
    } // end getX1
    
    // accessor method that gets y coordinate of first endpoint
    protected int getY1()
    {
        return y1;
    } // end getY1
    
    // accessor method that gets x coordinate of second endpoint
    protected int getX2()
    {
        return x2;
    } // end getX2
    
    // accessor method that gets y coordinate of second endpoint
    protected int getY2()
    {
        return y2;
    } // end getY2
    
    // accessor method that gets first the color of the shape
    protected Color getColor1()
    {
        return myColor1;
    } // end getColor1
    
    // accessor method that gets second the color of the shape
    protected Color getColor2()
    {
        return myColor2;
    } // end getColor2
    
    // accessor method that gets whether the shape is dashed
    protected boolean getIsDashed()
    {
        return isDashed;
    } // end getIsDashed
    
    // accessor method that gets whether the shape has a gradient
    protected boolean getHasGradient()
    {
        return hasGradient;
    } // end getHasGradient
    
    // accessor method that gets stroke width
    protected int getStrokeWidth()
    {
        return strokeWidth;
    } // end getstrokeWidth
    
    // accessor method that gets dash length
    protected int getDashLength()
    {
        return dashLength;
    } // end getDashLength
    
    // abstract draw method that draws the shape
    public abstract void draw( Graphics g );
    
} // end class MyLine