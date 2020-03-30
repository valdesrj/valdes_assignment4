
// Application to class DuplicateCounter
// Given the data file "problem2.txt" count duplicate words.
// The unique words in are stored in "unique_word_counts.txt".

public class Application {

	public static void main(String[] args) {
		
		DuplicateCounter duplicateCounter = new DuplicateCounter();
		
		duplicateCounter.count("problem2.txt");
		duplicateCounter.write("unique_word_counts.txt");

	}

}
