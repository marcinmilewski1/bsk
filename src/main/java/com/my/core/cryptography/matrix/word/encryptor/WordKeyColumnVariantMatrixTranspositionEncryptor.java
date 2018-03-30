package com.my.core.cryptography.matrix.word.encryptor;

import com.my.core.cryptography.Encryptor;
import com.my.core.cryptography.matrix.util.CharacterMatrix;
import com.my.core.util.CharacterIterator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class WordKeyColumnVariantMatrixTranspositionEncryptor extends WordKeyMatrixTranspositionEncryptor implements Encryptor {
    
    @Override
    public String encrypt(String data, Properties properties) {
        return super.encrypt(data, properties);
    }

    @Override
    public File encrypt(File data, Properties properties) {
        return null;
    }

    @Override
    protected List<CharacterMatrix> createMatrices(String data, int rowsNumber, int columnsNumber) {

        List<String> strings = split(data, getSubstringSize(order.size()));
        List<CharacterMatrix> matrices = new ArrayList<>();

        for (String string : strings) {
            CharacterMatrix matrix = new CharacterMatrix(columnsNumber, rowsNumber);
            CharacterIterator characterIterator = new CharacterIterator(string);
            int row = 0;
            for (Integer currentColumn : order) {
                matrix.setRow(row, characterIterator.getNext(currentColumn + 1));
                ++row;
            }
            matrices.add(matrix);
        }

        return matrices;
    }

    private int getSubstringSize(int length) {
        int size = 0;
        for (int i = length; i > 0; i--) {
            size += i;
        }
        return size;
    }
}
