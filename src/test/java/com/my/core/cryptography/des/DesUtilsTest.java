package com.my.core.cryptography.des;

import com.my.core.cryptography.generator.stream.util.BinaryUtils;
import org.junit.Assert;
import org.junit.Test;

import static com.my.core.cryptography.des.DesUtils.*;
import static com.my.core.cryptography.generator.stream.util.BinaryUtils.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class DesUtilsTest {
    @Test
    public void createBlocksTest() throws Exception {
        byte[] data = {0, 1, 2, 3, 4, 5 ,6 ,7};
        boolean[][] blocks = createBlocks(data);
        assertThat(blocks.length, is(1));
    }

    @Test
    public void createBlocksWhenIncompleteDataTest() throws Exception {
        byte[] data = {0, 1, 2, 3, 4, 5 ,6, 7, 1};
        boolean[][] blocks = createBlocks(data);
        assertThat(blocks.length, is(2));
    }

    @Test
    public void sBox1Test() throws Exception {
        assertThat(sBox1[0][0], is(b14));
        assertThat(sBox1[1][1], is(b15));
        assertThat(sBox1[2][15], is(b0));
        assertThat(sBox1[3][15], is(b13));
    }

    @Test
    public void sBoxesTest() throws Exception {
        assertThat(sBoxes[0], is(sBox1));
        assertThat(sBoxes[7], is(sBox8));
    }

    @Test
    public void getSBoxBinValue() throws Exception {
        assertThat(DesUtils.getSBoxBinValue(0, 0, 0), is(b14));
        assertThat(DesUtils.getSBoxBinValue(0, 3, 15), is(b13));
    }

}