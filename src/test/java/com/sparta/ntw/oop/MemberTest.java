package com.sparta.ntw.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class MemberTest {
    @Test
    @DisplayName("getFirstName returns the correct name")
    public void testGetFirstName(){
        // arrange
        Member sut = new Member("Jemma", "Simmons", 1990, 8, 4); //sut = subject under test
        // act and assert
        Assertions.assertEquals("Jemma", sut.getFirstName());
    }

    @Test
    @DisplayName("getLastName returns the correct name")
    public void testGetLastName(){
        // arrange
        Member sut = new Member("Jemma", "Simmons", 1990, 8, 4); //sut = subject under test
        // act and assert
        Assertions.assertEquals("Simmons", sut.getLastName());
    }

    @Test
    @DisplayName("getFullName returns the correct name")
    public void testGetFullName(){
        // arrange
        Member sut = new Member("Jemma", "Simmons", 1990, 8, 4); //sut = subject under test
        // act and assert
        Assertions.assertEquals("Jemma Simmons", sut.getFullName());
    }

    @Test
    @DisplayName("setLastName makes the expected change")
    public void testSetLastName(){
        // arrange
        Member sut = new Member("Jemma", "Simmons", 1990, 8, 4); //sut = subject under test
        // act
        sut.setLastName("Fitz");
        // assert
        Assertions.assertEquals("Fitz", sut.getLastName());
    }

    @Test
    @DisplayName("getMemberDays returns the correct number")
    public void testGetMemberDays(){
        // arrange
        Member sut = new Member("Jemma", "Simmons", 1990, 8, 4); //sut = subject under test
        long expected = LocalDate.of(1990, 8, 4).until(LocalDate.now(), ChronoUnit.DAYS);
        // act and assert
        Assertions.assertEquals(expected, sut.getMemberDays());
    }
}
