
// Application to text class DuplicateRemover
// Given the data file "problem1.txt" duplicate words are removed.
// The unique words in are stored in "unique_words.txt".

public class Application {

	public static void main(String[] args) {
		
		DuplicateRemover duplicateRemover = new DuplicateRemover();
		
		duplicateRemover.remove("problem1.txt");
		duplicateRemover.write("unique_words.txt");

	}

}
