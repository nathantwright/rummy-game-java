package com.sparta.ntw;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;

public class CollectionsExercisesTests {
    @Test
    @DisplayName("Test the result of makeFiveSet with the max set to 9 (expect [5])")
    public void testMakeFiveSet_6_5(){
        HashSet<Integer> expected = new HashSet<Integer>();
        expected.add(5);
        Assertions.assertEquals(expected, CollectionsExercises.makeFiveSet(9));
    }
    @Test
    @DisplayName("Test the result of makeFiveSet with the max set to 10 (expect [5, 10])")
    public void testMakeFiveSet_10_5and10(){
        HashSet<Integer> expected = new HashSet<Integer>();
        expected.add(5);
        expected.add(10);
        Assertions.assertEquals(expected, CollectionsExercises.makeFiveSet(10));
    }
    @Test
    @DisplayName("Test the result of makeFiveSet with the max set to 4 (expect [])")
    public void testMakeFiveSet_4_e(){
        HashSet<Integer> expected = new HashSet<Integer>();
        Assertions.assertEquals(expected, CollectionsExercises.makeFiveSet(4));
    }

    @Test
    @DisplayName("Test the result of longWordList with the start of The Hobbit")
    public void testLongWordList(){
        String sentence = "In a hole in the ground there lived a hobbit";
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("ground");
        expected.add("there");
        expected.add("lived");
        expected.add("hobbit");
        Assertions.assertEquals(expected, CollectionsExercises.longWordList(sentence));
    }
}
