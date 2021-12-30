package component2;
//importing built-in java classes to be used in the program
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The main graphical panel used to display conversion components.
 * Java GUI program that allows different conversion to occur through user input.
 * User needs to input a currency amount and that currency will be converted to selected currency Option.
 * @author Nimisha Raut
 */
@SuppressWarnings("serial")

//CurrencyPanel inherits built-in JFrame class
public class CurrencyPanel extends JPanel{
	
	/*----------- declaring components------------*/
	
	/* list interface for storing Object of class Currency
	 * will store all valid currency data from txt file
	 * one copy for all object of this class*/
	private static List<Currency> currencies= new ArrayList<Currency>();
	//pop up option selection box for selecting currency initially empty
	private JComboBox<String> currencyComboBox=new JComboBox<String>(); 
	
	/*Button that will cause conversion action to be performed when clicked*/
	private JButton convertButton;
	/*additonal button to clear back to start look*/
	private JButton resetButton;
	private JLabel resultValue; //for converted result
	private JTextField enteredAmount; //area for user input
	/*additional label displaying counting total number of conversions performed */
	private JLabel countLabel;
	/*additional checkbox for switching between reverse and normal conversion*/
	private JCheckBox reverseBox;
	
	/*integer vaiable to store total conversions initially zero*/
	private int conversionCount=0;
	/*will be set true if reverse option is selected*/
	private boolean checked=false;
	
	
	/*----------------- Default constructor -------------------
	 * to design and add all components to panel
	 * this will be added to frame in Converter.java */
	public CurrencyPanel()
	{
		//loading currency text file at start
		loadCurrencyFile(new File("currency.txt"));
		
		/*--------- JComboBox --------*/
		//combobox is populated when loadCurrencyFile is called
		currencyComboBox.setToolTipText("Select from list of currency option available");//add tooltip 

		/*--------- JLabel --------*/
		/*JLabel to instruct user to input  value*/
		JLabel inputLabel = new JLabel("Enter Amount:");
		inputLabel.setToolTipText("Message to enter amount to be converted");
		/*result of calculation will be blank --- by default*/
		resultValue = new JLabel("---");
		resultValue.setToolTipText("Result of the conversion");
		//change font for result 
		resultValue.setFont(new Font("Verdana", Font.PLAIN, 18));
		//setting additional label for counting
		countLabel=new JLabel("Conversion count : "+conversionCount );
		countLabel.setToolTipText("Total number of conversions performed");
		
		/*--------- JTextField --------*/
		/*JTextField with 5 columns 
		 * user input value in this field*/
		enteredAmount = new JTextField(5);
		enteredAmount.setToolTipText("Enter amount that needs to be converted here" );
		
		/*--------- JButton --------*/
		/*Button that will cause conversion action to be performed when clicked*/
		convertButton = new JButton("Convert");
		convertButton.setToolTipText("press to convert entered currency amount");
		
		//additional button to clear
		resetButton=new JButton ("Reset");
		resetButton.setToolTipText("press to reset all datas");
		//Button will initially be disabled
		resetButton.setEnabled(false);
		
		/*--------- JCheckBox --------*/
		//checkbox to reverse conversion
		reverseBox=new JCheckBox("reverse conversion");
		reverseBox.setToolTipText("Converesion done from selected unit to pounds when selected.");
		
		/*----------------Listeners---------------
		 * event handler definations for certain actions*/
		
		//for converting amount and incrementing conversionCount 
		ConvertListener convertAmount=new ConvertListener();
		//to call actionlistener when button is pressed
		convertButton.addActionListener(convertAmount); 
		//to call actionlistener when enter is pressed on textfield
		enteredAmount.addActionListener(convertAmount); 
		
		//for reverse conversion
		//itemListener as it is checkbox
		reverseBox.addItemListener(new ReverseListener());
		
		//for resetting back to default except currency file
		resetButton.addActionListener(e ->{
			clearValues(); //call method to reset 
		});
		
		
		/*----- Changing Layout -------*/
		//create object of panel for layout
		JPanel innerPanel=new JPanel();
		innerPanel.setPreferredSize(new Dimension (300, 190));
		innerPanel.setBackground(new Color(237, 216, 199));
		//use borderlayout for this panel
		innerPanel.setLayout(new BorderLayout(10,10));
				
		//panel that will be inside innerPanel
		JPanel innerPanel2=new  JPanel();
		innerPanel2.setPreferredSize(new Dimension (300, 100));
		innerPanel2.setBackground(new Color(237, 216, 199));
				
				
		//color of components bg
		inputLabel.setBackground(new Color(237, 216, 199));
		convertButton.setBackground(new Color(237, 216, 199));
		resultValue.setBackground(new Color(237, 216, 199));
		resetButton.setBackground(new Color(237, 216, 199));
		countLabel.setBackground(new Color(237, 216, 199));
		reverseBox.setBackground(new Color(237, 216, 199));
			
		//adding border
		//object of TitledBorder with LoweredBevelBorder 
		TitledBorder border=new TitledBorder(BorderFactory.createLoweredBevelBorder(), "Result"); 
		border.setTitleJustification(TitledBorder.CENTER);//title text alignment
		resultValue.setBorder(border);// order to reasultValue Component
		
		/*----- Add Components for CurrencyPanel -------*/
		//add inner panel to currencyPanel
		add(innerPanel);
		//add components in order to be displayed in innerPaneel
		resultValue.setHorizontalAlignment(JLabel.CENTER);
		innerPanel.add(resultValue, BorderLayout.NORTH);
		innerPanel.add(inputLabel, BorderLayout.WEST);
		innerPanel.add(enteredAmount, BorderLayout.CENTER);
		innerPanel.add(currencyComboBox, BorderLayout.EAST);
		//add another panel in south
		innerPanel.add(innerPanel2, BorderLayout.SOUTH);
		//add components to innerPanel2
		innerPanel2.add(convertButton);
		innerPanel2.add(resetButton);
		innerPanel2.add(countLabel);
		innerPanel2.add(reverseBox);
				
		//appearance of CurrencyPanel
		setPreferredSize(new Dimension(600, 200));
		setBackground(new Color(237, 216, 199));
		
		
	}
	
