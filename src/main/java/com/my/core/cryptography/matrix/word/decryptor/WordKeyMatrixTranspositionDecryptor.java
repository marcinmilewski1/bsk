package com.my.core.cryptography.matrix.word.decryptor;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.my.core.cryptography.Decryptor;
import com.my.core.cryptography.matrix.util.CharacterMatrix;
import com.my.core.cryptography.matrix.word.properties.WordKeyMatrixTranspositionProperty;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class WordKeyMatrixTranspositionDecryptor implements Decryptor {
    protected final List<Character> alphabet = Lists.newArrayList('a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z');

    @Override
    public String decrypt(String data, Properties properties) {
        String keyword = properties.getProperty(WordKeyMatrixTranspositionProperty.KEY_WORD.name());
        if (keyword.isEmpty()) throw new IllegalArgumentException();
        final int rowsNumber = keyword.length();
        final int columnsNumber = rowsNumber;

        List<Integer> key = getOrder(keyword);
        List<String> strings = getSubstrings(data, rowsNumber * columnsNumber);
        List<CharacterMatrix> matrices = createMatrices(strings.size(), rowsNumber, columnsNumber);

        for (String encoded : strings) {
            Map<Integer, Integer> columnLettersNumberMap = getColumnLettersNumberMap(encoded, rowsNumber, columnsNumber);
            Map<Integer, String> columnSubstringMap = getColumnSubstringMap(encoded, columnLettersNumberMap, key);

            matrices.add(createMatrixWithEncodedData(rowsNumber, columnSubstringMap));
        }

        StringBuilder sb = new StringBuilder();
        matrices.forEach(m -> sb.append(m.readByLines()));
        return sb.toString();
    }

    @Override
    public File decrypt(File data, Properties properties) {
        return null;
    }

    protected CharacterMatrix createMatrixWithEncodedData(int rowsNumber, Map<Integer, String> columnSubstringMap) {
        CharacterMatrix characterMatrix = new CharacterMatrix(rowsNumber, columnSubstringMap.size());
        columnSubstringMap.entrySet().stream().forEach(e -> characterMatrix.setColumn(e.getKey(), e.getValue()));
        return characterMatrix;
    }

    protected Map<Integer, String> getColumnSubstringMap(String encoded, Map<Integer, Integer> columnToLettersNumberMap, List<Integer> key) {
        Map<Integer, String> columnToSubstringMap = new HashMap<>();
        int startIndex = 0;
        for (Integer currentColumn : key) {
            columnToSubstringMap.put(currentColumn, getColumnSubstring(encoded, startIndex, columnToLettersNumberMap.get(currentColumn)));
            startIndex += columnToLettersNumberMap.get(currentColumn);
        }
        return columnToSubstringMap;
    }

    protected String getColumnSubstring(String encoded, Integer startIndex, Integer offset) {
        return encoded.length() > startIndex + offset ? encoded.substring(startIndex, startIndex + offset) : encoded.substring(startIndex);
    }

    protected Map<Integer, Integer> getColumnLettersNumberMap(String encoded, int rowsNumber, int columnsNumber) {
        Map<Integer, Integer> columnLettersNumberMap = new HashMap<>();
        final int mod = encoded.length() % columnsNumber;
        final int standardLengthOfColumn = (int) Math.floor(encoded.length() / columnsNumber);
        for (int i = 0; i < columnsNumber; i++) {
            columnLettersNumberMap.put(i, i + 1 <= mod ? standardLengthOfColumn + 1 : standardLengthOfColumn);
        }
        return columnLettersNumberMap;
    }

    protected List<String> getSubstrings(String data, int size) {
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

    protected List<CharacterMatrix> createMatrices(int matricesNumber, int rowsNumber, int columnsNumber) {
        List<CharacterMatrix> matrices = new ArrayList<>();
        for (int i = 0; i < matricesNumber; ++i) {
            matrices.add(new CharacterMatrix(columnsNumber, rowsNumber));
        }
        return matrices;
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