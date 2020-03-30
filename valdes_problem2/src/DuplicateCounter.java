import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class DuplicateCounter {
	// create HashMap to store String keys and Integer values
	private Map<String, Integer> wordCounter = new HashMap<>();
	
	// create map from dataFile
	void count(String dataFile)
	{
		// read all the words from dataFile
		Collection<String> tokens = readTextFile(dataFile);
		
		// iterate through tokens
		for (String token : tokens) {
			// if the map contains the word
			if (wordCounter.containsKey(token)) { // is word in map?
				int count = wordCounter.get(token);
				wordCounter.put(token, count + 1);
			}
			else {
				wordCounter.put(token, 1); // add new word with a count of 1
			}
		}
	}
	
	// method to output map content
	public void write(String outputFile)
	{
		try (Formatter output = new Formatter(outputFile)) 
		{
			// get the keys
			Set<String> keys = wordCounter.keySet();
			
			// sort the keys
			TreeSet<String> sortedKeys = new TreeSet<>(keys);
			
			// generate the output for each key in map
			for (String key : sortedKeys)
			{
				try
				{
					output.format("%-10s%10s%n", key, wordCounter.get(key));
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
