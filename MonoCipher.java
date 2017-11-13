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
		for (int i = 0; i < keywordSize; i++) {
			cipher[i] = keyword.charAt(i);
		}
					
		cipher[keywordSize] = (char) alphabet[26];
		
		for(int i = keywordSize + 1; i < (SIZE - keywordSize); i++) {
			cipher[i] = (char) (alphabet[26 - (i - keywordSize)]);
		}
		
			
		
			
			
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