	/*----------------- Class Method Definations  -------------------*/
	//method to change options in comboBox
	public void changeComboBox()
	{
		//check if there is any item populated in combobox
		if(currencyComboBox.getItemCount()>0)
		{
			//remove all items
			currencyComboBox.removeAllItems();
		}
		//populate combobox with new items using for each iterator
		for(Currency c:currencies)
		{
			currencyComboBox.addItem(c.getName());//calls method of class Currency
		}	
	}
	
	/*method for loading currency file
	 * takes in file as parameter 
	 * loads and reads from file and checks its content format
	 * stores proper currency details
	 * displays incorrect data details*/
	public void loadCurrencyFile(File file)
	{
		//exception handling
		try {
			//for storing error details of txt files
			String errorMsg="";
			
			//FileInputStream reads data in bytes from UTF8 file in parameter; filereader not used
			//InputStreamReader decodes the byte to character
			//BufferReader to read text from character stream; faster than scanner
			BufferedReader inReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
			
			//reads single line only
			String line = inReader.readLine();
			
			//loop till there is line available
			while ( line != null ) {
				/*split line using , as delimiter
				 * and save split data in array*/
				String [] splitLine = line.split(",");
				
				//check if 3 required data for each currency is present
				if(splitLine.length!=3)
				{
					errorMsg+="Incorrect Delimiter format : "+ line+"\n";
				}
				else //checking further error when there is 3 array elements
				{
					//for storing retrived data
					String name, rate,sign;
					double factor = 0.0;
					//removing white spaces from each data
					name=splitLine[0].trim();
					rate=splitLine[1].trim();
					sign=splitLine[2].trim();
					
					//check if data was empty 
					if(name.isEmpty() || rate.isEmpty() || sign.isEmpty())
					{
						errorMsg+="Value Missing : "+line+"\n";
					}
					else //if all 3 data are present
					{
						//further validation
						//check if conversion factor are in proper format
						//exception handling while extracting abbrevation
						try {
							//convert string to double
							factor=Double.parseDouble(rate);
							
							//extract abbrevation from name
							//start index from after ( and end index is same of ) as it is exclusive
							name = name.substring(name.indexOf("(") + 1, name.indexOf(")"));
							
							/*-------after all validation tests are passed--------*/
							currencies.add(new Currency(name, factor, sign));
						}
						catch(NumberFormatException e)
						{
							//add error message
							errorMsg+="Incorrect Conversion Factor Format : "+ line+"\n";
						}
						catch (StringIndexOutOfBoundsException e)
						{
							//add error message
							errorMsg+="Invalid Currency Name Format : "+ line+"\n";
						}
					}
				}

                // read next line (if available)
                line = inReader.readLine();  
            }
            inReader.close();//close bufferReader
            //show details about if any line in txt file was corrupted
            if(errorMsg!="")
            {
            	JOptionPane.showMessageDialog(new JFrame(),errorMsg,"Some data could not be read!", JOptionPane.WARNING_MESSAGE);
            }
            //call method to change combobox options
            changeComboBox();
            
		}
		catch(FileNotFoundException e)//when file does not exist
		{
			//JOptionPane class used to pop up a message dialogue box
			JOptionPane.showMessageDialog(new JFrame(),"Currency Conversion Rate file is not attached."," File Not Found!",JOptionPane.ERROR_MESSAGE);
		}
		catch(IOException e)//when there is error in reading from file
		{
			//JOptionPane class used to pop up a message dialogue box
			JOptionPane.showMessageDialog(new JFrame(),"Error in reading the file."," Error!",JOptionPane.ERROR_MESSAGE);
			
		}
		catch(Exception e)//incase of other error
		{
			String message= e.getMessage();
			//JOptionPane class used to pop up a message dialogue box
			JOptionPane.showMessageDialog(new JFrame(),message,"Error!", JOptionPane.ERROR_MESSAGE);
		}

	}
	
