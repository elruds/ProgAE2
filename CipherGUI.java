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
		private String key; //keyword
		private String fileName;
		private String core; //root file name
		private char inputFileType; //type of input file e.g. P or C
		private char outputFileType; //type of output file e.g. C or D
		
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
		public void actionPerformed(ActionEvent e) {	
			if (getKeyword() && processFileName()) {
			
			if (e.getSource() == monoButton) {
				mcipher = new MonoCipher(key);
				encryptionOrDecryptionProcess();
			}	
				else if (e.getSource() == vigenereButton) {
					vcipher = new VCipher(key);
				}
			}
		}
		public void encryptionOrDecryptionProcess() {
			
			if (inputFileType == 'P')	{
				char[] encryption;
				encryption = processFile(false, core + inputFileType + ".txt", core + outputFileType + ".txt", true);
				LetterFrequencies encryptionF = new LetterFrequencies(encryption, mcipher.getAlphabet());
				printReport(encryptionF);
		}
				
			else if (inputFileType == 'C')	{
				char[] decryption;
				decryption = processFile(false, core + inputFileType + ".txt", core + outputFileType + ".txt", false);
				LetterFrequencies decryptionF = new LetterFrequencies(decryption, mcipher.getAlphabet());
				printReport(decryptionF);
			
			}	
			System.exit(0); //terminate program after encryption or decryption
		}		

		public boolean isUnique(String key) {
		     boolean[] charArray = new boolean[100]; 	//creates array of booleans long enough to 
		     for (int i = 0; i < key.length(); i++) {	//accommodate letters A-Z initialised to
		         int c = key.charAt(i);					//false
		         if (charArray[c]) {
		             return false;		
		         }
		         charArray[c] = true;				//set value to true if keyword contains letter 
		     }										//with value corresponding to index of charArray
		     
		     return true;
		}
		
		
		/** 
		 * Obtains cipher keyword
		 * If the keyword is invalid, a message is produced
		 * @return whether a valid keyword was entered
		 */
		private boolean getKeyword()
		{
		  key = keyField.getText();
		  
		  if (key.isEmpty() || !key.equals(key.toUpperCase()) || !isUnique(key)) {
			  JOptionPane.showMessageDialog(null, "Please enter a valid keyword",
						"Error", JOptionPane.ERROR_MESSAGE);
			  keyField.setText("");
			  return false;
		  }
			
		  else {
			
		    return true;  
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
			fileName = messageField.getText();
			int fileNameLen = fileName.length();
			core = fileName.substring(0, fileNameLen-1);
			
			inputFileType = fileName.charAt(fileNameLen-1);
				
			if (inputFileType == 'P' || inputFileType == 'C') {
			
				if (inputFileType == 'P') {//validate if last character of filename is P or C
				outputFileType = 'C';
				}
				else if (inputFileType == 'C') {
				outputFileType = 'D';
				}
			}
			else 
			{JOptionPane.showMessageDialog(null, "Please enter a valid filename",
					"Error", JOptionPane.ERROR_MESSAGE);
			messageField.setText("");
			return false;
			}
			return true;
		}	

		/** 
		 * Reads the input text file character by character
		 * Each character is encoded or decoded as appropriate
		 * and written to the output text file
		 * @param vigenere whether the encoding is Vigenere (true) or Mono (false)
		 * @return whether the I/O operations were successful
		 */
		private char[] processFile(boolean vigenere, String readFileName, String writeFileName, boolean encode)
		{	
			
			FileReader reader = null;
			PrintWriter writer = null;
			char[] result = new char [2000];
			try {
				try {
				reader = new FileReader(readFileName);
				writer = new PrintWriter(writeFileName);
				int fileChar;
				int i = 0;
				while ((fileChar = reader.read()) != -1) {
						char letter = ((char)(fileChar));
						char newLetter;
						if (!vigenere) {
							if (encode) {
								newLetter = mcipher.encode(letter);
							} else {
								newLetter = mcipher.decode(letter);
							}
						}
						else {
							if (encode) {
								newLetter = vcipher.encode(letter);
							} else {
								newLetter = vcipher.decode(letter);
							}
						}
						writer.print(newLetter);
						result[i] = newLetter; //puts encoded or decoded letters into array
						i++;
					}
		
					}
					finally {
						
					if (reader != null) {
						reader.close();							
						} 
					if (writer != null) {
						writer.close();
						}
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
			return result;
	}
	
		private 	void printReport(LetterFrequencies encryptOrDecrypt)	
		{	
			PrintWriter reportWriter = null;
			
			try {
				reportWriter = new PrintWriter(core + "F.txt");
				reportWriter.write(encryptOrDecrypt.getReport());
				
			}	 catch (FileNotFoundException e) {
				 JOptionPane.showMessageDialog(null, "File not found",
							"Error", JOptionPane.ERROR_MESSAGE);
					messageField.setText("");
			}
			finally	{
			if (reportWriter != null)	{
				reportWriter.close();
			}
			}	
	}
}