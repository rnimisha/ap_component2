package component2;
//importing java packages to be used in the program
//built-in package
import javax.swing.JFrame;

/**
 *Driver program for testing GUI based currency conversion program.
 *frame which is part of JFrame is used here
 *object of CurrenyPanel is instantiated and added to the frame
 * @author Nimisha Raut
 */

//class defination for Converter
public class Converter {
	
	//	main method
	public static void  main(String[] args)
	{
		//create JFrame object as frame
		//paramerer passed to set the title of window frame
		JFrame frame=new JFrame("Currency Converter");
		//to prevent application from keep running after frame dissppers on clicking x exit
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        /*instance of class CurrencyPanel that inherists JPanel
         * invokes default constructor where panel is designed*/
        CurrencyPanel panel = new CurrencyPanel();
        
        //call method to set menu bar for the frame
        //parameter passed is a method that returns JMenuBar
        frame.setJMenuBar(panel.setupMenu());
		
        //add panel to frame
        //getContentPane() method retrieves the content pane layer so that you can add an object to it
        frame.getContentPane().add(panel);
		
        /*pack() method sets the frame size as per need 
         * packs components within the frame window*/
        frame.pack();
        
        //make panel visible and appear on screen
        frame.setVisible(true);
		
	}

}
