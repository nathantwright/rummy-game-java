package com.sparta.ntw;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.InputMismatchException;

public class FilmClassificationTests {
    @ParameterizedTest
    @CsvSource(value = {
            "U & PG films are available.; 0",
            "U, PG, & 12 films are available.; 12",
            "U, PG, 12, & 15 films are available.; 15",
            "All films are available.; 18",
            "U & PG films are available.; 11",
            "U, PG, & 12 films are available.; 14",
            "U, PG, 12, & 15 films are available.; 17",
            "All films are available.; 149",
    }, delimiter = ';')
    @DisplayName("getClassificationsByAge returns sensible result given age")
    void testGetClassificationsByAgeCSVSource(String result, int age){
        Assertions.assertEquals(result, FilmClassification.getClassificationsByAge(age));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 150})
    @DisplayName("Test if exception to invalid data thrown correctly")
    void testGetClassificationsByAgeInvalidData(int age){
        Assertions.assertThrows(InputMismatchException.class, () -> FilmClassification.getClassificationsByAge(age));
    }
}
