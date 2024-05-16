package org.geowealth;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;


public class NineLetterWordsTopDown {

    private static final int SIZE_NINE = 9;

    private final WordsLoader wordsLoader;
    //HashMap<Length, AllWordsWithThisLength>
    private final HashMap<Integer, TreeSet<String>> sizeWordsHashMap;
    private final List<String> resultNineLetterWords = new LinkedList<>();

    public NineLetterWordsTopDown(WordsLoader wordsLoader) {
        this.wordsLoader = wordsLoader;
        this.sizeWordsHashMap = loadWordsFromFile();
    }

    public List<String> nineLetterWords() {

        for (String nineLetterWord : sizeWordsHashMap.get(SIZE_NINE)) {
            List<String> dynamicProgrammingResults = new LinkedList<>();
            if (isValid(nineLetterWord, 9, dynamicProgrammingResults)) {
                resultNineLetterWords.add(nineLetterWord);
            }

        }

        return resultNineLetterWords;
    }

    private boolean isValid(String wordToCut, int wordSizeCounter, List<String> dynamicProgrammingResults) {
        if (wordSizeCounter == 0) {
            return true;
        } else {
            if (sizeWordsHashMap.get(wordSizeCounter).contains(wordToCut)) {
                dynamicProgrammingResults.add(wordToCut);
                char[] charArr = wordToCut.toCharArray();
                for (int index = 0; index < charArr.length; index++) {
                    String wordToCheck = removeCharAtIndex(wordToCut, index);
                    if (isValid(wordToCheck, wordSizeCounter - 1, dynamicProgrammingResults)) {
                        return true;
                    }
                }
                return false;
            }
            return false;
        }
    }

    private static String removeCharAtIndex(String wordToCut, int index) {
        return wordToCut.substring(0, index) + wordToCut.substring(index + 1);
    }

    private HashMap<Integer, TreeSet<String>> loadWordsFromFile() {
        try {
            return wordsLoader.loadWords();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
