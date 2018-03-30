package com.my.core.cryptography.generator.stream.util;

import edu.emory.mathcs.backport.java.util.Arrays;
import org.junit.Test;

import static com.my.core.cryptography.generator.stream.util.BinaryUtils.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class BinaryUtilsTest {
    @Test
    public void getMask() throws Exception {
        boolean[] mask = BinaryUtils.getBooleanArray("10100");
        assertThat(mask[0], is(true));
        assertThat(mask[1], is(false));
        assertThat(mask[2], is(true));
        assertThat(mask[3], is(false));
        assertThat(mask[4], is(false));
    }

    @Test
    public void shiftRightNoCarryTest() throws Exception {
        boolean[] mask = BinaryUtils.getBooleanArray("10100");
        mask = BinaryUtils.shiftRightNoCarry(mask); // should be 01010
        assertThat(mask[0], is(false));
        assertThat(mask[1], is(true));
        assertThat(mask[2], is(false));
        assertThat(mask[3], is(true));
        assertThat(mask[4], is(false));
    }

    @Test
    public void xorTest() throws Exception {
        assertThat(Boolean.logicalXor(true, true), is(false));
        assertThat(Boolean.logicalXor(false, false), is(false));
        assertThat(Boolean.logicalXor(true, false), is(true));
        assertThat(Boolean.logicalXor(false, true), is(true));
    }

    @Test
    public void toBytesWhenNewBooleanTable() throws Exception {
        boolean[] boolArr = new boolean[8];
        byte[] byteArray = BinaryUtils.toByteArrayLSBLeft(boolArr);
        assertThat(byteArray.length, is(1));
        assertThat(byteArray[0], is((byte) 0));
    }

    @Test
    public void shiftLeftWithCarryTest() throws Exception {
        boolean[] bits = new boolean[] {true, false, false, true, false};
        boolean[] shifted = BinaryUtils.shiftLeftWithCarry(bits);
        assertTrue(Arrays.equals(shifted, new boolean[] {false, false, true, false, true}));
    }

    @Test
    public void shiftLeftWithCarryMultipleTest() throws Exception {
        boolean[] bits = new boolean[] {true, false, false, true, false};
        boolean[] shifted = BinaryUtils.shiftLeftWithCarry(bits, 2);
        assertTrue(Arrays.equals(shifted, new boolean[] {false, true, false, true, false}));
    }

    @Test
    public void toBooleanArrayTest() throws Exception {
        boolean[] key = BinaryUtils.
                toBooleanArray(new String("00010011 00110100 01010111 01111001 10011011 10111100 11011111 11110001").replaceAll("\\s",""));
        assertThat(key.length, is(64));
        assertThat(key[0], is(false));
        assertThat(key[1], is(false));
        assertThat(key[2], is(false));
        assertThat(key[3], is(true));
        assertThat(key[4], is(false));
        assertThat(key[5], is(false));
        assertThat(key[6], is(true));
        assertThat(key[7], is(true));
        assertThat(key[8], is(false));

        assertThat(key[61], is(false));
        assertThat(key[62], is(false));
        assertThat(key[63], is(true));
    }

    @Test
    public void booleansToIntTest() throws Exception {
        boolean[] bits = {true, false, false, true};
        int dec = BinaryUtils.toInt(bits);
        assertThat(dec, is(9));
    }

    @Test
    public void binaryAdditionTest() throws Exception {
        boolean[] op1 = new boolean[] {true, false};
        boolean[] op2 = new boolean[] {true, true};
        boolean[] result = xor(op1, op2);
        assertThat(result, is(new boolean[]{false, true}));
    }
}