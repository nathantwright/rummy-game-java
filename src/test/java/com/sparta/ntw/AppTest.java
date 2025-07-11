package com.sparta.ntw;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.InputMismatchException;

public class AppTest {
    @Test
    @DisplayName("getGreeting returns 'Good evening!' when given 4")
    void testGetGreeting4GoodEvening(){
        Assertions.assertEquals("Good evening!", App.getGreeting(4));
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 12})
    @DisplayName("getGreeting returns 'Good morning!' when given 5 or 12")
    void testGetGreeting5and12GoodMorning(int time){
        Assertions.assertEquals("Good morning!", App.getGreeting(time));
    }

    @ParameterizedTest
    @CsvSource({
            "Good nooning!, 13",
            "Good nooning!, 18",
            "Good evening!, 19",
            "Good evening!, 23",
    })
    @DisplayName("getGreeting returns sensible greeting given time")
    void testGetGreetingCSVSource(String greeting, int time){
        Assertions.assertEquals(greeting, App.getGreeting(time));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 24})
    @DisplayName("Test if exception to invalid data thrown correctly")
    void testGetGreetingInvalidData(int time){
        Assertions.assertThrows(InputMismatchException.class, () -> App.getGreeting(time));
    }

    // 0 4 - eve
    //5 12 - mor
    //13 18 - noo
    // 19 23 - eve
}
