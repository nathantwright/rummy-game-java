package com.sparta.ntw.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class AnimalTest {
    @Test
    @DisplayName("Test Parrot's speak()")
    public void testParrotSpeak(){
        Parrot sut = new Parrot("Rebeaka", 2020, 12, 3, "LAND HO, SHIVER ME TIMBERS");
        Assertions.assertEquals("SQUAWK! I BE REBEAKA. LAND HO, SHIVER ME TIMBERS", sut.speak());
    }

    @Test
    @DisplayName("Test young Lion's speak()")
    public void testYoungLionSpeak(){
        Lion sut = new Lion("Rufus", LocalDate.now().getYear(), 1, 1);
        Assertions.assertEquals("RAR!", sut.speak());
    }

    @Test
    @DisplayName("Test older Lion's speak()")
    public void testOldLionSpeak(){
        Lion sut = new Lion("Alex", LocalDate.now().getYear()-10, 1, 1);
        Assertions.assertEquals("ROOOOOAR!", sut.speak());
    }

    @Test
    @DisplayName("Test Parrot's toString()")
    public void testParrotToString(){
        Parrot sut = new Parrot("Rebeaka", LocalDate.now().getYear()-3, 1, 1, "LAND HO, SHIVER ME TIMBERS");
        Assertions.assertEquals("The Parrot called Rebeaka is 3 years old and likes to say LAND HO, SHIVER ME TIMBERS", sut.toString());
    }

    @Test
    @DisplayName("Test Lion's toString()")
    public void testLionToString(){
        Lion sut = new Lion("Alex", LocalDate.now().getYear()-10, 1, 1);
        Assertions.assertEquals("The Lion called Alex is 10 years old", sut.toString());
    }

    @Test
    @DisplayName("Test Parrot's getFavouritePhrase()")
    public void testParrotGetFavouritePhrase(){
        Parrot sut = new Parrot("Rebeaka", 2020, 12, 3, "LAND HO, SHIVER ME TIMBERS");
        Assertions.assertEquals("LAND HO, SHIVER ME TIMBERS", sut.getFavouritePhrase());
    }

    @Test
    @DisplayName("Test Parrot's setFavouritePhrase()")
    public void testParrotSetFavouritePhrase(){
        Parrot sut = new Parrot("Rebeaka", 2020, 12, 3, "LAND HO, SHIVER ME TIMBERS");
        String originalFP = sut.getFavouritePhrase();
        sut.setFavouritePhrase("AYE, WALK THE PLANK");
        Assertions.assertNotEquals(originalFP, sut.getFavouritePhrase());
        Assertions.assertEquals("AYE, WALK THE PLANK", sut.getFavouritePhrase());
    }

    @Test
    @DisplayName("Test Animal's getName()")
    public void testAnimalGetName(){
        Parrot sut = new Parrot("Rebeaka", 2020, 12, 3, "LAND HO, SHIVER ME TIMBERS");
        Assertions.assertEquals("Rebeaka", sut.getName());
    }

    @Test
    @DisplayName("Test Animal's setName()")
    public void testAnimalSetName(){
        Lion sut = new Lion("Omar-Faroark", 2020, 12, 3);
        String originalName = sut.getName();
        sut.setName("Shakaroar");
        Assertions.assertNotEquals(originalName, sut.getName());
        Assertions.assertEquals("Shakaroar", sut.getName());
    }

    @Test
    @DisplayName("Test Animal's getAge()")
    public void testAnimalGetAge(){
        Parrot sut = new Parrot("Rebeaka", LocalDate.now().getYear()-3, 1, 1, "LAND HO, SHIVER ME TIMBERS");
        Assertions.assertEquals(3, sut.getAge());
    }
}
