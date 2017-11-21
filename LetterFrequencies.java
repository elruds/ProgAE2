/**
 * Programming AE2
 * Processes report on letter frequencies
 */
public class LetterFrequencies
{
	/** Size of the alphabet */
	private final int SIZE = 26;
	
	/** Count for each letter */
	private int [] alphaCounts;
	
	private double  [] alphaPC;
	
	private double  [] alphaPCDiff;
	
	/** The alphabet */
	private char [] alphabet; 
												 	
	/** Average frequency counts */
	private double [] avgCounts = {8.2, 1.5, 2.8, 4.3, 12.7, 2.2, 2.0, 6.1, 7.0,
							       0.2, 0.8, 4.0, 2.4, 6.7, 7.5, 1.9, 0.1, 6.0,  
								   6.3, 9.1, 2.8, 1.0, 2.4, 0.2, 2.0, 0.1};

	/** Character that occurs most frequently */
	private char maxCh;
	private double maxPC;
	
	/** Total number of characters encrypted/decrypted */
	private int totChars;
	
	/**
	 * Instantiates a new letterFrequencies object.
	 */
	public LetterFrequencies(char[] letters, char[] alphabet)
	{
		
		totChars = 0;
		this.alphabet = alphabet;
		
		alphaCounts = new int[SIZE];
		alphaPC = new double[SIZE];
		alphaPCDiff = new double[SIZE];
		
	    for (int i = 0; i < letters.length; i++) { // Iterate each character from the encr/decr result file
	    		char ch = letters[i]; // get the letter from the array
	        if (isLetter(ch)) { // Check if it is a letter A-Z
	        		addChar(ch);
	        		totChars++;
	         }
	     }
	    
	    maxPC = 0.0;
	    for (int k = SIZE - 1; k > -1; k--) { // Iterate each character from the encr/decr result file
    			int count = alphaCounts[k]; // get the letter from the array
    			double PC = ((double) count/totChars) * 100.0;
    			if (PC > maxPC) {
    				maxPC = PC;
    				maxCh = alphabet[k];
    			}
    			alphaPC[k] = PC;
    			alphaPCDiff[k] = alphaPC[k] - avgCounts[k];
	    }
	}
	
	public boolean isLetter(char ch) {
		return ch > 64 && ch < 91;
	}
		
	/**
	 * Increases frequency details for given character
	 * @param ch the character just read
	 */
	public void addChar(char ch)
	{
		int index = ch - 'A';
		alphaCounts[index] += 1; // Increase the frequency for the letter
	}
	
	/**
	 * Gets the maximum frequency
	 * @return the maximum frequency
	 */
	private double getMaxPC() {
	    return maxPC;  // replace with your code
	}
	
	/**
	 * Returns a String consisting of the full frequency report
	 * @return the report
	 */
	public String getReport()
	{
		String title = "LETTER ANALYSIS\n";
		StringBuilder report = new StringBuilder(title);
		
		report.append(String.format("%-5s %-5s %-5s %-5s %-5s\n", "Letter", "Freq", "Freq%", "AvgFreq%", "Diff"));
		for (int i = 0; i < SIZE; i++) {
			report.append(String.format("   %-3s %-4s %5.1f %5s %7.1f\n", alphabet[i], alphaCounts[i], alphaPC[i], avgCounts[i], alphaPCDiff[i]));
		}
		report.append(String.format("The most frequent letter is %s at %.2f%s", maxCh, maxPC, "%"));
		
		String freqReport = report.toString();
	    return freqReport;
	}
}