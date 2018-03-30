package com.my.core.cryptography.matrix.word.decryptor;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.my.core.cryptography.Decryptor;
import com.my.core.cryptography.matrix.util.CharacterMatrix;
import com.my.core.cryptography.matrix.word.properties.WordKeyMatrixTranspositionProperty;
import com.my.core.util.CharacterIterator;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class WordKeyColumnVariantMatrixTranspositionDecryptor extends WordKeyMatrixTranspositionDecryptor implements Decryptor {
    @Override
    public String decrypt(String data, Properties properties) {
        String keyword = properties.getProperty(WordKeyMatrixTranspositionProperty.KEY_WORD.name());
        if (keyword.isEmpty()) throw new IllegalArgumentException();
        final int rowsNumber = keyword.length();
        final int columnsNumber = rowsNumber;

        List<Integer> order = getOrder(keyword);

        final int subStringSize = getSubstringSize(keyword.length());
        List<String> strings = getSubstrings(data, subStringSize);

        List<CharacterMatrix> matrices = createMatrices(strings.size(), rowsNumber, columnsNumber);

        for (String encoded : strings) {
            Map<Integer, Integer> rowCharsNumberMap = getRowCharsNumberMap(encoded, order);
            Map<Integer, List<Character>> columnSubstringMap = Maps.newHashMap();
            CharacterIterator encodedIterator = new CharacterIterator(encoded);

            for (Integer currentColumn : order) {
                List<Character> columnData = createColumn(encodedIterator, currentColumn, rowCharsNumberMap);
                columnSubstringMap.put(currentColumn, columnData);
            }
            matrices.add(createInitialMatrix(rowsNumber, columnSubstringMap));
        }

        StringBuilder sb = new StringBuilder();
        matrices.forEach(m -> sb.append(m.readByLines()));
        return sb.toString();
    }

    @Override
    public File decrypt(File data, Properties properties) {
        return null;
    }

    private int getSubstringSize(int length) {
        int size = 0;
        for (int i = length; i > 0; i--) {
            size += i;
        }
        return size;
    }

    private CharacterMatrix createInitialMatrix(int rowsNumber, Map<Integer, List<Character>> columnSubstringMap) {
        CharacterMatrix characterMatrix = new CharacterMatrix(rowsNumber, columnSubstringMap.size());
        columnSubstringMap.entrySet().forEach(e -> characterMatrix.setColumn(e.getKey(), e.getValue()));
        return characterMatrix;
    }

    private List<Character> createColumn(CharacterIterator encoded, Integer currentColumn, Map<Integer, Integer> rowCharsNumberMap) {
        if (!encoded.hasNext()) return Lists.newArrayList();
        List<Character> columnData = Lists.newArrayList();
        currentColumn += 1;
        for (int row = 0; row < rowCharsNumberMap.size(); row++) {
            int charsInRow = rowCharsNumberMap.get(row);
            if (currentColumn <= charsInRow && encoded.hasNext()) {
                columnData.add(encoded.next());
            } else {
                columnData.add(null);
            }
        }
        return columnData;
    }


    private Map<Integer, Integer> getRowCharsNumberMap(String encoded, List<Integer> order) {
        Map<Integer, Integer> rowCharsNumberMap = Maps.newHashMap();
        int stringIndex = 0;
        for (int i = 0; i < order.size(); i++) {
            if (stringIndex + order.get(i) <= encoded.length()) {
                rowCharsNumberMap.put(i, order.get(i) + 1);
                stringIndex += order.get(i) + 1;
            } else if (stringIndex != encoded.length()) {
                rowCharsNumberMap.put(i, encoded.length() - stringIndex);
                stringIndex = encoded.length();
            } else {
                rowCharsNumberMap.put(i, 0);
            }
        }
        return rowCharsNumberMap;
    }
}
