package spell;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.HashMap;
import java.util.Scanner;



public class SpellCorrector implements ISpellCorrector {

    private String fileContent;
    @Override
    public void useDictionary(String dictionaryFileName) throws IOException {

        try {

            File file = new File(dictionaryFileName);

            Scanner scanner = new Scanner(file);

            StringBuilder fileContentBuilder = new StringBuilder();

            while (scanner.hasNextLine()) {
                fileContentBuilder.append(scanner.nextLine());

//               trie.append(fileContentBuilder);  ignore meeeeeee

                fileContentBuilder.append(System.lineSeparator());
            }

            fileContent = fileContentBuilder.toString();
            scanner.close();

            System.out.println(fileContent.toString());   //delete me later

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String suggestSimilarWord(String inputWord) {

        Trie wordCount = new Trie();
        int wordCountRetrieval = wordCount.getWordCount();

        HashMap<String, Integer> corrector = new HashMap<>();

        corrector.put(inputWord,wordCountRetrieval);

        System.out.println(corrector.toString());










//    Trie addWord = new Trie();
//    Trie findWord = new Trie();
//
//    String suggestedWord;
//
//    addWord.add(inputWord);
//
//    if(findWord.find(inputWord) != null){
//        return inputWord;
//    }
//
//
//
//
//
//    else{
//        return suggestedWord;
//
//    }

        return null;
    }
}

