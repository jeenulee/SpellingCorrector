package spell;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;

import java.util.*;


public class SpellCorrector implements ISpellCorrector {

    private Trie dictionary = new Trie();
    private HashMap<String, Integer> suggestDictionary = new HashMap<>();


    @Override
    public void useDictionary(String dictionaryFileName) throws IOException {

        try {
            File file = new File(dictionaryFileName);  //create a file to hold the thing we passing in
            Scanner scanner = new Scanner(file);       //create a scanner from the scanner class

            while (scanner.hasNext()) {                 //go through the file and add to dictionary one word at a time
                String word = scanner.next();
                dictionary.add(word);
            }
            scanner.close();                            //close the scanner

        } catch (FileNotFoundException exception) {    //in case file isn't readable or something
            exception.printStackTrace();
        }
    }

    @Override
    public String suggestSimilarWord(String word) {


        ArrayList<String> potential = new ArrayList<>();
        ArrayList<String> potential2 = new ArrayList<>();
        Set<String> set = new TreeSet<>();

        word = word.toLowerCase();      //make everything lowercase

        if (word.equals("")) {         //if empty then its null
            return null;
        }
        if (dictionary.find(word) != null) {   //if it ican find itself, then it's spelled correctly, return itself
            return word;
        }

        potential.addAll(deletionDistance(word));            //run the list of first potentials through the edit distances
        potential.addAll(transpositionDistance(word));
        potential.addAll(alterationDistance(word));
        potential.addAll(insertionDistance(word));

        for (String word2 : potential) {                  //go through the lsit of potentials and if the dictionary can find it after the alterations then add it to the set
            if (dictionary.find(word2) != null) {
                set.add(word2);
            }
        }

        if (!set.isEmpty()) {
            //check word count, higher one ta          //if the set isn't empty, which it shouldn't if it has a potential suggested, then run it through suggestion algo
            return suggestedWord(set);
        } else {
            for (String word3 : potential) {                  //if it doesn't work, then we try edit distance 2 by sending the functions again
                potential2.addAll(deletionDistance(word3));
                potential2.addAll(transpositionDistance(word3));
                potential2.addAll(alterationDistance(word3));
                potential2.addAll(insertionDistance(word3));
            }

            for (String suggestion : potential2) {
                if (dictionary.find(suggestion) != null) {   //once again if it can find it in the dictionary, then add it to the set
                    set.add(suggestion);
                }

            }
            return suggestedWord(set);

        }
    }

    public String suggestedWord(Set<String> set) {
        if (!set.isEmpty()) {             //if the set isn't empty then we need to go through the set
            int count = 0;
            String suggestedWord = null;

            for (String word : set) {                    //if the word in the set is found in the dictionary, and it has a value, then it exists
                if (dictionary.find(word) != null) {
                    int frequency = dictionary.find(word).getValue();     //frequency checks if the word that is found has a value, if it does then it exists

                    if (frequency > count) {
                        count = frequency;      //if it has a frequency then it exists and so update count
                        suggestedWord = word;
                    }
                }
            }
            return suggestedWord;
        }
        return null;

    }


    public ArrayList<String> deletionDistance(String inputWord) {
        ArrayList<String> potential = new ArrayList<>();

        for(int i = 0; i < inputWord.length(); i++){     //go through the word
            potential.add(inputWord.substring(0, i) + inputWord.substring(i + 1));    //add the original word with letter at first position and ith
        }                 //0th letter and ith letter                     //ith letter and the one after
        return potential;
    }

    public ArrayList<String> transpositionDistance(String inputWord) {
        ArrayList<String> potential = new ArrayList<>();

        for (int i = 0; i < inputWord.length() - 1; i++) {
            char[] charArray = inputWord.toCharArray();     //example: house
            char t = charArray[i];                         // t = h    ^
                charArray[i] = charArray[i + 1];           // h turns into o
                charArray[i + 1] = t;                      // move the incrementer 1 character over
                potential.add(new String(charArray));      //add it to the list and then start over with the incrementer 1 character over
        }
        return potential;
    }

    public ArrayList<String> alterationDistance(String inputWord) {
        ArrayList<String> potential = new ArrayList<>();

        for (int i = 0; i < inputWord.length(); i++) {
                for (char j = 'a'; j <= 'z'; j++) {          //essentially create a new word for every single position of the word, add it to the list
                if (inputWord.charAt(i) != j) {
                    potential.add(inputWord.substring(0, i) + j + inputWord.substring(i + 1));
                 }
            }
        }
        return potential;
    }

    public ArrayList<String> insertionDistance(String inputWord){

        ArrayList<String> potential = new ArrayList<>();

        for (int i = 0; i <= inputWord.length(); i++) {
                for (char j = 'a'; j <= 'z'; j++) {          //at every single letter in the word, add it with every letter of the alphabet to create a huge list
                potential.add(inputWord.substring(0, i) + j + inputWord.substring(i));
            }
        }
        return potential;
    }
}


