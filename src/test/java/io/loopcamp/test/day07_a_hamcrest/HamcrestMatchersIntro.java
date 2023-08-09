package io.loopcamp.test.day07_a_hamcrest;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

public class HamcrestMatchersIntro {

    @Test
    public void numberTest() {

        //MatcherAssert.assertThat(1+3, Matchers.is(4)); //This is another way how we can do the assertion.
        assertThat(1+3, is(4));
        assertThat(5+5, equalTo(10));
        assertThat(10+5, is(equalTo(15))); //chaining

        assertThat(100+4, is(greaterThan(99)));
        assertTrue(100+4>99); //this is from JUnit assertion

        int num = 20+2;
        assertThat(num, is(not(20)));
        assertThat(num, is(not(equalTo(20))));


    }
@Test
    public void stringTest(){

        String word = "loop academy";
        assertThat(word,is("loop academy"));
        assertThat(word,equalTo("loop academy"));
        assertThat(word,equalToIgnoringCase("LOOP ACADEMY"));

        //startWith - endWith
        assertThat(word, startsWith("loop"));
        assertThat(word, startsWithIgnoringCase("Loop"));
        assertThat(word,endsWith("emy"));
        assertThat(word, endsWithIgnoringCase("ACADEMY"));


        //contains
    assertThat(word,containsString("op "));
    assertThat(word,containsStringIgnoringCase("OOP"));

    //blank string
    String str = " ";
    assertThat(str, blankString());
    assertThat(str,is(blankString()));//blankString();-->method is returning true on both" "and""
    assertThat(str.replace(" ",""),is(blankString()));//blankString();-->method is returning true on both" "and""

   // assertThat(str,is(emptyOrNullString()));//emptyOrNullString();-->method is returning only when String is""
    assertThat(str.replace(" ",""),is(emptyOrNullString()));
    assertThat(str.trim(), emptyOrNullString());


    }

    public void listsTest(){
        List<Integer> nums = Arrays.asList(45,3,6,9);
        List<String> words = Arrays.asList("java","selenium","git","maven", "api");


        //size
        assertThat(nums,hasSize(4));
        assertThat(nums.size(), is(4));
        assertThat(words,hasSize(5));

        //contains
        assertThat(nums,hasItem(9));
        assertThat(nums,hasItems(3,6));
        assertThat(words,hasItems("git", "api"));

        //all the values
        assertThat(nums, containsInAnyOrder(6,9,45,3));//we need to provide all elements
        //assertThat(nums, containsInAnyOrder(6,9,45,3,5));
        //assertThat(nums, containsInAnyOrder(6,9,45));

        assertThat(nums,containsInRelativeOrder(45,3,6,9));//This method will try to verify if the order of the elements is same.

        //every values
        assertThat(nums, everyItem(greaterThan(0)));
        assertThat(nums, everyItem(greaterThanOrEqualTo(3)));
        assertThat(words, everyItem(not(blankString())));
    }

}