	//method to reset values 
	public void clearValues()
	{
		///clears input box
		enteredAmount.setText("");
		
		//clears result label
		resultValue.setText("---");
		
		//set count back to zero
		conversionCount=0;
		//show changes
		countLabel.setText("Conversion count : "+conversionCount );
		
		//disable clear button
		resetButton.setEnabled(false);
		
		//to uncheck reverse as well
		reverseBox.setSelected(false);
		
		//change combo box option back to default
		currencyComboBox.setSelectedIndex(0);
		
	}
	
	/*-----------------Menu  ---------------*/
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
		//to load file
		JMenuItem loadMenu=new JMenuItem("Load");
		
		//add image icon to JMenuItem
		exitMenu.setIcon(new ImageIcon("exiticon.png"));
		aboutMenu.setIcon(new ImageIcon("abouticon.png"));
		loadMenu.setIcon(new ImageIcon("loadicon.png"));
				
		//set Mnemonic forJMenuItem
		exitMenu.setMnemonic(KeyEvent.VK_X);//for Exit
		aboutMenu.setMnemonic(KeyEvent.VK_A); //for About
		loadMenu.setMnemonic(KeyEvent.VK_L);//for Load
				
		//add tooltips to JMenuItem
		exitMenu.setToolTipText("Closes the application");
		aboutMenu.setToolTipText("Gives information about the application");
		loadMenu.setToolTipText("To load file for currency rates into the application");
		
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
		
		/*File Chooser Box when Load is selected*/
		loadMenu.addActionListener(e ->{
			//file chooser component for loading currency file
			JFileChooser chooser=new JFileChooser();
			//title of Choose box
            chooser.setDialogTitle("Select a .txt file containing Currency Details");
            //restrict files that can be choosen
            chooser.setAcceptAllFileFilterUsed(false); //all restricted for now
            //
            FileNameExtensionFilter allowedExtension = new FileNameExtensionFilter(".txt Text file", "txt");
            chooser.addChoosableFileFilter(allowedExtension);
			//show dialog box to open a file
			int status = chooser.showOpenDialog(null);
			//if user selects a file
			if(status == JFileChooser.APPROVE_OPTION)
			{
     			//change file name to selected file name
				//load the file into the application
				loadCurrencyFile(chooser.getSelectedFile());
			}
			//to inform about loading completion
			JOptionPane.showMessageDialog(new JFrame(),"Valid Data has been loaded and invalid are discarded","File Loading Completed",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("fileloaded.png"));
			
		});
		
