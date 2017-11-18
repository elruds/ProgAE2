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
		//create alphabet
		alphabet = new char [SIZE];
		for (int i = 0; i < SIZE; i++)
			alphabet[i] = (char)('A' + i);		
				
		cipher = new char [SIZE];
		
			int k;
		
			for (k = 0; k < keyword.length(); k++) { 										
				cipher[k] = keyword.charAt(k); 	//while index of cipher array less than 	
			}								//length of keyword, assign letters 
											//in keyword to cipher elements
			
			boolean[] foundArray = initFoundArray(keyword);
			int i = 0;
			int c = keyword.length();
			//iterate through alphabet in reverse
			for (i = SIZE-1; i > -1; i--) {
				if (!foundArray[alphabet[i]]) {
					cipher[c] = alphabet[i];
					c++;
				}
			}
			System.out.print(cipher);
		
		}
	
	public boolean[] initFoundArray(String key) {
	     boolean[] charArray = new boolean[100]; //creates array of booleans long enough to 
	     for (int i = 0; i < key.length(); i++) {	//accommodate letters A-Z initialised to
	         int c = key.charAt(i);					//false
	         charArray[c] = true;	//set value to true if keyword contains letter 
	     }							//with value corresponding to index of charArray
	     
	     return charArray;
	}
	
	public char getAlphabet(int i){
		return alphabet[i];
		
	}
	
			
 		// create first part of cipher from keyword
		// create remainder of cipher from the remaining characters of the alphabet
		// print cipher array for testing and tutors
	
	
	/**
	 * Encode a character
	 * @param ch the character to be encoded
	 * @return the encoded character
	 */
	public char encode(char ch)
	{	
		int index = ch - 'A';
		
		if (ch < 65 || ch > 91) {
			return ch;
		}
				
	    return cipher[index];  // replace with your code
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
	    return ch;  // replace with your code
	}
//	public void setKeyword(String keyWord) {
//		this.keyword = keyWord;
//}
}