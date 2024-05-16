package org.geowealth;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        final WordsLoader wordsLoader = new WordsLoader();
        final NineLetterWordsTopDown nineLetterWordsTopDown = new NineLetterWordsTopDown(wordsLoader);

        final List<String> validNineLetterWords =  nineLetterWordsTopDown.nineLetterWords();

        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;

        System.out.println("Valid nine letter words count: " + validNineLetterWords.size());
        System.out.println(validNineLetterWords);

        System.out.println("elapsedTime = " + timeElapsed);
    }
}