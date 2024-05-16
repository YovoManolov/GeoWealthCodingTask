package org.geowealth;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


public class NineLetterWordsTopDownTest {

    private NineLetterWordsTopDown sut;

    @Before
    public void setup(){
        sut = new NineLetterWordsTopDown(new WordsLoader());
    }

    @Test
    public void shouldFindValidNineLetterWords() {
        List<String> resultOfValidString = sut.nineLetterWords();

        assertFalse(resultOfValidString.isEmpty());
        //at the time I ran this, 775 was correct number. I hope the file in the repo will not change.
        assertEquals(775, resultOfValidString.size());
    }
}