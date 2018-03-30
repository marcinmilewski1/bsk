package com.my.core.util;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableMap;

public class AlphabetBiMap {
    public static final ImmutableBiMap<Integer, Character> alphabet =
            new ImmutableBiMap.Builder<Integer, Character>()
                .put(0, 'a')
                .put(1, 'b')
                .put(2, 'c')
                .put(3, 'd')
                .put(4, 'e')
                .put(5, 'f')
                .put(6, 'g')
                .put(7, 'h')
                .put(8, 'i')
                .put(9, 'j')
                .put(10, 'k')
                .put(11, 'l')
                .put(12, 'm')
                .put(13, 'n')
                .put(14, 'o')
                .put(15, 'p')
                .put(16, 'q')
                .put(17, 'r')
                .put(18, 's')
                .put(19, 't')
                .put(20, 'u')
                .put(21, 'v')
                .put(22, 'w')
                .put(23, 'x')
                .put(24, 'y')
                .put(25, 'z')
                    .build();

    public static final ImmutableMap<Character, Integer> characterValueMap = alphabet.inverse();

    public static final ImmutableMap<Integer, ImmutableBiMap<Integer, Character>> alphabets = buildAlphabetsMap();

    private static ImmutableMap<Integer, ImmutableBiMap<Integer, Character>> buildAlphabetsMap() {
        ImmutableMap.Builder<Integer, ImmutableBiMap<Integer, Character>> builder = new ImmutableMap.Builder<>();
        for (int i = 0; i < 26; i++) {
            builder.put(i, getShiftedAlphabet(i));
        }
        return builder.build();
    }

    private static ImmutableBiMap<Integer, Character> getShiftedAlphabet(int shift) {
        ImmutableBiMap.Builder<Integer, Character> builder = new ImmutableBiMap.Builder<>();
        for (int i = 0; i < alphabet.size(); i++) {
            builder.put(i, alphabet.get((i + shift) % alphabet.size()));
        }
        return builder.build();
    }

}
