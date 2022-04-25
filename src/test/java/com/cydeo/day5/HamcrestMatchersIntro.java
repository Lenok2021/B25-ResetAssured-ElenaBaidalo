package com.cydeo.day5;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class HamcrestMatchersIntro {

    /**
     * one of Assertion way, you can implement in any Project not only APi
     * we gonna do the same way as Assertions, but has more capability
     * we can do same jod in different way, in the easiest way
     * we will be able to automate test in another way
     */

    @DisplayName("Assertion with numbers")
    @Test
    public void test1() {

        assertThat(5 + 5, is(10));
        assertThat(5 + 5, equalTo(10));

        //matcher has 2 overloaded version
        //first that accept actual value
        //second that accept another matchers
        assertThat(5 + 5, is(equalTo(10)));

        assertThat(5 + 5, not(9));
        assertThat(5 + 5, is(not(9)));
        assertThat(5 + 5, is(not(equalTo(8))));

        //greaterThan()
        assertThat(5+5, greaterThan(8));
        //greaterThanOrEqualTo()
        assertThat(5+5,greaterThanOrEqualTo(10));
        //lessThan()
        assertThat(5+5,lessThan(15));
        //lessThanOrEqualTo()
        assertThat(5 + 5, is(greaterThan(9)));

    }


    @DisplayName("Assertion with String")
    @Test
    public void test2() {

        String text = "B25 is learning Hamcrest";

        //checking for equality
        assertThat(text, is("B25 is learning Hamcrest"));
        assertThat(text, equalTo("B25 is learning Hamcrest"));
        assertThat(text, is(equalTo("B25 is learning Hamcrest")));

        //check if this text starts with B25
        assertThat(text, startsWith("B25"));
        //case insensitive
        assertThat(text, startsWithIgnoringCase("b25"));
        //ends with
        assertThat(text, endsWith("rest"));

        //check if text contains String learning

        assertThat(text, containsString("learning"));
        //with ignoring case
        assertThat(text, containsStringIgnoringCase("LEARNING"));

        String str = "   ";

        //check if above str is blank
        assertThat(str, blankString());
        //check if trimmed str is empty string
        assertThat(str.trim(), emptyString());


    }

    @DisplayName("Hamcrest for Collection")
    @Test
    public void testCollection() {

        List<Integer> listOfNumbers = Arrays.asList(1, 4, 5, 6, 32, 54, 66, 43, 12, 312, 35);

        //check size of the list
        assertThat(listOfNumbers, hasSize(11));

        //check if this list has Item 54
        assertThat(listOfNumbers, hasItem(54));

        //check if this list hasItems 6,54,43
        assertThat(listOfNumbers, hasItems(6, 54, 43));

        //check if all numbers are greater than 0
        assertThat(listOfNumbers, everyItem(greaterThan(0)));


    }
}

