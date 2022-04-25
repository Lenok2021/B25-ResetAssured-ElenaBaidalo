package com.cydeo.day5;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

        assertThat(5+5,is(10));
        assertThat(5+5,equalTo(10));

        //matchers has 2 overloaded version
        //first that accept actual value
        //second that accept another matchers
        assertThat(5+5,is(equalTo(10)));

        assertThat(5+5,not(9));
        assertThat(5+5,is(not(9)));
        assertThat(5+5,is(not(equalTo(8))));

        //greaterThan()
        //greaterThanOrEqualTo()
        //lessThan()
        //lessThanOrEqualTo()
        assertThat(5+5,is(greaterThan(9)));

    }




}
