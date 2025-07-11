package com.sparta.ntw;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class PracticeExercisesTests {
    @ParameterizedTest(name = "Testing {0} returns \"{1}\"")
    @CsvSource(value = {
            "-1; -1",
            "0; 0",
            "4; 4, 3, 2, 1"
    }, delimiter = ';')
    void testCountdown(int inputNum, String expected){
        Assertions.assertEquals(expected, PracticeExercises.countdown(inputNum));
    }

    @ParameterizedTest(name = "Testing {0} returns 5")
    @MethodSource("secondLargestIsFive")
    void testSecondLargestIsFive(int[] inputArray){
        Assertions.assertEquals(5, PracticeExercises.secondLargest(inputArray));
    }

    @ParameterizedTest(name = "Testing {0} returns \"{1}\"")
    @MethodSource("songDurations")
    void testSongDuration(int[] inputArray, String expected){
        Assertions.assertEquals(expected, PracticeExercises.songDuration(inputArray));
    }

    @ParameterizedTest(name = "Testing {0} returns \"{1}\"")
    @MethodSource("argsForIntsToString")
    void testIntsToString(int[] inputArray, String expected){
        Assertions.assertEquals(expected, PracticeExercises.intsToString(inputArray));
    }

    @ParameterizedTest(name = "Testing \"{0}\" returns \"{1}\"")
    @CsvSource(value = {
            "Hello there!; Hll thr!",
            "UNLIMITED POWER!; NLMTD PWR!",
            "They fly now?; Thy fly nw?"
    }, delimiter = ';')
    void testRemoveVowels(String inputString, String expected){
        Assertions.assertEquals(expected, PracticeExercises.removeVowels(inputString));
    }

    @ParameterizedTest(name = "Testing \"{0}\" returns \"{1}\"")
    @CsvSource(value = {
            "Hello there!; Hll thr!",
            "UNLIMITED POWER!; NLMTD PWR!",
            "They fly now?; Thy fly nw?"
    }, delimiter = ';')
    void testRemoveVowels2(String inputString, String expected){
        Assertions.assertEquals(expected, PracticeExercises.removeVowels2(inputString));
    }

    @ParameterizedTest(name = "Testing {0} returns \"{1}\"")
    @CsvSource(value = {
            "1; 1",
            "100; 100",
            "1000; 1,000",
            "10000; 10,000",
            "10010; 10,010",
            "1100010001; 1,100,010,001"

    }, delimiter = ';')
    void testGroupedByCommas(double inputDouble, String expected){
        Assertions.assertEquals(expected, PracticeExercises.groupedByCommas(inputDouble));
    }

    @ParameterizedTest(name = "Testing (\"{0}\", {1}) returns {2}")
    @MethodSource("argsForContainsString")
    void testContainsString(String queryString, String[] searchArray, String[] expected){
        for (int c = 0; c < expected.length; c++){
            Assertions.assertEquals(expected[c], (PracticeExercises.containsString(queryString, searchArray))[c]);
        }
    }

    @ParameterizedTest(name = "Testing {0} returns {1}")
    @MethodSource("argsForReverseDigits")
    void testReverseDigits(int intIn, int[] expected){
        for (int c = 0; c < expected.length; c++){
            Assertions.assertEquals(expected[c], (PracticeExercises.reverseDigits(intIn))[c]);
        }
    }

    @ParameterizedTest(name = "Testing {0} returns {1}")
    @MethodSource("argsForStringLengths")
    void testStringLengths(String[] arrayIn, int[] expected){
        for (int c = 0; c < expected.length; c++){
            Assertions.assertEquals(expected[c], (PracticeExercises.stringLengths(arrayIn))[c]);
        }
    }

    @ParameterizedTest(name = "Testing \"{0}\" returns \"{1}\"")
    @CsvSource(value = {
            "Hello; (())(",
            "OneTwoThree; )())())(())",
            "''; ''"
    }, delimiter = ';')
    void testDuplicateEncoder(String inputString, String expected){
        Assertions.assertEquals(expected, PracticeExercises.duplicateEncoder(inputString));
    }

    @ParameterizedTest(name = "Testing \"{0}\" returns {1}")
    @MethodSource("argsForCountChars")
    void testCountChars(String stringIn, Map<String, Integer> expected){
        Assertions.assertEquals(expected, PracticeExercises.countChars(stringIn));
    }

    @ParameterizedTest(name = "Testing {0} returns \"{1}\"")
    @MethodSource("argsForWhoLikes")
    void testWhoLikes(String[] stringsIn, String expected){
        Assertions.assertEquals(expected, PracticeExercises.whoLikes(stringsIn));
    }


    static Stream<int[]> secondLargestIsFive(){
        int[] i1 = {1, 2, 3, 4, 5, 6};
        int[] i2 = {6, 5, 4, 3, 2, 1};
        int[] i3 = {3, 5, 2, 1, 5, 4};
        int[] i4 = {5, 4, 3, 2, 1, 6};
        int[] i5 = {5, 6, 4, 3, 2, 1};
        int[] i6 = {1, 2, 3, 4, 6, 5};
        int[] i7 = {1, 2, 6, 3, 4, 5};
        return Stream.of(i1, i2, i3, i4, i5, i6, i7);
    }

    static Stream<Arguments> songDurations(){
        int[] i1 = {};
        int[] i2 = {5, 10, 15};
        int[] i3 = {5, 10, 55, 55};
        return Stream.of(
                Arguments.arguments(i1, "0 minutes and 0 seconds"),
                Arguments.arguments(i2, "0 minutes and 30 seconds"),
                Arguments.arguments(i3, "2 minutes and 5 seconds")
        );
    }

    static Stream<Arguments> argsForIntsToString(){
        int[] i1 = {1};
        int[] i2 = {13, 66, 84};
        int[] i3 = {};
        int[] i4 = {1000000000, 3};

        return Stream.of(
                Arguments.arguments(i1, "[1]"),
                Arguments.arguments(i2, "[13, 66, 84]"),
                Arguments.arguments(i3, "[]"),
                Arguments.arguments(i4, "[1000000000, 3]")
        );
    }

    static Stream<Arguments> argsForContainsString(){
        String[] s1 = {"This", "or", "that"};
        String[] s2 = {"Twiddle", "lee", "dee"};
        String[] s3 = {};
        String[] s4 = {"postantidisestablishmentarianism might be spelled wrong"};
        String[] r1 = {"This", "that"};
        String[] r2 = {};
        String[] r3 = {};
        String[] r4 = {"postantidisestablishmentarianism might be spelled wrong"};

        return Stream.of(
                Arguments.arguments("th", s1, r1),
                Arguments.arguments("th", s2, r2),
                Arguments.arguments("th", s3, r3),
                Arguments.arguments("", s4, r4)
        );
    }

    static Stream<Arguments> argsForReverseDigits(){
        int[] i1 = {0};
        int[] i2 = {6, 3, 7, 3, 5, 9, 9};

        return Stream.of(
                Arguments.arguments(0, i1),
                Arguments.arguments(9953736, i2)
        );
    }

    static Stream<Arguments> argsForStringLengths(){
        String[] s1 = {"This", "or", "that"};
        String[] s2 = {};
        String[] s3 = {"", ""};
        String[] s4 = {"postantidisestablishmentarianism", "", "A", "12"};
        int[] i1 = {4, 2, 4};
        int[] i2 = {};
        int[] i3 = {0, 0};
        int[] i4 = {32, 0, 1, 2};

        return Stream.of(
                Arguments.arguments(s1, i1),
                Arguments.arguments(s2, i2),
                Arguments.arguments(s3, i3),
                Arguments.arguments(s4, i4)
        );
    }

    static Stream<Arguments> argsForCountChars(){
        Map<String, Integer> returnMap = new HashMap<String, Integer>();
        returnMap.put(" ", 9);
        returnMap.put("a", 2);
        returnMap.put("b", 2);
        returnMap.put("d", 2);
        returnMap.put("e", 5);
        returnMap.put("g", 1);
        returnMap.put("h", 4);
        returnMap.put("I", 1);
        returnMap.put("i", 3);
        returnMap.put("l", 2);
        returnMap.put("n", 3);
        returnMap.put("o", 3);
        returnMap.put("r", 2);
        returnMap.put("t", 3);
        returnMap.put("u", 1);
        returnMap.put("v", 1);
        Map<String, Integer> emptyMap = new HashMap<String, Integer>();

        return Stream.of(
                Arguments.arguments("In a hole in the ground there lived a hobbit", returnMap),
                Arguments.arguments("", emptyMap)
        );
    }

    static Stream<Arguments> argsForWhoLikes(){
        String[] s1 = {};
        String[] s2 = {"Daisy"};
        String[] s3 = {"Daisy", "Leopold"};
        String[] s4 = {"Daisy", "Leopold", "Jemma"};
        String[] s5 = {"Daisy", "Leopold", "Jemma", "Phil", "Melinda", "Grant"};

        return Stream.of(
                Arguments.arguments(s1, "No-one likes this"),
                Arguments.arguments(s2, "Daisy likes this"),
                Arguments.arguments(s3, "Daisy and Leopold like this"),
                Arguments.arguments(s4, "Daisy, Leopold and Jemma like this"),
                Arguments.arguments(s5, "Daisy, Leopold and 4 others like this")
        );
    }
}
