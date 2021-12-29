package component2;
//importing java packages to be used in the program
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//inherits built-in JFrame class
public class CurrencyPanel extends JPanel{
	
	/*----- declaring  components -------*/
	
	/*String array of options for currency options available
	 * one copy for all object of this class
	 * final so will be constant
	 * static so value will be same for all instance */
	private final static String[] currencyOption = { "JPY","EUR","USD","AUD","CAD","KRW","THB","AED"};
	private JComboBox<String> currencyComboBox; //option selection box
	
	/*Button that will cause conversion action to be performed when clicked*/
	private JButton convertButton;
	private JLabel resultValue; //for converted result
	private JTextField inputData; //area for user input
	/*additonal button to clear back to start look*/
	private JButton resetButton;
	/*additional label displaying counting total number of conversions performed */
	private JLabel countLabel;
	/*integer vaiable to store total conversions initially zero*/
	private int conversionCount=0;
	/*additional checkbox for switching between reverse and normal conversion*/
	private JCheckBox reverseBox;
	
	
	
	/*----- Default constructor -------
	 * to design and add all components to panel
	 * this will be added to frame in Converter.java */
	public CurrencyPanel()
	{
		/*add String array to combobox*/
		currencyComboBox = new JComboBox<String>(currencyOption);
		//add tooltip 
		currencyComboBox.setToolTipText("Select from list of currency option available");
		
		/*JLabel to instruct user to input  value*/
		JLabel inputLabel = new JLabel("Enter value:");
		inputLabel.setToolTipText("Message to enter amount to be converted");
		
		/*Button that will cause conversion action to be performed when clicked*/
		convertButton = new JButton("Convert");
		convertButton.setToolTipText("press to convert entered currency amount");
		
		/*result of calculation will be blank --- by default*/
		resultValue = new JLabel("---");
		resultValue.setToolTipText("Result of the conversion");
		
		/*JTextField with 5 columns 
		 * user input value in this field*/
		inputData = new JTextField(5);
		inputData.setToolTipText("Enter amount that needs to be converted here" );
		
		
		//additional button to clear
		resetButton=new JButton ("Reset");
		resetButton.setToolTipText("press to reset all datas");
		
		//setting additional label for counting
		countLabel=new JLabel("Conversion count : "+conversionCount );
		countLabel.setToolTipText("Total number of conversions performed");
		
		//checkbox to reverse conversion
		reverseBox=new JCheckBox("reverse conversion");
		reverseBox.setToolTipText("Converesion done from selected unit to pounds when selected.");
		
		
		/*----- Changing Layout -------*/
		//create object of panel for layout
		JPanel innerPanel=new JPanel();
		innerPanel.setPreferredSize(new Dimension (300, 180));
		innerPanel.setBackground(new Color(137, 181, 217));
		//use borderlayout for this panel
		innerPanel.setLayout(new BorderLayout());
		
				
		//panel that will be inside innerPanel
		JPanel innerPanel2=new  JPanel();
		innerPanel2.setPreferredSize(new Dimension (300, 100));
		innerPanel2.setBackground(new Color(137, 181, 217));
				
				
		//color of components bg
		inputLabel.setBackground(new Color(137, 181, 217));
		convertButton.setBackground(new Color(137, 181, 217));
		resultValue.setBackground(new Color(137, 181, 217));
		resetButton.setBackground(new Color(137, 181, 217));
		countLabel.setBackground(new Color(137, 181, 217));
		reverseBox.setBackground(new Color(137, 181, 217));
			
		/*----- Add Components for CurrencyPanel -------*/
		//add inner panel to currencyPanel
		add(innerPanel);
		//add components in order to be displayed in innerPaneel
		innerPanel.add(currencyComboBox, BorderLayout.NORTH);
		innerPanel.add(inputLabel, BorderLayout.WEST);
		innerPanel.add(inputData, BorderLayout.CENTER);
		innerPanel.add(resultValue, BorderLayout.EAST);
		//add another panel in south
		innerPanel.add(innerPanel2, BorderLayout.SOUTH);
		//add components to innerPanel2
		innerPanel2.add(convertButton);
		innerPanel2.add(resetButton);
		innerPanel2.add(countLabel);
		innerPanel2.add(reverseBox);
				
		//appearance of CurrencyPanel
		setPreferredSize(new Dimension(500, 200));
		setBackground(new Color(137, 181, 217));
	}
	
	
	
	
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
