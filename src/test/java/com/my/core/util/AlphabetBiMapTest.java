package com.my.core.util;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import static com.my.core.util.AlphabetBiMap.alphabets;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AlphabetBiMapTest {

    @Test
    public void shiftedAlphabetTest() throws Exception {
        ImmutableMap<Integer, Character> alphabet0 = alphabets.get(0);
        assertThat(alphabet0.get(0), is('a'));
        assertThat(alphabet0.get(1), is('b'));

        ImmutableMap<Integer, Character> alphabet1 = alphabets.get(1);
        assertThat(alphabet1.get(0), is('b'));
        assertThat(alphabet1.get(1), is('c'));

        ImmutableMap<Integer, Character> alphabet25 = alphabets.get(25);
        assertThat(alphabet25.get(0), is('z'));
        assertThat(alphabet25.get(1), is('a'));
    }

}