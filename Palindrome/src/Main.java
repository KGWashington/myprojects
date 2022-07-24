import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// Initialize scanner
		Scanner scanner = new Scanner(System.in);
		
		// Initialize word variables & Get a word or Phase for the user
		
		String word, wordFx;
		System.out.println("Type in a word or phrase to check: ");
		word = scanner.nextLine();
		

		// Remove all spaces in the word or phrase and lowercases them
		wordFx= word.replaceAll("\\s", "").toLowerCase();
		
		// Use Palindrome Method to check the boolean value.
		if(Palindrome(wordFx))
			System.out.println(word + " is a Palindrome");
		else
			System.out.println(word + " is not a Palindrome");
		
		
		
		
	}
	// boolean method to use pointers to check each character and see if it is a Palindrome
	private static boolean Palindrome(String word) {
		
		// Pointers for checking the characters
		int forPoint = 0 , revPoint = word.length()-1;
		
		// Iterate over the word using the pointers to see if the characters or the same.
		while(forPoint < revPoint) {
			if(word.charAt(forPoint) != word.charAt(revPoint))
				return false;
				forPoint++;
				revPoint--;
			
		}
		return true;
	}
}