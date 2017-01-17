// Name: Tiger Dong
// Date: 01/14/2017
// Description: DrawFrame class for SuperPaint application

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JColorChooser;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.KeyStroke;
import javax.swing.JFileChooser;
import javax.imageio.ImageIO;

public class DrawFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	// GUI items
    private JDesktopPane paintDesktopPane;
    private ArrayList<JInternalFrame> paintInternalFrame;
    
    private JButton color1Button;
    private JButton color2Button;
    
    @SuppressWarnings("rawtypes")
	private JComboBox shapeComboBox;
    
    private JCheckBox filledCheckBox;
    private JCheckBox dashedCheckBox;
    private JCheckBox gradientCheckBox;
    
    private JLabel statusLabel;
    private JLabel strokeWidthLabel;
    private JLabel dashLengthLabel;
    
    private JTextField strokeWidthTextField;
    private JTextField dashLengthTextField;
    
    private ArrayList<DrawPanel> drawPanel;
    private JPanel interfacePanel;
    private JPanel coordinatePanel;
    
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu editMenu;
    
    private JMenuItem newMenuItem;
    private JMenuItem openMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem aboutMenuItem; 
    private JMenuItem prefsMenuItem; 
    private JMenuItem exitMenuItem; 
    
    private JMenuItem undoMenuItem; 
    private JMenuItem redoMenuItem; 
    private JMenuItem clearMenuItem; 
    
    // Preferences window GUI items and variables
    private JFrame prefsFrame;
    private JPanel prefsPanel;
    private JPanel prefsButtonsPanel;
    private JPanel prefsStrokeWidthPanel;
    private JPanel prefsDashLengthPanel;
    private JPanel prefsColor1Panel;
    private JPanel prefsColor2Panel;
    private JPanel prefsShapePanel;
    private JButton prefsColor1Button;
    private JButton prefsColor2Button;
    private JButton prefsSaveButton;
    private JButton prefsCancelButton;
    @SuppressWarnings("rawtypes")
	private JComboBox prefsShapeComboBox;
    private JCheckBox prefsFilledCheckBox;
    private JCheckBox prefsDashedCheckBox;
    private JCheckBox prefsGradientCheckBox;
    private JTextField prefsStrokeWidthTextField;
    private JTextField prefsDashLengthTextField;
    private JLabel prefsStrokeWidthLabel;
    private JLabel prefsDashLengthLabel;
    private JLabel prefsHeadingLabel;
    private JLabel prefsColor1Label;
    private JLabel prefsColor2Label;
    private JLabel prefsShapeLabel;
    private String prefsFilledText = "0";
    private String prefsDashedText = "0";
    private String prefsGradientText = "0";
    private String prefsStrokeWidthText = "1";
    private String prefsDashLengthText = "10";
    private String prefsColor1Text = "0\n0\n0";
    private String prefsColor2Text = "0\n0\n0";
    private String prefsShapeText = "0";
    
    private boolean defaultShapeFilled = false;
    private boolean defaultShapeDashed = false;
    private boolean defaultShapeGradient = false;
    private int defaultStrokeWidth = 1;
    private int defaultDashLength = 10;
    private int defaultShapeType = 0;
    private Color defaultColor1 = Color.BLACK;
    private Color defaultColor2 = Color.BLACK;
    private boolean currentShapeFilled = false;
    private boolean currentShapeDashed = false;
    private boolean currentShapeGradient = false;
    private int currentStrokeWidth = 1;
    private int currentDashLength = 10;
    private int currentShapeType = 0;
    private Color currentColor1 = Color.BLACK;
    private Color currentColor2 = Color.BLACK;
    
    private String shapeNames[] = { "Line", "Oval", "Rectangle", "Free Draw"};
    private ArrayList<Integer> untitledNumbers = new ArrayList<Integer>();
    
    private JFileChooser fileChooser = new JFileChooser();
    
    // constructor for the DrawFrame class
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public DrawFrame()
    {
        super( "SuperPaint Application" );
        setLayout( new BorderLayout() );
        
        // Creating components
        paintDesktopPane = new JDesktopPane();
		paintInternalFrame = new ArrayList<JInternalFrame>();
        
        menuBar = new JMenuBar();
        
        fileMenu = new JMenu( "File" );
        // Adding shortcut alt + F
        fileMenu.setMnemonic( KeyEvent.VK_F );
        
        editMenu = new JMenu( "Edit" );
        // Adding shortcut alt + E
        editMenu.setMnemonic( KeyEvent.VK_E) ;
        
        menuBar.add( fileMenu );
        menuBar.add( editMenu );
        
        newMenuItem = new JMenuItem( "New" );
        // Adding shortcut control + N
        KeyStroke ctrlNKey = KeyStroke.getKeyStroke( "control N" );
        newMenuItem.setAccelerator( ctrlNKey );
        
        openMenuItem = new JMenuItem( "Open" );
        // Adding shortcut control + O
        KeyStroke ctrlOKey = KeyStroke.getKeyStroke( "control O ");
        openMenuItem.setAccelerator( ctrlOKey );
        
        saveMenuItem = new JMenuItem( "Save" );
        // Adding shortcut control + S
        KeyStroke ctrlSKey = KeyStroke.getKeyStroke( "control S" );
        saveMenuItem.setAccelerator( ctrlSKey );
        
        aboutMenuItem = new JMenuItem( "About" );
        // Adding shortcut control + A
        KeyStroke ctrlAKey = KeyStroke.getKeyStroke( "control A" );
        aboutMenuItem.setAccelerator( ctrlAKey );
        
        prefsMenuItem = new JMenuItem( "Preferences" );
        // Adding shortcut control + P
        KeyStroke ctrlPKey = KeyStroke.getKeyStroke( "control P" );
        prefsMenuItem.setAccelerator( ctrlPKey );
        
        exitMenuItem = new JMenuItem( "Exit" );
        // Adding shortcut control + Q
        KeyStroke ctrlQKey = KeyStroke.getKeyStroke( "control Q" );
        exitMenuItem.setAccelerator( ctrlQKey );
        
        fileMenu.add( newMenuItem );
        fileMenu.add( openMenuItem );
        fileMenu.add( saveMenuItem );
        fileMenu.add( aboutMenuItem );
        fileMenu.add( prefsMenuItem );
        fileMenu.add( exitMenuItem );
        
        undoMenuItem = new JMenuItem( "Undo" );
        // Adding shortcut control + Z
        KeyStroke ctrlZKey = KeyStroke.getKeyStroke( "control Z" );
        undoMenuItem.setAccelerator( ctrlZKey );
        
        redoMenuItem = new JMenuItem( "Redo" );
        // Adding shortcut control + Y
        KeyStroke ctrlYKey = KeyStroke.getKeyStroke( "control Y" );
        redoMenuItem.setAccelerator( ctrlYKey );
        
        clearMenuItem = new JMenuItem( "Clear" );
        // Adding shortcut control + D
        KeyStroke ctrlDKey = KeyStroke.getKeyStroke( "control D" );
        clearMenuItem.setAccelerator( ctrlDKey );
        
        editMenu.add( undoMenuItem );
        editMenu.add( redoMenuItem );
        editMenu.add( clearMenuItem );
        
        filledCheckBox = new JCheckBox( "Fill Shape" );
        dashedCheckBox = new JCheckBox( "Dashed" );
        gradientCheckBox = new JCheckBox( "Gradient" );
        
        statusLabel = new JLabel( "(0, 0)" );
        strokeWidthLabel = new JLabel( "Stroke Width:" );
        dashLengthLabel = new JLabel( "Dash Length:" );
        
        strokeWidthTextField = new JTextField( 2 );
        dashLengthTextField = new JTextField( 2 );
        
        drawPanel = new ArrayList<DrawPanel>();
        
        color1Button = new JButton();
        color2Button = new JButton();
        
        shapeComboBox = new JComboBox( shapeNames ); 
        shapeComboBox.setMaximumRowCount( 4 );
        
        // Creating preferences window components
        prefsFrame = new JFrame( "Preferences" );
        prefsFrame.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        prefsFrame.setVisible( false );
        prefsFrame.setLayout( new BorderLayout() );
        
        prefsPanel = new JPanel();
        prefsPanel.setLayout( new BoxLayout( prefsPanel, BoxLayout.Y_AXIS ) );
        prefsButtonsPanel = new JPanel();
        prefsButtonsPanel.setLayout( new FlowLayout() );
        prefsStrokeWidthPanel = new JPanel();
        prefsStrokeWidthPanel.setLayout( new FlowLayout() );
        prefsDashLengthPanel = new JPanel();
        prefsDashLengthPanel.setLayout( new FlowLayout() );
        prefsColor1Panel = new JPanel();
        prefsColor1Panel.setLayout( new FlowLayout() );
        prefsColor2Panel = new JPanel();
        prefsColor2Panel.setLayout( new FlowLayout() );
        prefsShapePanel = new JPanel();
        prefsShapePanel.setLayout( new FlowLayout() );
        
        prefsHeadingLabel = new JLabel( "Set Default Values" );
        prefsFilledCheckBox = new JCheckBox( "Fill Shape" );
        prefsDashedCheckBox = new JCheckBox( "Dashed" );
        prefsGradientCheckBox = new JCheckBox( "Gradient" );
        prefsStrokeWidthLabel = new JLabel( "Stroke Width:" );
        prefsDashLengthLabel = new JLabel( "Dash Length:" );
        prefsColor1Label = new JLabel( "Color 1:" );
        prefsColor2Label = new JLabel( "Color 2:" );
        prefsShapeLabel = new JLabel( "Shape:" );
        prefsShapeComboBox = new JComboBox( shapeNames ); 
        prefsShapeComboBox.setMaximumRowCount( 4 );
        prefsStrokeWidthTextField = new JTextField( 2 );
        prefsDashLengthTextField = new JTextField( 2 );
        prefsSaveButton = new JButton( "Save" );
        prefsCancelButton = new JButton( "Cancel" );
        prefsColor1Button = new JButton();
        prefsColor2Button = new JButton();
        
        // Set default values of components
        try {
            Scanner fileInput = new Scanner( new File("preferences.txt") );
            
            try {
                if (fileInput.nextInt() == 1)
                    defaultShapeFilled = true;
                else
                    defaultShapeFilled = false;
                if (fileInput.nextInt() == 1)
                    defaultShapeDashed = true;
                else
                    defaultShapeDashed = false;
                if (fileInput.nextInt() == 1)
                    defaultShapeGradient = true;
                else
                    defaultShapeGradient = false;
                defaultStrokeWidth = fileInput.nextInt();
                defaultDashLength = fileInput.nextInt();
                defaultShapeType = fileInput.nextInt();
                defaultColor1 = new Color( fileInput.nextInt(), fileInput.nextInt(), fileInput.nextInt() );
                defaultColor2 = new Color( fileInput.nextInt(), fileInput.nextInt(), fileInput.nextInt() );
                
            }
            catch ( InputMismatchException inputMismatchException ) {
                JOptionPane.showMessageDialog( null, "Error reading preferences.txt.", "ERROR", 0 );
                fileInput.next(); // Skip the invalid data
            }
            fileInput.close();
        }
        catch ( IOException ioException ) {
        	
	        try {
	            PrintWriter fileOutput = new PrintWriter( "preferences.txt" );
	            
	            fileOutput.println( prefsFilledText );
	            fileOutput.println( prefsDashedText );
	            fileOutput.println( prefsGradientText );
	            fileOutput.println( prefsStrokeWidthText );
	            fileOutput.println( prefsDashLengthText );
	            fileOutput.println( prefsShapeText );
	            fileOutput.println( prefsColor1Text );
	            fileOutput.println( prefsColor2Text );
	            fileOutput.close();
	            
        	} catch ( Exception e ) {
                JOptionPane.showMessageDialog( null, "Error reading preferences.txt.", "ERROR", 0 );
        	}
        }
        
        // Initialize current values
        currentShapeFilled = defaultShapeFilled;
        currentShapeDashed = defaultShapeDashed;
        currentShapeGradient = defaultShapeGradient;
        currentStrokeWidth = defaultStrokeWidth;
        currentDashLength = defaultDashLength;
        currentShapeType = defaultShapeType;
        currentColor1 = defaultColor1;
        currentColor2 = defaultColor2;
        
        // Call method to set default values
        setCurrentValues();
        
        // Set components' default appearance
        filledCheckBox.setSelected( currentShapeFilled );
        dashedCheckBox.setSelected( currentShapeDashed );
        gradientCheckBox.setSelected( currentShapeGradient );
        shapeComboBox.setSelectedIndex( currentShapeType );
        prefsFilledCheckBox.setSelected( defaultShapeFilled );
        prefsDashedCheckBox.setSelected( defaultShapeDashed );
        prefsGradientCheckBox.setSelected( defaultShapeGradient );
        prefsShapeComboBox.setSelectedIndex( defaultShapeType );
        
        color1Button.setBackground( defaultColor1 );
        color2Button.setBackground( defaultColor2 );
        color1Button.setPreferredSize( new Dimension( 25, 25 ) );
        color2Button.setPreferredSize( new Dimension( 25, 25 ) );
        prefsColor1Button.setBackground( defaultColor1 );
        prefsColor2Button.setBackground( defaultColor2 );
        prefsColor1Button.setPreferredSize( new Dimension( 25, 25 ) );
        prefsColor2Button.setPreferredSize( new Dimension( 25, 25 ) );
        
        if ( dashedCheckBox.isSelected() )
            dashLengthTextField.setEditable( true );
        else
            dashLengthTextField.setEditable( false );
        
        // Adding components
        prefsButtonsPanel.add( prefsSaveButton );
        prefsButtonsPanel.add( prefsCancelButton );
        prefsStrokeWidthPanel.add( prefsStrokeWidthLabel );
        prefsStrokeWidthPanel.add( prefsStrokeWidthTextField );
        prefsDashLengthPanel.add( prefsDashLengthLabel );
        prefsDashLengthPanel.add( prefsDashLengthTextField );
        prefsColor1Panel.add( prefsColor1Label );
        prefsColor1Panel.add( prefsColor1Button );
        prefsColor2Panel.add( prefsColor2Label );
        prefsColor2Panel.add( prefsColor2Button );
        prefsShapePanel.add( prefsShapeLabel );
        prefsShapePanel.add( prefsShapeComboBox );
        
        // Align preferences components to center
        prefsHeadingLabel.setAlignmentX( Component.CENTER_ALIGNMENT );
        prefsFilledCheckBox.setAlignmentX( Component.CENTER_ALIGNMENT );
        prefsDashedCheckBox.setAlignmentX( Component.CENTER_ALIGNMENT );
        prefsGradientCheckBox.setAlignmentX( Component.CENTER_ALIGNMENT );
        prefsStrokeWidthPanel.setAlignmentX( Component.CENTER_ALIGNMENT );
        prefsDashLengthPanel.setAlignmentX( Component.CENTER_ALIGNMENT );
        prefsColor1Panel.setAlignmentX( Component.CENTER_ALIGNMENT );
        prefsColor2Panel.setAlignmentX( Component.CENTER_ALIGNMENT );
        prefsShapePanel.setAlignmentX( Component.CENTER_ALIGNMENT );
        prefsButtonsPanel.setAlignmentX( Component.CENTER_ALIGNMENT );
        
        prefsPanel.add( prefsHeadingLabel );
        prefsPanel.add( prefsFilledCheckBox );
        prefsPanel.add( prefsDashedCheckBox );
        prefsPanel.add( prefsGradientCheckBox );
        prefsPanel.add( prefsStrokeWidthPanel );
        prefsPanel.add( prefsDashLengthPanel );
        prefsPanel.add( prefsShapePanel );
        prefsPanel.add( prefsColor1Panel );
        prefsPanel.add( prefsColor2Panel );
        prefsPanel.add( prefsButtonsPanel );
        
        prefsFrame.add( prefsPanel, BorderLayout.CENTER );
        
        interfacePanel = new JPanel();
        interfacePanel.setLayout( new FlowLayout() );
        interfacePanel.add( strokeWidthLabel );
        interfacePanel.add( strokeWidthTextField );
        interfacePanel.add( dashLengthLabel );
        interfacePanel.add( dashLengthTextField );
        interfacePanel.add( filledCheckBox );
        interfacePanel.add( dashedCheckBox );
        interfacePanel.add( gradientCheckBox );
        interfacePanel.add( shapeComboBox );
        interfacePanel.add( color1Button );
        interfacePanel.add( color2Button );
        
        coordinatePanel = new JPanel();
        coordinatePanel.setLayout( new FlowLayout() );
        coordinatePanel.add( statusLabel );
        
        add( interfacePanel, BorderLayout.NORTH );
        add( coordinatePanel, BorderLayout.SOUTH );
        add( paintDesktopPane, BorderLayout.CENTER );
        setJMenuBar( menuBar );
        
        // Action event handling 
        ActionHandler actionHandler = new ActionHandler();
        color1Button.addActionListener( actionHandler );
        color2Button.addActionListener( actionHandler );
        newMenuItem.addActionListener( actionHandler );
        openMenuItem.addActionListener( actionHandler );
        saveMenuItem.addActionListener( actionHandler );
        aboutMenuItem.addActionListener( actionHandler );
        prefsMenuItem.addActionListener( actionHandler );
        exitMenuItem.addActionListener( actionHandler );
        undoMenuItem.addActionListener( actionHandler );
        redoMenuItem.addActionListener( actionHandler );
        clearMenuItem.addActionListener( actionHandler );
        strokeWidthTextField.addActionListener( actionHandler );
        dashLengthTextField.addActionListener( actionHandler );
        
        prefsColor1Button.addActionListener( actionHandler );
        prefsColor2Button.addActionListener( actionHandler );
        prefsSaveButton.addActionListener( actionHandler );
        prefsCancelButton.addActionListener( actionHandler );
        prefsStrokeWidthTextField.addActionListener( actionHandler );
        prefsDashLengthTextField.addActionListener( actionHandler );
        
        // Item event handling
        ItemHandler itemHandler = new ItemHandler();
        filledCheckBox.addItemListener( itemHandler );
        dashedCheckBox.addItemListener( itemHandler );
        gradientCheckBox.addItemListener( itemHandler );
        shapeComboBox.addItemListener( itemHandler );
        
        prefsFilledCheckBox.addItemListener( itemHandler );
        prefsDashedCheckBox.addItemListener( itemHandler );
        prefsGradientCheckBox.addItemListener( itemHandler );
        prefsShapeComboBox.addItemListener( itemHandler );
    } // end DrawFrame constructor
    
    // private inner class for ActionListener event handling
    private class ActionHandler implements ActionListener 
    {
        @SuppressWarnings("unused")
		public void actionPerformed( ActionEvent event )
        {
            // exception handling, in case an int isn't entered
            try {
                if ( event.getSource() == strokeWidthTextField )
                {
                    int width = Integer.parseInt( event.getActionCommand() );
					for ( int i = 0; i <= (drawPanel.size()-1); i++ ) 
						drawPanel.get( i ).setCurrentShapeStrokeWidth( width );
					currentStrokeWidth = width;
                }
                if ( event.getSource() == dashLengthTextField )
                {
                    int length = Integer.parseInt( event.getActionCommand() );
					for ( int i = 0; i <= (drawPanel.size()-1); i++ ) 
						drawPanel.get( i ).setCurrentShapeDashLength( length );
					currentDashLength = length;
                }
                if ( event.getSource() == prefsStrokeWidthTextField )
                {
                    prefsStrokeWidthText = event.getActionCommand();
                    int exceptionText = Integer.parseInt( event.getActionCommand() );
                }
                if ( event.getSource() == prefsDashLengthTextField )
                {
                    prefsDashLengthText = event.getActionCommand();
                    int exceptionText = Integer.parseInt( event.getActionCommand() );
                }
            } catch ( NumberFormatException e ) {
                JOptionPane.showMessageDialog( null, "Please enter a valid integer.", "ERROR", 0);
            }
            if ( event.getSource() == undoMenuItem )
            {
                getCurrentDrawPanel().clearLastShape();
            }
            if ( event.getSource() == redoMenuItem )
            {
                getCurrentDrawPanel().redoLastShape();
            }
            if ( event.getSource() == clearMenuItem )
            {
                getCurrentDrawPanel().clearDrawing();
            }
            if ( event.getSource() == color1Button )
            {
                Color color1 = currentColor1;
                Color selectedColor1 = JColorChooser.showDialog( null, "Choose Color 1", color1 );
                if ( selectedColor1 != null )
                {
					for ( int i = 0; i <= (drawPanel.size()-1); i++ ) 
						drawPanel.get( i ).setCurrentShapeColor1( selectedColor1 );
					currentColor1 = selectedColor1;
                    color1Button.setBackground( selectedColor1 );
                }
            }
            if ( event.getSource() == color2Button )
            {
                Color color2 = currentColor2;
                Color selectedColor2 = JColorChooser.showDialog( null, "Choose Color 2", color2 );
                if ( selectedColor2 != null )
                {
					for ( int i = 0; i <= (drawPanel.size()-1); i++ ) 
						drawPanel.get( i ).setCurrentShapeColor2( selectedColor2 );
					currentColor2 = selectedColor2;
                    color2Button.setBackground( selectedColor2 );
                }
            }
            if ( event.getSource() == prefsColor1Button )
            {
                Color currentDefaultColor1 = defaultColor1;
                Color defaultColor1 = JColorChooser.showDialog( null, "Choose Color 1", currentDefaultColor1 );
                if ( defaultColor1 != null )
                {
                    prefsColor1Text = Integer.toString(defaultColor1.getRed())+"\n"+Integer.toString(defaultColor1.getGreen())+"\n"+Integer.toString(defaultColor1.getBlue());
                    prefsColor1Button.setBackground( defaultColor1 );
                    prefsColor1Button.setBackground( defaultColor1 );
                }
            }
            if ( event.getSource() == prefsColor2Button )
            {
                Color currentDefaultColor2 = defaultColor2;
                Color defaultColor2 = JColorChooser.showDialog( null, "Choose Color 2", currentDefaultColor2 );
                if ( defaultColor2 != null )
                {
                    prefsColor2Text = Integer.toString(defaultColor2.getRed())+"\n"+Integer.toString(defaultColor2.getGreen())+"\n"+Integer.toString(defaultColor2.getBlue());
                    prefsColor2Button.setBackground( defaultColor2 );
                }
            }
            if ( event.getSource() == prefsSaveButton )
            {
                try {
                    PrintWriter fileOutput = new PrintWriter( "preferences.txt" );
                    
                    fileOutput.println( prefsFilledText );
                    fileOutput.println( prefsDashedText );
                    fileOutput.println( prefsGradientText );
                    fileOutput.println( prefsStrokeWidthText );
                    fileOutput.println( prefsDashLengthText );
                    fileOutput.println( prefsShapeText );
                    fileOutput.println( prefsColor1Text );
                    fileOutput.println( prefsColor2Text );
                    fileOutput.close();
                }
                catch ( IOException ioException ) {
                    JOptionPane.showMessageDialog( null, "Error with preferences.txt.", "ERROR", 0);
                }
                Object[] options = {"OK"};
                JOptionPane.showOptionDialog( null, "Please restart SuperPaint for your changes to take effect.", "Restart", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, options, options[0] );
                prefsFrame.dispose();
            }
            if ( event.getSource() == prefsCancelButton )
            {
                prefsFrame.dispose();
            }
            if (event.getSource() == newMenuItem)
                createDrawWindow();
            if (event.getSource() == openMenuItem) {
            	int returnVal = fileChooser.showOpenDialog( DrawFrame.this );
            	if ( returnVal == JFileChooser.APPROVE_OPTION ) {
            		try {
            			BufferedImage image = ImageIO.read( fileChooser.getSelectedFile() );
            			createDrawWindow();
            			paintInternalFrame.get( paintInternalFrame.size()-1 ).setSize( image.getWidth(), image.getHeight() );
            			paintInternalFrame.get( paintInternalFrame.size()-1 ).setTitle( fileChooser.getSelectedFile().getName() );
            			drawPanel.get( drawPanel.size()-1 ).loadImage( image );
            		} catch( Exception e ) {
                        JOptionPane.showMessageDialog( null, "Error opening file.", "ERROR", 0 );
            		}
                }
            }
            if (event.getSource() == saveMenuItem) {
            	try {
	            	fileChooser.setSelectedFile( new File( paintDesktopPane.getSelectedFrame().getTitle() ) );
	            	int returnVal = fileChooser.showSaveDialog( DrawFrame.this );
	    			BufferedImage image = ScreenImage.createImage( getCurrentDrawPanel() );
        			String fileName = fileChooser.getSelectedFile().getName();
	            	if ( returnVal == JFileChooser.APPROVE_OPTION ) {
	            		try {
	            			fileName = fileName.indexOf('.') == -1 ? fileName+".png" : fileName;
	            			ScreenImage.writeImage( image, fileChooser.getCurrentDirectory(), fileName );
	            			paintDesktopPane.getSelectedFrame().setTitle( fileName );
	            		} catch( Exception e ) {
	                        JOptionPane.showMessageDialog( null, "Error saving file. Make sure you have a valid file extension.", "ERROR", 0 );
	            		}
	            	}
            	} catch( java.lang.NullPointerException e ) {
            		JOptionPane.showMessageDialog( null, "Error saving file. You don't have any image selected to save!", "ERROR", 0);
            	}
            }
            if ( event.getSource() == aboutMenuItem )
                JOptionPane.showMessageDialog( null, "Author: Tiger Dong\nLast Revised: January 14th, 2017\nCompiler: JDK 8.0_91\nVersion: Java SE 8", "About", 1 );
            if ( event.getSource() == prefsMenuItem )
            {
                prefsFrame.setSize( 400, 400 );;
                prefsFrame.setVisible( true );
            }
            if ( event.getSource() == exitMenuItem )
                System.exit(0);
            
        } // end method actionPerformed
    } // end private inner class ActionHandler
    
    // private inner class for ItemListener event handling
    private class ItemHandler implements ItemListener 
    {
        public void itemStateChanged( ItemEvent event )
        {
            if ( event.getSource() == filledCheckBox )
            {
                if ( filledCheckBox.isSelected() )
                {
					for ( int i = 0; i <= (drawPanel.size()-1); i++ ) 
						drawPanel.get( i ).setCurrentShapeFilled( true );
					currentShapeFilled = true;
                }
                else
                {
					for ( int i = 0; i <= (drawPanel.size()-1); i++ ) 
						drawPanel.get( i ).setCurrentShapeFilled( false );
					currentShapeFilled = false;
                }
            }
            if ( event.getSource() == dashedCheckBox )
            {
                if ( dashedCheckBox.isSelected() )
                {
					for ( int i = 0; i <= (drawPanel.size()-1); i++ ) 
						drawPanel.get( i ).setCurrentShapeDashed( true );
					currentShapeDashed = true;
                    dashLengthTextField.setEditable( true );
                }
                else
                {
					for ( int i = 0; i <= (drawPanel.size()-1); i++ ) 
						drawPanel.get( i ).setCurrentShapeDashed( false );
					currentShapeDashed = false;
                    dashLengthTextField.setEditable( false );
                }
            }
            if ( event.getSource() == gradientCheckBox )
            {
                if ( gradientCheckBox.isSelected() )
                {
					for ( int i = 0; i <= (drawPanel.size()-1); i++ ) 
						drawPanel.get( i ).setCurrentShapeGradient( true );
					currentShapeGradient = true;
                }
                else
                {
					for ( int i = 0; i <= (drawPanel.size()-1); i++ ) 
						drawPanel.get( i ).setCurrentShapeGradient( false );
					currentShapeGradient = false;
                }
            }
            if ( event.getSource() == shapeComboBox )
            {
				for ( int i = 0; i <= (drawPanel.size()-1); i++ ) 
					drawPanel.get( i ).setCurrentShapeType( shapeComboBox.getSelectedIndex() );
				currentShapeType = shapeComboBox.getSelectedIndex();
            }
            if ( event.getSource() == prefsFilledCheckBox )
            {
                if ( prefsFilledCheckBox.isSelected() )
                    prefsFilledText = "1";
                else
                    prefsFilledText = "0";
            }
            if ( event.getSource() == prefsDashedCheckBox )
            {
                if ( prefsDashedCheckBox.isSelected() )
                    prefsDashedText = "1";
                else
                    prefsDashedText = "0";
            }
            if ( event.getSource() == prefsGradientCheckBox )
            {
                if ( prefsGradientCheckBox.isSelected() )
                    prefsGradientText = "1";
                else
                    prefsGradientText = "0";
            }
            if ( event.getSource() == prefsShapeComboBox )
                prefsShapeText = Integer.toString(prefsShapeComboBox.getSelectedIndex());
        } // end method itemStateChanged
    } // end private inner class ItemHandler

    // private method that updates paintInternalFrame based on whether or not any windows have been closed
    private void updateInternalFrames() {
    	for (int i = paintInternalFrame.size()-1; i >= 0; i--) {
    		if (paintInternalFrame.get(i).isClosed()) {
    			drawPanel.remove(i);
    			paintInternalFrame.remove(i);
    			untitledNumbers.remove(i);
    		}
    	}
    } // end method updateInternalFrames
    
    // private method to add an additional JInternalFrame draw window
    private void createDrawWindow()
    {
    	updateInternalFrames();
    	int currentUntitledNumber = max(untitledNumbers)+1;
    	boolean currentNumInUse = false;
    	for (int i = max(untitledNumbers); i > 0; i--) {
    		for (int j = untitledNumbers.size()-1; j >= 0; j--) {
    			if (i == untitledNumbers.get(j))
    				currentNumInUse = true;
    		}
    		if (currentNumInUse == false)
    			currentUntitledNumber = i;
    		currentNumInUse = false;
    	}
    	
		String text = String.format("Untitled%d.png", currentUntitledNumber);
		untitledNumbers.add(currentUntitledNumber);
        paintInternalFrame.add( new JInternalFrame( text, true, true, true, true ) );
		paintInternalFrame.get( paintInternalFrame.size()-1 ).setSize( 750, 500 );
        paintInternalFrame.get( paintInternalFrame.size()-1 ).setVisible( true );
		paintInternalFrame.get( paintInternalFrame.size()-1 ).setDefaultCloseOperation( DISPOSE_ON_CLOSE );
		drawPanel.add( new DrawPanel( statusLabel ) );
		paintInternalFrame.get( paintInternalFrame.size()-1 ).add( drawPanel.get( drawPanel.size()-1 ) );
		paintDesktopPane.add( paintInternalFrame.get( paintInternalFrame.size()-1 ) );
		paintInternalFrame.get( paintInternalFrame.size()-1 ).moveToFront();
		setCurrentValues();
		
    } // end createDrawWindow
    
    
    // method that returns the max value in an integer ArrayList
    private Integer max(ArrayList<Integer> array) {
    	Integer max = 0;
    	for (int i = 0; i < array.size(); i++) {
    		max = (max == null || array.get(i) > max) ? array.get(i) : max;
    	}
    	return max;
    }
    
    // method that retrieves the currently selected drawPanel
    private DrawPanel getCurrentDrawPanel()
    {
    	updateInternalFrames();
		for (int i = 0; i <= (paintDesktopPane.getAllFrames().length-1); i++) {
			if ( paintDesktopPane.getSelectedFrame() == paintInternalFrame.get( i ) )
				return drawPanel.get( i );
		}
		return null;
    } // end getCurrentDrawPanel
    
    // method that sets the default values of all possible draw panels
    private void setCurrentValues()
    {
		// set whether the shape is filled
		for ( int i = 0; i <= (drawPanel.size()-1); i++ ) {
			drawPanel.get( i ).setCurrentShapeFilled( currentShapeFilled );
			drawPanel.get( i ).setCurrentShapeDashed( currentShapeDashed );
			drawPanel.get( i ).setCurrentShapeGradient( currentShapeGradient );
			drawPanel.get( i ).setCurrentShapeStrokeWidth( currentStrokeWidth );
			drawPanel.get( i ).setCurrentShapeDashLength( currentDashLength );
			drawPanel.get( i ).setCurrentShapeType( currentShapeType );
			drawPanel.get( i ).setCurrentShapeColor1( currentColor1 );
			drawPanel.get( i ).setCurrentShapeColor2( currentColor2 );
		}
    } // end setCurrentValues
    
} // end class DrawFrame