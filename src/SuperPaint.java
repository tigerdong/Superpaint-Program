// Name: Tiger Dong
// Date: 01/14/2017
// Description: SuperPaint application that allows the drawing of different lines, rectangles, and ovals

import javax.swing.JFrame;
 
public class SuperPaint extends JFrame 
{
	private static final long serialVersionUID = 1L;

public static void main( String args[] )
   { 
      DrawFrame superPaint = new DrawFrame(); 
      superPaint.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      superPaint.setSize( 1000, 750 ); // set frame size
      superPaint.setVisible( true ); // display frame
   } // end main
} // end class SuperPaint