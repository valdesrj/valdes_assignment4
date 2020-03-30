import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.HashSet;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;

public class DuplicateRemover {
	// array list containing the unique words
	private List<String> uniqueWords = new ArrayList<>();
	
	// method takes the dataFile which is a file containing words.
	// a Set of Strings is used to eliminate duplicate words from dataFile
	public void remove(String dataFile)
	{
		// read all the words from dataFile
		Collection<String> values = readTextFile(dataFile);
		
		// create a Hashset to eliminate duplicate Strings
		Set<String> set = new HashSet<>(values);
		
		// store the unique words in the instance variable
		for (String word : set)
		{
			uniqueWords.add(word);
		}
	}
	
	// method to output the unique words to outputFile
	public void write(String outputFile)
	{
		try (Formatter output = new Formatter(outputFile)) 
		{
			for (String word : uniqueWords)
			{
				try
				{
					output.format("%s ", word);
				}
				catch (IllegalFormatException | FormatterClosedException e)
				{
					System.out.println("Problem writing the file. Application ending.");
					e.printStackTrace();
				}
			}
		}
		catch (SecurityException | FileNotFoundException |
				FormatterClosedException e) 
		{
				e.printStackTrace();
		}
	}
	
	// read the dataFile and return a List of Strings
	public List<String> readTextFile(String dataFile)
	{
		// create ArrayList to store words
		List<String> words = new ArrayList<>();
		
		// open dataFile, read its contents and close the file
        try (Scanner input = new Scanner(Paths.get(dataFile))) 
        {	
			// read record from file
			while (input.hasNext()) { // while there is more to read
				
				// read the next string, remove punctuation, change to lower
				// case and add to the array list
				words.add(input.next().replaceAll("[^a-zA-Z-0-9]", "").
					toLowerCase());
			}
		}
		catch (IOException | NoSuchElementException |
			IllegalStateException e) 
        {
			System.out.println("Problem reading the file. Application ending.");
			e.printStackTrace();
		}
		
		// return the List of words
		return words;
	}

}
