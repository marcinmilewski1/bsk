package com.my.core.cryptography.generator.stream.util;

import com.google.common.primitives.Booleans;

import java.util.BitSet;

public class BinaryUtils {
    public static final boolean T = true;
    public static final boolean F = false;
    public static final boolean[] b0 = {F, F, F, F};
    public static final boolean[] b1 = {F, F, F, T};
    public static final boolean[] b2 = {F, F, T, F};
    public static final boolean[] b3 = {F, F, T, T};
    public static final boolean[] b4 = {F, T, F, F};
    public static final boolean[] b5 = {F, T, F, T};
    public static final boolean[] b6 = {F, T, T, F};
    public static final boolean[] b7 = {F, T, T, T};
    public static final boolean[] b8 = {T, F, F, F};
    public static final boolean[] b9 = {T, F, F, T};
    public static final boolean[] b10 = {T, F, T, F};
    public static final boolean[] b11 = {T, F, T, T};
    public static final boolean[] b12 = {T, T, F, F};
    public static final boolean[] b13 = {T, T, F, T};
    public static final boolean[] b14 = {T, T, T, F};
    public static final boolean[] b15 = {T, T, T, T};

    public static int toInt(boolean[] bits) {
        int n = 0, l = bits.length;
        for (int i = 0; i < l; ++i) {
            n = (n << 1) + (bits[i] ? 1 : 0);
        }
        return n;
    }

    public static boolean[] getBooleanArray(String maskString) {
        boolean[] mask = new boolean[maskString.length()];
        for (int i = 0; i < maskString.length(); i++) {
            mask[i] = maskString.charAt(i) == '1' ? true:  false;
        }
        return mask;
    }

    public static boolean[] shiftRightNoCarry(boolean[] bits) {
        boolean[] shifted = new boolean[bits.length];
        shifted[0] = false;
        int j = 1;
        for (int i = 0; i < bits.length - 1; i++, j++) {
            shifted[j] =  bits[i];
        }
        return shifted;
    }

    public static boolean xor(boolean x, boolean y) {
        return ( ( x || y ) && ! ( x && y ) );
    }

    private byte[] xor(byte[] dataBytes, byte[] generatorBytes) {
        byte[] result = new byte[dataBytes.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = (byte) (((int) dataBytes[i]) ^ ((int) generatorBytes[i]));
        }
        return result;
    }

    public static boolean[] toBooleanArray(BitSet bitset, int length) {
        boolean[] result = new boolean[length];
        for (int i = 0; i < length; i++) {
            result[i] = bitset.get(i);
        }
        return result;
    }

    public static boolean[] toBooleanArray(String zerosOnesString) {
        boolean[] result = new boolean[zerosOnesString.length()];
        for (int i = 0; i < result.length; i++) {
            char c = zerosOnesString.charAt(i);
            if (c != '1' && c != '0') throw new IllegalArgumentException("Invalid character: " + c);

            if (c == '1') {
                result[i] = true;
            } else if (c == '0') {
                result[i] = false;
            }
            else throw new IllegalArgumentException("Illegal character");
        }
        return result;
    }

    public static byte[] toByteArrayLSBLeft(boolean[] input) {
        if (input.length % 8 != 0) throw new IllegalArgumentException("input should divide by 8 ");
        byte[] toReturn = new byte[input.length / 8];
        int j = 0;
        for(int i = 0; i < input.length / 8; i++, j+=8) {
            int k = j;
            toReturn[i] = (byte)((input[k+0]?1<<7:0) + (input[k+1]?1<<6:0) + (input[k+2]?1<<5:0) +
                    (input[k+3]?1<<4:0) + (input[k+4]?1<<3:0) + (input[k+5]?1<<2:0) +
                    (input[k+6]?1<<1:0) + (input[k+7]?1:0));
        }
        return toReturn;
    }

    public static byte[] toByteArrayLSBRight(boolean[] input) {
        if (input.length % 8 != 0) throw new IllegalArgumentException("input should divide by 8 ");
        byte[] toReturn = new byte[input.length / 8];
        int j = 0;
        for(int i = 0; i < input.length / 8; i++, j+=8) {
            int k = j;
            toReturn[i] = (byte)((input[k+7]?1<<7:0) + (input[k+6]?1<<6:0) + (input[k+5]?1<<5:0) +
                    (input[k+4]?1<<4:0) + (input[k+3]?1<<3:0) + (input[k+2]?1<<2:0) +
                    (input[k+1]?1<<1:0) + (input[k+0]?1:0));
        }
        return toReturn;
    }

    public static byte[] toByteArrayLSBLeft(boolean[][] input) {
        return toByteArrayLSBLeft(Booleans.concat(input));
    }

    public static boolean[][] toBoolean2DArray(byte[] input) {
        boolean[][] result = new boolean[input.length][8];
        for (int i = 0; i < input.length; i++) {
            result[i][7] = ((input[i] & 0x01) != 0);
            result[i][6] = ((input[i] & 0x02) != 0);
            result[i][5] = ((input[i] & 0x04) != 0);
            result[i][4] = ((input[i] & 0x08) != 0);
            result[i][3] = ((input[i] & 0x10) != 0);
            result[i][2] = ((input[i] & 0x20) != 0);
            result[i][1] = ((input[i] & 0x40) != 0);
            result[i][0] = ((input[i] & 0x80) != 0);
        }
        return result;
    }


    public static boolean[] shiftLeftWithCarry(boolean[] bits) {
        boolean[] shifted = new boolean[bits.length];
        boolean carry = bits[0];
        for (int i = 0; i < bits.length - 1; i++ ) {
            shifted[i] = bits[i + 1];
        }
        shifted[bits.length - 1] = carry;
        return shifted;
    }

    public static boolean[] shiftLeftWithCarry(boolean[] bits, int n) {
        if (n <= 0) throw new IllegalArgumentException();
        boolean[] shifted = bits;
        for (int i = 0; i < n; i++) {
            shifted = shiftLeftWithCarry(shifted);
        }
        return shifted;
    }

    public static boolean[] xor(boolean[] operand1, boolean[] operand2) {
        if (operand1.length != operand2.length) throw new IllegalArgumentException();
        boolean[] result = new boolean[operand1.length];
        for (int i = 0; i < operand1.length; i++) {
            result[i] = operand1[i] != operand2[i] ? true : false;
        }
        return result;
    }

    public static void swap(boolean[] operand1, boolean[] operand2) {
        boolean[] operand1Tmp = operand1;
        operand1 = operand2;
        operand2 = operand1Tmp;
    }

    public static boolean[] merge(boolean[] left, boolean[] right) {
        boolean[] result = new boolean[left.length + right.length];
        int i = 0;
        for (;i < left.length; i++) {
            result[i] = left[i];
        }
        for (int j = 0;j < right.length; j++, i++) {
            result[i] = right[j];
        }
        return result;
    }

    public static String toString(boolean[] data) {
        if (data.length == 0) return "";
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (boolean b : data) {
            sb.append(b==true ? "1" : "0");
        }
        sb.append(" ]");
        return sb.toString();
    }

    public static String toString(boolean[][] data) {
        if (data.length == 0) return "";
        StringBuilder sb = new StringBuilder();
        sb.append("[[ \n");
        int i = 0;
        for (boolean[] inner : data) {
            sb.append(i + " [ ");
            for (boolean b : inner) {
                sb.append(b==true ? "1" : "0");
            }
            sb.append(" ]\n");
            i++;
        }
        sb.append(" ]]");
        return sb.toString();
    }
}
