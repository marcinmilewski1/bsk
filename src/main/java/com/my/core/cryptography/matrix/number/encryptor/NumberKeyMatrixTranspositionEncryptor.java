package com.my.core.cryptography.matrix.number.encryptor;

import com.my.core.cryptography.Encryptor;
import com.my.core.cryptography.matrix.util.CharacterMatrix;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class NumberKeyMatrixTranspositionEncryptor implements Encryptor{
    @Override
    public String encrypt(String data, Properties properties) {
        // matrix 5x5, key - 3 4 1 5 2
        List<CharacterMatrix> matrices = createMatrices(data);
        return encryptInternal(matrices);
    }

    @Override
    public File encrypt(File data, Properties properties) {
        return null;
    }

    private List<CharacterMatrix> createMatrices(String data) {
        List<String> strings = split(data, 5, 5);
        List<CharacterMatrix> matrices = new ArrayList<>();
        for (String string : strings) {
            matrices.add(new CharacterMatrix(5, 5,string));
        }
        return matrices;
    }

    private List<String> split(String data, int width, int height) {
        int offest = width * height;
        List<String> result = new ArrayList<>();
        for (int i = 0; i < data.length(); i = i + offest) {
            if (data.length() > i + offest) {
                result.add(data.substring(i, i + offest));
            } else {
                result.add(data.substring(i));
            }
        }
        return result;
    }

    private String encryptInternal(List<CharacterMatrix> matrices) {
        StringBuilder sb = new StringBuilder();
        for (CharacterMatrix matrix : matrices) {
            sb.append(matrix.getColumn(2));
            sb.append(matrix.getColumn(3));
            sb.append(matrix.getColumn(0));
            sb.append(matrix.getColumn(4));
            sb.append(matrix.getColumn(1));
        }
        return sb.toString();
    }
}
