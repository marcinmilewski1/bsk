package com.my.core.cryptography.railfence.encryptor;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import com.my.core.cryptography.Encryptor;
import com.my.core.cryptography.railfence.properties.RailfenceProperty;
import com.my.core.util.CharacterIterator;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static com.my.core.cryptography.railfence.util.RailFenceUtil.upDownList;

public class RailFenceEncryptor implements Encryptor {

    @Override
    public String encrypt(String data, Properties properties) {
        String depth = properties.getProperty(RailfenceProperty.DEPTH.name());
        if (depth == null || depth.isEmpty() || data.isEmpty()) throw new IllegalArgumentException();
        return encryptInternal(Integer.valueOf(depth), data);
    }

    @Override
    public File encrypt(File data, Properties properties) {
        return null;
    }

    private String encryptInternal(int depth, String data) {
        List<Integer> upDownList = upDownList(depth, data.length());
        Multimap<Integer, Character> rowCharacterMap = getRowCharacterMap(upDownList, data, depth);
        return toString(rowCharacterMap);
    }

    private String toString(Multimap<Integer, Character> rowCharacterMap) {
        Set<Integer> sortedKeySet = rowCharacterMap.keys().stream().sorted().collect(Collectors.toCollection(TreeSet<Integer>::new));
        StringBuilder sb = new StringBuilder();
        sortedKeySet.forEach(e -> rowCharacterMap.get(e).forEach(c -> sb.append(c)));
        return sb.toString();
    }

    private Multimap<Integer,Character> getRowCharacterMap(List<Integer> upDownList, String data, int depth) {
        Multimap<Integer, Character> rowCharacterMap = LinkedListMultimap.create();
        CharacterIterator characterIterator = new CharacterIterator(data);
        for (Integer integer : upDownList) {
            rowCharacterMap.put(integer, characterIterator.next());
        }
        return rowCharacterMap;
    }

    public static Multimap<Integer, Integer> getRowIntegerRailFenceOrderMap(List<Integer> upDownList, List<Integer> data, int depth) {
        Multimap<Integer, Integer> rowIntegerMap = LinkedListMultimap.create();
        Iterator<Integer> iterator = data.iterator();
        for (Integer integer : upDownList) {
            rowIntegerMap.put(integer, iterator.next());
        }
        return rowIntegerMap;
    }





}
