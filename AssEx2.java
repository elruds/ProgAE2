/*A program that encrypts or decrypts the contents of a file using a secret keyword.  The keyword 
 * and the file name are entered into text fields in a GUI.  The user chooses whether encryption 
 * is monoalphabetic or Vigenere by clicking the corresponding button in the GUI.  The program
 * accepts plain text files (those with filenames ending in 'P') for encryption and writes 
 * encrypted files with filenames ending in 'C'.  Names of decrypted files end in 'D'. The program 
 * creates a file with filename ending in 'F' which contains a report of the frequency with which
 * each letter A-Z appears in the file.  It also contains a comparison of the frequencies to those 
 * of the letters in an average English language document as well as the letter which appeared most
 * frequently in the file.*/

import java.io.FileReader;
import java.util.Scanner;

/**
 * Programming AE2
 * Creates and shows the cipher GUI
 */
public class AssEx2
{


	/**
	 * The main method
	 * @param args the arguments
	 */
	public static void main(String [] args)
	{
		CipherGUI CipherGUI = new CipherGUI();
		CipherGUI.setVisible(true);

	}
}
		
		

	
