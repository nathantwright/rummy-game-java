package com.sparta.ntw;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class StringExercisesTests {
    @ParameterizedTest
    @CsvSource(value = {
            "We made a big mistake; w; 1",
            "The cat ate the body; t; 2",
            "The cat is a carnivorous criminal; c; 3",
            "One two three; x; 0",
    }, delimiter = ';')
    @DisplayName("Test countWords method")
    public void testCountWords(String myString, char myChar, int expected){
        String[] myArray = myString.split(" ");
        Assertions.assertEquals(expected, StringExercises.countWords(myArray, myChar));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "We made a big mistake; w; 1",
            "The cat ate the body; t; 2",
            "The cat is a carnivorous criminal; c; 3",
            "One two three; x; 0",
    }, delimiter = ';')
    @DisplayName("Test countWordsInString method")
    public void testCountWordsInString(String myString, char myChar, int expected){
        Assertions.assertEquals(expected, StringExercises.countWordsInString(myString, myChar));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "Cathy; 10; CATHY0123456789",
            "; 4; 0123",
            "Piano; 12; PIANO01234567891011"
    }, delimiter = ';')
    @DisplayName("Hi")
    public void testStringTransform(String myString, int myInt, String expected){
        if (myString == null){
            myString = "";
        }
        Assertions.assertEquals(expected, StringExercises.stringTransform(myString, myInt));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "Bob; true",
            "Sammy; false",
            "racecar; true"
    }, delimiter = ';')
    @DisplayName("Hi")
    public void testStringReversible(String myString, boolean expected){
        if (myString == null){
            myString = "";
        }
        Assertions.assertEquals(expected, StringExercises.stringReversible(myString));
    }
}