		/*--------- Add---------*/
		/*add JMenuItem to JMenu 
		 * to show as pull down menu*/
		fileMenu.add(loadMenu);
		fileMenu.add(exitMenu);
		helpMenu.add(aboutMenu);
		
		//add JMenu on JMenuBar
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);
		
		//returns final menu designed
		return menuBar;
		
	}


	/*-----------------ActionListener   ---------------*/
	/* defination for ConvertListener
	 * converts amount entered by user to pounds or pounds to selected currency 
	 * and sets result in two decimal place
	 * also increments total conversions performed
	 * will be implementing ActionListener interface */
	public class ConvertListener implements ActionListener
	{

		@Override
		//overriding method as per needed
		//when actionlistener catches an event
		public void actionPerformed(ActionEvent event) 
		{
			
			/*storing after removing white space in front and back from currency entered by user*/
			String amount=enteredAmount.getText().trim();
			
			//check if value entered is null by user
			if(!(amount.isEmpty()))
			{
				//exception handling
				try {
					//convert String to Double 
					double numericAmount = Double.parseDouble(amount);
				
					//check which option is selected according to index
					int index=currencyComboBox.getSelectedIndex();
					//get factor and symbol for that index
					double factor=currencies.get(index).getFactor();//conversion factor
					String symbol=currencies.get(index).getSymbol();//Symbol of currency
					double result=0; //final result
					
					//change decimal places to two
					DecimalFormat df = new DecimalFormat("###,##0.00");
					
					String answer;
					//in case reverse button is not selected
					if(!checked)
					{
						result=numericAmount*factor;
						//df.format returns string
						answer=symbol+df.format(result);
					}
					else {
						result=numericAmount/factor;
						//df.format returns string
						answer="£"+df.format(result);
					}
					
					//change label holding result to converted answer
					resultValue.setText(answer);
					
					//enable clearButton
					resetButton.setEnabled(true);	
					
					//increment conversion count
					conversionCount++;
					//change conversion count label text with updated value
					countLabel.setText("Conversion count : "+conversionCount );
					
				}
				//if format of amount is not numeric then
				catch(NumberFormatException e)
				{
					//pop message dialogue box to inform about incorrect data format error
					JOptionPane.showMessageDialog(new JFrame(),"Please enter data in correct numeric format."," Data format not matched!",JOptionPane.ERROR_MESSAGE);
				}
				
			}
			/*if nothing is entered in the textfield
			 * will display dialogue box with message*/
			else 
			{
				//JOptionPane class used to pop up a message dialogue box
				JOptionPane.showMessageDialog(new JFrame(),"Please enter value on textfield first."," Data Missing!",JOptionPane.ERROR_MESSAGE);
				
			}

		}
	}
	
	
	//listener to reverse units that implements ItemListener
	//when clicked on checkbox
	public class ReverseListener implements ItemListener
	{
		//overriding method as per needed
		//invoked when click or unclicked on checkbox
		public void itemStateChanged(ItemEvent event)
		{
			//if user checks checkbox
			/*checked is used to perform calculation
			 * if true then normal conversion from pounds will happen
			 * if false then other currency will be converted to pounds*/
			if(reverseBox.isSelected())
			{
				resetButton.setEnabled(true); //enable reset button
				checked=true; //reverse checkbox is checked
			}
			else
			{
				checked=false;//reverse checkbox is not checked
				//disable reset button if nothing to reset
				if(enteredAmount.getText().isEmpty() && resultValue.getText()=="---")
				{
					resetButton.setEnabled(false);
				}
			}
		}
	}
	
	/*actionListener for resetting
	 * will change all panel component everyting back to default*/
//	public class ResetListener implements ActionListener
//	{
//
//		@Override 
//		//overriding to make it perform actions as required 
//		public void actionPerformed(ActionEvent e) {
//			
//			///clears input box
//			enteredAmount.setText("");
//			
//			//clears result label
//			resultValue.setText("---");
//			
//			//set count back to zero
//			conversionCount=0;
//			//show changes
//			countLabel.setText("Conversion count : "+conversionCount );
//			
//			//disable clear button
//			resetButton.setEnabled(false);
//			
//			//to uncheck reverse as well
//			reverseBox.setSelected(false);
//			
//			//change combo box option back to default
//			currencyComboBox.setSelectedIndex(0);
//			
//		}
//		
//	}

}
