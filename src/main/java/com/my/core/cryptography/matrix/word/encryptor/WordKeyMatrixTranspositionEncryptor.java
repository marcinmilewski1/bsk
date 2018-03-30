package com.my.core.cryptography.matrix.word.encryptor;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.my.core.cryptography.Encryptor;
import com.my.core.cryptography.matrix.util.CharacterMatrix;
import com.my.core.cryptography.matrix.word.properties.WordKeyMatrixTranspositionProperty;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class WordKeyMatrixTranspositionEncryptor implements Encryptor {
    protected final List<Character> alphabet = Lists.newArrayList('a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z');
    protected List<Integer> order;

    @Override
    public String encrypt(String data, Properties properties) {
        String keyword = properties.getProperty(WordKeyMatrixTranspositionProperty.KEY_WORD.name());
        if (keyword.isEmpty()) throw new IllegalArgumentException();
        final int rowsNumber = keyword.length();
        final int columnsNumber = rowsNumber;

        order = getOrder(keyword);
        List<CharacterMatrix> matrices = createMatrices(data, rowsNumber, columnsNumber);
        return encryptInternal(matrices, keyword);
    }

    @Override
    public File encrypt(File data, Properties properties) {
        return null;
    }

    protected List<CharacterMatrix> createMatrices(String data, int rowsNumber, int columnsNumber) {
        List<String> strings = split(data, rowsNumber * columnsNumber);
        List<CharacterMatrix> matrices = new ArrayList<>();
        for (String string : strings) {
            //string.replaceAll("\\s+",""))
            matrices.add(new CharacterMatrix(columnsNumber, rowsNumber, string));
        }
        return matrices;
    }

    protected List<String> split(String data, int size) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < data.length(); i = i + size) {
            if (data.length() > i + size) {
                result.add(data.substring(i, i + size));
            } else {
                result.add(data.substring(i));
            }
        }
        return result;
    }

    protected String encryptInternal(List<CharacterMatrix> matrices, String keyword) {
        StringBuilder sb = new StringBuilder();
        matrices.forEach(m ->  order.forEach(e -> sb.append(m.getColumn(e))));
        return sb.toString();
    }

    protected List<Integer> getOrder(String keyword) {
        Map<Integer, Character> positionCharacterMap = Maps.newHashMap();
        for (int i = 0; i < keyword.length(); i++) {
            positionCharacterMap.put(i, keyword.charAt(i));
        }
        List<Integer> order = Lists.newArrayList();
        String lowerCaseKeyword = keyword.toLowerCase();
        for (Character character : alphabet) {
            if (keyword.contains(character.toString())) {
                order.addAll(positionCharacterMap.entrySet()
                        .stream()
                        .filter(entry -> Objects.equal(entry.getValue(), character))
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toList()));
            }
        }
        return order;
    }
}
