package org.geowealth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class WordsLoader {
    public HashMap<Integer, TreeSet<String>> loadWords() throws IOException {

        final URL wordsUrl = URI.create("https://raw.githubusercontent.com/nikiiv/JavaCodingTestOne/master/scrabble-words.txt").toURL();

        //HashMap<Word, wordLength>
        final HashMap<String, Integer> wordsMap;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(wordsUrl.openConnection().getInputStream()))) {
            wordsMap = (HashMap<String, Integer>) br.lines().skip(2).filter(s -> s.length() <= 9).collect(Collectors.toMap(w -> w, String::length));
        }

        //Single letter words are missing in the file but according to the pdf requirements I and A are valid
        wordsMap.put("I",1);
        wordsMap.put("A",1);

        return extractSizeKeyWordsHashMap(wordsMap);
    }

    private static HashMap<Integer, TreeSet<String>>  extractSizeKeyWordsHashMap(HashMap<String, Integer> wordSizeHashMap) {

        final HashMap<Integer, TreeSet<String>> sizeKeyWordsHashMap =  new HashMap<>();
        for(int wordsOfSize = 1; wordsOfSize <= 9; wordsOfSize++){

            int finalWordsOfSize = wordsOfSize;
            sizeKeyWordsHashMap.put(finalWordsOfSize,
                    wordSizeHashMap.entrySet().stream().filter(e-> e.getValue() == finalWordsOfSize)
                            .map(Map.Entry::getKey).collect(Collectors.toCollection(TreeSet::new))
            );

        }
        return sizeKeyWordsHashMap;
    }
}
