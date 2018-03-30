package com.my.core.cryptography.des;

import com.my.core.cryptography.generator.stream.util.BinaryUtils;

import java.util.Properties;

import static com.my.core.cryptography.generator.stream.util.BinaryUtils.*;

public class DesUtils {

    /**
     * @return array of 8 byte blocks (complemented with 0 if needed)
     */
    public static boolean[][] createBlocks(byte[] data) {
        boolean[][] dataBits = BinaryUtils.toBoolean2DArray(data);
        int fullBlocksNumber = (int) Math.floor(dataBits.length / 8);
        boolean isNeedComplementation = data.length - (fullBlocksNumber * 8) > 0 ? true : false;

        boolean[][] blocks = isNeedComplementation ? new boolean[fullBlocksNumber + 1][64] : new boolean[fullBlocksNumber][64];

        int blockIndex;
        int byteIndex = 0;
        for (blockIndex = 0; blockIndex < fullBlocksNumber; ++blockIndex) {
            int bitIndex = 0;
            for (int i = 0; i < 8; i++, byteIndex++) {
                for (int j = 0; j < 8; ++j, bitIndex++) {
                    blocks[blockIndex][bitIndex] = dataBits[byteIndex][j];
                }
            }
        }

        if (isNeedComplementation) {
            int bitIndex = 0;
            for (int i = 0; i < 8; i++, byteIndex++) {
                for (int j = 0; j < 8; j++) {
                    blocks[blockIndex][bitIndex] = byteIndex < dataBits.length ? dataBits[byteIndex][j] : false;
                    bitIndex++;
                }
            }
        }
        return blocks;
    }

    public static boolean[] get64KeyBlock(Properties properties) {
        final String keyContent = properties.getProperty(String.valueOf(DesProperty.KEY)).replaceAll("\\s+","");
        if (keyContent.isEmpty() && keyContent.length() != 64) throw new IllegalArgumentException();
        return BinaryUtils.toBooleanArray(keyContent.replace("/s", ""));
    }

    public static final int[] initialPermutationLUT = new int[]{
            57, 49, 41, 33, 25, 17, 9, 1,
            59, 51, 43, 35, 27, 19, 11, 3,
            61, 53, 45, 37, 29, 21, 13, 5,
            63, 55, 47, 39, 31, 23, 15, 7,
            56, 48, 40, 32, 24, 16, 8, 0,
            58, 50, 42, 34, 26, 18, 10, 2,
            60, 52, 44, 36, 28, 20, 12, 4,
            62, 54, 46, 38, 30, 22, 14, 6
    };

    public static final int[] finalPermutationLUT = new int[]{
            39, 7, 47, 15, 55, 23, 63, 31,
            38, 6, 46, 14, 54, 22, 62, 30,
            37, 5, 45, 13, 53, 21, 61, 29,
            36, 4, 44, 12, 52, 20, 60, 28,
            35, 3, 43, 11, 51, 19, 59, 27,
            34, 2, 42, 10, 50, 18, 58, 26,
            33, 1, 41, 9, 49, 17, 57, 25,
            32, 0, 40, 8, 48, 16, 56, 24
    };

    public static final int[] expansionFunctionLUT = new int[]{
            31, 0, 1, 2, 3, 4,
            3, 4, 5, 6, 7, 8,
            7, 8, 9, 10, 11, 12,
            11, 12, 13, 14, 15, 16,
            15, 16, 17, 18, 19, 20,
            19, 20, 21, 22, 23, 24,
            23, 24, 25, 26, 27, 28,
            27, 28, 29, 30, 31, 0
    };

    public static final int[] pPermutationLUT = new int[]{
            15, 6, 19, 20, 28, 11, 27, 16,
            0, 14, 22, 25, 4, 17, 30, 9,
            1, 7, 23, 13, 31, 26, 2, 8,
            18, 12, 29, 5, 21, 10, 3, 24
    };

    public static final int[] pc1PermutationLUT = new int[]{
            56, 48, 40, 32, 24, 16, 8,
            0, 57, 49, 41, 33, 25, 17,
            9, 1, 58, 50, 42, 34, 26,
            18, 10, 2, 59, 51, 43, 35,
            62, 54, 46, 38, 30, 22, 14,
            6, 61, 53, 45, 37, 29, 21,
            13, 5, 60, 52, 44, 36, 28,
            20, 12, 4, 27, 19, 11, 3
    };

