package component2;
//importing java packages to be used in the program
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//inherits built-in JFrame class
public class CurrencyPanel extends JPanel{
	
	/*----- declaring  components -------*/
	
	
	
	//method defination to design and return menu
	public JMenuBar setupMenu() {
		
		/*instance of JMenuBar
		 * that will be used to display menu bar on the frame*/
		JMenuBar menuBar = new JMenuBar();
		menuBar.setToolTipText("menu bar of the app"); //on hover information shown for menubar
		
		/*--------- JMenu ----------*/
		/*object of JMenu to be added to menu bar
		 * parameter passed is for title of menu*/
		JMenu fileMenu = new JMenu("File");
		JMenu helpMenu = new JMenu("Help");
		
		//add image icon for JMenu
		fileMenu.setIcon(new ImageIcon("fileicon.png"));
		helpMenu.setIcon(new ImageIcon("helpicon.png"));
		
		//add tooltips to JMenu 
		//shows message passed on parameter on hover
		fileMenu.setToolTipText("Click to open exit option");
		helpMenu.setToolTipText("Provides help for using this app");
				
		//adding mnemonics to JMenu
		//keyboard shortcut for accessing JMenu
		fileMenu.setMnemonic(KeyEvent.VK_F); 
		helpMenu.setMnemonic(KeyEvent.VK_H); 
		
		/*--------- JMenuItem ---------*/
		//for closing the application
		JMenuItem exitMenu = new JMenuItem("Exit");
		//to show purpose of application
		JMenuItem aboutMenu=new JMenuItem("About");
		
		//add image icon to JMenuItem
		exitMenu.setIcon(new ImageIcon("exiticon.png"));
		aboutMenu.setIcon(new ImageIcon("abouticon.png"));
				
		//set Mnemonic forJMenuItem
		exitMenu.setMnemonic(KeyEvent.VK_X);//for Exit
		aboutMenu.setMnemonic(KeyEvent.VK_A); //for About
				
		//add tooltips to JMenuItem
		exitMenu.setToolTipText("Closes the application");
		aboutMenu.setToolTipText("Gives information about the application");
		
		/*----------- Dialogue box------------*/
		/* JOptionPane class used to pop up a dialogue box
		 * listener to make Exit work to make application to close when pressed
		 * show dialog box to confirm operation before closing*/
		//lambda, shortblock of code used as only one method is needed
		exitMenu.addActionListener(e -> {
			/* show yes or no button*/
			int confirmation=JOptionPane.showConfirmDialog(new JFrame(), "Are you sure you want to exit?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
			//exit if user chooses yes option
			if(confirmation==JOptionPane.YES_OPTION)
				System.exit(0); //closes application
		});
		
		/*Dialogue box when About is selected
		 * to display information about system on different frame*/
		aboutMenu.addActionListener(m ->{
			/*message to be shown on dialogue box*/
			String aboutMessage="Author : Nimisha Raut \n"
					+ " This Application is designed with aim of converting British pounds with other currency. \n"
					+ "Other currency can be converted to British pounds as well\n"
					+ "Conversion are also counted to sum up total conversion operations\n"
					+"Copyright ©Nimisha Raut 2021 ";
			// Message Dialogue Box
			JOptionPane.showMessageDialog(new JFrame(),aboutMessage);
		});
		
		/*--------- Add---------*/
		/*add JMenuItem to JMenu 
		 * to show as pull down menu*/
		fileMenu.add(exitMenu);
		helpMenu.add(aboutMenu);
		
		//add JMenu on JMenuBar
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);
		
		//returns final menu designed
		return menuBar;
		
	}

}
