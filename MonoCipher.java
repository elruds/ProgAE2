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
		
		int keywordSize = keyword.length();		
				
		cipher = new char [SIZE];
		
			
			for (int k = 0; k < keywordSize; k++) { 						
								
			cipher[k] = keyword.charAt(k); 	//while index of cipher array less than 	
			}								//length of keyword, assign letters 
											//in keyword to cipher elements
			int i = 0;
			int k = 0;
			for (i = SIZE-1; i > -1; i--) {		//iterate through alphabet in reverse
				for (k = keywordSize; k < SIZE; k++)	{ //iterate through keyword
														
			boolean found = false;																																		
				if (alphabet[i] == cipher[k]) {
					i--;
					found = true;		//if letter of alphabet has already appeared in 
										//keyword, skip to next letter of alphabet
				}	
					else {
					cipher[k] = alphabet[i]; //otherwise put alphabet letter in cipher element
					i--;
					}
		}
			
				}
			System.out.print(cipher);
		
		}
	
		
//		for (int i = keywordSize; i < SIZE; i++)	{ //when cipher array index is greater than length
//												//of keyword
//				for(int j = 25; j >= 0; j--) {            //go through alphabet in reverse
//			
//			for (int k = 0; k < keywordSize; k++) {      //go through each letter of keyword
//				
//				if (keyword.charAt(k) == alphabet[0]) {
//					cipher[25] = alphabet[0];
//				}
//				
//				else if (alphabet[j] == keyword.charAt(k)){ //if contents of alphabet array are the
//					cipher[i] = alphabet[j-1];					//same as a letter of keyword
//				}
//			
//			else cipher[i] = alphabet[j];
//			
			
	
		
	
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
		
	    return ' ';  // replace with your code
	}

	/**
	 * Decode a character
	 * @param ch the character to be encoded
	 * @return the decoded character
	 */
	public char decode(char ch)
	{
	    return ' ';  // replace with your code
	}
	
//	public void setKeyword(String keyWord) {
//		this.keyword = keyWord;
	}