    public static final int[] pc2PermutationLUT = new int[]{
            13, 16, 10, 23, 0, 4,
            2, 27, 14, 5, 20, 9,
            22, 18, 11, 3, 25, 7,
            15, 6, 26, 19, 12, 1,
            40, 51, 30, 36, 46, 54,
            29, 39, 50, 44, 32, 47,
            43, 48, 38, 55, 33, 52,
            45, 41, 49, 35, 28, 31
    };

    public static final boolean[][] sBox1Row0 = new boolean[][]{
            b14, b4, b13, b1, b2, b15, b11, b8, b3, b10, b6, b12, b5, b9, b0, b7
    };
    public static final boolean[][] sBox1Row1 = new boolean[][]{
            b0, b15, b7, b4, b14, b2, b13, b1, b10, b6, b12, b11, b9, b5, b3, b8
    };
    public static final boolean[][] sBox1Row2 = new boolean[][]{
            b4, b1, b14, b8, b13, b6, b2, b11, b15, b12, b9, b7, b3, b10, b5, b0
    };
    public static final boolean[][] sBox1Row3 = new boolean[][]{
            b15, b12, b8, b2, b4, b9, b1, b7, b5, b11, b3, b14, b10, b0, b6, b13
    };

    public static final boolean[][] sBox2Row0 = new boolean[][]{
            b15, b1, b8, b14, b6, b11, b3, b4, b9, b7, b2, b13, b12, b0, b5, b10
    };
    public static final boolean[][] sBox2Row1 = new boolean[][]{
            b3, b13, b4, b7, b15, b2, b8, b14, b12, b0, b1, b10, b6, b9, b11, b5
    };
    public static final boolean[][] sBox2Row2 = new boolean[][]{
            b0, b14, b7, b11, b10, b4, b13, b1, b5, b8, b12, b6, b9, b3, b2, b15
    };
    public static final boolean[][] sBox2Row3 = new boolean[][]{
            b13, b8, b10, b1, b3, b15, b4, b2, b11, b6, b7, b12, b0, b5, b14, b9
    };

    public static final boolean[][] sBox3Row0 = new boolean[][]{
            b10, b0, b9, b14, b6, b3, b15, b5, b1, b13, b12, b7, b11, b4, b2, b8
    };
    public static final boolean[][] sBox3Row1 = new boolean[][]{
            b13, b7, b0, b9, b3, b4, b6, b10, b2, b8, b5, b14, b12, b11, b15, b1
    };
    public static final boolean[][] sBox3Row2 = new boolean[][]{
            b13, b6, b4, b9, b8, b15, b3, b0, b11, b1, b2, b12, b5, b10, b14, b7
    };
    public static final boolean[][] sBox3Row3 = new boolean[][]{
            b1, b10, b13, b0, b6, b9, b8, b7, b4, b15, b14, b3, b11, b5, b2, b12
    };

    public static final boolean[][] sBox4Row0 = new boolean[][]{
            b7, b13, b14, b3, b0, b6, b9, b10, b1, b2, b8, b5, b11, b12, b4, b15
    };
    public static final boolean[][] sBox4Row1 = new boolean[][]{
            b13, b8, b11, b5, b6, b15, b0, b3, b4, b7, b2, b12, b1, b10, b14, b9
    };
    public static final boolean[][] sBox4Row2 = new boolean[][]{
            b10, b6, b9, b0, b12, b11, b7, b13, b15, b1, b3, b14, b5, b2, b8, b4
    };
    public static final boolean[][] sBox4Row3 = new boolean[][]{
            b3, b15, b0, b6, b10, b1, b13, b8, b9, b4, b5, b11, b12, b7, b2, b14
    };

    public static final boolean[][] sBox5Row0 = new boolean[][]{
            b2, b12, b4, b1, b7, b10, b11, b6, b8, b5, b3, b15, b13, b0, b14, b9
    };
    public static final boolean[][] sBox5Row1 = new boolean[][]{
            b14, b11, b2, b12, b4, b7, b13, b1, b5, b0, b15, b10, b3, b9, b8, b6
    };
    public static final boolean[][] sBox5Row2 = new boolean[][]{
            b4, b2, b1, b11, b10, b13, b7, b8, b15, b9, b12, b5, b6, b3, b0, b14
    };
    public static final boolean[][] sBox5Row3 = new boolean[][]{
            b11, b8, b12, b7, b1, b14, b2, b13, b6, b15, b0, b9, b10, b4, b5, b3
    };

