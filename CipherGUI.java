import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/** 
 * Programming AE2
 * Class to display cipher GUI and listen for events
 */
public class CipherGUI extends JFrame implements ActionListener
{
	//instance variables which are the components
	private JPanel top, bottom, middle;
	private JButton monoButton, vigenereButton;
	private JTextField keyField, messageField;
	private JLabel keyLabel, messageLabel;

	//application instance variables
	//including the 'core' part of the textfile filename
	//some way of indicating whether encoding or decoding is to be done
	private MonoCipher mcipher;
	private VCipher vcipher;
	
	/**
	 * The constructor adds all the components to the frame
	 */
	public CipherGUI()
	{
		this.setSize(400,150);
		this.setLocation(100,100);
		this.setTitle("Cipher GUI");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.layoutComponents();
	}
	
	/**
	 * Helper method to add components to the frame
	 */
	public void layoutComponents()
	{
		//top panel is yellow and contains a text field of 10 characters
		top = new JPanel();
		top.setBackground(Color.yellow);
		keyLabel = new JLabel("Keyword : ");
		top.add(keyLabel);
		keyField = new JTextField(10);
		top.add(keyField);
		this.add(top,BorderLayout.NORTH);
		
		//middle panel is yellow and contains a text field of 10 characters
		middle = new JPanel();
		middle.setBackground(Color.yellow);
		messageLabel = new JLabel("Message file : ");
		middle.add(messageLabel);
		messageField = new JTextField(10);
		middle.add(messageField);
		this.add(middle,BorderLayout.CENTER);
		
		//bottom panel is green and contains 2 buttons
		
		bottom = new JPanel();
		bottom.setBackground(Color.green);
		//create mono button and add it to the top panel
		monoButton = new JButton("Process Mono Cipher");
		monoButton.addActionListener(this);
		bottom.add(monoButton);
		//create vigenere button and add it to the top panel
		vigenereButton = new JButton("Process Vigenere Cipher");
		vigenereButton.addActionListener(this);
		bottom.add(vigenereButton);
		//add the top panel
		this.add(bottom,BorderLayout.SOUTH);
	}
	
	/**
	 * Listen for and react to button press events
	 * (use helper methods below)
	 * @param e the event
	 */
	public void actionPerformed(ActionEvent e)
	{	
		if (e.getSource() == monoButton) {
			boolean vigenere = false;
			getKeyword();
			processFileName();
	}	
		
		else if (e.getSource() == vigenereButton) {
			boolean vigenere = true;
			getKeyword();
			processFileName();
		}
	}	
		
	public boolean isUnique(String input) {
	     boolean[] charArray = new boolean[100];
	     for (int i = 0; i < input.length(); i++) {
	         int c = input.charAt(i);
	         if (charArray[c]) {
	             return false;
	         }
	         charArray[c] = true;
	     }
	     return true;
	}
	
//	public int[] getFrequency(String input) {
//	     int[] charArray = new int[100];
//	     for (int i = 0; i < input.length(); i++) {
//	         int c = input.charAt(i);
//	         charArray[c] += 1;
//	     }
//	     return charArray;
//	}
	
	/** 
	 * Obtains cipher keyword
	 * If the keyword is invalid, a message is produced
	 * @return whether a valid keyword was entered
	 */
	private boolean getKeyword()
	{
	  String keyWord = keyField.getText();
	  
	  if (keyWord.isEmpty() || !keyWord.equals(keyWord.toUpperCase()) || !isUnique(keyWord)) {
		  JOptionPane.showMessageDialog(null, "Please enter a valid keyword",
					"Error", JOptionPane.ERROR_MESSAGE);
		  keyField.setText("");
		  return false;
	  }
		
	  else {
		
		MonoCipher mcipher = new MonoCipher(keyWord);
	    return true;  // replace with your code
	  }
	}
	
	/** 
	 * Obtains filename from GUI
	 * The details of the filename and the type of coding are extracted
	 * If the filename is invalid, a message is produced 
	 * The details obtained from the filename must be remembered
	 * @return whether a valid filename was entered
	 */
	private boolean processFileName()
	{
		String fileName = messageField.getText();
		int fileNameLen = fileName.length();
		
		if (fileName.charAt(fileNameLen-1) == 'P' || (fileName.charAt(fileNameLen-1) == 'C' )) {
			
			 return true; 			//validate if last character of filename is P or C
			 
		}
		else 
		{JOptionPane.showMessageDialog(null, "Please enter a valid filename",
				"Error", JOptionPane.ERROR_MESSAGE);
		messageField.setText("");
		return false;
		}
	}

	
			
	    // replace with your code

	
	/** 
	 * Reads the input text file character by character
	 * Each character is encoded or decoded as appropriate
	 * and written to the output text file
	 * @param vigenere whether the encoding is Vigenere (true) or Mono (false)
	 * @return whether the I/O operations were successful
	 */
	private boolean processFile(boolean vigenere)
	{	
		String inputFileName = "/Users/Lauren/MScSoftwareDev/Programming/text1P.txt";
		
		FileReader reader = null;
		
			try {
				try {
			
			reader = new FileReader(inputFileName);
			int fileChar;
			while ((fileChar = reader.read()) != -1) {
							
				{
					char letter = ((char)(fileChar));
					System.out.print(letter);

					if (vigenere == false) {
						mcipher.encode(letter);
					}
					else {
					vcipher.encode(letter);
				}
			}
				}
				}
				finally {
				if (reader != null) reader.close();
			}
			}
				
				catch (IOException ioe) {
					JOptionPane.showMessageDialog(null, "File not found",
							"Error", JOptionPane.ERROR_MESSAGE);
					messageField.setText("");
				}
		
				
				catch (InputMismatchException e) {
					JOptionPane.showMessageDialog(null, "Invalid file content",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			
	    return true;  // replace with your code
	}
}
