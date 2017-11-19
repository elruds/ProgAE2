import java.util.Arrays;

/**
 * Programming AE2
 * Contains monoalphabetic cipher and methods to encode and decode a character.
 */
public class MonoCipher
{
	/** The size of the alphabet. */
	private final int SIZE = 26;

	/** The alphabet. */
	private char [] alphabet;
	
	/** The cipher array. */
	private char [] cipher;
	
	private String keyword;

	/**
	 * Instantiates a new mono cipher.
	 * @param keyword the cipher keyword
	 */
	
	
	public MonoCipher(String keyword)
	{					
		//creates alphabet
		alphabet = new char [SIZE];
		for (int i = 0; i < SIZE; i++)
			alphabet[i] = (char)('A' + i);		
				
		cipher = new char [SIZE];
		
			int k;
		
			for (k = 0; k < keyword.length(); k++) { // create first part of cipher from keyword										
				cipher[k] = keyword.charAt(k); 	
			}
			
			boolean[] foundArray = initFoundArray(keyword); //array of booleans which contains
			int i = 0;						//true at index positions of letters in keyword
			int c = keyword.length();
			
			for (i = SIZE-1; i > -1; i--) {		//iterate through alphabet in reverse 
				if (!foundArray[alphabet[i]]) {
					cipher[c] = alphabet[i];
					c++;						   //if letter not in keyword, put it in cipher array
				}
			}
			System.out.print(cipher); // print cipher array for testing and tutors
		
		}
	
	public boolean[] initFoundArray(String key) {
	     boolean[] charArray = new boolean[100];    //creates array of booleans long enough to 
	     for (int i = 0; i < key.length(); i++) {   //accommodate letters A-Z initialised to false
	         int c = key.charAt(i);					
	         charArray[c] = true;	//set value to true if keyword contains letter 
	     }							//with value corresponding to index of charArray
	     
	     return charArray;
	}
	
	public char[] getAlphabet(){
		return alphabet;
		
	}
	/**
	 * Encode a character
	 * @param ch the character to be encoded
	 * @return the encoded character
	 */
	public char encode(char ch)
	{	
		int index = ch - 'A';		
		
		if (ch < 65 || ch > 91) { //if character not a letter A-Z e.g. space, number
			return ch;			//do not encode
		}
				
	    return cipher[index];  
	}

	/**
	 * Decode a character
	 * @param ch the character to be encoded
	 * @return the decoded character
	 */
	public char decode(char ch)
	{
		for (int i = 0; i< SIZE; i++) {
			if (ch == cipher[i]) {	
				return alphabet[i];
			}
		}
	    return ch;  //if character not in cipher array, do not decode
	}
}