    public static final boolean[][] sBox6Row0 = new boolean[][]{
            b12, b1, b10, b15, b9, b2, b6, b8, b0, b13, b3, b4, b14, b7, b5, b11
    };
    public static final boolean[][] sBox6Row1 = new boolean[][]{
            b10, b15, b4, b2, b7, b12, b9, b5, b6, b1, b13, b14, b0, b11, b3, b8
    };
    public static final boolean[][] sBox6Row2 = new boolean[][]{
            b9, b14, b15, b5, b2, b8, b12, b3, b7, b0, b4, b10, b1, b13, b11, b6
    };
    public static final boolean[][] sBox6Row3 = new boolean[][]{
            b4, b3, b2, b12, b9, b5, b15, b10, b11, b14, b1, b7, b6, b0, b8, b13
    };

    public static final boolean[][] sBox7Row0 = new boolean[][]{
            b4, b11, b2, b14, b15, b0, b8, b13, b3, b12, b9, b7, b5, b10, b6, b1
    };
    public static final boolean[][] sBox7Row1 = new boolean[][]{
            b13, b0, b11, b7, b4, b9, b1, b10, b14, b3, b5, b12, b2, b15, b8, b6
    };
    public static final boolean[][] sBox7Row2 = new boolean[][]{
            b1, b4, b11, b13, b12, b3, b7, b14, b10, b15, b6, b8, b0, b5, b9, b2
    };
    public static final boolean[][] sBox7Row3 = new boolean[][]{
            b6, b11, b13, b8, b1, b4, b10, b7, b9, b5, b0, b15, b14, b2, b3, b12
    };

    public static final boolean[][] sBox8Row0 = new boolean[][]{
            b13, b2, b8, b4, b6, b15, b11, b1, b10, b9, b3, b14, b5, b0, b12, b7
    };
    public static final boolean[][] sBox8Row1 = new boolean[][]{
            b1, b15, b13, b8, b10, b3, b7, b4, b12, b5, b6, b11, b0, b14, b9, b2
    };
    public static final boolean[][] sBox8Row2 = new boolean[][]{
            b7, b11, b4, b1, b9, b12, b14, b2, b0, b6, b10, b13, b15, b3, b5, b8
    };
    public static final boolean[][] sBox8Row3 = new boolean[][]{
            b2, b1, b14, b7, b4, b10, b8, b13, b15, b12, b9, b0, b3, b5, b6, b11
    };

    public static final boolean[][][] sBox1 = new boolean[][][]{
        sBox1Row0, sBox1Row1, sBox1Row2, sBox1Row3
    };
    public static final boolean[][][] sBox2 = new boolean[][][]{
        sBox2Row0, sBox2Row1, sBox2Row2, sBox2Row3
    };
    public static final boolean[][][] sBox3 = new boolean[][][]{
        sBox3Row0, sBox3Row1, sBox3Row2, sBox3Row3
    };
    public static final boolean[][][] sBox4 = new boolean[][][]{
        sBox4Row0, sBox4Row1, sBox4Row2, sBox4Row3
    };
    public static final boolean[][][] sBox5 = new boolean[][][]{
        sBox5Row0, sBox5Row1, sBox5Row2, sBox5Row3
    };
    public static final boolean[][][] sBox6 = new boolean[][][]{
        sBox6Row0, sBox6Row1, sBox6Row2, sBox6Row3
    };
    public static final boolean[][][] sBox7 = new boolean[][][]{
        sBox7Row0, sBox7Row1, sBox7Row2, sBox7Row3
    };
    public static final boolean[][][] sBox8 = new boolean[][][]{
        sBox8Row0, sBox8Row1, sBox8Row2, sBox8Row3
    };

    public static final boolean[][][][] sBoxes = new boolean[][][][] {
            sBox1, sBox2, sBox3, sBox4, sBox5, sBox6, sBox7, sBox8
    };

    public static boolean[] getSBoxBinValue(int no, int row, int column) {
        return sBoxes[no][row][column];
    }
    
}
