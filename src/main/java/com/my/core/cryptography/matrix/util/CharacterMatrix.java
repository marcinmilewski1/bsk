package com.my.core.cryptography.matrix.util;

import com.my.core.util.CharacterIterator;

import java.util.List;

public class CharacterMatrix {
    private final int width;
    private final int height;

    private Character[][] matrix;

    public CharacterMatrix(int width, int height) {
        if (width < 0 || height < 0) throw new IllegalArgumentException();
        this.width = width;
        this.height = height;
        matrix = new Character[width][height];
    }

    public CharacterMatrix(int width, int height, String data) {
        if (width < 0 || height < 0) throw new IllegalArgumentException();
        this.width = width;
        this.height = height;
        matrix = new Character[width][height];
        set(data);
    }

    public void set(String data) {
        CharacterIterator characterIterator = new CharacterIterator(data);
        for (int j = 0; j < height; ++j) {
            for (int i = 0; i < width; ++i) {
                matrix[j][i] = characterIterator.hasNext() ? characterIterator.next() : null;
            }
        }
    }

    public String getColumn(int column) {
        if (column >= width) {
            throw new IllegalArgumentException();
        } else {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < height; i++) {
                if (matrix[i][column] != null) {
                    sb.append(matrix[i][column]);
                }
            }
            return sb.toString();
        }
    }

    public void setColumn(int column, String data) {
        if (data.length() > height) throw new IllegalArgumentException();
        CharacterIterator characterIterator = new CharacterIterator(data);
        for (int i = 0; i < height; i++) {
            matrix[i][column] = characterIterator.hasNext() ? characterIterator.next() : null;
        }
    }

    public void setColumn(int column, List<Character> characterList) {
        if (characterList.size() > height) throw new IllegalArgumentException();
        for (int i = 0; i < height; i++) {
            matrix[i][column] = i < characterList.size() ? characterList.get(i) : null;
        }
    }
    public void setRow(int row, String data) {
        if (data.length() > width) throw new IllegalArgumentException();
        CharacterIterator characterIterator = new CharacterIterator(data);
        for (int i = 0; i < width; i++) {
            matrix[row][i] = characterIterator.hasNext() ? characterIterator.next() : null;
        }
    }

    public String readByLines() {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < height; ++j) {
            for (int i = 0; i < width; ++i) {
                sb.append(matrix[j][i] != null ? matrix[j][i] : "");
            }
        }
        return sb.toString();
    }

    public String getRow(int row) {
        if (row >= height) {
            throw new IllegalArgumentException();
        } else {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < width; i++) {
                if (matrix[row][i] != null) {
                    sb.append(matrix[row][i]);
                }
            }
            return sb.toString();
        }
    }
}
