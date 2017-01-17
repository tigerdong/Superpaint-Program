// Name: Tiger Dong
// Date: 01/14/2017
// Description: DrawPanel class for SuperPaint application

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.JLabel;

public class DrawPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	
    // constants for shape types
	private final int LINE = 0;
    private final int OVAL = 1;
    private final int RECT = 2;
    private final int PATH = 3;
    
    // variables to keep track of shapes/current shape
    private LinkedList<MyShape> shapeObjects;
    private LinkedList<MyShape> clearedShapes;
    private int currentShapeType;
    private MyShape currentShapeObject;
    private Color currentShapeColor1;
    private Color currentShapeColor2;
    private boolean currentShapeFilled;
    private boolean currentShapeDashed;
    private boolean currentShapeGradient;
    private int currentShapeStrokeWidth;
    private int currentShapeDashLength;
    private JLabel statusLabel;
    
    // constructor, creates a panel with random shapeObjects
    public DrawPanel( JLabel label )
    {
        statusLabel = label;
        currentShapeType = LINE;
        currentShapeFilled = false;
        currentShapeDashed = false;
        currentShapeGradient = false;
        currentShapeColor1 = Color.BLACK;
        currentShapeColor2 = Color.BLACK;
        currentShapeStrokeWidth = 1;
        currentShapeDashLength = 10;
        shapeObjects = new LinkedList<MyShape>();
        clearedShapes = new LinkedList<MyShape>();
        setBackground(Color.WHITE);
        
        MouseHandler mouseHandler = new MouseHandler(); 
        addMouseListener( mouseHandler ); 
        addMouseMotionListener( mouseHandler );
    } // end DrawPanel constructor
    
    // for each shape array, draw the individual shapeObjects
    public void paintComponent( Graphics g )
    {
        super.paintComponent( g );
        
        // draw the shapeObjects
        if (shapeObjects.getFront() != null)
        {
            drawShapes( shapeObjects.getFront(), g );
        }
        
        if (currentShapeObject != null)
            currentShapeObject.draw( g );
    } // end method paintComponent
    
    // method that loads an image onto the draw panel
    public void loadImage(BufferedImage image) {
    	shapeObjects.addFront( new MyImage(image) );
    	repaint();
    } // end method loadImage
    
    // mutator method for currentShapeType
    public void setCurrentShapeType( int shapeType )
    {
        currentShapeType = shapeType;
    } // end method setCurrentShapeObject
    
    // mutator method for currentShapeColor1
    public void setCurrentShapeColor1( Color shapeColor )
    {
        currentShapeColor1 = shapeColor;
    } // end method setCurrentShapeColor1
    
    // mutator method for currentShapeColor2
    public void setCurrentShapeColor2( Color shapeColor )
    {
        currentShapeColor2 = shapeColor;
    } // end method setCurrentShapeColor2
    
    // mutator method for currentShapeDashed
    public void setCurrentShapeDashed( boolean shapeDashed )
    {
        currentShapeDashed = shapeDashed;
    } // end method setCurrentShapeDashed
    
    // mutator method for currentShapeGradient
    public void setCurrentShapeGradient( boolean shapeGradient )
    {
        currentShapeGradient = shapeGradient;
    } // end method setCurrentShapeGradient
    
    // mutator method for currentShapeFilled
    public void setCurrentShapeFilled( boolean shapeFilled )
    {
        currentShapeFilled = shapeFilled;
    } // end method setCurrentShapeFilled
    
    // mutator method for currentShapeStrokeWidth
    public void setCurrentShapeStrokeWidth( int strokeWidth )
    {
        currentShapeStrokeWidth = strokeWidth;
    } // end method setCurrentShapeStrokeWidth
    
    // mutator method for currentShapeDashLength
    public void setCurrentShapeDashLength( int dashLength )
    {
        currentShapeDashLength = dashLength;
    } // end method setCurrentShapeDashLength
    
    // accessor method for currentShapeFilled
    public boolean getCurrentShapeFilled()
    {
        return currentShapeFilled;
    } // end method getCurrentShapeFilled
    
    // accessor method for currentShapeDashed
    public boolean getCurrentShapeDashed()
    {
        return currentShapeDashed;
    } // end method getCurrentShapeDashed
    
    // accessor method for currentShapeGradient
    public boolean getCurrentShapeGradient()
    {
        return currentShapeGradient;
    } // end method getCurrentShapeGradient
    
    // accessor method for currentShapeType
    public int getCurrentShapeType()
    {
        return currentShapeType;
    } // end method getCurrentShapeType
    
    // accessor method for currentShapeColor1
    public Color getCurrentShapeColor1()
    {
        return currentShapeColor1;
    } // end method getCurrentShapeColor1
    
    // accessor method for currentShapeColor2
    public Color getCurrentShapeColor2()
    {
        return currentShapeColor2;
    } // end method getCurrentShapeColor2
	
    // accessor method for currentShapeStrokeWidth
    public int getCurrentShapeStrokeWidth()
    {
        return currentShapeStrokeWidth;
    } // end method getCurrentShapeStrokeWidth
    
    // accessor method for currentShapeDashLength
    public int getCurrentShapeDashLength()
    {
		return currentShapeDashLength;
    } // end method getCurrentShapeDashLength
    
    // method to undo last shape
    public void clearLastShape()
    {
        if (shapeObjects.isEmpty() == false)
        {
            clearedShapes.addFront(shapeObjects.first());
            shapeObjects.removeFront();
            repaint();
        }
    } // end method clearLastShape
    
    // method to redo last shape
    public void redoLastShape()
    {
        if (clearedShapes.isEmpty() == false)
        {
            shapeObjects.addFront(clearedShapes.first());
            clearedShapes.removeFront();
            repaint();
        }
    } // end method redoLastShape
    
    // method to clear the drawing
    public void clearDrawing()
    {
        clearedShapes.makeEmpty();
        shapeObjects.makeEmpty();
        repaint();
    } // end method clearDrawing
    
    // method to draw shapes from shapeObjects
    private void drawShapes( ListNode<MyShape> currentNode, Graphics g )
    {
        if (currentNode != null)
        {
        	
            if (currentNode.getNext() != null )
                drawShapes( currentNode.getNext(), g );
            currentNode.getData().draw( g );
        }
    } // end method drawShapes
    
    // inner class for handling mouse events
    private class MouseHandler extends MouseAdapter
    {
        // handle event when mouse pressed
        public void mousePressed( MouseEvent event )
        {
            if (currentShapeObject == null)
            {
                if (currentShapeType == LINE)
                {
                    currentShapeObject = new MyLine( event.getX(), event.getY(), event.getX(), event.getY(), currentShapeColor1, currentShapeColor2, currentShapeDashed, currentShapeGradient, currentShapeStrokeWidth, currentShapeDashLength );
                }
                else if (currentShapeType == OVAL)
                {
                    currentShapeObject = new MyOval( event.getX(), event.getY(), event.getX(), event.getY(), currentShapeColor1, currentShapeColor2, currentShapeDashed, currentShapeGradient, currentShapeFilled, currentShapeStrokeWidth, currentShapeDashLength );
                }
                else if (currentShapeType == RECT)
                {
                    currentShapeObject = new MyRectangle( event.getX(), event.getY(), event.getX(), event.getY(), currentShapeColor1, currentShapeColor2, currentShapeDashed, currentShapeGradient, currentShapeFilled, currentShapeStrokeWidth, currentShapeDashLength );
                }
                else if (currentShapeType == PATH)
                {
                	currentShapeObject = new MyPath( event.getX(), event.getY(), event.getX(), event.getY(), currentShapeColor1, currentShapeColor2, currentShapeDashed, currentShapeGradient, currentShapeStrokeWidth, currentShapeDashLength );
                	((MyPath)currentShapeObject).extend();
                }
            }
            repaint();
            statusLabel.setText( String.format("(%d, %d)", event.getX(), event.getY()) );
        } // end method mousePressed
        
        // handle event when mouse released after dragging
        public void mouseReleased( MouseEvent event )
        {
            if (currentShapeObject != null)
            {
                if ( currentShapeType == PATH ) {
                	((MyPath)currentShapeObject).extend();
                	((MyPath)currentShapeObject).close();
                }
                currentShapeObject.setX2( event.getX() );
                currentShapeObject.setY2( event.getY() );
                shapeObjects.addFront( currentShapeObject );
                currentShapeObject = null;
                clearedShapes.makeEmpty();
                repaint();
            }
        } // end method mouseReleased
        
        // handle event when user moves mouse
        public void mouseMoved( MouseEvent event )
        {
            statusLabel.setText( String.format("(%d, %d)", event.getX(), event.getY()) );
        } // end method mouseMoved
        
        // handle event when user drags mouse with button pressed
        public void mouseDragged( MouseEvent event )
        {
            if ( currentShapeObject != null )
            {
                currentShapeObject.setX2( event.getX() );
                currentShapeObject.setY2( event.getY() );
                if ( currentShapeType == PATH ) {
                	((MyPath)currentShapeObject).extend();
                }
                repaint();
                statusLabel.setText( String.format("(%d, %d)", event.getX(), event.getY()) );
            }
        } // end method mouseDragged
    } // end class MouseHandler
} // end class DrawPanel