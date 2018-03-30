package com.my.core.cryptography.des;

import com.my.core.cryptography.generator.stream.util.BinaryUtils;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class DesAlgorithmTest {

    @Test
    public void create64BitKeyTest() throws Exception {
        boolean[] key = BinaryUtils.
                toBooleanArray(new String("00010011 00110100 01010111 01111001 10011011 10111100 11011111 11110001").replaceAll("\\s", ""));
        boolean[] pc1Key = DesAlgorithm.create64BitKey(key);
        assertThat(pc1Key.length, is(56));
        assertThat(pc1Key[0], is(true));
        assertThat(pc1Key[1], is(true));
        assertThat(pc1Key[2], is(true));
        assertThat(pc1Key[3], is(true));
        assertThat(pc1Key[4], is(false));
        assertThat(pc1Key[54], is(true));
        assertThat(pc1Key[55], is(true));
    }

    @Test
    public void create16Subkeys() throws Exception {
        boolean[] key = BinaryUtils.
                toBooleanArray(new String("00010011 00110100 01010111 01111001 10011011 10111100 11011111 11110001").replaceAll("\\s", ""));
        boolean[] pc1Key = DesAlgorithm.create64BitKey(key);
        boolean[][] subKeys = DesAlgorithm.create16Subkeys(pc1Key);
        assertThat(subKeys[0][0], is(false));
        assertThat(subKeys[0][1], is(false));
        assertThat(subKeys[0][2], is(false));
        assertThat(subKeys[0][3], is(true));
        assertThat(subKeys[0][4], is(true));
        assertThat(subKeys[0][5], is(false));
    }

    @Test
    public void initialPermutationTest() throws Exception {
        boolean[] block = BinaryUtils.
                toBooleanArray(new String("0000 0001 0010 0011 0100 0101 0110 0111 1000 1001 1010 1011 1100 1101 1110 1111").replaceAll("\\s", ""));
        boolean[] ipBlock = DesAlgorithm.initialPermutate(block);
        boolean[] expected = BinaryUtils.
                toBooleanArray(new String("1100 1100 0000 0000 1100 1100 1111 1111 1111 0000 1010 1010 1111 0000 1010 1010").replaceAll("\\s", ""));
        assertTrue(Arrays.equals(ipBlock, expected));
    }

    @Test
    public void pc1PermutationTest() throws Exception {
        boolean[] block = BinaryUtils.
                toBooleanArray(new String("00111011 00111000 10011000 00110111 00010101 00100000 11110111 01011110").replaceAll("\\s", ""));
        boolean[] pc1Permutated = DesAlgorithm.create64BitKey(block);
        boolean[] expected = BinaryUtils.
                toBooleanArray(new String("0100010 0110000 0001101 0111101 1100100 1110110 0010000 1111111").replaceAll("\\s", ""));
        assertThat(pc1Permutated, is(expected));
    }

    @Test
    public void finalPermutationTest() throws Exception {
        boolean[] block = BinaryUtils.
                toBooleanArray(new String("00001010 01001100 11011001 10010101 01000011 01000010 00110010 00110100").replaceAll("\\s", ""));
        boolean[] ipBlock = DesAlgorithm.finalPermutate(block);
        boolean[] expected = BinaryUtils.
                toBooleanArray(new String("10000101 11101000 00010011 01010100 00001111 00001010 10110100 00000101").replaceAll("\\s", ""));
        assertTrue(Arrays.equals(ipBlock, expected));
    }

    @Test
    public void finalPermutationTest2() throws Exception {
        boolean[] block = BinaryUtils.
                toBooleanArray(new String("10010100 10010101 01110101 01001000 11011100 11110001 11011111 00111110").replaceAll("\\s", ""));
        boolean[] ipBlock = DesAlgorithm.finalPermutate(block);
        boolean[] expected = BinaryUtils.
                toBooleanArray(new String("00111100 00001010 11011110 10001011 11111110 00100110 10101101 11111000").replaceAll("\\s", ""));
        assertTrue(Arrays.equals(ipBlock, expected));
    }

    @Test
    public void getExpandedTest() throws Exception {
        boolean[] bits32 = BinaryUtils.
                toBooleanArray(new String("1111 0000 1010 1010 1111 0000 1010 1010 ").replaceAll("\\s", ""));
        boolean[] expected = BinaryUtils.
                toBooleanArray(new String("011110 100001 010101 010101 011110 100001 010101 010101 ").replaceAll("\\s", ""));
        boolean[] result = DesAlgorithm.getExpanded(bits32);
        assertTrue(Arrays.equals(result, expected));
    }

    @Test
    public void encryptBlockTest() throws Exception {
        boolean[] key = BinaryUtils.
                toBooleanArray(new String("00010011 00110100 01010111 01111001 10011011 10111100 11011111 11110001").replaceAll("\\s", ""));
        boolean[] pc1Key = DesAlgorithm.create64BitKey(key);
        boolean[][] subKeys = DesAlgorithm.create16Subkeys(pc1Key);
        boolean[] block = BinaryUtils.toBooleanArray(new String("0000 0001 0010 0011 0100 0101 0110 0111 1000 1001 1010 1011 1100 1101 1110 1111").replaceAll("\\s", ""));
        boolean[] expected = BinaryUtils.toBooleanArray(new String("1000 0101 1110 1000 0001 0011 0101 0100 0000 1111 0000 1010 1011 0100 0000 0101").replaceAll("\\s", ""));
        boolean[] result = DesAlgorithm.encryptBlock(block, subKeys);
        assertThat(result, is(expected));
    }

    @Test
    public void decryptBlockTest() throws Exception {
        boolean[] key = BinaryUtils.
                toBooleanArray(new String("00010011 00110100 01010111 01111001 10011011 10111100 11011111 11110001").replaceAll("\\s", ""));
        boolean[] pc1Key = DesAlgorithm.create64BitKey(key);
        boolean[][] subKeys = DesAlgorithm.create16Subkeys(pc1Key);
        boolean[] block = BinaryUtils.toBooleanArray(new String("1000 0101 1110 1000 0001 0011 0101 0100 0000 1111 0000 1010 1011 0100 0000 0101").replaceAll("\\s", ""));
        boolean[] expected = BinaryUtils.toBooleanArray(new String("0000 0001 0010 0011 0100 0101 0110 0111 1000 1001 1010 1011 1100 1101 1110 1111").replaceAll("\\s", ""));
        boolean[] result = DesAlgorithm.decryptBlock(block, subKeys);
        assertThat(result, is(expected));
    }


    @Test
    public void getFunctionValueTest() throws Exception {
        boolean[] key = BinaryUtils.toBooleanArray(new String("000110 110000 001011 101111 111111 000111 000001 110010").replaceAll("\\s", ""));
        boolean[] rightSide = BinaryUtils.toBooleanArray(new String("1111 0000 1010 1010 1111 0000 1010 1010").replaceAll("\\s", ""));
        boolean[] expected = BinaryUtils.toBooleanArray(new String("0010 0011 0100 1010 1010 1001 1011 1011").replaceAll("\\s", ""));
        boolean[] result = DesAlgorithm.getFunctionValue(key, rightSide);
        assertThat(result, is(expected));
    }
}