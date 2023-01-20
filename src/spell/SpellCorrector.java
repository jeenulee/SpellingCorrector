package spell;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
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

                //add to trie structure (dictionary)
                //how do i load into a trie?
//               trie.append(fileContentBuilder);

                fileContentBuilder.append(System.lineSeparator());
            }

            fileContent = fileContentBuilder.toString();
            scanner.close();
            System.out.println(fileContent.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }




    @Override
    public String suggestSimilarWord(String inputWord) {



        return null;
    }
}

