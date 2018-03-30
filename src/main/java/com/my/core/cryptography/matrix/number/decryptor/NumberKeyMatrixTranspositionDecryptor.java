package com.my.core.cryptography.matrix.number.decryptor;

import com.google.common.collect.Lists;
import com.my.core.cryptography.Decryptor;
import com.my.core.cryptography.matrix.util.CharacterMatrix;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class NumberKeyMatrixTranspositionDecryptor implements Decryptor {
    @Override
    public String decrypt(String data, Properties properties) {
        final int rowsNumber = 5;
        final int columnsNumber = 5;
        final int size = rowsNumber * columnsNumber;
        List<Integer> key = Lists.newArrayList(3, 4, 1, 5, 2);
        key = key.stream().map(integer -> integer - 1).collect(Collectors.toList());
        List<String> strings = getSubstrings(data, size);
        List<CharacterMatrix> matrices = createMatrices(strings.size());

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

    private CharacterMatrix createMatrixWithEncodedData(int rowsNumber, Map<Integer, String> columnSubstringMap) {
        CharacterMatrix characterMatrix = new CharacterMatrix(rowsNumber, columnSubstringMap.size());
        columnSubstringMap.entrySet().stream().forEach(e -> characterMatrix.setColumn(e.getKey(), e.getValue()));
        return characterMatrix;
    }

    private Map<Integer, String> getColumnSubstringMap(String encoded, Map<Integer, Integer> columnToLettersNumberMap, List<Integer> key) {
        Map<Integer, String> columnToSubstringMap = new HashMap<>();
        int startIndex = 0;
        for (Integer currentColumn : key) {
            columnToSubstringMap.put(currentColumn, getColumnSubstring(encoded, startIndex, columnToLettersNumberMap.get(currentColumn)));
            startIndex += columnToLettersNumberMap.get(currentColumn);
        }
        return columnToSubstringMap;
    }

    private String getColumnSubstring(String encoded, Integer startIndex, Integer offset) {
        return encoded.length() > startIndex + offset ? encoded.substring(startIndex, startIndex + offset) : encoded.substring(startIndex);
    }

    private Map<Integer, Integer> getColumnLettersNumberMap(String encoded, int rowsNumber, int columnsNumber) {
        Map<Integer, Integer> columnLettersNumberMap = new HashMap<>();
        final int mod = encoded.length() % columnsNumber;
        final int standardLengthOfColumn = (int) Math.floor(encoded.length() / columnsNumber);
        for (int i = 0; i < columnsNumber; i++) {
            columnLettersNumberMap.put(i, i + 1 <= mod ? standardLengthOfColumn + 1 : standardLengthOfColumn);
        }
        return columnLettersNumberMap;
    }

    private List<String> getSubstrings(String data, int size) {
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

    private List<CharacterMatrix> createMatrices(int matricesNumber) {
        List<CharacterMatrix> matrices = new ArrayList<>();
        for (int i = 0; i < matricesNumber; ++i) {
            matrices.add(new CharacterMatrix(5, 5));
        }
        return matrices;
    }
}
