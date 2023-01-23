package spell;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;



public class SpellCorrector implements ISpellCorrector {

    private String fileContent;
    private Trie dictionary = new Trie();
    private HashMap<String, Integer> suggestDictionary = new HashMap <>();



    @Override
    public void useDictionary(String dictionaryFileName) throws IOException {

        try {

            File file = new File(dictionaryFileName);
            Scanner scanner = new Scanner(file);
            StringBuilder fileContentBuilder = new StringBuilder();

            while (scanner.hasNextLine()) {
                fileContentBuilder.append(scanner.nextLine());
                fileContentBuilder.append(System.lineSeparator());
            }

            fileContent = fileContentBuilder.toString();
            scanner.close();

            System.out.println(fileContent.toString()); //delete me later

            dictionary.add(fileContent); // might need to loop through here
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String suggestSimilarWord(String inputWord) {
        String suggestion;

        inputWord.toLowerCase();

        if (inputWord.equals("")) {
            return null;
        }

        if (dictionary.find(inputWord) != null) {
            return inputWord;
        }

        deletionDistance(inputWord);
        transpositionDistance(inputWord);
        alterationDistance(inputWord);
        insertionDistance(inputWord);

//        System.out.println("Suggestion is: " + suggestion);



    return null;

    }

    public static ArrayList<String> deletionDistance(String inputWord) {

        ArrayList<String> potential = new ArrayList<>();
        int wordLength = inputWord.length();

        for(int i = 0; i < inputWord.length(); i++){
            potential.add(inputWord.substring(0, i) + inputWord.substring(i + 1, wordLength));
        }
        return potential;
    }

    public static ArrayList<String> transpositionDistance(String inputWord) {
        ArrayList<String> potential = new ArrayList<>();
        int wordLength = inputWord.length();

        for (int i = 0; i < inputWord.length(); i++) {
            //for each letter swap it with i +1
            //house,
            // ^
            potential.add(inputWord.substring(0, i) + inputWord.substring(i + 1, i + 2) + inputWord.substring(i, i + 1)
                    + inputWord.substring(i + 2, wordLength));
        }
        return potential;
    }

    public void alterationDistance(String inputWord) {
        char insertLetter;
        ArrayList<String> potential = new ArrayList<>();
        int wordLength = inputWord.length();

        for (int i = 0; i < inputWord.length(); i++) {
            insertLetter = (char) ('a' + i);
            potential.add(inputWord.substring(0, i) + insertLetter + inputWord.substring(i + 1, wordLength));
        }
    }

    public void insertionDistance(String inputWord){
        char insertLetter;
        int wordLength = inputWord.length();
        ArrayList<String> potential = new ArrayList<>();

        for (int i = 0; i <= wordLength; i++) {
            for (int j = 0; j < 26; j++) {
                insertLetter = (char) ('a' + i);
                potential.add(inputWord.substring(0, i) + insertLetter + inputWord.substring(i, wordLength));
            }
        }
    }
}




